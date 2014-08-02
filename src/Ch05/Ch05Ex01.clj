(ns Ch05Ex01)

; Substituting the bananas for kiwis
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 5
; Exercise 1
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

(def a1 'chili)
(def a4 'sauce)
(def l4 '(texas hot chili))
(def l5 '(soy sauce and tomato sauce))
(def l2 '())

(defn multisubst-kiwis [ a lat]
  (cond
   (empty? lat) '()
   true (cond
       (= (first lat) 'kiwis)
        (cons a (multisubst-kiwis a (rest lat)))
       true (cons (first lat)
                (multisubst-kiwis
                 a (rest lat))))))

(println (multisubst-kiwis  b lat1))
;(bananas plums)
(println (multisubst-kiwis  y lat2))
;(peaches apples bananas)
(println (multisubst-kiwis  y lat4))
;(dot mangoes dot guavas dot)
(println  (multisubst-kiwis  y l4))
;(texas hot chilli)


