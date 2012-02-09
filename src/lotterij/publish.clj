(ns lotterij.publish)

(defn compose-announcement [winners]
  (with-out-str
    (println "The winners are:")
    (doseq [winner winners]
      (printf "\t- %s\n" winner))))

(defn tweet [m]
  (println "IMPLEMENT TWEET!"))

(defn publish-results [winners]  
  (let [message (compose-announcement winners)]
    (println message)
    (tweet message)))
