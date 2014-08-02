(ns Ch02Ex03)

; Is there coffee in this cake? Member of a list
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 2
; Exercise 3
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-2-do-it-do-it.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def l1 '(german chocolate cake))
(def l2 '(poppy seed cake))
(def l3 '((linzer) (torte)()))
(def l4 '((bleu cheese)(and)(red)(wine)))
(def l5 '(()()))
(def a1 'coffee)
(def a2 'seed)
(def a3 'poppy)

(defn lat? [l] 
  (cond
   (empty? l) true
   (not (list? (first l))) (recur (rest l)) ; note replacement of lat? with recur
   true false))
; ------------------------------
(defn member? [ a lat]
  (cond
   (empty? lat) false
   true (or
       (= (first lat) a)
       (recur a (rest lat)))))

(member? a1 l1)
(println (member? a1 l1))
;false  - There is no coffee in german chocolate cake!

(member? a2 l2)
(println (member? a2 l2))
;true - There is seed in poppy seed cake :)

; Note also the built-in function member - which behaves differently
;(member a2 l2)
(contains? a2 l2); note replacement of member with contains?
(println (contains? a2 l2))


