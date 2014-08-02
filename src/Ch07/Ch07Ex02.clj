(ns Ch07Ex02)

; Validating an expression in your DSL
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 7
; Exercise 2
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-7-shadows.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def l1 '())
(def l2 '(3 + (66 6)))
(def aexp1 '(1 + (3 * 4)))
(def aexp2 '((3 raise 4) + 5))
(def aexp4 5)
; ------------------------------

(defn operator [aexp_]
  (first (rest aexp_)))

(println (operator '(1 + 2)))
;+

(defn isoperator [a]
  (cond
   ;(empty? a) false
   (= a '+) true
   (= a '*) true
   (= a 'raise) true
   true false))

(println (isoperator 'raise))
;T

(defn first-sub-expr [aexp_]
  (first aexp_))

(println (first-sub-expr '(1 + 2))) ;note that the first letter of a symbol can't be an integer
;1

(defn second-sub-expr [aexp_]
  (first (rest (rest aexp_))))

(println (second-sub-expr '(1 + 2)))
;2

(defn number_ [n]
  (cond
   (empty? n) true
   true (and
       empty? (first n)
       (number_ (rest n)))))

(println (cons '()(cons '() '())))
;(NIL  false
(println (number_ (cons '()(cons '() '()))))
;T
;(number_ (first '(1 + 2)))

(defn sub1 [n]
  (- n 1))

;(defn notatom (lat)
;  (not (atom lat)))

(defn number__ [n]
  (cond
   ;(empty? n) false
   (list? n) false
    (= 0 n) true
   true (number__ (sub1 n))))

(println (number__ 10))
;T

(println (number__ '(66 6)))
;NIL


(defn aexp? [aexp_]
  (cond
;   (empty? aexp_) false
   (number__ aexp_) true
   (isoperator (operator aexp_))
    (and (aexp? (first-sub-expr aexp_))
    (aexp? (second-sub-expr aexp_)))    
   true false))

(println (aexp? aexp1))
;T

(println (aexp? aexp2))
;T 

(println (aexp? l1))
;NIL (false)

(println (aexp? l2))
;NIL (false)
