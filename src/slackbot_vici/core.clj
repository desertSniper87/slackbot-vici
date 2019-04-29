(ns slackbot-vici.core
  (:require [slackbot_vici.message.model :as messages])
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Welcome to concitus"})

(defroutes app-routes
  (GET "/" [] app)
  (route/not-found "404 NOT Found!"))

(defn -main
  [& args]
  (clojure.java.jdbc/execute! messages/db [messages/create-table-sql])
  (let [port (Integer/parseInt "8080")]
    (server/run-server #'app-routes {:port port})
    (println (str "Running webserber at http://127.0.0.1:" port "/"))))
