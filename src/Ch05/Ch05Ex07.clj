(ns Ch05Ex07)

; Are these equal? Does it break the law?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 5
; Exercise 7
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

; Because the changing argument should be tested in the terminating condition - and in this case it is not
; In this case the other argument is tested as the terminating condition - which then triggers a test
; of the recurring agument 

(defn zero [n]
  (= 0 n))

(defn sub1 [n] 
  (- n 1))

(defn =_ [ n m]
  (cond
   (zero n) (zero m)
   true (= n (sub1 m))))

(println (=_ 1 2))

; In the default implementation - this implementation doesn't actually work - we assume they meant this:
(defn =_ [ n m]
  (cond
   (zero n) (zero m)
   true (= m (sub1 n))))

(println (=_ 1 2))

;In this case - the recurring argument is not directly tested for a null condition, but it is triggered when
;recurring as the arguments are flipped around - in which case it is in the spirit of the the sixth commandment


