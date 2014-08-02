(ns Ch01Ex08)

; Meatballs and spaghetti. Is the end of a nested list an atom or null?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 1
; Exercise 8
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-1-toys.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

(def l '((meatballs) and spaghetti))
(first l)
(not (list? (first l))) ; note replacement of (atom with (not (list?
(println (not (list? (first l))))
; False

(def l '((meatballs)))
(rest l)
;(println (rest l))
(next (rest l))
(println (nil? (next l))) ; note replacement of (rest with (next for nil test
; True

(def l '(two meatballs))
(= (first l)(first (rest l)))
(println (= (first l)(first (rest l)))); note replacement of (eq with (=
; False

(def l '(ball))
(def a 'meat)
;(println (cons a l))
;(println (list? (cons a l)))
;(println (seq? (cons a l))); note replacement of (list? with (seq?
(not (seq? (cons a l)))
(println (not (seq? (cons a l))))
; False

;The remainder of a nested list is null


