(ns Ch04Ex09)

; Telling LISP to find that remainder
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 4
; Exercise 9
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



(defn div [ m n]
  (cond
   (zero-? m) 0
   (< m 0) 0
   (< m n) 0
   ;((zero n) 0);actually infinity
   true (+ 1 (div (- m n) n))))


(defn remainder [ n m]
  (cond
   true (- n (* m (div n m)))))

(println (remainder 12 3))
;0
(println (remainder 13 3))
;1
(println (remainder  7 5))
;2
(println (remainder  8 2))
;0
(println (remainder  2 3))
;2

