(ns br.com.battle-robots.api.config.server-config
  (:require [br.com.battle-robots.api.config.routs-config :as routes]
            [clojure.tools.logging :as log]
            [ring.adapter.jetty :as jetty]
            [reitit.ring :as ring]))


(def app
  (-> (ring/ring-handler
        routes/routes
        (ring/create-default-handler))))


(defonce server (atom nil))

(defn start!
  ([] (start! 8080))
  ([port]
   (when-let [s @server]
     (.stop s))
   (reset! server
           (jetty/run-jetty #'app
                            {:port port
                             :join? false}))
   :started))

(defn stop! []
  (when-let [s @server]
    (.stop s)
    (reset! server nil))
  :stopped)


(start!)