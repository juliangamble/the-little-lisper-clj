(ns Ch02Ex01)

; German chocolate cake - What is a List of AToms? 
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 2
; Exercise 1
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

(cond (= 1 2) 2 (= 3 3) 3) ; note that cond in clojure has a lot less brackets

(defn lat? [l]   ; note replacement of (defun lat? (l) with (defn lat? [l] 
  (cond
   (empty? l) true  ; note replacement of t with true - note replacement of nil? with empty?
   (not (list? (first l))) (recur (rest l)) ;(lat? (rest l)); note replacement of (atom? with (not (list?
   true false))  ; note replacement of t with true

; ------------------------------
; Note that in our usage of defun to define a function - we've left out the lambda
; function - this is deliberate for simplicity - we'll add it back in in chapter 9

;(lat? l1)
(println (lat? l1))
; This is true (T)
;(lat? l2)
(println (lat? l2))
; This is true (T)
;(lat? l3)
(println (lat? l3))
; This is false (NIL)

; You can define a list of atoms (LAT) using the LAT function. A nested list returns nil
; for this function.
