(ns Ch02Ex08)

; Did changing the recipe spoil the cake? (member? function)
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 2
; Exercise 8
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


(defn member2? [ a lat]
  (cond
  (empty? lat) false
  true (or
      (= a (first lat)) ;swapped since can only recur from tail position
      (recur a (rest lat)))))

                            
(member? a3 l2)
(println (member? a3 l2))
; true

; Stepping through we first do
(empty? '(poppy seed cake))
(println (empty? '(poppy seed cake)))
; false

(= (first '(poppy seed cake)) 'poppy)
(println (= (first '(poppy seed cake)) 'poppy))
; true - so poppy is in (poppy seed cake)

(member2? a3 l2)
(println (member2? a3 l2))
; true

; Stepping through we first do
(empty? '(poppy seed cake))
(println (empty? '(poppy seed cake)))
; false

(rest '(poppy seed cake))
(println (rest '(poppy seed cake)))
; (seed cake)

(empty? '(seed cake))
(println (empty? '(seed cake)))
; false

(rest '(seed cake))
(println (rest '(seed cake)))
; (cake)

(empty? '(cake))
(println (empty? '(cake)))
; false

(rest  '(cake))
(println (rest  '(cake)))
; ()

(empty? '())
(println (empty? '()))
; true

(= (first '(poppy seed cake)) 'poppy)
(println (= (first '(poppy seed cake)) 'poppy))
; true - so poppy is in (poppy seed cake)

;  We can see that member2 is inefficient


