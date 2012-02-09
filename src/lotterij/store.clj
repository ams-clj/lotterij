(ns lotterij.store
  (:require [clj-redis.client :as redis]))

(def config {:url (System/getenv "REDISTOGO_URL")})

(def db (redis/init config))

(defn read [k]
  (redis/get db k))

(defn write [k v]
  (redis/set db k v))

(defn encode [s]
  (str s))

(defn decode [s]
  (read-string s))

(defn get-winners []
  (when-let [winners (read "lotterij-winners")]
    (decode winners)))

(defn set-winners [winners]
  (write "lotterij-winners" (encode winners)))
