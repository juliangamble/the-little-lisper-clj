(ns Ch04Ex01)

; Duplicate that atom
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 4
; Exercise 1
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-4-number-games.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def vec1 '(1 2))
(def vec2 '(3 2 4))
(def vec3 '(2 1 3))
(def vec4 '(6 2 1))
(def l '())
(def zero 0)
(def one 1)
(def three 3)
(def obj '(x y))
; ------------------------------

(defn zero-? [n] ; note clojure is a LISP-1 can't be 'zero since share namespace!
  (cond
;   (empty? n) '()
  (= zero n) true
   true false))


(println (zero-? 1))
(println (zero-? zero))
(println (zero-? 0))
;(println (zero-? NIL))

(defn sub1 [ n]
  (cond
;   (empty? n) '()
   (= n '()) '()
   true (- n 1)))

(println (sub1 12))
(println (sub1 3))
(println (sub1 ()))
(println (sub1 -12))

(defn duplicate [n obj]
  (cond
   (zero-? n ) '()
;   (empty? obj) '()
   (= 1 n) (cons obj '())
   true (cons obj
            (duplicate (sub1 n) obj))))
(zero-?  (sub1 1))
(zero-? 0)
(= zero 0)
;(empty? 'a)
(duplicate  2 'a)

(println (duplicate 3 obj))
; ((X Y) (X Y) (X Y))
(println (duplicate zero obj))
; NIL
(println (duplicate one vec1))
; ((1 2))


(zero-? 3)
(zero-? 0)

