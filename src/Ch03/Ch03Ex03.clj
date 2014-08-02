(ns Ch03Ex03)

; hot hot chili - duplicating an atom function
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 3
; Exercise 3
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

(defn double_ [a lat]
  (cond
   (empty? lat) '()
   (= (first lat) a)(cons a lat)
   true (cons (first lat)
            (double_ a (rest lat)))))

(double_ a2 l2)
(println (double_ a2 l2))

(double_ a1 l3)
(println (double_ a1 l3))

(double_ a2 l4)
(println (double_ a2 l4))

