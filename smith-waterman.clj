(ns smith-waterman)

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



(defn get_match [a] (get a :match))
(defn path [x y]
      (if (or (empty? x) (empty? y))
        {:match 0}
        (let [max_path (max-key
                         (partial get_match)
                         (path (rest x) (rest y))
                         (path x (rest y))
                         (path (rest x) y))]
             (let [xi (first x)
                   yi (first y)
                   matchi (match xi yi)]
                  {
                   :match (+
                            (get max_path :match)
                            matchi)
                   :sequence (cons
                          {:x xi :y yi :! matchi}
                          (get max_path :sequence))
                   })
             )))

(def result (path "helloWorld" "whateverWh"))
(println (get result :match))
(doseq [o (get result :sequence)] (println o))

