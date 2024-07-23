(ns clojure-coffee.routes
  (:require [compojure.core :as compojure]
            [compojure.route :as compojure-route]
            [clojure.data.json :as json]
            [ring.util.response :as r]
            [ring.middleware.params :as rmp]
            [ring.middleware.reload :refer [wrap-reload]]
            [clojure-coffee.db :as db]
            [crypto.password.bcrypt :as encrypt-password]))

(defn json-response [res]
  (-> (r/response (json/write-str res))
      (r/header "Content-Type" "application/json")))

(compojure/defroutes app-routes
  (compojure/GET "/index"
    {params :params}
    (do
      (db/insert-in-db)
      (json-response params)))
  (compojure/POST "/create-user"
    [username password]
    (db/create-user username (encrypt-password/encrypt password)))
  (compojure-route/not-found "Page not found"))

(def reloadable-app
  (-> #'app-routes
      (rmp/wrap-params)
      (wrap-reload)))
