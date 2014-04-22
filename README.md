# dstk-clj

A Clojure wrapper for the Data Science Toolkit API

http://www.dataciencetoolkit.org

https://github.com/campeterson/dstk-clj

## Usage

### Try it out at the REPL

    lein try dstk-clj 0.1.1

### Use it in your project

Add the following dependency to your project.clj file:

    ["dstk-clj" 0.1.1]

    (require '[dstk-clj.core :as dstk])

    (dstk/text2places "Egypt")

## Status

street2coordinates (OK)

geocode (OK)

coordinates2politics (OK)

text2sentiment (OK)

coordinates2statistics (OK)

geodict (text2places) (OK)

ip2coordinates (OK)

text2sentences (OK)

html2text (OK)

html2story (FAILING - SERVER ISSUE)

text2people (NOT YET IMPLEMENTED)

text2times (OK)

file2text (NOT YET IMPLEMENTED)

twofishes (FAILING - SERVER ISSUE)

## Tests
Run

    lein expectations

## License

Copyright © 2013 Cam Peterson
Distributed under the Eclipse Public License, the same as Clojure.
