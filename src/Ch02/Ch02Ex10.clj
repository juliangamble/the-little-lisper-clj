(ns Ch02Ex10)

; Did we put the chocolate in twice? (Dup. list members)
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 2
; Exercise 10
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-2-do-it-do-it.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def l1 '(german chocolate cake))
(def l2 '(poppy seed cake))
(def l3 '((linzer) (true orte)()))
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
   true  (or
       (= (first lat) a)
       (recur a (rest lat)))))


(defn member-twice? [ a lat]
  (cond
   (member? a lat)(member? a (rest lat))
   true  false))

(member-twice? 'poppy '(poppy poppy))
(println (member-twice? 'poppy '(poppy poppy)))
; true

(member-twice? 'poppy '(poppy))
(println (member-twice? 'poppy '(poppy)))
; false

(member-twice? 'poppy (cons a3 l2)) 
(println (member-twice? 'poppy (cons a3 l2)))
; true

(member-twice? 'poppy  l2) 
(println (member-twice? 'poppy  l2) )
; false
