(ns roman.core-test
  (:require [clojure.test :refer :all]
            [roman.core :refer :all]))

(defn roman
  ([decimal] (roman decimal '([1000 "M"] [900 "CM"] [500 "D"] [400 "CD"] [100 "C"] [90 "XC"] [50 "L"] [40 "XL"] [10 "X"] [9 "IX"] [5 "V"] [4 "IV"] [1 "I"]) ""))
  ([decimal mappings result]
    (let [[current-mapping & other-mappings] mappings
          [current-decimal current-roman] current-mapping
          rest (- decimal current-decimal)]
      (cond (< rest 0) (roman decimal other-mappings result)
            (= rest 0) (str result current-roman)
            :else (roman rest mappings (str result current-roman)))
      ))
  )

(deftest decimal-to-roman
  (testing "key numbers"
    (is (= "I" (roman 1)))
    (is (= "II" (roman 2)))
    (is (= "III" (roman 3)))
    (is (= "IV" (roman 4)))
    (is (= "V" (roman 5)))
    (is (= "VII" (roman 7)))
    (is (= "IX" (roman 9)))
    (is (= "XXIV" (roman 24)))
    (is (= "MMMMDLXXVI" (roman 4576)))
    (is (= "MMMMCMXCIX" (roman 4999)))
    ))
