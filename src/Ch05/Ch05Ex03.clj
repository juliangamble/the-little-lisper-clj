(ns Ch05Ex03)

; Building a nests for the banana kiwi list (multidown)
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 5
; Exercise 3
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

;note the usage of the list function to build lists - where quote is not sufficient
(quote 'bob)
(quote (quote 'bob))
(list 'bob)

(defn multidown [ lat]
  (cond
   (empty? lat) '()
   true
       (cons (list (first lat)) (multidown  (rest lat)))))

(println (multidown lat1))
;((BANANAS) (KIWIS))
(println (multidown lat2))
;((PEACHES) (APPLES) (BANANAS))
(println (multidown l4))
;()

