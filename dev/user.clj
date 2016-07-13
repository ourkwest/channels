(ns user
  (:require
    ;[clojure.java.io :as io]
    ;[clojure.string :as str]
    ;[clojure.pprint :refer [pprint]]
    [clojure.repl :refer :all]
    [clojure.test :as test]
    [clojure.tools.namespace.repl :refer [refresh refresh-all]]
    [channels.core :as core]
    [com.stuartsierra.component :as c]))


(println "Hello User.")

(def system nil)

(defn init
  "Constructs the current development system."
  []
  (alter-var-root #'system
                  (constantly (core/new-system))))

(defn start
  "Starts the current development system."
  []
  (alter-var-root #'system c/start))

(defn stop
  "Shuts down and destroys the current development system."
  []
  (alter-var-root #'system
                  (fn [s] (when s (c/stop s)))))

(defn go
  "Initializes the current development system and starts it running."
  []
  (init)
  (start))

(defn reset []
  (stop)
  (refresh :after 'user/go))
