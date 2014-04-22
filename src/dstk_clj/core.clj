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

(defn call-dstk-api [endpoint arguments & [data_payload data_payload_type]]
  (if data_payload
    (client/post (api_url endpoint)
      {:body (prep-payload data_payload data_payload_type)})
    (client/get (api_url endpoint) {:query-params arguments})))

;;;;;;;;;;;;;;;; Calls ;;;;;;;;;;;;;;;;;;;

; street2coordinates
(defn street2coordinates [addresses]
  (json/read-str
    (:body
      (call-dstk-api "/street2coordinates" {} (to->vector addresses) "json"))))

; geocode
(defn geocode [input]
  (json/read-str
    (:body
      (call-dstk-api "/maps/api/geocode/json" {"address" input}))))

;coordinates2politics
(defn coordinates2politics [coordinates]
  (json/read-str
    (:body
      (call-dstk-api "/coordinates2politics" {} (to->vector coordinates) "json"))))

; text2sentiment
(defn text2sentiment [text]
  (json/read-str
    (:body
      (call-dstk-api "/text2sentiment" {} text "string"))))

; coordinates2statistics
(defn coordinates2statistics [coordinates]
  (json/read-str
    (:body
      (call-dstk-api "/coordinates2statistics" {} (to->vector coordinates) "json"))))

; text2places (geodict)
(defn text2places [input]
  (json/read-str
    (:body
      (call-dstk-api "/text2places" {} input "json"))))

; ip2coordinates
(defn ip2coordinates [ips]
  (json/read-str
    (:body
      (call-dstk-api "/ip2coordinates" {} (to->vector ips) "json"))))

; text2sentences
(defn text2sentences [text] ; passing!
  (json/read-str
    (:body
      (call-dstk-api "/text2sentences" {} text "json"))))

; html2text
(defn html2text [html]
  (json/read-str
    (:body
      (call-dstk-api "/html2text" {} html "string"))))

; html2story
; fixme server not giving good response
;(defn html2story [html]
  ;(json/read-str
    ;(:body
      ;(call-dstk-api "/html2story" {} html "string"))))

; text2people
; fixme - needs better test
;(defn text2people [text]
  ;(json/read-str
    ;(:body
      ;(call-dstk-api "/text2people" {} text "string"))))

; text2times
(defn text2times [text]
  (json/read-str
    (:body
      (call-dstk-api "/text2times" {} text "string"))))

; file2text
; fixme - not implemented yet
(defn file2text [file]
  (json/read-str
    (:body
      (call-dstk-api "/text2sentences" {} {"inputfile" file} "file"))))

; twofishes
; fixme server is giving 503 - service unavailable
;(defn twofishes [text]
  ;(json/read-str
    ;(:body
      ;(call-dstk-api "/twofishes" {"query" text}))))
