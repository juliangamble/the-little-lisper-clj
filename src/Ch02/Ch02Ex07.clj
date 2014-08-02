(ns Ch02Ex07)

; Can we change the recipe of member? again?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 2
; Exercise 7
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

(defn member? [a lat]
  (cond
   (empty? lat) false
   true (or
       (= (first lat) a)
       (recur a (rest lat)))))


(defn member2? [a lat]
  (cond
  (empty? lat) false
  true (or
      (= a (first lat))
      (recur a (rest lat))))) ; note swapping of order of or items, since recur must be a tail item.

; They are the same - member2? gives non-lazy (inefficient)
; evaluation of the list

(member? a1 l1)
(println (member? a1 l1))
; false

(member2? a1 l1)
(println (member2? a1 l1))
; false
; => both functions return the same result

(member? a1 l2)
(println (member? a1 l2))
; false

(member2? a1 l2)
(println (member2? a1 l2))
; false
; => both functions return the same result

(member? a2 l2)
(println (member? a2 l2))
; true

(member2? a2 l2)
(println (member2? a2 l2))
; true
; => both functions return the same result
