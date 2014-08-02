(ns Ch01Ex07)

; What if you add nothing to nothing? What about a null list?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 1
; Exercise 7
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-1-toys.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

(def s 'x)
(def l 'y)
(cons s '(l))
(println (cons s '(l)))
; Cons-ing an atom onto an atom gives a list the two elements separated by an empty list

(def s '())
(def l '())
(cons s l)
(println (cons s l))
; Cons-ing a null list onto a null list gives a single null list (no effect)

(def s '())
(first s)
(println (first s))
; Getting the first element of a null list is null

(def l '('()))
(rest l)
(print (rest l))
;Getting the remainder of a list of a null element is null
