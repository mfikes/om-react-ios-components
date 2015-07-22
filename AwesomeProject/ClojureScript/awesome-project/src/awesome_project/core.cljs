;; Need to set js/React first so that Om can load
(set! js/React (js/require "react-native/Libraries/react-native/react-native"))

(ns awesome-project.core
  (:require [om.core :as om]))

;; Reset js/React back as the form above loads in an different React
(set! js/React (js/require "react-native/Libraries/react-native/react-native"))


;; Setup some methods to help create React Native elements

(defn view [opts & children]
  (apply js/React.createElement js/React.View (clj->js opts) children))

(defn text [opts & children]
  (apply js/React.createElement js/React.Text (clj->js opts) children))


;; Set up our Om UI

(defonce app-state (atom {:text "Hello from ClojureScript!"}))

(defn widget [data owner]
  (reify
    om/IRender
    (render [this]
      (view {:style {:flexDirection "row" :margin 40 :backgroundColor "cyan"}}
        (text nil (:text data))))))

(om/root widget app-state {:target 1})

(defn ^:export init []  
  ((fn render []
     (.requestAnimationFrame js/window render))))
