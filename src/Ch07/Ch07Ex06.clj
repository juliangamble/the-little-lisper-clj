(ns Ch07Ex06)

; Add binary operators to your expressions
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 7
; Exercise 6
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

(def lexp1 '(AND (OR x y) y))
(def lexp2 '(AND (NOT y)(OR u v)))
(def lexp3 '(OR x y))
(def lexp4 'z)

(defn loperator? [a]
  (cond
   (= '() a) false
   (= a 'OR) true
   (= a 'AND) true
   (= a 'NOT) true
   true false))

(loperator? 'AND)
;T
(loperator? 'bob)
;NIL (false)

(defn first-sub-exp [aexp_]
  (println "first-sub-exp " aexp_)
  (first (rest aexp_)))

(first-sub-exp '(+ 1 2))

(defn second-sub-exp [aexp_]
    (println "second-sub-exp " aexp_)
  (first (rest (rest aexp_))))

(second-sub-exp '(+ 1 2))


(defn lexp? [lexp_]
  (println "lexp?" lexp_)
  (cond
   (= '() lexp_) false 
   (not(list? lexp_)) true
   (loperator? (first lexp_))
    (cond 
     (= (first lexp_) 'NOT)
      (lexp? (first-sub-exp lexp_))
     true 
       (and (lexp? (first-sub-exp lexp_))
       (lexp? (second-sub-exp lexp_))))
   true false))

(lexp? lexp1)
;T

(lexp? lexp2)
;T

(lexp? lexp3)
;T

(lexp? aexp1)
;NIL (false)

(lexp? l2)
;NIL (false)
