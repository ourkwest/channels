(ns channels.core
  (:require [yada.yada :refer [listener] :as yada]
            [clojure.tools.logging :refer :all]
            [bidi.vhosts :refer [make-handler vhosts-model]]
            [bidi.bidi :refer [tag]]
            [yada.resources.webjar-resource :refer [new-webjar-resource]]
            [channels.index :as index]
            [channels.mvc :as mvc]
            [channels.server :as server]
            [com.stuartsierra.component :as c]
            [cheshire.core :as json]
            ))


(defmethod yada.body/render-map "text/plain"
  [m _]
  (-> (json/encode m {:pretty true})))

(defn make-routes []
  ["/channels" [["/index" (yada/resource
                            {:produces #{"text/plain"}
                             :methods
                             {:get
                              {:response (index/index-for mvc/description)}}})]
                mvc/routes]])

(def routes
  [""
   [
    (make-routes)
    ["/api" (-> (make-routes)
                ;; Wrap this route structure in a Swagger
                ;; wrapper. This introspects the data model and
                ;; provides a swagger.json file, used by Swagger UI
                ;; and other tools.
                (yada/swaggered
                  {:info {:title "Hello World!"
                          :version "1.0"
                          :description "An API on the classic example"}
                   :basePath "/api"})
                ;; Tag it so we can create an href to this API
                (tag :edge.resources/api))]

    ;; Swagger UI
    ["/swagger" (-> (new-webjar-resource "/swagger-ui" {:index-files ["index.html"]})
                    ;; Tag it so we can create an href to the Swagger UI
                    (tag :edge.resources/swagger))]

    [true (yada/handler nil)]]])

(defn new-system []
  (c/system-map
    :port 3000
    :routes routes
    :server (c/using (server/new-server)
                     [:port :routes])))

(defn -main [& args]
  (c/start (new-system)))
