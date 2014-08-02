(ns Ch08Ex10)

; Expression evals - Is your expression partial order?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 8
; Exercise 10
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

(defn member-pair? [pair rel]
  (println "member-pair?" pair rel)
  (cond
   (empty? pair) true
   (empty? rel) false
   (not(pair? pair)) false
   (not(rel? rel)) false
   (eq-pair (first rel) pair) true   
   true (member-pair? pair (rest rel))))

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

(defn idrel [s]
  (cond
   (empty? s) '()
   true (cons (build (first s) (first s))
            (idrel (rest s)))))

(defn notlist? [a]
  (println "notlist?" a)
  (not (seq? a))) ;note seq? instead of list?

(defn pair? [lat]
  (println "pair?" lat)
  (cond
   (= '() lat) false
   (notlist? lat) false
   (and  (not (= (first_ lat) nil))
              (not (= (second_ lat) nil))
          (= (third_ lat) nil)) true
   true false))

(defn rel? [rel]
  (println "rel?" rel)
  (cond
   (empty? rel) true
   (notlist? rel) false
   (pair? (first rel))
    (rel? (rest rel))
   true nil))



(defn reflexive? [lat]
  (cond
   (empty? lat) false
   true (member-rel? (idrel (domset lat)) lat)))

(defn quasi-order? [rel]
  (and (reflexive? rel) (transitive? rel)))

(quasi-order? r1)
;T

(quasi-order? r3)
;NIL false

(defn intersect [set1 set2]
  (cond
   (empty? set1) '()
;   (not (member_ (first set1) set2));
   (not (member? (first set1) set2));  
    (intersect (rest set1) set2)
   true (cons (first set1)
            (intersect (rest set1) set2))))

(defn revrel [rel]
  (cond
   (empty? rel) '()
    true (cons 
       (build
        (second (first rel))
        (first (first rel)))
       (revrel (rest rel)))))

(defn antisymetric? [rel]
  (subset? (intersect (revrel rel) rel) (idrel (domset rel))))


(defn partial-order? [rel]
  (and (quasi-order? rel) (antisymetric? rel)))

(partial-order? r1)
;T

(partial-order? r3)
;NIL false

(defn eqset? [set1 set2]
  (and
   (subset? set1 set2)
   (subset? set2 set1)))

(defn symmetric? [rel]
  (eqset? rel (revrel rel)))

(defn equivalence? [rel]
  (and (quasi-order? rel) (symmetric? rel)))

(equivalence? r1)
;NIL false

(equivalence? r2)
;T

