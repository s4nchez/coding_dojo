(ns roman.core-test
  (:require [clojure.test :refer :all]
            [roman.core :refer :all]))

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
