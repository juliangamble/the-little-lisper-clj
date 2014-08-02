(ns Ch03Ex05)

; and tomato sauce - multi substution function
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 3
; Exercise 5
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

(defn subst3 [ new o1 o2 o3 lat]
  (cond 
   (empty? lat) '()
   true (cond
       (= (first lat) o1)
        (cons new (rest lat))
       (= (first lat) o2)
        (cons new (rest lat))
       (= (first lat) o3)
        (cons new (rest lat))
       true (cons (first lat)
                (subst3 new o1 o2 o3 (rest lat))))))

(subst3 a5 a1 a2 a4 l5) 
(println (subst3 a5 a1 a2 a4 l5))
;(SOY SOY AND TOMATO SAUCE) 

(subst3 a4 a1 a2 a3 l4)
(println (subst3 a4 a1 a2 a3 l4))
;TEXAS SAUCE CHILI) 


(subst3 a3 a1 a2 a5 l2)
(println (subst3 a3 a1 a2 a5 l2))
;() 
