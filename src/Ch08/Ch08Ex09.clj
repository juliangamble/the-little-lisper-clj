(ns Ch08Ex09)

; Expression evaluators - Is your expression transitive?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 8
; Exercise 9
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

(defn union_ [set1 set2]
  (cond
   (empty? set1) set2
   (member? (first set1) set2)
    (union_ (rest set1) set2)
   true (cons (first set1)
            (union_ (rest set1) set2))))

(union_ '(tomatoes and marcaroni casserole) '(marcaroni and cheese))
;(TOMATOES CASSEROLE MARCARONI AND CHEESE)


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

(defn rapply [rel x]
  (cond
   (empty? rel) '()
   (= '() x) '()
   (and (rel? rel) (not (seq? x)))
    (cond
     (= (first_ (first rel)) x) 
      (cons (second_ (first rel)) (rapply (rest rel) x))
     true (rapply (rest rel) x))
   true '()))

(defn build [a b]
  (cons a (cons b '())))

(defn lat? [l]
  (cond
   (empty? l) true
   (not (seq? (first l))) (lat? (rest l))
   true false))

(defn rin [x set]
  (cond
   (= '() x) '()
   (empty? set) '()
   (lat? set)
    (cons (build x (first set))
          (rin x (rest set)))
   true '()))

(defn rcomp [rel1 rel2]
  (println "rcomp" rel1 rel2)
  (cond
   (empty? rel1) '()
   true (union_
       (rin
        (first_ (first rel1))
        (rapply rel2 (second_ (first rel1))))
       (rcomp (rest rel1) rel2))))

(defn subset? [set1 set2]
  (cond
   (empty? set1) true
   (member? (first set1) set2)
    (subset? (rest set1) set2)
   true false))

(defn transitive? [rel]
  (subset? (rcomp rel rel) rel))

(transitive? r1)
;T

(transitive? r3)
;T

(transitive? f1)
;T

;non transitive relation
(transitive? '((a b)(b d)))
;ie transforms to not nil and not something that is part of the parent relation

