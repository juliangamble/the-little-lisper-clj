(ns Ch04Ex05)

; What is the index of this atom in the list?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 4
; Exercise 5
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-4-number-games.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def vec1 '(1 2))
(def vec2 '(3 2 4))
(def vec3 '(2 1 3))
(def vec4 '(6 2 1))
(def l '())
(def zero 0)
(def one 1)
(def three 3)
(def obj '(x y))
; ------------------------------

(defn add1 [n]
  (cond
   ;(empty? n) '()
   true (+ n 1)))
(add1 1)

(defn index_ [a lat]
  (cond
;   (empty? a) 0
   (empty? lat) 0
   (= a (first lat)) 1
   true (add1 
       (index_ a (rest lat)))))

(index_ 'a '(b a))

(def a_ 'car)
(def lat1 '(cons cdr car null eq))
(def b 'motor)
(def lat2 '(first engine auto motor))

(println (index_ a_ lat1))
;3
(println (index_ a_ lat2))
;4 ;1
(println (index_ b lat2))
;4


