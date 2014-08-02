(ns Ch06Ex05)

; Fish and chips - finding the first nested occurrence
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 6
; Exercise 5
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

(println (member* 'chips '((potato) (chips ((with) fish) (chips)))))
;T

;(defn notatom (lat)
;  (not (atom lat)))

;version that starts at the end of the list
(defn member-backwards* [a l oldl]
  (cond
   (empty? l) (member* a oldl)
   (list? (first l))
             (or (member-backwards* a (rest l) oldl)
                   (member-backwards* a (first l) oldl))
   true 
    (member-backwards* a 
                       (rest l) 
                       (cons (first l) oldl))))

(println (member-backwards* 'chips '((potato) (chips ((with) fish) (chips))) '()))
;T
                                     
