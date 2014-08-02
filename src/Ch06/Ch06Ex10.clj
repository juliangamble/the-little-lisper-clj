(ns Ch06Ex10)

; Can we accumulate the (nested) potatoes in this dish?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 6
; Exercise 10
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

(defn add1 [a]
  (+ 1 a))

(defn occur* [a lat acc]
  (cond 
   (empty? lat) acc
   (list? (first lat))
    (occur* a (rest lat) (occur* a (first lat) acc))
   true (cond
       (= (first lat) a)
        (occur* a (rest lat) (add1 acc))
       true (occur* a (rest lat) acc))))

(println (occur* 'a '(a b c d c (b a)) 0))
;2


