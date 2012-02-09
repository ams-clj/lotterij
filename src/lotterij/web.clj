(ns lotterij.web
  (:use ring.adapter.jetty)
  (:require [lotterij :as l]
            [lotterij.store :as s]))

(defn output []
  (if-let [winners (s/get-winners)]
    (str winners)
    "No winners yet, come along later on"))

(defn app [req]
  {:status 200
   :headers [["Content-Type" "text/plain"]]
   :body (output)})

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
