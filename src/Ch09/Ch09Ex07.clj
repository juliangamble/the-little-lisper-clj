(ns Ch09Ex07)

; Can you abstract accumulators into a single function?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 9
; Exercise 7
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-9-lamdba-ultimate.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

(defn operator [aexp_]
  (first (rest aexp_)))

(println (operator '(1 + 2)))
; +

(defn isoperator [a]
  (cond
   (= () a) false
   (= a '+) true
   (= a '*) true
   (= a 'expn) true
   true false))

(println (isoperator 'expn))
; T

(println (isoperator (operator '(1 + 2))))
; T

(defn first-sub-expr [aexp_]
  (first aexp_))

(println (first-sub-expr '(1 + 2)))
; 1

(defn second-sub-expr [aexp_]
  (first (rest (rest aexp_))))

(println (second-sub-expr '(1 + 2)))
; 2

(defn sub1 [n]
  (- n 1))

(defn notatom [lat]
  (println "notatom" lat)
  (seq? lat))

(defn number__ [n]
  (println "number__" n)
  (cond
   (= () n) false
   (notatom n) false
  (= 0 n) true
   true (number__ (sub1 n))))

(println (number__ 10))
;T

(defn count-op [aexp_]
  (println "count-op" aexp_)
  (cond
   (= () aexp_) 0
   (number__ aexp_) 0
   (isoperator (operator aexp_))
    (+ 
    (+ 1 (count-op (first-sub-expr aexp_)))
    (count-op (second-sub-expr aexp_)))
   true 0))

(= () 3)

(println (count-op '(3 * (4 * (5 * 6)))))


(defn count-op-f [aexp_ op-function]
  (println "count-op-f" aexp_ op-function)
  (cond
   (= () aexp_) 0
   (number__ aexp_) 0
   (= (operator aexp_) op-function)
    (+ 
     (+ 1
       (count-op-f (first-sub-expr aexp_) op-function))
    (count-op-f (second-sub-expr aexp_) op-function))
   true 0))

(println (count-op-f '(1 + 1)  '+))
; 1

(println (count-op-f '(1 + (1 + 1)) '+))
; 2

(println (count-op-f '(3 * (4 * (5 * 6)))  '*))
; 3

(println (count-op-f '(1 - 1)  '+))
; 0
