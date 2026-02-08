(ns br.com.battle-robots.main
  (:gen-class)
  (:require [br.com.battle-robots.http.server :as server]
            [clojure.tools.logging :as log]))

(defn -main [& _]
  (log/info "Starting battle-robots API")
  (server/start!))
