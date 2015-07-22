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

(def Swiper (js/require "react-native-swiper/dist/index"))
(defn swiper [opts & children]
  (apply js/React.createElement Swiper (clj->js opts) children))

;; Set up our Om UI

(defonce app-state (atom {:text "Hello from ClojureScript!"}))

(def wrapper-style {})

(def slide1-style {:flex            1
                   :justifyContent  "center"
                   :alignItems      "center"
                   :backgroundColor "#9DD6EB"})

(def slide2-style {:flex            1
                   :justifyContent  "center"
                   :alignItems      "center"
                   :backgroundColor "#97CAE5"})

(def slide3-style {:flex            1
                   :justifyContent  "center"
                   :alignItems      "center"
                   :backgroundColor "#92BBD9"})


(def text-style {:color      "#fff"
                 :fontSize   30
                 :fontWeight "bold"})

(defn widget [data owner]
  (reify
    om/IRender
    (render [this]
      (swiper {:style wrapper-style}
        (view {:style slide1-style}
          (text {:style text-style}
            "Hello Swiper"))
        (view {:style slide2-style}
          (text {:style text-style}
            "Beautiful"))
        (view {:style slide3-style}
          (text {:style text-style}
            "And simple"))))))

(om/root widget app-state {:target 1})

(defn ^:export init []
  ((fn render []
     (.requestAnimationFrame js/window render))))
