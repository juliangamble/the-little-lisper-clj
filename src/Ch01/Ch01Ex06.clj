(ns Ch01Ex06)

; What is the meaning of nothing? Can you have a null list?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 1
; Exercise 6
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-1-toys.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

;No - see the following - 
(def a 'bob)
(cons a nil)
(cons a ())
(cons a '())
(nil? (cons a nil))
(println (nil? (cons a nil)))
; Basically an atom is never equal to null/NIL
(nil? (first (cons nil '())))
(println (nil? (first (cons nil '()))))
; You can achieve this if a is NIL - but then it wouldn't  be an atom
