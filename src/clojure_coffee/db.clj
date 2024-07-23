(ns clojure-coffee.db
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import [org.bson.types ObjectId]
           [com.mongodb DB WriteConcern]))


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
    (mc/insert-and-return db "users" {:username username :password password})))