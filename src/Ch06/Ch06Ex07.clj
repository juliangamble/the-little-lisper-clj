(ns Ch06Ex07)

; What does this function g* do?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 6
; Exercise 7
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

(defn g* [lvec acc]
  (cond
   (empty? lvec) acc
   (not (list? (first lvec)))
    (g* (rest lvec)(+ (first lvec) acc))
   true (g* (first lvec)(g* (rest lvec) acc))))

(not(list? (first '(1))))
(g* '() 1)
(empty? '())
(println (g* '(1) 0))

(println (g* '(1 (2 (3))) 0))
;6
'This takes a list of numbers and adds them into the accumulator (acc)


