(ns slackbot-vici.core
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
  "I don't do a whole lot ... yet."
  [& args]
  (let [port (Integer/parseInt "8080")]
    (server/run-server #'app-routes {:port port})
    (println (str "Running webserber at http://127.0.0.1:" port "/"))))
