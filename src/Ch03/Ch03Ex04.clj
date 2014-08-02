(ns Ch03Ex04)

; hot sauce - substutition function
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 3
; Exercise 4
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
(defn subst-sauce [a lat]
  (cond
   (empty? lat) '()
   true (cond
       (= (first lat) 'sauce)
        (cons a (rest lat))
       true (cons (first lat)
                    (subst-sauce a (rest lat))))))

(subst-sauce a1 l4)
;correct (texas hot chili)
(println (subst-sauce a1 l4))

(subst-sauce a1 l5)
;correct (soy chili and tomato sauce)
(println (subst-sauce a1 l5))

(subst-sauce a4 l2)
;correct NIL
(println (subst-sauce a4 l2))
