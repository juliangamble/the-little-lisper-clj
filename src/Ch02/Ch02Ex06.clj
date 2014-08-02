(ns Ch02Ex06)

; Is there cake in this list?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 2
; Exercise 6
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

(defn member-cake? [lat]
  (cond 
   (empty? lat) false
   true (or
       (= (first lat) 'cake)
       (recur (rest lat)))))

(member-cake? l1)
(println (member-cake? l1))
;true - there is cake in (german chocolate cake)

(member-cake? l2)
(println (member-cake? l2))
;true - there is cake in (poppy seed cake)

(member-cake? l3)
(println (member-cake? l3))
;false - there is no cake in (linzer)(torte)())
