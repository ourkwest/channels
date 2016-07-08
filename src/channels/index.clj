(ns channels.index)




(def index {:humans "This is an index."
            :channels [{:humans "Minimum Viable Channel"
                        :url-path-segments "/mvc"
                        :endpoints [{:humans "Write here"
                                     :type "sink"
                                     :url-path-segments "/write"
                                     :authentication [{:type "none"}]
                                     :identification [{:type "none"}]}
                                    {:humans "Read here"
                                     :type "source"
                                     :url-path-segments "/read"
                                     :authentication [{:type "none"}]
                                     :identification [{:type "none"}]}]}]})
