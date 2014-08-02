(ns Ch09Ex02)

; ycombinator - Apply this fn to this pair in the list
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 9
; Exercise 2
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-9-lamdba-ultimate.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(defn first_ [l]
  (println "first_" l)
  (cond
   (empty? l) '()
   true (first l)))

(defn second_ [l]
  (println "second_" l)
  (cond
   (empty? l) '()
   true (first (rest l))))

(defn third_ [l]
  (println "third_" l)
  (cond
   (empty? l) '()
   true (first (rest (rest l)))))


(defn pair? [lat]
  (println "pair?" lat)
  (cond
   (= '() lat) false
   (not (seq? lat)) false
   (and  (not (= (first_ lat) nil))
              (not (= (second_ lat) nil))
          (= (third_ lat) nil)) true
   true false))


(defn eq-pair-first [pair-a-first pair-b]
  (println "eq-pair-first" pair-a-first pair-b)
  (cond
   (= () pair-a-first) false
   (empty? pair-b) false
   (not(atom pair-a-first)) false
   (not(seq? pair-b)) false
   (not (pair? pair-b)) false
   (= pair-a-first 
             (first_ pair-b)) true
   true false))

(println (eq-pair-first 'a '(a b)))
;T

(println (eq-pair-first 'a '(b a)))
;NIL false


(defn rel? [rel]
  (cond
   (empty? rel) true
   (not(seq? rel)) false
   (pair? (first rel))
    (rel? (rest rel))
   true false))

(println (rel? '((a b)(a c)(a a))))
;T

(defn exists? [first__ rel]   
  (cond
   (= () first__) true
   (empty? rel) false
   (not(rel? rel)) false
   (eq-pair-first first__ (first rel)) true   
   true (exists? first__ (rest rel))))

(println (exists? 'a '((a b)(a c)(a a))))
;T

(println (exists? 'd '((a b)(a c)(a a))))
;NIL false
   
(defn find-match [a l]
  (println "find-match" a l)
  (cond
   (= () a) ()
   (= () l) ()
   (not(rel? l)) ()
   (eq-pair-first a (first l) ) (first l)   
   true (find-match a (rest l))))
;     true (recur a (rest l)))

(println (eq-pair-first 'a '(a a)))
(println (find-match 'a '((a b)(a c)(a a))))
;(A B)

(defn assq-sf [a l sk fk]
  (cond
   (exists? a l)
    (sk (find-match a l))
   true (fk a)))

(defn build [a b]
  (cons a (cons b '())))

(defn add1 [n] 
  (+ 1 n))

(def a 'apple)
(def b1 '())
(def b2 '((apple 1)(plum 2)))
(def b3 '((peach 3)))
;(def sk (lambda (p)(build (first p) (add1 (second p)))))
(def sk (fn [p](build (first p) (add1 (second p)))))
;(def fk (lambda  (name) (cons name (quote (not-in-list)))))
(def fk (fn  [name] (cons name (quote (not-in-list)))))

(println (assq-sf a b1 sk fk))
;(APPLE NOT-IN-LIST)

(println (assq-sf a b2 sk fk))
;(APPLE 2)

(println (assq-sf a b3 sk fk))
;(APPLE NOT-IN-LIST)
