(ns Ch08Ex01)

; Expression evaluators - Is this an identity relation?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 8
; Exercise 1
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-8-friends-and.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def r1 '((a b)(a a)(b b)))
(def r2 '((c c)))
(def r3 '((a c)(b c)))
(def r4 '((a b)(b a)))
(def f1 '((a 1)(b 2)(c 2)(d 1)))
(def f2 '())
(def f3 '((a 2)(b 1)))
(def f4 '((1 $)(3 *)))
(def d1 '(a b)) 
(def d2 '(c d))
(def x 'a)
; ------------------------------

(defn member? [a lat]
  (cond
   (empty? lat) false
   true (or
       (= (first lat) a)
       (member? a (rest lat)))))

(defn makeset [lat]
  (cond 
   (empty? lat) '()
   (member? (first lat) (rest lat))
    (makeset (rest lat))
   true (cons (first lat)
            (makeset (rest lat)))))

(makeset '(a b c d e d c b a))
;(E D C B A)

(defn notatom [lat]
  ;(not (atom lat)))
  (list? lat))

(defn flatten [lat acc]
  (cond
   (empty? lat) acc
   (notatom (first lat))
    (flatten (first lat) (flatten (rest lat) acc))
   true (flatten (rest lat) (cons (first lat) acc))))

(flatten '(a b c (d e f)) '())
;(F E D C B A)

(flatten r3 '())
;(C A C B);

(flatten '((a c)(b c)) '())
;(C A C B)

(defn domset [rel]
  (cond
   (empty? rel) '()
   true (makeset (flatten rel '()))))

(domset r1)
;(a b)

(domset r2)
;(c)

(domset r3)
;(A C B)

(defn build [a b]
  (cons a (cons b '())))

(build 'a 'b)
;(A B)
  
(defn idrel [s]
  (cond
   (empty? s) '()
   true (cons (build (first s) (first s))
            (idrel (rest s)))))

(println (idrel '(a b c)))
;((A A) (B B) (C C))
