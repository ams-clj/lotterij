(ns lotterij.web
  (:use ring.adapter.jetty)
  (:require [lotterij :as l]))

(def sample-eligible
  ["carlo" "hubert" "coen" "gijs" "pepijn" "remco" "joost" "peter" "cees" "walter"])

(defn output []
  (str (l/pick-winners sample-eligible 3)
       "\n"
       (l/val)))

(defn app [req]
  {:status 200
   :headers [["Content-Type" "text/plain"]]
   :body (output)})

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
