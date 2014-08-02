(ns Ch05Ex04)

; Counting the common ingredients (occurN)
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 5
; Exercise 4
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

(defn add1 [ n]
  (cond
   ;(empty? n) '()
   true (+ n 1)))

(println (add1 1))

(defn occurNa [ a1 lat]
  (cond
   (empty? lat) 0
   ;(empty? a1) 0
   true (cond
       (= (first lat) a1)
        (add1 (occurNa a1 (rest lat)))
       true (occurNa a1 (rest lat)))))

(println (occurNa 'c (list 'a 'b 'c)))
(println (occurNa 'bananas lat2))

(defn occurN [ alat lat]
  (cond 
   (empty? alat) 0
   (empty? lat) 0
   true (+ (occurNa (first alat) lat)
      (occurN (rest alat)  lat))))

(println (occurN (list 'bananas) (list 'bananas 'peaches)))
; 1
(println (occurN lat1 l4))
; 0
(println (occurN lat1 lat2))
; 1
(println (occurN lat1 lat3))
; 2
