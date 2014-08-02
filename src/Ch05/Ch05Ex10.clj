(ns Ch05Ex10)

; Have we broken a law? Did we obey the spirit of the law?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 5
; Exercise 10
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-5-multichapter.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def x 'comma)
(def y 'dot)
(def a 'kiwis)
(def b 'plums)
(def lat1 '(bananas kiwis))
(def lat2 '(peaches apples bananas))
(def lat3 '(kiwis pears plums bananas cherries))
(def lat4 '(kiwis mangoes kiwis guavas kiwis))
(def l1 '((curry) () (chicken) ()))
(def l2 '((peaches) (and cream)))
(def l4 '())
; ------------------------------

;Some don't all obey the forth commandment - to ask two questions - but they're numeric
;so they're in the spirit - or the test is combined
;They do all appear to obey the sixth commandment - testing for termination on the value changed during recursion
