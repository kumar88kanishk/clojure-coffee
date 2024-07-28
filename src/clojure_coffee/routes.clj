(ns clojure-coffee.routes
  (:require [compojure.core :as compojure]
            [compojure.route :as compojure-route]
            [clojure.data.json :as json]
            [ring.util.response :as r]
            [ring.middleware.params :as rmp]
            [ring.middleware.reload :refer [wrap-reload]]
            [clojure-coffee.db :as db]
            [crypto.password.bcrypt :as encrypt-password]
            [monger.json]
            [cheshire.core :refer [generate-string]]))

(defn json-response [res]
  (-> (generate-string res)
      (r/header "Content-Type" "application/json")))

(defn server-error [body]
  {:status 500 :headers {} :body body})

(compojure/defroutes app-routes
  (compojure/GET "/index"
    {params :params}
    (do
      (db/insert-in-db)
      (json-response params)))
  (compojure/POST "/signup"
    [username password]
    (try
      (-> (db/create-user username
                          (encrypt-password/encrypt password))
          (generate-string)
          (r/response)
          (r/header "Content-Type" "application/json"))
      (catch Exception e
        (-> {:message (ex-message e)}
            (generate-string)
            (r/bad-request)
            (r/header "Content-Type" "application/json")))))

  (compojure/POST "/signin"
    [username password]
    (json-response (db/fetch-user username password)))

  (compojure-route/not-found "Page not found"))

(def reloadable-app
  (-> #'app-routes
      (wrap-reload)
      (rmp/wrap-params)))
