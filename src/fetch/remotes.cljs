(ns fetch.remotes
  (:require [fetch.core :as core]
            [cljs.reader :as reader]))

(defn remote-uri [context] (str context "/_fetch"))

(defn remote-callback [remote params callback]
  (core/xhr [:post (remote-uri "remotes")]
            {:remote remote
             :params (pr-str params)}
            (when callback
              (fn [data]
                (let [data (if (= data "") "nil" data)]
                  (callback (reader/read-string data)))))))
