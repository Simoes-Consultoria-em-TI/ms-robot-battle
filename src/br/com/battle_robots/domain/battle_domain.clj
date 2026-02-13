(ns br.com.battle-robots.domain.battle-domain
  (:require [clojure.tools.logging :as log]))

(defrecord BattleData [name damage defense hp])

(defn create-battle-data
  [robot-data]
  (->BattleData
    (:name robot-data)
    (+ (:attack robot-data) (:agility robot-data))
    (+ (:defense robot-data) (:agility robot-data))
    (* (:defense robot-data) (:agility robot-data))))


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
    (do
      (log/info (:name attacker) "hits" (:name new-defender) "new hp =" (:hp new-defender))
      (if (pos? (:hp new-defender))

        (let [new-attacker (hit defender attacker)]
          (log/info (:name new-defender) "hits" (:name attacker) "new hp =" (:hp attacker))
          [new-attacker new-defender]
          )
        [attacker new-defender]
        )
      )
    )

  )
