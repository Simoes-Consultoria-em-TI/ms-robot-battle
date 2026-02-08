(ns br.com.battle-robots.usecase.battle-usecase
  (:require [clojure.tools.logging :as log]
            [br.com.battle-robots.domain.battle-domain :as battle-domain]))


(defn battle [robot-a robot-b]
  (let [first (battle-domain/create-battle-data robot-a)
        second (battle-domain/create-battle-data robot-b)]
    (log/info "Converted robots into data-battle")
    (loop [first first
           second second]
      (log/info "Check winner")
      (cond
        (not (pos? (:hp first))) second
        (not (pos? (:hp second))) first
        :else
        (let [[new-first new-second] (battle-domain/battle-turn first second)]
          (recur new-first new-second)
          )
        )
      )
    )
  )