(ns Ch03Ex06)

; texas hot hot - list intersection substitution function
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 3
; Exercise 6
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-3-cons.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def l1 '((paella spanish)(wine red)(and beans)))
(def l2 '())
(def l3 '(cincinnati chili))
(def l4 '(texas hot chili))
(def l5 '(soy sauce and tomato sauce))
(def l6 '((spanish)()(paella)))
(def l7 '((and hot)(but dogs)))
(def a1 'chili)
(def a2 'hot)
(def a3 'spicy)
(def a4 'sauce)
(def a5 'soy)
; ------------------------------

(defn member? [ a lat]
  (cond
   (empty? lat) false
   true  (or
       (= (first lat) a)
       (recur a (rest lat)))))

(defn substN [ new slat lat]
  (cond
   (empty? lat) '()
   true (cond
       (member? (first lat) slat)
        (cons new (rest lat))
       true (cons (first lat)
                (substN new slat (rest lat))))))

(substN a2 l3 l4)
(println (substN a2 l3 l4))
;(TEXAS HOT HOT) 

(substN a4 l3 l5)
(println (substN a4 l3 l5))
;(SOY SAUCE AND TOMATO SAUCE) 

(substN a4 l3 l2)
(println (substN a4 l3 l2))
;NIL 