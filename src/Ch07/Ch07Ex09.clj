(ns Ch07Ex09)

; Evaluate your expression in your DSL
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 7
; Exercise 9
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

(defn first-sub-expr [aexp_]
  (first (rest aexp_)))

(first-sub-expr '(+ 1 2))
;1

(defn second-sub-expr [aexp_]
  (first (rest (rest aexp_))))

(second-sub-expr '(+ 1 2))
;2

(defn operator [aexp_]
  (first aexp_))

(operator '(NOT x))
; NOT


(defn lookup [a lat]
  (println "lookup" a lat)
  (cond
   (= '() a) false
   (empty? lat) false
   (= a (first (first lat)))
    (first (rest (first lat)))
   true
    (lookup a (rest lat))))

(lookup 'x '((x 1) (y 0) (z 0)))

(defn Mlexp [lexp_ alist_]
  (println "Mlexp" lexp_ alist_)
  (cond
   (= '() lexp_) false
   (empty? alist_) false
   (not(list? lexp_))
    (cond
     ;(= lexp_  true t)
     (= 1 (lookup lexp_ alist_)) true
     true false)
   (= (operator lexp_) 'AND)
    (cond
    (and
     (Mlexp (first-sub-expr lexp_) alist_)
     (Mlexp (second-sub-expr lexp_) alist_))
      true
     true false)
   (= (operator lexp_) 'OR)
    (cond
      (or
     (Mlexp (first-sub-expr lexp_) alist_)
     (Mlexp (second-sub-expr lexp_) alist_))
      true
     true false)
    (= (operator lexp_) 'NOT)
     (cond
    (not
     (Mlexp (first-sub-expr lexp_) alist_))
       true
     true false)
   true false))

(def lexp1 '(AND (OR x y) y))
(def lexp2 '(AND (NOT y)(OR u v)))
(def lexp3 '(OR x y))
(def lexp4 'z)

(println (Mlexp lexp1 '((x 1)(y 0)(z 0))))
;NIL false

(println (Mlexp lexp2 '((y 0)(u 0)(v 1))))
;T 

(println (Mlexp lexp4 '((x 1)(y 0)(z 0))))
; NIL false
