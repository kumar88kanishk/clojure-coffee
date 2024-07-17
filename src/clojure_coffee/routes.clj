(ns clojure-coffee.routes
  (:require [compojure.core :as compojure]
            [compojure.route :as compojure-route]
            [clojure.data.json :as json]
            [ring.util.response :as r]))

(compojure/defroutes app-routes
  (compojure/GET "/index" [] (-> (r/response (json/write-str {:msg "hi"}))
                                 (r/header "Content-Type" "application/json")))
  (compojure-route/not-found "Page not found"))