(ns Ch06Ex02)

; Is there chili in the hot potatoes? (nested intersection)
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 6
; Exercise 2
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-6-oh-my-gawd-its.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def l1 '((fried potatoes)(baked (fried)) tomatoes))
(def l2 '(((chili) chili (chili))))
(def l3 '())
(def lat1 '(chili and hot)) 
(def lat2 '(baked fried)) 
(def a 'fried)
; ------------------------------

(defn add1 [n]
  (cond
;   (empty? n) '()
   true (+ n 1)))

(println (add1 1))

(defn occurNa* [a1 lat]
  (cond
   (empty? lat) 0
   ;s(empty? a1) 0
   true (cond
       (list? (first lat))
        (+ (occurNa* a1 (first lat))
           (occurNa* a1 (rest lat)))
       (= (first lat) a1)
        (add1 (occurNa* a1 (rest lat)))
       true (occurNa* a1 (rest lat)))))

(println (occurNa* 'c (list 'a 'b 'c)))
(println (occurNa* 'bananas '(((bananas))(bananas))))

(defn occurN* [alat lat]
  (cond 
   (empty? alat) 0
   (empty? lat) 0
   (list? (first alat))
    (+ (occurN* (first alat) lat)
       (occurN* (rest alat) lat))
   true (+ (occurNa* (first alat) lat)
         (occurN* (rest alat)  lat))))

(println (occurN* '(baked fried) '((fried potatoes)(baked (fried)) tomatoes)))
; 3

(println (occurN* lat1 l2))
;3

(println (occurN* lat2 l1))
;3

(println (occurN* lat1 l3))
;0
