(ns clojure-coffee.server
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [clojure.pprint]
            [clojure-coffee.routes :as routes]))

(defn -main
  [& args]
  (jetty/run-jetty routes/reloadable-app
                   {:port 3000
                    :join? true}))