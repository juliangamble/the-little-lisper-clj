(ns Ch04Ex03)

; What is the fifth commandment?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 4
; Exercise 3
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

; Fifth Commandment
; Similar to *, When building a value with ^, always use 1 for the value of the terminating line, 
; for raising to the power of 1 does not change the value of an exponentiation.
