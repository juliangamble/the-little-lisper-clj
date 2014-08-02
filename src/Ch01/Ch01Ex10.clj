(ns Ch01Ex10)

; Peanut butter. How do I manually extract a particular atom from a list?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 1
; Exercise 10
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-1-toys.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

(def l '(apples in (Harry has a backyard)))
(first (first (rest (rest l))))
(println (first (first (rest (rest l)))))

(def l '(apples and Harry))
(first (rest (rest l)))
(println (first (rest (rest l))))

(def l '(((apples) and ((Harry))) in his backyard))
(first (first (first (rest (rest (first l))))))
(println (first (first (first (rest (rest (first l)))))))

; You manually extract a particular atom in a list by carefully obtaining your 
; list first elements and list remainders
