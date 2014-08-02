(ns Ch03Ex07)

; Telling LISP to remove the sauce (atom)
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 3
; Exercise 7
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
(defn rember [ a lat]
  (cond 
   (empty? lat) '()
   true (cond
       (= (first lat) a) (rest lat)
       true (cons (first lat)
                (rember
                 a (rest lat))))))

(rember a4  l5)
(rember 'sauce '(soy sauce and tomato sauce))

; stepping through this
(empty? '(soy sauce and tomato sauce))
(println (empty? '(soy sauce and tomato sauce)))
; false

(= (first '(soy sauce and tomato sauce)) 'sauce)
(println (= (first '(soy sauce and tomato sauce)) 'sauce))
; false

(rest '(soy sauce and tomato sauce))
(println (rest '(soy sauce and tomato sauce)))
; (sauce and tomato sauce)

(empty? '(soy sauce and tomato sauce))
(println (empty? '(soy sauce and tomato sauce)))
; false

(= (first '(sauce and tomato sauce)) 'sauce)
(println (= (first '(sauce and tomato sauce)) 'sauce))
; true

(rest '(sauce and tomato sauce))
(println (rest '(sauce and tomato sauce)))
; '(and tomato sauce)

(first '(soy sauce and tomato sauce))
(println (first '(soy sauce and tomato sauce)))
; soy

(cons 'soy '(and tomato sauce))
(println (cons 'soy '(and tomato sauce)))
; (soy and tomato sauce)
; => Here the first sauce has been removed





