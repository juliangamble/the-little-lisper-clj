(ns Ch07Ex08)

; Get the variables passed into your expression DSL
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 7
; Exercise 8
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

(defn lookup [a lat]
  (println "lookup" a lat)
  (cond
   (= '() a) false
   (empty? lat) false
   (= a (first (first lat)))
    (first (rest (first lat)))
   true
    (lookup a (rest lat))))

(println (lookup 'y '((x 1)(y 0))))
;0

(println (lookup 'x '((x 1)(y 0))))
;1

(println (lookup 'u '((u 1)(v 1))))
;1

(println (lookup 'y '()))
;NIL  no answer
