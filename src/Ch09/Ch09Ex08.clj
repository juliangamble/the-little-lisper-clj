(ns Ch09Ex08)

; Can you write your function as a thunk?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 9
; Exercise 8
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-9-lamdba-ultimate.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

(defn member? [a lat]
  (cond
   (empty? lat) false
   (= a (first lat)) true
   true
    (member? a (rest lat))))

(println (member? 'x '(a b c d e f)))
(println (member? 'f '(a b c d e f)))

(defn subset? [lat lat2]
  (cond
   (empty? lat) true
   true
    (and (member? (first lat) lat2)
         (subset? (rest lat) lat2))))

(println (subset? '(a b) '(a c d)))
(println (subset? '(a c) '(a c d)))

(defn intersect? [lat lat2]
  (cond
   (empty? lat) true
   true
    (or (member? (first lat) lat2)
         (intersect? (rest lat) lat2))))

(println (intersect? '(a b) '(a c d)))
(println (intersect? '(a c) '(a c d)))

(defn or-func [or1 or2]
      (or (or1)(or2)))

(defn and-func [or1 or2]
      (and (or1)(or2)))

;(setf (symbol-function 'set-f?)
;  (lambda (current-function)
;  (function 
;   (lambda (cond1 cond2)
;     (funcall current-function cond1 cond2)))))
(println (or-func (fn [] (= 1 1)) (fn []  (= 1 2))))


;(println (funcall (set-f? 'or-func) (lambda() (= 1 1)) (lambda() (= 1 2))))
;T

(println (or-func (fn [] (= 1 1)) (fn []  (= 1 2))))
;(println (or-func (lambda() (= 1 1)) (lambda() (= 1 2))))
;T

;(println (funcall (set-f? 'and-func) (lambda() (= 1 1)) (lambda() (= 1 2))))
;NIL false

(println (and-func (fn [] (= 1 1)) (fn []  (= 1 2))))
;(println (and-func (lambda() (= 1 1)) (lambda() (= 1 2))))


(defn intersect-func? [lat lat2]
  (cond
   (empty? lat) true
   true
    (or-func (fn [] (member? (first lat) lat2))
             (fn [] (intersect-func? (rest lat) lat2)))))

(println (intersect-func? '(a) '(a)))
; T

(println (intersect-func? '(a b) '(a c d)))
; T

(println (intersect-func? '(a c) '(a c d)))
; T

(defn subset-func? [lat lat2]
  (cond
   (empty? lat) true
   true
    (and-func (fn [] (member? (first lat) lat2))
         (fn [] (subset-func? (rest lat) lat2)))))

(println (subset-func? '(a b) '(a c d)))
; NIL false

(println (subset-func? '(a c) '(a c d)))
; T

