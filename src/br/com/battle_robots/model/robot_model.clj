(ns br.com.battle-robots.model.robot-model)


(defrecord Robot  [name attack defense agility])

(defn create-robot
  [robot-data]
  (->Robot
    (get robot-data :name)
    (get robot-data :attack)
    (get robot-data :defense)
    (get robot-data :agility)))