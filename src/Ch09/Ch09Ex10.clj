(ns Ch09Ex10)

; Can you use P and Q to build a stream?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 9
; Exercise 10
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-9-lamdba-ultimate.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

(defn add1 [n]
 (+ 1 n))

(defn zero_? [n]
  (= 0 n))

(defn sub1 [n]
  (- n 1))

(defn build [a b]
  ;(println "build" a b)
  (cons a (cons b '())))

(defn str-maker [next n]
  (build n (fn [] (str-maker next (next n)))))

;(set 'int-maker (str-maker (function add1) 0))
(def int_ (str-maker add1 0))

;(defun first$ (l) 
;  (first l))

;(defun second$ (l) 
;  (funcall (second l)))

(def first$ first)

(defn second_ [l]
  ;(println "second_" l)
  (cond
   (empty? l) '()
   true (first (rest l))))

(defn second$ [str]
  ;(println "second$" str)
  ((second_ str)))

(defn div [m n]
  (cond
   (zero? m) 0 ; note zero? function here
   (< m 0) 0
   (< m n) 0
   true (+ 1 (div (- m n) n))))

(println (div 6 2))


(defn remainder [n m]
  (cond
   true (- n (* m (div n m))))) ;note times function here

(defn Q [str n]
  (cond
   (zero? (remainder (first$ str) n))
         (Q (second$ str) n)
   true (build (first$ str) (fn [] (Q (second$ str) n)))))

(defn P [str]
  (build (first$ str) (fn []  (P (Q str (first$ str))))))



(defn frontier [str n]
  ;(println "frontier" str n)
  (cond
    (= 0 n) ()
    true (cons (first$ str) (frontier (second$ str) (sub1 n)))))

;(frontier (P (second$ (second$ int-maker))) 10)
(frontier (P (second$ (second$ int_))) 10)
(frontier (P (second$ (second$ int_))) 100)
; (2 3 5 7 11 13 17 19 23 29)
; This is a stream of prime numbers


