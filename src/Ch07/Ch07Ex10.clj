(ns Ch07Ex10)

; Add binary operators into the evaluation ofalse  your DSL
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 7
; Exercise 10
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-7-shadows.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def  l1 '())
(def  l2 '(3 + (66 6)))
(def  aexp1 '(1 + (3 * 4)))
(def  aexp2 '((3 raise 4) + 5))
(def  aexp3 '(3 * (4 * (5 * 6))))
(def  aexp4 5)
; ------------------------------
(def lexp1 '(AND (OR x y) y))
(def lexp2 '(AND (NOT y)(OR u v)))
(def lexp3 '(OR x y))
(def lexp4 'z)


(or true false  true false)
;T

(and true false true false)
;NIL false

(defn first-sub-expr [aexp_]
  (println "first-sub-expr" aexp_)
  (first (rest aexp_)))

(first-sub-expr '(+ 1 2))
;1

(first-sub-expr '(AND (OR x y) y))

(defn second-sub-expr [aexp_]
  (println "second-sub-expr" aexp_)
  (first (rest (rest aexp_))))

(second-sub-expr '(+ 1 2))
;2

(second-sub-expr '(AND (OR x y) y))


(defn third-sub-expr [aexp_]
  (first (rest (rest (rest aexp_)))))

(defn fourth-sub-expr [aexp_]
  (first (rest (rest (rest (rest aexp_))))))

(defn operator [aexp_]
  (println "operator" aexp_)
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

(lookup 'z '((z 0)))

;(defn Mlexp [lexp_ alist_]
;  (println "Mlexp" lexp_ alist_)
;  (cond
;   (= '() lexp_) false
;   (empty? alist_) false
;   (not(list? lexp_))
;    (cond
;     ;(= lexp_  true t)
;     (= 1 (lookup lexp_ alist_)) true
;     true false)
;   (= (operator lexp_) 'AND)
;    (cond
;    (and
;     (Mlexp (first-sub-expr lexp_) alist_)
;     (Mlexp (second-sub-expr lexp_) alist_))
;      true
;     true false)
;   (= (operator lexp_) 'OR)
;    (cond
;      (or
;     (Mlexp (first-sub-expr lexp_) alist_)
;     (Mlexp (second-sub-expr lexp_) alist_))
;      true
;     true false)
;    (= (operator lexp_) 'NOT)
;     (cond
;    (not
;     (Mlexp (first-sub-expr lexp_) alist_))
;       true
;     true false)
;   true false))


(defn Mlexp-OR [lat alist]
  (println "Mlexp-OR" lat alist)
  (cond
   (empty? alist) false
   (empty? lat) false
   true
    (or
     ;(Mlexp (first lat) alist)      
     (= 1 (lookup (first lat) alist))
     (Mlexp-OR (rest lat) alist))))

(Mlexp-OR '(x y z) '((x 1)(y 1)(z 0)))
;T

(Mlexp-OR '(x y z) '((x 1)))
;T

(lookup 'x '((x 1)(y 1)(z 0)))
;1

(defn Mlexp-AND [lat alist]
  (println "Mlexp-AND" lat alist)
  (cond
   (empty? alist) false
   (empty? lat) true
   true
    (and 
     ;(Mlexp (first lat) alist)
      (= 1 (lookup (first lat) alist))
     (Mlexp-AND (rest lat) alist))))

(println (Mlexp-AND '(x y z) '((x 1)(y 1)(z 0))))
;NIl false

(println (Mlexp-AND '(x) '((x 0))))
;NIL false

(println (Mlexp-AND '(x) '((y 1)(x 0))))
;NIL false

(println (Mlexp-AND '(x y) '((x 1)(y 1)(z 0))))
;true 

(defn isoperator [a]
;  (println "isoperator " a)
  (cond
   ;(empty? a) false
   (= a '+) true
   (= a '*) true
   (= a 'raise) true
   (= a 'AND) true
   (= a 'NOT) true
   (= a 'OR) true
   true false))

(defn cnt-operands [a]
  (cond
    (empty? a) 0
    true (+ 1 (cnt-operands (rest a)))    
    )
  )

(defn cnt-aexp [a]
  (println "cnt-aexp" a)
  (cond
    (empty? a) 0
    (isoperator (first a))
      (cnt-operands (rest a))
    )
  )

(cnt-operands '(+ 1 2 3 4))
(cnt-operands '(* 1 2 3 '()))


(defn Mlexp [lexp_ alist_]
  (println "Mlexp" lexp_ alist_)
  (cond
   (= '() lexp_) false
   (empty? alist_) false
   (not(list? lexp_))
    (cond
     ;((eq lexp_ 't) t)
     (= 1 (lookup lexp_ alist_)) true
     true false)
   (and (= (operator lexp_) 'AND) (= (cnt-aexp lexp_) 2))
      (and (Mlexp (first-sub-expr lexp_) alist_) 
        (Mlexp (second-sub-expr lexp_)  alist_))
   (and (= (operator lexp_) 'AND) (= (cnt-aexp lexp_) 3))
      (and (Mlexp (first-sub-expr lexp_)  alist_) 
        (Mlexp (second-sub-expr lexp_)  alist_)
        (Mlexp (third-sub-expr lexp_)  alist_))
   (and (= (operator lexp_) 'AND) (= (cnt-aexp lexp_) 4))
      (and (Mlexp (first-sub-expr lexp_)  alist_) 
        (Mlexp (second-sub-expr lexp_)  alist_)
        (Mlexp (third-sub-expr lexp_)  alist_)
        (Mlexp (fourth-sub-expr lexp_)  alist_))
   (and (= (operator lexp_) 'OR) (= (cnt-aexp lexp_) 2))
      (or (Mlexp (first-sub-expr lexp_)  alist_) 
        (Mlexp (second-sub-expr lexp_)  alist_))
   (and (= (operator lexp_) 'OR) (= (cnt-aexp lexp_) 3))
      (or (Mlexp (first-sub-expr lexp_)  alist_) 
        (Mlexp (second-sub-expr lexp_)  alist_)
        (Mlexp (third-sub-expr lexp_) alist_))
   (and (= (operator lexp_) 'OR) (= (cnt-aexp lexp_) 4))
      (or (Mlexp (first-sub-expr lexp_)  alist_) 
        (Mlexp (second-sub-expr lexp_)  alist_)
        (Mlexp (third-sub-expr lexp_)  alist_)
        (Mlexp (fourth-sub-expr lexp_)  alist_)) 
   (= (operator lexp_) 'NOT)
      (not (Mlexp (first-sub-expr lexp_) alist_))
   true false))

(operator '(AND (OR x y) y))
(cnt-aexp '(AND (OR x y) y))
(println (Mlexp '(AND (OR x y) y) '((x 1)(y 0)(z 0))))
(println (Mlexp lexp1 '((x 1)(y 0)(z 0))))
;NIL false

(operator '(AND (NOT y) (OR u v)))
(cnt-aexp '(AND (NOT y) (OR u v)))

(println (Mlexp '(AND (NOT y)(OR u v)) '((y 0)(u 0)(v 1))))
(println (Mlexp lexp2 '((y 0)(u 0)(v 1))))
;T

(println (Mlexp '(OR u v) '((y 0)(u 0)(v 1))))
;T

(println (Mlexp '(OR u (OR u v)) '((y 0)(u 0)(v 1))))
;T

(println (Mlexp '(NOT  y) '((y 0)(u 0)(v 1))))
;T

(println (Mlexp '(AND v v) '((y 0)(u 0)(v 1))));
;T

(println (Mlexp '(AND (AND v v) v) '((y 0)(u 0)(v 1))))
;T

(println (Mlexp 'z '((x 1)(y 0)(z 0))))
(println (Mlexp lexp4 '((x 1)(y 0)(z 0))))
;NIL false



(println (Mlexp '(AND x y z) '((x 1)(y 0)(z 0))));F;correct
(println (Mlexp '(NOT (AND x y z)) '((x 1)(y 0)(z 0))));T;correct
(println (Mlexp '(OR z z (NOT (AND x y z))) '((x 1)(y 0)(z 0))));T;correct???

