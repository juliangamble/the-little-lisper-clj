(ns Ch08Ex02)

; Expression evaluators - Is this reflexive?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 8
; Exercise 2
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

(defn notatom [lat]
  ;(not (atom lat)))
  ;(list? lat))
  (seq? lat))


(defn flatten [lat acc]
  (cond
   (empty? lat) acc
   (notatom (first lat))
    (flatten (first lat) (flatten (rest lat) acc))
   true (flatten (rest lat) (cons (first lat) acc))))

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

(defn domset [rel]
  (cond
   (empty? rel) '()
   true (makeset (flatten rel '()))))


(defn build [a b]
  (cons a (cons b '())))

(defn idrel [s]
  (cond
   (empty? s) '()
   true (cons (build (first s) (first s))
            (idrel (rest s)))))


(idrel (domset r1))
;((A A) (B B))

(idrel (domset r2))
;((C C))

(idrel (domset r3))
;((A A) (C C) (B B))

(member? 'a '(a b c))
;T

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

(first_ '(a b))
;A

(second_ '(c d))
;D

(third_ '(a b c))
;C

(defn notlist? [a]
  (println "notlist?" a)
  (not (seq? a))) ;note seq? instead of list?

(notlist? '((a a) (b b)))

(defn pair? [lat]
  (println "pair?" lat)
  (cond
   (= '() lat) false
   (notlist? lat) false
   (and  (not (= (first_ lat) nil))
              (not (= (second_ lat) nil))
          (= (third_ lat) nil)) true
   true false))

(first_ '(a b))
(second_ '(a b))
(third_ '(a b))
(pair? '(a b))
;T

(pair? 'a)
;NIL false

(pair? '(a b c))
;NIL false


(defn rel? [rel]
  (println "rel?" rel)
  (cond
   (empty? rel) true
   (notlist? rel) false
   (pair? (first rel))
    (rel? (rest rel))
   true nil))

(rel? '((a b)))
;T

(rel? '((a a)(a b)))
;T 

(rel? '(a b))
;NIL false

(rel? '((a b c)))
;NIL false

(rel? '((a b) c))
;NIL

(defn eq-pair [pair-a pair-b]
  (println "eq-pair" pair-a pair-b)
  (cond
   (= '() pair-a) false
   (= '() pair-b) false
   (not (seq? pair-a)) false ;note seq? instead of list?
   (not (seq? pair-b)) false
   (not (pair? pair-a)) false
   (not (pair? pair-b)) false
   (and (= (first_ pair-a) 
             (first_ pair-b))
         (= (second_ pair-a)
             (second_ pair-b))) true
   true false))

(eq-pair '(a b) '(a b))
;T

(eq-pair '(a a) '(b b))
;NIL false

(eq-pair '(a b c) '(a b c))
:NIL false

(eq-pair '(a a) 'a)
;false

(defn member-pair? [pair rel]
  (println "member-pair?" pair rel)
  (cond
   (empty? pair) true
   (empty? rel) false
   (not(pair? pair)) false
   (not(rel? rel)) false
   (eq-pair (first rel) pair) true   
   true (member-pair? pair (rest rel))))
   
(member-pair? '(a b) '(a b c))
;NIL false

(member-pair? '(a d) '(a b c))
;NIL false

(member-pair? '(a a) '((a a)(a b)))
;T

(member-pair? '(a a) '((a b)(a a)))
;

(defn member-rel? [rel1 rel2]
  (println "member-rel?" rel1 rel2)
  (cond
   (empty? rel1) true
   (empty? rel2) false
   (not (rel? rel1)) false
   (not (rel? rel2)) false
   (member-pair? (first rel1) rel2)    
    (member-rel? (rest rel1) rel2)
   true false))

(member-rel? '((a a)) '((a b)(a a)))
;T

(member-rel? '((a a)) '((a b)(a c)))
;NIL false

(member-rel? '((a)) '((a b)(a a)))
;NIL false

(member-rel? '((a)) '((a b)(a)))
;NIL false

(member-rel? '(a a) '((a b)(a a)))
;NIL false

(member-rel? '((a a)) '(a b))
;NIL false

(defn reflexive? [lat]
  (cond
   (empty? lat) false
   true (member-rel? (idrel (domset lat)) lat)))

;(def r1 '((a b)(a a)(b b)))
(domset r1)
; (a b)

(idrel '(a b))
; ((a a) (b b))

(idrel (domset r1))
; ((a a) (b b))

(println '((a a) (b b)))

(notlist? '((a a) (b b)))
(notlist? (idrel (domset r1)))

(pair? '(a a))

(rel? '((a a) (b b)))
(rel? (idrel '(a b)))
(rel? (idrel (domset r1)))
(println r1)
(member-rel? '((a a) (b b)) '((a b) (a a) (b b)))
(member-rel? (idrel (domset r1)) r1)
;T 

(println (reflexive? r1))
;T

(println (reflexive? r2))
;T

(println (reflexive? r3))
;NIL false
