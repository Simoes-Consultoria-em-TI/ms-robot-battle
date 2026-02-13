(ns br.com.battle-robots.api.config.routs-config
  (:require [reitit.ring :as ring]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [muuntaja.core :as m]
            [br.com.battle-robots.api.config.robot-handler :as battle-handler]))


(defn greet-handler [_]
  {:status 200
   :body   {:message "Hello, robot!"}})

(def routes
  (ring/router
    [
     ["/greet" {:get greet-handler}]
     ["/battle" {:post battle-handler/robot-battle-handler}]
     ]
    {:data {:muuntaja m/instance
            :middleware [muuntaja/format-middleware]}}
    )
  )
