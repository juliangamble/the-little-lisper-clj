(ns Ch05Ex09)

; Curry chicken - How to de-nest this list
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 5
; Exercise 9
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

(println (list? (list a)))

;(not not (list? (list a)))
;validate that in this implementation a list is not an atom

(defn listlength [lat]
  (cond
   (not (list? lat)) 0
   (empty? lat) 0
   true (+ 1 (listlength (rest lat)))))

(println (listlength (list 'a 'b 'c 'd 'e)))
(println (listlength '()))


(defn up [l] 
  (cond
   (empty? l) '()
   true (cond
       (= 1 (listlength (first l)))
        (cons (first (first l)) (rest l))
       (= '() (first l))
        (rest l)
       true (cons (first l) (up (rest l))))))

(println (up (list 'a 'b (list 'c) 'd 'e)))
;flatten this list with a single atom nested list
(println (up (list 'a 'b '() 'd 'e)))


(defn multiup [l] 
  (cond
   (empty? l) '()
   true (cond
       (= 1 (listlength (first l)))
        (cons (first (first l)) (multiup (rest l)))
       (= '() (first l))
        (multiup (rest l))       
       true (cons (first l) (multiup (rest l))))))

(println (multiup (list 'a 'b (list 'c) 'd (list 'e))))
(println (multiup '(a b (c)(d)())))

(println (multiup l4))
;NIL
(println (multiup l1))
;(CURRY CHICKEN)
(println (multiup l2))
;(PEACHES (AND CREAM))
