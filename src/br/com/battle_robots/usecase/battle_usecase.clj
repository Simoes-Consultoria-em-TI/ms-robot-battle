(ns br.com.battle-robots.usecase.battle-usecase
  (:require [br.com.battle-robots.model.robot-keys :as keys]
            [br.com.battle-robots.model.robot-model :as robot-data]))

(defrecord BattleData [damage defense hp])

(defn create-battle-data
  [robot-data]
  (->BattleData
    (+ (keys/attack robot-data) (keys/agility robot-data))
    (+ (keys/defense robot-data) (keys/agility robot-data))
    (* (keys/defense robot-data) (keys/agility robot-data))))


(defn calculate-damage
  [attack defender]
  (-> (:damage attack)
      (- (:defense defender))
      (max 1)
      )
  )


(defn remove-hp
  [damage defender]
  (update defender :hp - damage))


(defn hit [attacker defender]
  (-> (calculate-damage attacker defender)
      (remove-hp defender)
      )
  )

(defn battle-turn
  [attacker defender]
  (let [new-defender (hit attacker defender)]
    (if (pos? (:hp new-defender))
      (let [new-attacker (hit defender attacker)]
        [new-attacker new-defender]
        )
      [attacker new-defender]
      )
    )
  )

(defn battle [robot-a robot-b]
  (let [first (create-battle-data robot-a)
        second (create-battle-data robot-b)]
    (loop [first first
           second second]
      (println "hp first:" (:hp first) "hp second:" (:hp second))
      (cond
        (not (pos? (:hp first))) second
        (not (pos? (:hp second))) first
        :else
        (let [[new-first new-second] (battle-turn first second)]
          (recur new-first new-second)
          )
        )
      )
    )
  )