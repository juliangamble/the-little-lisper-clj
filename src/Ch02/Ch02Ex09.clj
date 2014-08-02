(ns Ch02Ex09)

; Is there cake in this red wine? (member? and nested lists)
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 2
; Exercise 9
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
   (not (list? (first l))) (recur (rest l)) 
   true false))

; ------------------------------

(defn member? [ a lat]
  (cond
   (empty? lat) false
   true (or
       (= (first lat) a)
       (recur a (rest lat)))))


(member? a2 l3)
(println (member? a2 l3))
; This question appears to address an issue in Scheme that is not an issue in 
; Common LISP



