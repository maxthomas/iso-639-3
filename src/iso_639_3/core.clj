(ns iso-639-3.core
  (:gen-class)
  (:require [clojure.core.typed :refer [ann cf check-ns U Seqable
                                        Keyword HMap Val Any Map]]
            [org.httpkit.client :as http]
            [cheshire.core :as json]))

(ann ^:no-check clj-http.client/get [Any * -> Any])
(ann ^:no-check -main [Any *])
(ann json/encode [Any -> String])

(ann iso-url String)
(def iso-url "http://www-01.sil.org/iso639-3/iso-639-3.tab")

(ann get-data [Any -> String])
(defn get-data []
  (let [{:keys [status headers body error] :as resp} @(http/get iso-url)]
    (if error
      (println "Failed, exception: " error)
      body)))

(ann tab-json (Seqable '{:code String :name String}))
(defn tab-json [^String tabs]
  (->> tabs
       (clojure.string/split-lines)
       (drop 1)
       (map #(let [spl (clojure.string/split % #"\t")]
               {:code (first spl)
                :name (last spl)}))))

(defn -main
  "Create a file named 'iso-639-3.json' with the latest codes."
  [& args]
  (let [^String fn "iso-639-3.json"
        results (->> (get-data)
                     tab-json
                     json/encode)]
    (spit fn results)))
