(ns Ch04Ex10)

; Less than or equal to?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 4
; Exercise 10
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

(defn <=_ [ m n]
  (cond
   (= m n) true
   (= (div m n) 0) true
   true false))

(println (<=_  zero one))
;true
(println (<=_  one one))
;true
(println (<=_  three one))
;false


