(ns smith-waterman)
(declare get_max_path path)


(defn match [a b]
      (if (= a b) 1 0))

(defn alikeness [x y]
      (if (or (empty? x) (empty? y))
        0
        (+
          (match (first x) (first y))
          (max
            (alikeness (rest x) (rest y))
            (alikeness x (rest y))
            (alikeness (rest x) y)
            ))))

(println (alikeness "helloWorld" "whateverWh"))

(defn get_max_path [x y]
      (max-key
        :score
        (path (rest x) (rest y))
        (path x (rest y))
        (path (rest x) y)))

(defn path [x y]
      (if (or (empty? x) (empty? y))
        {:score 0}
        (let [max_path (get_max_path x y)
             xi (first x)
             yi (first y)
             matchi (match xi yi)]
              {
               :score (+
                        (get max_path :score)
                        matchi)
               :sequence (conj
                      (:sequence max_path)
                      {:x xi :y yi :! matchi})
              })))

(def result (path "helloWorld" "whateverWh"))
(println (get result :score))
(doseq [o (get result :sequence)] (println o))
