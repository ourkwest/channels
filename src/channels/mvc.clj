(ns channels.mvc
  (:require [yada.yada :as yada]
            [hiccup.core :as hiccup]
            [clojure.java.io :as io]))


(def messages (atom []))

(def description {:humans "Minimum Viable Channel"
                  :path "/mvc"
                  :endpoints [{:humans "Write here"
                               :type "sink"
                               :path "/write"
                               :authentication [{:type "none"}]
                               :identification [{:type "none"}]}
                              {:humans "Read here"
                               :type "source"
                               :path "/read"
                               :authentication [{:type "none"}]
                               :identification [{:type "none"}]}]})

(def routes
  ["/mvc"
   [["/compose"
     (yada/as-resource (io/file "resources/test.html"))
     ;(yada/handler (hiccup/html [:html
     ;                            [:head]
     ;                            [:body
     ;                             [:h2 "Writer:"]
     ;                             [:div
     ;                              [:form
     ;                               [:input {:type "text"
     ;                                        :name "id"}]
     ;                               [:input {:type "text"
     ;                                        :name "parents"}]
     ;                               [:input {:type "text"
     ;                                        :name "children"}]
     ;                               [:input {:type "text"
     ;                                        :name "timestamp"}]
     ;                               [:input {:type "text"
     ;                                        :name "media-type"}]
     ;                               [:input {:type "text"
     ;                                        :name "content"}]
     ;                               [:input {:type "text"
     ;                                        :name "dimensions"}]
     ;                               ]]]]))
     ]
    ["/write"
     (yada/resource
       {:methods
        {:post
         {:parameters
          {:form {:id         String
                  :parents    String
                  :children   String
                  :timestamp  String
                  :media-type String
                  :content    String
                  :dimensions String}}
          :consumes
          [{:media-type #{"multipart/form-data"
                          "application/x-www-form-urlencoded"
                          "application/edn"}}]
          :response
          (fn [ctx]
            (println ctx)
            (let [message (or (get-in ctx [:parameters :form])
                              (:body ctx))]
              (println message)
              (swap! messages conj message))

            "okay.")}}})]
    ["/read"
     (yada/resource
       {:methods
        {:get
         {:produces {:media-type #{"application/edn;q=0.9"
                                   "application/json"}}
          :response (fn [ctx]

                      (sort-by :timsetamp @messages)

                      )}}})]]])
