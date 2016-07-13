(ns channels.server
  (:require [com.stuartsierra.component :as c]
            [yada.yada :as yada]
            [bidi.vhosts :as vhosts]))


(defn start-a-server [port routes]
  (let [vhosts-model (vhosts/vhosts-model
                       [{:scheme :http
                         :host (format "localhost:%d" port)}
                        routes])
        listener (yada/listener vhosts-model {:port port})]
    listener))

(defrecord Server [port routes stop-fn]
  c/Lifecycle
  (start [component]
    (println "Starting server...")
    (assoc component :stop-fn (:close (start-a-server port routes))))
  (stop [component]
    (when-let [stop! stop-fn]
      (println "Stopping server...")
      (stop!))
    (assoc component :stop-fn nil)))

(defn new-server [] (map->Server {}))
