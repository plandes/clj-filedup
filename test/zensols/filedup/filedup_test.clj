(ns zensols.filedup.filedup-test
  (:require [clojure.java.io :as io])
  (:require [clojure.test :refer :all]
            [zensols.filedup.find-dups :refer :all]))

(deftest test-find-dups
  (is (= (set ["file1" "file3"])
         (->> (find-dups (io/file "test-resources/dupdir"))
              (map (fn [files]
                     (map #(.getName %) files)))
              (apply set)))))
