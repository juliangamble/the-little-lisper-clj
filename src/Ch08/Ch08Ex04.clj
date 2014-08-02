(ns Ch08Ex04)

; Expression evalutors - What is value of f at x?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 8
; Exercise 4
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

(defn fapply [f x]
  (cond
   (empty? f) false
   (= '() x) false
   (and (rel? f) (not (seq? x)))
    (cond
     (= (first_ (first f)) x) (second_ (first f))
     true (fapply (rest f) x))
   true false))

(println (fapply '((a 1)(b 2)) 'b))
;2

(println (fapply f1 'x))
;book incorrect - presume they mean 'a - merely a typo
;NIL no  answer

(println (fapply f2 'x))
;NIL no  answer

(println (fapply f3 'x))
;NIL no  answer

(println (fapply f1 'a))
;1

(println (fapply f2 'a))
;NIL no answer

(fapply f3 'a)
;2
