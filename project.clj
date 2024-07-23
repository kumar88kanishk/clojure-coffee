(defproject clojure-coffee "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring/ring-core "1.12.2"]
                 [ring/ring-jetty-adapter "1.12.2"]
                 [clj-time "0.15.2"]
                 [compojure "1.7.1"]
                 [org.clojure/data.json "2.5.0"]
                 [com.novemberain/monger "3.5.0"]
                 [ring/ring-devel "1.12.2"]
                 [crypto-password "0.3.0"]]
  :main clojure-coffee.server
  :repl-options {:init-ns clojure-coffee.core})
