(ns Ch07Ex07)

; In your DSL check you have values for your expressions
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 7
; Exercise 7
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


(defn add1 [n]
  (cond
   (= '() n) '())
   (+ n 1))

(add1 1)
;2

(defn occurNa [a1 lat]
  (cond
   (empty? lat) 0
   (= '() a1) 0
   true (cond
       (= (first lat) a1)
        (add1 (occurNa a1 (rest lat)))
       true (occurNa a1 (rest lat)))))

(occurNa 'c (list 'a 'b 'c))
;1

(def lat2 '(peaches apples bananas))
(occurNa 'bananas lat2)
;1

(defn occurN [alat lat]
  (cond 
   (empty? alat) 0
   (empty? lat) 0
   true (+ (occurNa (first alat) lat)
      (occurN (rest alat)  lat))))

(occurN (list 'bananas) (list 'bananas 'peaches 'bananas))
;2

(defn occur* [a lat]
   (println "occur*" a lat)
  (cond
   (empty? lat) false
   (list? (first lat))
    (or
     (occur* a (first lat))
    (occur* a (rest lat)))
   (= a (first lat))  true
   true (occur* a (rest lat))
 ))
   
(occur* 'bananas '(bananas peaches))
;T
(occur* 'bananas '((bananas) peaches))
;T
(occur* 'kiwis '(bananas peaches))
;NIL (false)


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

(defn covered? [lexp_ lat]
   (println "covered?" lexp_ lat)
  (cond
   (= '() lexp_) false 
   ;((not (lexp? lexp_)) false
   (not(list? lexp_)) 
    (occur* lexp_ lat)
   true (cond
       (= (operator lexp_) 'NOT)
        (covered? (first-sub-expr lexp_) lat)
       true 
        (and 
         (covered? (first-sub-expr lexp_) lat)
         (covered? (second-sub-expr lexp_) lat)))))

(covered? lexp1 '(x y z u))
;T

(covered? lexp2 '(x y z u))
;NIL (false)

(covered? lexp4 '(x y z u))
;T

(covered? '(NOT x) '(x))
;T

(covered? '(NOT x) '(x y))
;T

(covered? '(AND x y) '(x y))
;T

(occur* 'y '(x y))
;T
;(def lexp1 '(AND (OR x y) y))
(covered? '(OR x y) '(x y z u))
(occur* 'x '(x y z u))
(covered? 'y '(x y z u))      

