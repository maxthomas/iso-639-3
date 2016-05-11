(defproject iso-639-3 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.typed "0.3.23"]
                 [cheshire "5.6.1"]
                 [http-kit "2.1.19"]]
  :main ^:skip-aot iso-639-3.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
