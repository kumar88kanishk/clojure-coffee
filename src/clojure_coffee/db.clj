(ns clojure-coffee.db
  (:require [monger.core :as mg]
            [monger.collection :as mc]))


(defn insert-in-db []
  (let [conn (mg/connect)
        db   (mg/get-db conn "clojure-coffee")]
    ;; with a generated document id, returns the complete
    ;; inserted document
    (mc/insert-and-return db "documents" {:name "John" :age 30})))

(defn create-user [username password]
  (let [conn (mg/connect)
        db   (mg/get-db conn "clojure-coffee")]
    ;; with a generated document id, returns the complete
    ;; inserted document
    (if-not (mc/find-one db "users" {:username username})
      (mc/insert-and-return db "users" {:username username :password password})
      (throw (Exception. "Username taken, please use a different username")))))

(defn fetch-user [username password]
  (let [conn (mg/connect)
        db   (mg/get-db conn "clojure-coffee")]
      ;; with a generated document id, returns the complete
      ;; inserted document
    #_(encrypt-password/check password encrypted)
    #_(println (mc/find db "users" {:username username}))
    (first (mc/find-maps db "users" {:username username}))))