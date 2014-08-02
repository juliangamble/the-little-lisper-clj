(ns Ch01Ex04)

; Putting the french in french fries. (What is a list remainder?)
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 1
; Exercise 4
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-1-toys.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def a 'french) 
(def l '(fries))
(first (cons a l))
(println (first (cons a l)))

(def a 'oranges) 
(def l '(apples and peaches))
(rest (cons a l))
(println (rest (cons a l)))





