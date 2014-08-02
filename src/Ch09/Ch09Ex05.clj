(ns Ch09Ex05)

; Use a continuation function instead of an evaluator
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 9
; Exercise 5
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-9-lamdba-ultimate.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

;Note multi-subst is from chapter 5 exercises - accumulators in 6
;original
(def x 'comma)
(def y 'dot)
(def a 'kiwis)
(def b 'plums)
(def lat1 '(bananas kiwis))
(def lat3 '(kiwis pears plums bananas cherries))

(defn multisubst2 [new_ o1 o2 lat]
  (cond
  (empty? lat) ()
  true (cond 
      (= (first lat) o1)
       (multisubst2 new_ o1 o2 (cons new_ (rest lat)))
      (= (first lat) o2)
       (multisubst2 new_ o1 o2 (cons new_ (rest lat)))
      true (cons (first lat)
               (multisubst2 new_ 
                            o1 o2 (rest lat))))))

(println (multisubst2 x a b lat1))
;(bananas comma);

(println (multisubst2 y a b lat3))
;(dot pears dot bananas cherries)

(println (multisubst2 a x y lat1))
;(bananas kiwis)

(defn multisubst [new_ old lat]
  (cond
   (empty? lat) ()
   true (cond
       (= (first lat) old)
        (cons new_ 
              (multisubst 
               new_ old (rest lat)))
       true (cons (first lat)
                (multisubst
                 new_ old (rest lat))))))

(println (multisubst 'sandwich 'club '(sandwich club sandwich club)))
;(SANDWICH SANDWICH SANDWICH SANDWICH)

(defn multisubst-k [new_ old lat k]
  (cond
   (empty? lat) (k ())
   (= (first lat) old)
    (multisubst-k new_ old (rest lat)
                  (fn [d]
                    (k (cons new_ d))))
   true (multisubst-k new_ old (rest lat)
                    (fn [d]
                      (k (cons (first lat) d))))))

(println (multisubst-k 'sandwich 'club '(sandwich club sandwich club) (fn [x] x)))
;(SANDWICH SANDWICH SANDWICH SANDWICH)

(println (multisubst-k 'y 'x '(u v x x y z z) (fn [x] x)))
;(U V Y Y Y Z Z)

;Comparison of steps

; Things you need to do when you return from a recursive function 
; corresponding continuation function
; Instead of just returning a (quote()) function - you need to send the quote 
; function to the continuation - and let it return it
; otherwise you call the continuation to escape the recursion ?
; so instead of consing the result of the recursion on the parent function
; you're consing the results of the continuation...


