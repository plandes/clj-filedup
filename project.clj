(defproject com.zensols.tools/filedup "0.1.0-SNAPSHOT"
  :description "Find file dups"
  :url "https://github.com/plandes/clj-file-dups"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"
            :distribution :repo}
  :plugins [[lein-codox "0.10.3"]
            [lein-javadoc "0.3.0"]
            [org.clojars.cvillecsteele/lein-git-version "1.2.7"]]
  :codox {:metadata {:doc/format :markdown}
          :project {:name "File dup finder"}
          :output-path "target/doc/codox"
          :source-uri "https://github.com/plandes/clj-file-dups/blob/v{version}/{filepath}#L{line}"}
  :git-version {:root-ns "zensols.filedup"
                :path "src/clojure/zensols/filedup"
                :version-cmd "git describe --match v*.* --abbrev=4 --dirty=-dirty"}
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :javac-options ["-Xlint:unchecked"]
  :jar-exclusions [#".gitignore"]
  :exclusions [org.slf4j/slf4j-log4j12
               ch.qos.logback/logback-classic]
  :dependencies [[org.clojure/clojure "1.8.0"]

                 ;; logging
                 [org.apache.logging.log4j/log4j-core "2.7"]
                 [org.apache.logging.log4j/log4j-slf4j-impl "2.7"]

                 ;; command line
                 [com.zensols.tools/actioncli "0.0.27"]

                 ;; md5sum
                 [digest "1.4.5"]]
  :pom-plugins [[org.codehaus.mojo/appassembler-maven-plugin "1.6"
                 {:configuration ([:programs
                                   [:program
                                    ([:mainClass "zensols.filedup.core"]
                                     [:id "filedup"])]]
                                  [:environmentSetupFileName "setupenv"])}]]
  :profiles {:1.9 {:dependencies [[org.clojure/clojure "1.9.0"]]}
             :uberjar {:aot [zensols.filedup.core]}
             :appassem {:aot :all}
             :snapshot {:git-version {:version-cmd "echo -snapshot"}}
             :test
             {:jvm-opts ["-Dlog4j.configurationFile=test-resources/test-log4j2.xml" "-Xmx12g"]}}
  :main zensols.filedup.core)
