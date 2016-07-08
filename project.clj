(defproject channels "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [aleph "0.4.1"]
                 [yada "1.1.26"]
                 ;
                 ;[yada "1.1.14"]
                 ;
                 ;[aleph "0.4.1"]
                 ;[bidi "2.0.0"]
                 ]
  :main channels.core
  :profiles {:dev {:source-paths ["dev"]}})
