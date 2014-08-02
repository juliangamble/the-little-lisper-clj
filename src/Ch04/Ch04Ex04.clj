(ns Ch04Ex04)

; Is the list exponent function correct?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 4
; Exercise 4
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

; 3^4 = 3^3 * 3
; 3^3 = 3^2 * 3
; 3^2 = 3^1 * 3
; 3^1 = 3^0 * 3
; 3^4 = 1 * 3 * 3 * 3 * 3
;  = 81
