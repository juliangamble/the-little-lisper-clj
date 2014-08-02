(ns Ch05Ex02)

; Substituting two things for kiwis
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 5
; Exercise 2
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

(defn subst-sauce [ a lat]
  (cond
   (empty? lat) '()
   true (cond
       (= (first lat) 'sauce)
        (cons a (rest lat))
       true (cons (first lat)
                (subst-sauce
                 a (rest lat))))))

(defn subst2 [ new o1 o2 lat]
  (cond
  (empty? lat) '()
  true (cond 
      (= (first lat) o1)
       (cons new (rest lat))
      (= (first lat) o2)
       (cons new (rest lat))
      true (cons (first lat)
               (subst2 new 
                       o1 o2 (rest lat))))))

(def new1 'vanilla)
(def o11 'chocolate)
(def o21 'banana)
(def mylat '(banana ice cream with chocolate topping))

(println (subst2 new1 o11 o21 mylat))
;(vanilla ice cream with chocolate topping)

(defn multisubst2 [ new o1 o2 lat]
  (cond
  (empty? lat) '()
  true (cond 
      (= (first lat) o1)
       (multisubst2 new o1 o2 (cons new (rest lat)))
      (= (first lat) o2)
       (multisubst2 new o1 o2 (cons new (rest lat)))
      true (cons (first lat)
               (multisubst2 new 
                            o1 o2 (rest lat))))))

(println (multisubst2 x a b lat1))
;(bananas comma)
(println (multisubst2 y a b lat3))
;(dot pears dot bananas cherries)
(println (multisubst2 a x y lat1))
;(bananas kiwis)


