(ns Ch08Ex05)

; Expression evaluators - Compose function f and g?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 8
; Exercise 5
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

(defn rel? [rel]
  (println "rel?" rel)
  (cond
   (empty? rel) true
   (not (seq? rel)) false
   (pair? (first rel))
    (rel? (rest rel))
   true nil))

(defn build [a b]
  (cons a (cons b '())))

(defn fapply [f x]
  (cond
   (empty? f) '()
   (= '() x) '()
   (and (rel? f) (not (seq? x)))
    (cond
     (= (first_ (first f)) x) (second_ (first f))
     true (fapply (rest f) x))
   true '()))

(defn fcomp-pair [rel1 pair]
  (println "fcomp-pair" rel1 pair)
  (cond
   (empty? rel1) '()
   (empty? pair) '()
   (and (rel? rel1) (pair? pair))
    (cond 
     (not (= '() (fapply rel1 (second_ pair)))) 
      (build (first_ pair) (fapply rel1 (second_ pair)))
     true '())
   true '()))


(fcomp-pair '((a b)(c d)) '(a c))
;(a d)

(fcomp-pair '((a b)(c d)) '(a b))
;NIL

(defn fcomp [rel1 rel2]
  (println "fcomp" rel1 rel2)
  (cond
   (empty? rel1) '()
   (= '() rel2) '()
   (and (rel? rel1) (rel? rel2))
    (cond
     (not (= '() (fapply rel1 (second_ (first rel2)) )))
      (cons (fcomp-pair rel1 (first rel2))
            (fcomp rel1 (rest rel2)))
     true (fcomp rel1 (rest rel2)))
   true false))

(println (second_ (first '((b c)(d e)))))
;C

(println (fapply  '((a b)(c d)) 'c))
;D

(println (fapply '((a b)(c d)) (second_ (first '((b c)(d e))))))
;D

(println (fcomp '((y z)) '((x y))))
; ((X Z))

(println (fcomp '((b c)(d e)) '((a b)(c d))))
;((A C) (C E))

(println (fcomp f1 f4))
;NIL

(println (fcomp f1 f3))
;NIL

(println (fcomp f4 f1))
;((A $) (D $))

(println (fcomp f4 f3))
;((B $))
