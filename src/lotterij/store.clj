(ns lotterij.store
  (:require [clj-redis.client :as redis]))

(def config {:url (System/getenv "REDISTOGO_URL")})

(def db (redis/init config))

(defn read [k]
  (redis/get db k))

(defn write [k v]
  (redis/set db k v))
