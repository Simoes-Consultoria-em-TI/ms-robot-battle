(ns br.com.battle-robots.api.config.robot-handler
  (:require [br.com.battle-robots.usecase.battle-usecase :as battle]
            [clojure.tools.logging :as log]
            [ring.util.response :as response]))

(def robot-keys [:name :attack :defense :agility])

(defn ->robot
  [dto]
  "Convert dto into domain Robot"
  (select-keys dto robot-keys))

(defn ->winner
  [winner]
  (-> winner response/response)
  )

(defn robot-battle-handler
  [request]
  (println "=== ENTROU NO HANDLER ===")
  (println "REQUEST:" request)

  (let [body (get-in request [:body-params])]
    (println "BODY:" body)

    (let [attacker (:attacker body)
          defender (:defender body)]
      (println "ATTACKER RAW:" attacker)
      (println "DEFENDER RAW:" defender)

      (let [atk   (->robot attacker)
            defen (->robot defender)]
        (println "ATTACKER NORMALIZED:" atk)
        (println "DEFENDER NORMALIZED:" defen)

        (->winner (battle/battle atk defen))))))

