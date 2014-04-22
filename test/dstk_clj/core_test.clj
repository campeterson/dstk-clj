(ns dstk-clj.core-test
  (:use expectations
        dstk-clj.core))

; text2places
(expect [{"type" "CITY", "latitude" "30.05", "longitude" "31.25", "start_index" "1", "end_index" "5", "name" "Cairo", "code" "", "matched_string" "Cairo, Egypt"}]
        (text2places "Cairo, Egypt"))

; ip2coordinates
(expect {"71.198.248.36" {"postal_code" "", "locality" "Berkeley", "latitude" 37.878101348877, "longitude" -122.271003723145, "dma_code" 807, "country_name" "United States", "region" "CA", "country_code" "US", "country_code3" "USA", "area_code" 510}}
        (ip2coordinates "71.198.248.36") )

; street2coordinates
(expect {"2543 Graystone Pl, Simi Valley, CA 93065" {"locality" "Simi Valley", "fips_county" "06111", "street_number" "2543", "street_name" "Graystone Pl", "confidence" 1.0, "latitude" 34.280874, "longitude" -118.766282, "country_name" "United States", "region" "CA", "country_code" "US", "street_address" "2543 Graystone Pl", "country_code3" "USA"}}
        (street2coordinates "2543 Graystone Pl, Simi Valley, CA 93065"))

; geocode
(expect {"results" [{"geometry" {"viewport" {"southwest" {"lng" -118.767282, "lat" 34.279874}, "northeast" {"lng" -118.765282, "lat" 34.281874}}, "location_type" "ROOFTOP", "location" {"lng" -118.766282, "lat" 34.280874}}, "formatted_address" "2543 Graystone Pl, Simi Valley, CA 93065", "address_components" [{"short_name" "2543", "long_name" "2543", "types" ["street_number"]} {"short_name" "Graystone Pl", "long_name" "Graystone Pl", "types" ["route"]} {"short_name" "Simi Valley", "long_name" "Simi Valley", "types" ["locality" "political"]} {"short_name" "CA", "long_name" "CA", "types" ["administrative_area_level_1" "political"]} {"short_name" "US", "long_name" "United States", "types" ["country" "political"]}], "types" ["street_address"]}], "status" "OK"}
        (geocode "2543 Graystone Pl, Simi Valley, CA 93065"))

; coordinates2politics
; Call is broken
;(expect [{"politics" [{"name" "United States", "code" "usa", "friendly_type" "country", "type" "admin2"} {"name" "Ventura", "code" "06_111", "friendly_type" "county", "type" "admin6"} {"name" "Simi Valley", "code" "06_72016", "friendly_type" "city", "type" "admin5"} {"name" "California", "code" "us06", "friendly_type" "state", "type" "admin4"} {"name" "Twenty fourth district, CA", "code" "06_24", "friendly_type" "constituency", "type" "constituency"}], "location" {"latitude" 34.281016, "longitude" -118.766282}}]
;        (coordinates2politics ["37.769456","-122.429128"]))

;file2text
;(expect {}
;        (file2text ""))

; text2sentences
(expect {"sentences" "\"kfFJF -d a  a. This should look like a real sentence. So should this, hopefully, if things are working correctly. Blahalala. Not. A  Rea;l <<<< sentence\" \n"}
        (text2sentences "kfFJF -d a  a. This should look like a real sentence. So should this, hopefully, if things are working correctly. Blahalala. Not. A  Rea;l <<<< sentence"))

;; OK
; html2text
(expect {"text" "Some text that should show up\n"}
        (html2text "<html><head><title>Foo</title><script type='text/javascript'>shouldBeIgnored();</script></head><body><p>Some text that should show up</p></body></html>"))

; html2story
; Server not giving good response
; TODO - Needs better test
;(expect {}
        ;(html2story "<html><head><title>Foo</title><script type='text/javascript'>shouldBeIgnored();</script></head><body><p>Some text that shouldn't showup</p></body></html>"))

; text2people
; TODO - Needs better test
;(expect {}
        ;(text2people "Samuel L Jackson"))

; text2times
(expect [{"time_seconds" 1.4259996E9, "time_string" "Tue Mar 10 15:00:00 +0000 2015", "duration" 1, "start_index" 0, "end_index" 16, "matched_string" "March 10th at 3pm", "is_relative" true}]
        (text2times "March 10th at 3pm \n Something that is not a time\n"))

; text2sentimentent
(expect {"score" 3.0}
        (text2sentiment "I love this hotel!"))

; coordinates2statistics
(expect
 [{"statistics" {"population_density" {"value" 0, "description" "The number of inhabitants per square kilometer around this point.", "source_name" "NASA Socioeconomic Data and Applications Center (SEDAC) – Hosted by CIESIN at Columbia University"}, "precipitation" {"units" "millimeters", "value" [0 0 0 0 0 0 0 0 0 0 0 0], "description" "The monthly average total precipitation at this point.", "source_name" "WorldClim"}, "land_cover" {"index" 20, "value" "Water Bodies", "description" "What type of environment exists around this point - urban, water, vegetation, mountains, etc.", "source_name" "European Commission Land Resource Management Unit Global Land Cover 2000"}, "elevation" {"units" "meters", "value" 0, "description" "The height of the surface above sea level at this point.", "source_name" "NASA and the CGIAR Consortium for Spatial Information"}, "mean_temperature" {"units" "degrees Celsius", "value" [0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0], "description" "The mean monthly temperature at this point.", "source_name" "WorldClim"}}, "location" {"latitude" 0.0, "longitude" -118.766282}}]
  (coordinates2statistics [34.281016, -118.766282]))

; twofishes
;(expect {"interpretations" [{"what" "pizza", "where" "القاهرة", "feature" {"cc" "EG", "woeType" 7, "names" [{"name" "Cairo", "lang" "en", "flags" [16 1]} {"name" "Cairo", "lang" "en", "flags" [16]}], "name" "Cairo", "displayName" "Cairo, EG", "matchedName" "القاهرة, EG", "geometry" {"center" {"lat" 30.06263, "lng" 31.24967}, "bounds" {"ne" {"lat" 30.1480960846, "lng" 31.3563537598}, "sw" {"lat" 29.9635601044, "lng" 31.1625480652}}}, "longId" "72057594038288566", "attributes" {"adm0cap" 1, "scalerank" 0, "labelrank" 3, "natscale" 600, "population" 7734614}, "ids" [{"source" "geonameid", "id" "360630"}], "id" "geonameid:360630", "highlightedName" "<b>القاهرة</b>, EG"}, "scores" {}}]}
        ;(twofishes "pizza القاهرة"))
