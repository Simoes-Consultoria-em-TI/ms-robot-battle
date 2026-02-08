(ns br.com.battle-robots.model.robot-model
  (:require [br.com.battle-robots.model.robot-keys :as keys]))


(defrecord Robot  [name attack defense agility])

(defn create-robot
  [robot-data]
  (->Robot
    (get robot-data keys/name)
    (get robot-data keys/attack)
    (get robot-data keys/defense)
    (get robot-data keys/agility)))