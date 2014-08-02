(ns Ch02Ex05)

; Is this (poppy seed cake) fake? LISP and fake LATs
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 2
; Exercise 5
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
(defn nonlat? [l] 
  (cond
   (empty? l) false
   (not (list? (first l))) (nonlat? (rest l))
   true true))


(lat? l1)
(println (lat? l1))
; true  - l1 is a list of atoms

(nonlat? l1)
(println (nonlat? l1))
; false - so l1 is not a non-list of atoms

(nonlat? l2)
(println (nonlat? l2))
; false - so l2 is not a non-list of atoms 

(nonlat? l3)
(println (nonlat? l3))
; true 
; (actually false - the manual is wrong according to this
; http://newsgroups.derkeiler.com/Archive/Comp/comp.lang.scheme/2008-09/msg00252.html
; http://www.cs.rutgers.edu/~ccshan/cs314/
; They suggest that an empty list is in fact an atom - but for the where 
; The Little Lisper is driving at  (it has very particular goals) - it is not - so this 
; should return false for lat?)
; We'll ignore this discussion for now

(nonlat? l4)
(println (nonlat? l4))
; true - this is a non-list of atoms 

(defn nonlat? [l]
  (cond
   (empty? l) true
    (lat? l) false
   true true))
; This is an alternate definition of nonlat? which is basically NOT lat?
; It returns the same results
