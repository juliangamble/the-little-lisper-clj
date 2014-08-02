(ns Ch04Ex06)

; What is the product of these two vectors?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 4
; Exercise 6
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

(defn product [ vec1_ vec2_]
  (cond
   (empty? vec1_) vec2_
   (empty? vec2_) vec1_
   true (cons (* (first vec1_)(first vec2_))
            (product 
             (rest vec1_)(rest vec2_)))))

(println (product vec1 vec2))
;(3 4 4)
(println (product vec2 vec3))
;(6 2 12)
(println (product vec3 vec4))
;(12 2 3)
