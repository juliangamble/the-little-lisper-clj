(ns Ch03Ex02)

; hot chili - List of duplicates function
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 3
; Exercise 2
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
;(defn dupla [ a l]
;  (cond
;   (empty? l) '()
;   true (cons a (recur a (rest l)))))

(defn dupla [ a l acc] ;note - rewrite to use accumulator because can only recur from tail position
  (cond
   (empty? l) acc
   true  (recur a (rest l) (cons a acc))))
;See further discussion here: http://groups.google.com/group/clojure/browse_thread/thread/4e7a4bfb0d71a508
; probably a more idiomatic solution using apply exists

(dupla a2 l4 '())
(println (dupla a2 l4 '()))

(dupla a2 l2 '())
(println (dupla a2 l2 '()))

(dupla a1 l5 '())
(println (dupla a1 l5 '()))

