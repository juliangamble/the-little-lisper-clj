(ns Ch07Ex04)

; Count the operands in your DSL
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 7
; Exercise 4
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-7-shadows.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def l1 '())
(def l2 '(3 + (66 6)))
(def aexp1 '(1 + (3 * 4)))
(def aexp2 '((3 raise 4) + 5))
(def aexp3 '(3 * (4 * (5 * 6))))
(def aexp4 5)
; ------------------------------

(defn sub1 [n]
;  (println "sub1" n)
  (- n 1))

(defn operator [aexp_]
;  (println "operator " aexp_)
  (first (rest aexp_)))

(defn isoperator [a]
;  (println "isoperator " a)
  (cond
   ;(empty? a) false
   (= a '+) true
   (= a '*) true
   (= a 'raise) true
   true false))

(defn first-sub-expr [aexp_]
;  (println "first-sub-expr" aexp_)
  (first aexp_))

(defn second-sub-expr [aexp_]
;  (println "second-sub-expr" aexp_)
  (first (rest (rest aexp_))))

(defn number__ [n]
;   (println "number__ " n)
  (cond
   ;(empty? n) false
    (seq? n) false ; note that cons returns a sequence, not a list - so you have to test for this separately
   ;(list? n) false
    (= 0 n) true
   true (number__ (sub1 n))))


(defn count-numbers [aexp_]
  (cond
   ;(empty? aexp_) 0
    (= aexp_ '()) 0
   (number__ aexp_) 1
   (isoperator (operator aexp_))
    (+ 
    (count-numbers (first-sub-expr aexp_))
    (count-numbers (second-sub-expr aexp_)))
   true 0)) 

(println (count-numbers '(1 + 2)))
;2

(println (count-numbers aexp1))
;3

(println (count-numbers aexp3))
;4

(println (count-numbers aexp4))
;1
