(ns dstk-clj.core
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json])
  (:use [ring.util.codec :only [url-encode]]))

(defn api_url [endpoint]
  ;(str "http://localhost:8080" endpoint))
  (str "http://www.datasciencetoolkit.org" endpoint))

(defn prep-payload [data_payload data_payload_type]
  (if (= data_payload_type "json")
    (json/write-str data_payload)
    data_payload))

(defn to->vector [input]
  (if (= clojure.lang.PersistentVector (type input))
    input
    [input]))

(defn parse-response [response]
   (json/read-str
     (response :body)))

(defn call-dstk-api [endpoint arguments & [data_payload data_payload_type]]
  (if data_payload
    (client/post (api_url endpoint)
      {:body (prep-payload data_payload data_payload_type)})
    (client/get (api_url endpoint) {:query-params arguments})))

(defn call-endpoint [& args]
  (parse-response (apply call-dstk-api args)))

;;;;;;;;;;;;;;;; Calls ;;;;;;;;;;;;;;;;;;;

; street2coordinates
(defn street2coordinates [addresses]
  (call-endpoint "/street2coordinates" {} (to->vector addresses) "json"))

; geocode
(defn geocode [input]
  (call-endpoint "/maps/api/geocode/json" {"address" input}))

;coordinates2politics
(defn coordinates2politics [coordinates]
  (call-endpoint "/coordinates2politics" {} (to->vector coordinates) "json"))

; text2sentiment
(defn text2sentiment [text]
  (call-endpoint "/text2sentiment" {} text "string"))

; coordinates2statistics
(defn coordinates2statistics [coordinates]
  (call-endpoint "/coordinates2statistics" {} (to->vector coordinates) "json"))

; text2places (geodict)
(defn text2places [input]
  (call-endpoint "/text2places" {} input "json"))

; ip2coordinates
(defn ip2coordinates [ips]
  (call-endpoint "/ip2coordinates" {} (to->vector ips) "json"))

; text2sentences
(defn text2sentences [text] ; passing!
  (call-endpoint "/text2sentences" {} text "json"))

; html2text
(defn html2text [html]
  (call-endpoint "/html2text" {} html "string"))

; html2story
; fixme server not giving good response
;(defn html2story [html]
  ;(call-endpoint "/html2story" {} html "string"))

; text2people
; fixme - needs better test
;(defn text2people [text]
  ;(call-endpoint "/text2people" {} text "string"))

; text2times
(defn text2times [text]
  (call-endpoint "/text2times" {} text "string"))

; file2text
; fixme - not implemented yet
(defn file2text [file]
  (call-endpoint "/text2sentences" {} {"inputfile" file} "file"))

; twofishes
; fixme server is giving 503 - service unavailable
;(defn twofishes [text]
  ;(call-endpoint "/twofishes" {"query" text}))
