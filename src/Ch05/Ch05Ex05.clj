(ns Ch05Ex05)

; Finding the common ingredients
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 5
; Exercise 5
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


(defn I_ [ a_ lat2_]
  (cond
   ;(empty? a_) '()
   (empty? lat2_) '()
   true (cond
       (= (first lat2_) a_)
        a_
       true (I_ a_ (rest lat2_)))))

(println (I_ 'bananas (list 'bananas 'peaches)))
(println (I_ 'apples (list 'bananas 'peaches)))

(defn I_exists [ a_ lat2_]
  (cond
   ;(empty? a_) '()
   (empty? lat2_) false
   true (cond
       (= (first lat2_) a_)
        true
;       true (I_ a_ (rest lat2_)))))
    true (I_exists a_ (rest lat2_)))))

(println (I_exists 'bananas (list 'bananas 'peaches)))
(println (I_exists 'apples (list 'bananas 'peaches)))


(defn I [ lat1_ lat2_]
  (cond
   (empty? lat1_) '()
   (empty? lat2_) '()
  true (cond
      (I_exists (first lat2_) lat1_  )
       (I_  (first lat2_) lat1_ )
      true (I (rest lat2_) lat1_  ))))

(println (I (list  'apples 'bananas) (list 'bananas 'peaches)))
(println (I (list  'apples 'bananas 'pears) (list 'bananas 'peaches 'pears)))

(defn multiI [ lat1_ lat2_]
  (cond
      (empty? lat1_) '()
   (empty? lat2_) '()
   true (cond
       (I_exists (first lat2_) lat1_ )
        (cons (first lat2_) (multiI lat1_ (rest lat2_)))
       true (multiI lat1_ (rest lat2_)))))

(I_exists 'pears (list 'bananas 'pears 'peaches))
(I_exists 'apples (list 'bananas 'pears 'peaches))

(println (multiI (list  'apples 'bananas 'pears) (list 'bananas 'peaches 'pears)))

(println (I lat1 l4))
;()
(println (I lat1 lat2))
;bananas
(println (I lat1 lat3))
;kiwis
(println (multiI lat1 l4))
;()
(println (multiI lat1 lat2))
;(bananas)
(println (multiI lat1 lat3))
;(kiwis bananas)



