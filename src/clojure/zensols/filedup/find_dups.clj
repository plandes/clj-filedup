(ns zensols.filedup.find-dups
  (:require [clojure.java.io :as io]
            [clojure.tools.logging :as log]
            [clojure.string :as s])
  (:require [zensols.actioncli.log4j2 :as lu]
            [zensols.actioncli.parse :refer (with-exception)])
  (:require [digest :as d]))

(defn find-dups
  "Recursively iterate through all directories starting from **directory** and
  finds files that are the same by content.  Duplicates are returned as lists
  of duplicates file object lists."
  [directory]
  (log/infof "finding duplicate files in %s" directory)
  (->> (file-seq (io/as-file directory))
       (filter #(.isFile %))
       (map (fn [file]
              (log/infof "processing %s" file)
              {(d/md5 file) [file]}))
       (apply merge-with concat)
       vals
       (filter #(> (count %) 1))))

(defn print-dups
  "Print the results of [[find-dups]].  Start at **directory** and print
  absolute paths if **absolute?** is non-nil."
  [directory absolute?]
  (let [path-fn (if absolute? #(.getAbsolutePath %) #(.getPath %))]
    (->> (find-dups directory)
         (map (fn [files]
                (->> files
                     (map path-fn)
                     (s/join " ")
                     println)))
         doall)))

(defn print-hashes
  [directory absolute?]
  (log/infof "printing file hashes %s" directory)
  (let [path-fn (if absolute? #(.getAbsolutePath %) #(.getPath %))]
    (->> (file-seq (io/as-file directory))
         (filter #(.isFile %))
         (map (fn [file]
                (log/infof "processing %s" file)
                (println (str (path-fn file) ": " (d/md5 file)))))
         doall)))

(def ^:private ops
  [(lu/log-level-set-option)
   ["-a" "--absolute" "Print absolute path names if given."]
   ["-d" "--directory" "The directory to find duplicate files."
    :required "<directory>"
    :default (io/file ".")
    :parse-fn io/file
    :validate [#(.isDirectory %) "Not a directory"]]])

(def find-dups-command
  "CLI command to find duplicate files"
  {:description "find duplicate files"
   :options ops
   :app (fn [{:keys [directory absolute] :as opts} & args]
          (with-exception
            (print-dups directory absolute)))})

(def print-hash-command
  "CLI command to find duplicate files"
  {:description "find duplicate files"
   :options ops
   :app (fn [{:keys [directory absolute] :as opts} & args]
          (with-exception
            (print-hashes directory absolute)))})
