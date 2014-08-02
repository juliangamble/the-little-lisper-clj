(ns Ch07Ex05)

; Make your DSL expressions scalable
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 7
; Exercise 5
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


;note first use of primitive numberp

(defn numbered? [aexp_]
  (cond
   (not (list? aexp_)) (number? aexp_)
   ;(not (list? aexp_) (number? aexp_))
   true (and
       (numbered? (first aexp_))
       (numbered?
        (first (rest (rest aexp_)))))))
;numbered? tests whether a representation of an arithmetic expression only contains numbers besides the +, * and ^

(println (numbered? '(1 + 2)))
;T
(println (numbered? '(1 + a)))
;NIL (false)

(defn first-sub-exp [aexp_]
  (first aexp_))

(println (first-sub-exp '(1 + 2)))

(defn second-sub-exp [aexp_]
  (first (rest (rest aexp_))))

(println (second-sub-exp '(1 + 2)))

(use 'clojure.contrib.math); note use of clojure library function exp
(expt 2 2)

;value returns what we think is the natural value of a numbered arithemetic expression
(defn value__ [aexp_]
  (cond
   (number? aexp_) aexp_
   (= (operator aexp_) '+)
    (+ (value__ (first-sub-exp aexp_))
       (value__ (second-sub-exp aexp_)))
   (= (operator aexp_) '*)
    (* (value__ (first-sub-exp aexp_))
       (value__ (second-sub-exp aexp_)))
   true
    (expt (value__ (first-sub-exp aexp_))
       (value__ (second-sub-exp aexp_)))))

(println (value__ '(1 + 2)))

;define numbered? for arbitrary length list
(defn numbered_? [aexp_]
  (cond
   (empty? aexp_) true
   (list? (first aexp_))
    (and 
     (numbered? (first aexp_))
     (numbered_? (rest aexp_)))
   (number? (first aexp_)) ; note use of clojure library number?
    (numbered_? (rest aexp_))
   true false))
    
(defn numbered? [aexp_]
  (cond
   (empty? aexp_) false
   (isoperator (first aexp_))
    (numbered_? (rest aexp_))
   true false))

(println (numbered?'(+ 1 2 3 4 5)))
;T

(println (numbered?'(+ 1 2 3 (* 3 4))))
;T

;(defn value_ [aexp_]
;  (cond
;   (number? aexp_) aexp_ ; note use of clojure library
;   (list? (first aexp_))
;    (value_ (cons (value_ (first aexp_))(rest aexp_))) ; check this line
;   (= (first aexp_) '+)
;    (addvec (rest aexp_))
;   (= (first aexp_) '*)
;    (multvec (rest aexp_))
;   true 
;    (_r (value_ (first-sub-expr aexp_))
;       (value_ (second-sub-expr aexp_)))))

(defn value_ [op aexp_]
  (cond
    (number? aexp_) aexp_
    (empty? aexp_) 0
    (= op +)
        (+ (first aexp_) (value_ + (rest aexp_)))
    (= op *)
        (* (first aexp_) (value_ * (rest aexp_)))
    (= op expt)
        (expt (first aexp_) (value_ expt (rest aexp_)))
))

(defn value_ [aexp_]
  (cond
   (number? aexp_) aexp_ ; note use of clojure library
   (list? (first aexp_))
    (value_ (cons (value_ (first aexp_))(rest aexp_))) ; check this line
   (= (first aexp_) '+)
    (addvec (rest aexp_))
   (= (first aexp_) '*)
    (multvec (rest aexp_))
   true 
    (_r (value_ (first-sub-expr aexp_))
       (value_ (second-sub-expr aexp_)))))



;define value? for an arbitrary length list
(defn addvec [vec]
  (cond 
   (empty? vec) 0
   (list? (first vec))
    (+ (value_ (first vec)) 
       (addvec (rest vec)))
   true (+ (first vec)(addvec (rest vec)))))


(defn multvec [vec]
  (cond 
   (empty? vec) 1
   (list? (first vec))
    (* (value_ (first vec)) 
       (multvec (rest vec)))
   true (* (first vec)(multvec (rest vec)))))

(println (addvec '(1 2 3)))
;6


(println (multvec '(1 2 3)))
;6

(println (value_ '(+ 3 2 (* 7 8))))
;61 

(println (value_ '(* 3 4 5 6)))
;360


(defn _r [n m]
  (cond
   (= 0 m) 1
   true (* n (_r n (sub1 m)))))

(_r 2 3)
;8

