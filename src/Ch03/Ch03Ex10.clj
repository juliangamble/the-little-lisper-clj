(ns Ch03Ex10)

; Are there some common ingredients in insert and subst?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 3
; Exercise 10
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

(defn rember [a lat]
  (cond
   (empty? lat) '()
   (= (first lat) a)(rest lat)
   true (cons (first lat)
            (rember a (rest lat)))))

(defn insertR [new old lat];COMMON 1
  (cond;COMMON 1
   (empty? lat) '();COMMON 1
   true (cond;COMMON 1
       (= (first lat) old);COMMON 1
        (cons old
              (cons new (rest lat)))
       true (cons (first lat);COMMON 2
                (insertR;COMMON 2
                 new old (rest lat))))));COMMON 2

(defn insertL [new old lat];COMMON 1
  (cond;COMMON 1
   (empty? lat) '();COMMON 1
   true (cond;COMMON 1
       (= (first lat) old);COMMON 1
        (cons new
              (cons old (rest lat)))
       true (cons (first lat);COMMON 2
                (insertL;COMMON 2
                 new old (rest lat))))));COMMON 2

(defn subst_ [new old lat];COMMON 1
  (cond;COMMON 1
   (empty? lat) '();COMMON 1
   true (cond;COMMON 1
       (= (first lat) old);COMMON 1
        (cons new (rest lat))
       true (cons (first lat);COMMON 2
                (subst_;COMMON 2
                 new old (rest lat))))));COMMON 2




