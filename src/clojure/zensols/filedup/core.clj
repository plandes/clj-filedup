(ns zensols.filedup.core
  (:require [zensols.actioncli.parse :as cli]
            [zensols.actioncli.log4j2 :as lu])
  (:require [zensols.filedup.version :as ver])
  (:gen-class :main true))

(defn- version-info-action []
  (println (format "%s (%s)" ver/version ver/gitref)))

(defn- create-action-context []
  (cli/multi-action-context
   '((:dups zensols.filedup.find-dups find-dups-command)
     (:hashes zensols.filedup.find-dups find-dups-command))
   :version-option (cli/version-option version-info-action)
   :default-arguments ["dups"]))

(defn -main [& args]
  (lu/configure "filedup-log4j2.xml")
  (cli/set-program-name "filedup")
  (-> (create-action-context)
      (cli/process-arguments args)))
