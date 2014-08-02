(ns Ch06Ex08)

; What does this function f* do?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 6
; Exercise 8
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

(defn member* [a l]
  (cond
   (empty? l) false
   (not (list? (first l)))
    (or
     (= (first l) a)
     (member* a (rest l)))
   true (or 
       (member* a (first l))
       (member* a (rest l)))))


(defn f* [l acc]
  (cond 
   (empty? l) acc
   (not(list? (first l)))
    (cond
     (member* (first l) acc) (f* (rest l) acc)
     true (f* (rest l)(cons (first l) acc)))
   true (f* (first l)(f* (rest l) acc))))

(println (f* '(1 2 3 4 (4 5 3 2 1)) '()))
;This removes duplicates and returns list in reverse order without sublists

