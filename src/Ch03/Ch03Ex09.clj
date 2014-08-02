(ns Ch03Ex09)

; Removing the second chili (from this list)
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 3
; Exercise 9
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

(defn rember [a lat]
  (cond 
   (empty? lat) '()
   true (cond
       (= (first lat) a) (rest lat)
       true (cons (first lat)
                (rember
                 a (rest lat))))))
  
(defn rember2 [a lat]
  (cond
   (empty? lat) '()
   (= (first lat) a) (cons a (rember a (rest lat)))
   true (cons (first lat)
            (rember2 a (rest lat)))))

(rember2 a1 l3)
(println (rember2 a1 l3))

(rember2 a4 l5)
(println (rember2 a4 l5))

(rember2 a4 l2)
(println (rember2 a4 l2))

