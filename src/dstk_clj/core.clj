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


; text2places
(defn text2places [input]
  (json/read-str
    (:body
      (call-dstk-api "/text2places" {} input "json"))))

; ip2coordinates
(defn ip2coordinates [ips] ; PASSING!
  (json/read-str
    (:body
      (call-dstk-api "/ip2coordinates" {} (to->vector ips) "json"))))

; street2coordinates
(defn street2coordinates [addresses] ; PASSING!
  (json/read-str
    (:body
      (call-dstk-api "/street2coordinates" {} (to->vector addresses) "json"))))

; geocode
(defn geocode [input] ; PASSING!
  (json/read-str
    (:body
      (call-dstk-api "/maps/api/geocode/json" {"address" input}))))

;coordinages2politics
; FIXME - failing
(defn coordinates2politics [input]
  (json/read-str
    (:body
      (call-dstk-api "/coordinates2politics" {} (str
                                                    (first input)
                                                    ","
                                                    (last input)) "json"))))

; file2text
(defn file2text [file]
  (json/read-str
    (:body
      (call-dstk-api "/text2sentences" {} {"inputfile" file} "file"))))

; text2sentences
(defn text2sentences [text] ; PASSING!
  (json/read-str
    (:body
      (call-dstk-api "/text2sentences" {} text "json"))))

; html2text
(defn html2text [html]
  (json/read-str
    (:body
      (call-dstk-api "/html2text" {} html "string"))))

; html2story
(defn html2story [html]
  (json/read-str
    (:body
      (call-dstk-api "/html2story" {} html "string"))))

; text2people
(defn text2people [text]
  (json/read-str
    (:body
      (call-dstk-api "/text2people" {} text "string"))))

; text2times
(defn text2times [text]
  (json/read-str
    (:body
      (call-dstk-api "/text2times" {} text "string"))))

; text2sentiment
(defn text2sentiment [text]
  (json/read-str
    (:body
      (call-dstk-api "/text2sentiment" {} text "string"))))

; coordinates2statistics
; FIXME - failing
(defn coordinates2statistics [input]
  (json/read-str
    (:body
      (call-dstk-api "/coordinates2statistics" {} (str
                                                    (first input)
                                                    ","
                                                    (last input)) "json"))))

; twofishes
(defn twofishes [text]
  (json/read-str
    (:body
      (call-dstk-api "/twofishes" {"query" text}))))
