(ns channels.index)


(def index {:humans "This is an index."
            :channels [{:humans "Minimum Viable Channel"
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
                                     :identification [{:type "none"}]}]}]})

(defn index-for [& channels]
  {:humans "This is an index."
   :channels channels})