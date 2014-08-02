(ns Ch04Ex07)

; What is the vector dot product of these two lists?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 4
; Exercise 7
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
(defn dot-product [ vec1_ vec2_]
  (cond
   (empty? vec1_) 0
   (empty? vec2_) 0
   true (+ (* (first vec1_) (first vec2_))
         (dot-product
          (rest vec1_)(rest vec2_)))))

(println (dot-product vec2 vec2))
;29
(println (dot-product vec2 vec4))
;26
(println (dot-product vec3 vec4))
;17

