(ns Ch07Ex03)

; Count the operators in your DSL
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 7
; Exercise 3
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

; need to def aexp1 and aexp2
; need to add number__, first-sub-expr, second-sub-expr, sub1, isoperator, operator

(defn sub1 [n]
  (println "sub1" n)
  (- n 1))

(defn operator [aexp_]
  (println "operator " aexp_)
  (first (rest aexp_)))

(defn isoperator [a]
  (println "isoperator " a)
  (cond
   ;(empty? a) false
   (= a '+) true
   (= a '*) true
   (= a 'raise) true
   true false))


(defn first-sub-expr [aexp_]
  (println "first-sub-expr" aexp_)
  (first aexp_))

(defn second-sub-expr [aexp_]
  (println "second-sub-expr" aexp_)
  (first (rest (rest aexp_))))

(defn number__ [n]
   (println "number__ " n)
  (cond
   ;(empty? n) false
    (seq? n) false ; note that cons returns a sequence, not a list - so you have to test for this separately
   ;(list? n) false
    (= 0 n) true
   true (number__ (sub1 n))))


(defn mk+exp [aexp1_ aexp2_]
  (cons aexp1_
        (cons '+
              (cons aexp2_ '()))))

(defn mk*exp [aexp1_ aexp2_]
  (cons aexp1_
        (cons '*
              (cons aexp2_ '()))))

(def aexp1 (mk+exp 1 (mk*exp 3 4)))
(def aexp3 (mk*exp 3 (mk*exp 4 (mk*exp 5 6))))

(defn count-op [aexp_]
  (println "count-op" aexp_)
  (cond
   (= aexp_ '()) 0       
    (number__ aexp_) 0
   (isoperator (operator aexp_))
    (+ 
    (+ 1 (count-op (first-sub-expr aexp_)))
    (count-op (second-sub-expr aexp_)))
   true 0))

(println (count-op '(1 + 2)))
;1
(println (count-op '(1 + (3 * 4))))
;2
(= '(1 + (3 * 4)) aexp1)
;true
(number__ '(1 + (3 * 4)))
;false
(number__ aexp1)
;error
(list? '(1 + (3 * 4)))
;true
(list? aexp1)
;false

(println (count-op aexp1))
;2

(println (count-op aexp3))
;3

(println (count-op aexp4))
;0

(defn countatomplus [a]
  (println "countatomplus" a)
  (cond
;   (empty? a) 0
   (= a '+) 1
   true 0))

(println (countatomplus '*))
;0

(println (countatomplus '+))
;1

(defn count+ [aexp_]
  (println "count+" aexp_)
  (cond
   (= aexp_ '()) 0
   (number__ aexp_) 0
   (isoperator (operator aexp_))
    (+ 
    (+ (countatomplus(operator aexp_)) (count+ (first-sub-expr aexp_)))
    (count+ (second-sub-expr aexp_)))
   true 0))

(count+ 1)
(println (count+ aexp1))
;1

(defn countatomtimes [a]
  (cond
   ;(empty? a) 0
    (= a '()) 0
   (= a '*) 1
   true 0))

(defn count* [aexp_]
  (cond
;   (empty? aexp_) 0
    (= aexp_ '()) 0
   (number__ aexp_) 0
   (isoperator (operator aexp_))
    (+ 
    (+ (countatomtimes(operator aexp_)) (count* (first-sub-expr aexp_)))
    (count* (second-sub-expr aexp_)))    
   true 0))

(println (count* aexp1))
;1


(println (count* aexp3))
;3

(defn countatomexp [a]
  (cond
;   (empty? a) 0
    (= '() a) 0
   (= a '_r) 1
   true 0))

(defn count_r [aexp_]
  (cond
;   (empty? aexp_) 0
    (= '() aexp_) 0
   (number__ aexp_) 0
   (isoperator (operator aexp_))
    (+ 
    (+ (countatomexp(operator aexp_)) (count_r (first-sub-expr aexp_)))
    (count_r (second-sub-expr aexp_)))
   true 0))

(println (count_r aexp1))
;0


