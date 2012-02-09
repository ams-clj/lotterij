(ns lotterij
  (:use [clojure.test]
        lotterij.publish
        lotterij.store))

(defn random-pick [v]
  (v (int (rand (count v)))))

(defn
  ^{:test (fn []
            (comment "Ensure correctness")
            (let [eligibles ["carlo" "hubert" "gijs" "pepijn" "remco" "joost"
                             "peter" "thomas" "vijay" "fizz" "buzz" "foobar"]
                  avg (fn [s] (/ (reduce + (map #(if (pos? %) % (* -1 %)) s)) (count s)))
                  wc (fn [subjects]
                       (let [words (flatten (map seq (map #(.split % " ") subjects)))]
                         (reduce #(assoc %1 %2 (inc (get %1 %2 0))) {} words)))]
              (is (= 3 (count (pick-winners eligibles 3))))

              (comment "Ensure fairness")
              (let [res (wc (reduce into [] (for [x (range 0 100000)] (pick-winners eligibles 3))))
                    hits (vals res)
                    hits-avg (avg hits)
                    hits-diff (map - hits (repeat hits-avg))
                    hits-diff-avg (avg hits-diff)]
                (is (= (count eligibles) (count res)))
                (is (> (* hits-avg 1/100) hits-diff-avg))
                (println (str hits-diff-avg " "
                              hits-avg)))))}
  pick-winners [names max]
  (loop [winners #{}]
    (if (> max  (count winners))
      (recur (conj winners (random-pick names)))
      winners)))

(defn val []
  (let [oldval (or (read "removeme") 0)]
    (write (inc oldval))))

(defn -main []
  (let [eligibles ["carlo" "hubert" "gijs" "pepijn" "remco" "joost"
                   "peter" "thomas" "vijay" "fizz" "buzz" "foobar"]]
    (publish-results (pick-winners eligibles 3))))
