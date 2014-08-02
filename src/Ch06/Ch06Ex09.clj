(ns Ch06Ex09)

; Can we accumulate the potatoes in this dish?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 6
; Exercise 9
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

(defn occur [a lat]
  (cond
   (empty? lat) 0
   true (cond
       (= (first lat) a)
        (add1 (occur a (rest lat)))
       true (occur a (rest lat)))))

(println (occur 'a '(a b c d c (b a))))

(defn occur [a lat acc]
  (cond
   (empty? lat) acc
   true (cond
       (= (first lat) a)
        (occur a (rest lat) (add1 acc))
       true (occur a (rest lat) acc))))

(println (occur 'a '(a b c d c (b a)) 0))

(defn occur [a lat acc]
  (cond
   (empty? lat) acc
   true (occur a (rest lat) 
             (cond 
              (= (first lat) a)
               (add1 acc)
              true acc))))

(println (occur 'a '(a b c d c (b a)) 0))

;The original value for acc is 0
