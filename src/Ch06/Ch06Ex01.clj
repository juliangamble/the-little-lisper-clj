(ns Ch06Ex01)

; Finding the hidden chili and separating them out
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 6
; Exercise 1
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-6-oh-my-gawd-its.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def l1 '((fried potatoes)(baked (fried)) tomatoes))
(def l2 '(((chili) chili (chili))))
(def l3 '())
(def lat1 '(chili and hot)) 
(def lat2 '(baked fried)) 
(def a 'fried)
; ------------------------------

;(println (atom? 'chili))
;(println (atom '(chili)))
;(println (atom (first '(chili))))
;(println (consp (first l2)))

;(defn notatom (lat)
;  (not (atom lat)))

(println (list? 'a))
(println (list? '(a)))

(defn down* [lat]
  (cond
   (empty? lat) '()
   (list? (first lat))
    (cons (down* (first lat))
            (down* (rest lat)))
   true (cons (list (first lat))
            (down* (rest lat)))))

(println (down* (list 'bob)))
;((BOB))

(println (down* l2))
;((((CHILI)) (CHILI) ((CHILI))))

(println (down* l3))
;NIL;

(println (down* lat1))
;((CHILI) (AND) (HOT))

