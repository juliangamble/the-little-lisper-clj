(ns Ch06Ex03)

; Double frying the baked tomatoes (nested atom duplication)
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 6
; Exercise 3
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

(defn double_ [a lat]
  (cond
   (empty? lat) '()
   (= (first lat) a)(cons a lat)
   true (cons (first lat)
            (double_ a (rest lat)))))

(println (double_ 'bob '(bob the builder)))

(defn double* [a lat]
  (cond
   (empty? lat) '()
   (list? (first lat))
    (cons (double* a (first lat))
          (double* a (rest lat)))
   (= (first lat) a)(cons a lat)
   true (cons (first lat)
            (double* a (rest lat)))))

(println (double* 'bob '(bob the builder)))
;(BOB BOB THE BUILDER)

(println (double* 'bob '((bob) the builder)))
;((BOB BOB) THE BUILDER)

(println (double* a l1))
;((FRIED FRIED POTATOES) (BAKED (FRIED FRIED)) TOMATOES)

(println (double* a l2))
;(((CHILI) CHILI (CHILI)))

(println (double* a lat2))
;(BAKED FRIED FRIED)
