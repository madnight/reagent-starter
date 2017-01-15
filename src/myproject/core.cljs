(ns myproject.core
  (:require [reagent.core :as reagent :refer [atom]]
            [goog.net.XhrIo :as xhr]
            [goog.events :as events]
            [goog.history.EventType :as EventType])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:import goog.History))


(defn receiver [event]
  (let [response (.-target event)]
    (.log js/console (JSON.parse (.getResponseText response)))))

(defn fetch [url]
  (xhr/send url receiver "GET"))

(defn fetchAPI []
  (fetch "https://api.github.com/users/octocat"))

(defn child-component [name]
  [:p "TEST 123 Hi, I am " (fetchAPI)])

(defn parent-component []
  [child-component "Brent"])

(defn ^:export main [container-id]
  (reagent/render-component [parent-component]
                            (.getElementById js/document container-id)))
