(ns Ch05Ex08)

; Broken count function - can you fix it?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 5
; Exercise 8
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-5-multichapter.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def x 'comma)
(def y 'dot)
(def a 'kiwis)
(def b 'plums)
(def lat1 '(bananas kiwis))
(def lat2 '(peaches apples bananas))
(def lat3 '(kiwis pears plums bananas cherries))
(def lat4 '(kiwis mangoes kiwis guavas kiwis))
(def l1 '((curry) () (chicken) ()))
(def l2 '((peaches) (and cream)))
(def l4 '())
; ------------------------------

(defn zero [n]
  (= 0 n))

;Incorrect version
(defn count0_ [vec]
  (cond
   (empty? vec) '( 1)
   true (cond 
       (zero (first vec))
        (cons 0 (count0_ (rest vec)))
       true (count0_ (rest vec)))))

(empty? (list 0 0 0 0))
(zero (first (list 0 0 0 0)))
(println (count0_ (list 0 0 0 0)))

;correct version
(defn count0 [vec]
  (cond
   (empty? vec) 0
   true (cond 
       (zero (first vec))
        (+ 1 (count0 (rest vec)))
       true (count0 (rest vec)))))

(println (count0 (list 0 0 0 0)))
;4
