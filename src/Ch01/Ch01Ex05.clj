(ns Ch01Ex05)

; Are two lisp's the same? When are atoms equal?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 1
; Exercise 5
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-1-toys.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

(def x 'lisp)
(def y 'lisp)
(= x y)
(println (= x y))
; The atom 'lisp is equal to another atom 'lisp

(def x (first('lisp 'lisp)))
(def y 'lisp)
(= x y)
(println (= x y))
;  The atom 'lisp is equal to the first element of a list which is 'lisp

(= 'lisp 'LISP)
(println (= 'lisp 'LISP))
; The atom 'lisp is equal to another atom 'LISP being a non-casesensitive comparison
