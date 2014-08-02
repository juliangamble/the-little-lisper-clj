(ns Ch06Ex04)

; LISP asking three questions about the fried potatoes
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 6
; Exercise 4
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
;Function lat? from Chapter 2
(defn lat? [l]
  (cond
   (empty? l) true
   (not (list? (first l))) (lat? (rest l))
   true false))

(println (lat? '(bacon (and eggs))))
;F

(println (lat? '(bacon and eggs)))
;T

; Why does it have to ask three questions? (and not two like other functions in chapter 2)
;; The only other real function is member - which determines if a flat-list contains an atom or not
;; The first question is the termination of the recursion on the list which is being examined
;; The second question is the truth test - does this list contain atoms only
;; The third question is if this list contains a non-atom (ie a list) - then fail
;;; So the reason we ask three questions is because the definition of lat? is two-fold 
;;; (both contains atoms and doesn't contain lists)
; Why does lat not have to recur on the car?
;; Because lat? is asking the questions whether this is a 'flat list' ie "Is this is a list but not a list of lists."

