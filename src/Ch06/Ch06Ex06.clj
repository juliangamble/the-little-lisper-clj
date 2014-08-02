(ns Ch06Ex06)

; Adding the nested numbers
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 6
; Exercise 6
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
(defn addvec [vec]
  (cond 
   (empty? vec) 0
   true (+ (first vec)(addvec (rest vec)))))

(println (addvec '(1 2 3)))
;6

(defn list+ [vec]
  (cond 
   (empty? vec) 0
   (list? (first vec))
    (+ (list+ (first vec))
       (list+ (rest vec)))
   true (+ (first vec)(list+ (rest vec)))))

(println (list+ '(1 2 3)))
;6
(println (list+ '(1 (2 (3)))))
;6
