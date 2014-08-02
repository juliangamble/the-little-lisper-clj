(ns Ch08Ex03)

; Expression evaluators - Is this symmetric?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 8
; Exercise 3
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

(defn member* [a l]
  (cond
   (empty? l) false
   (not (seq? (first l)))
    (or
     (= (first l) a)
     (member* a (rest l)))
   true (or 
       (member* a (first l))
       (member* a (rest l)))))

(member* 'chips '((potato) (chips ((with) fish) (chips))))
;T

(member* 'fries '((potato) (chips ((with) fish) (chips))))
;NIL false

(defn lat? [l]
  (cond
   (empty? l) true
   (not (seq? (first l))) (lat? (rest l))
   true false))
(lat? '(bacon (and eggs)))
;NIL

(lat? '(bacon and eggs))
;T

(defn eqlat? [lat1 lat2]
  (cond
   (and
    (empty? lat1)
     (empty? lat2)) true
   (empty? lat1) false
   (empty? lat2) false
   (not (lat? lat1)) false
   (not (lat? lat2)) false
   (= (first lat1) (first lat2))
        (eqlat? (rest lat1) (rest lat2))
   true false))

(eqlat? '(a b) '(a b))
;T

(eqlat? '(a b) '(a a))
;NIL false

(eqlat? '(a b) '(a b c))
;NIL false

(eqlat? '(a b) '(a (b)))
;NIL false

(defn non-atom? [a]
  (seq? a))

(non-atom? 'a)
;NIL false

(non-atom? '(a))
;T

(defn eqlist? [l1 l2]
  (println "eqlist?" l1 l2)
  (cond
   (and (= '() l1) (= '() l2)) true
   (or (= '() l1) (= '() l2)) false
   (and (not (seq? l1)) (seq? l2)) false
   (and (seq? l1) (not (seq? l2))) false
   (and (not (seq? l1)) (not (seq? l2)))
        (= l1 l2)
   true (and
       (eqlist? (first l1) (first l2))
       (eqlist? (rest l1) (rest l2)))))

(and (not (seq? 'a)) (not (seq? 'a)))
(eqlist? 'a 'a)

(eqlist? '((a b)(c d)) '((a b)(c d)))
;T

(eqlist? '((a b)(c d)) '((a b)(c e)))
;NIL false

;(defn equal? [s1 s2]
;  (cond
;   ((and (not (seq? s1) (not (seq? s2))
;        (= s1 s2))
;   ((and
;     (non-atom? s1)
;     (non-atom? s2))
;    (eqlist? s1 s2))
;    true false))

;(equal? 'a 'a)
;T

;(equal? '(a) '(a))
;T  

(defn member_ [lista listb]
  (println "member_" lista listb)
  (cond
   (= '() lista) true
   (= '() listb) false
   (not (seq? listb))
    (= lista listb)
   (not (seq? lista))
    (member* lista listb)
   (eqlist? lista listb) true
   true (or (member_ lista (first listb))
       (member_ lista (rest listb)))))
   ;true false))

(eqlist? '(with) 'potato)   
   
(member_ '((with) fish) '((potato) (chips ((with) fish) (chips))))
;T

(member_ '(chips) '((potato) (chips ((with) fish) (chips))))
;T

(member_ '((a a)) '((a b)(a a)))
;T

(member_ '((a c)) '((a b)(a a)))
;NIL false

(member_ '(a a) '((a b)(a a)))
;T

(defn subset? [set1 set2]
  (cond
   (empty? set1) true
   (member_ (first set1) set2)
    (subset? (rest set1) set2)
   true false))

(subset? '(b) '(c b))
;T

(subset? '((a b)) '((a a)(a b)))
;T

(subset? '((a a)(a b)) '((a a)(a b)(a c)(a d)))
;T

(defn eqset? [set1 set2]
  (and
   (subset? set1 set2)
   (subset? set2 set1)))

(eqset? '((a b)) '((a b)))
;T

(defn build [a b]
  (cons a (cons b '())))

(defn revrel [rel]
  (cond
   (empty? rel) '()
    true (cons 
       (build
        (second (first rel));need to check this
        (first (first rel)));need to check this
       (revrel (rest rel)))))

(revrel '((a b)(c d)))
;((B A) (D C))

(revrel r1)
;((B A) (A A) (B B))

(defn symmetric? [rel]
  (eqset? rel (revrel rel)))

(symmetric? r1)
;NIL false

(symmetric? r2)
;T

(symmetric? f2)
;T

(defn intersect [set1 set2]
  (cond
   (empty? set1) '()
   (not (member_ (first set1) set2))
    (intersect (rest set1) set2)
   true (cons (first set1)
            (intersect (rest set1) set2))))
  
(intersect '((a a)(a b)(b b)) '((a a)(b b)))
;((A A) (B B))

(intersect '(a b) '(a b c))
;(A B)

(defn idrel [s]
  (cond
   (empty? s) '()
   true (cons (build (first s) (first s))
            (idrel (rest s)))))

(defn flatten [lat acc]
  (cond
   (empty? lat) acc
   (seq? (first lat))
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


(defn antisymetric? [rel]
  (subset? (intersect (revrel rel) rel) (idrel (domset rel))))

(revrel r1)
;((B A) (A A) (B B))

(intersect (revrel r1) r1)
;((A A) (B B))
  
(antisymetric? r1)
;T

(antisymetric? r2)
;T

(antisymetric? r4)
;NIL false

(defn asymmetric? [rel]
  (empty? (intersect rel (revrel rel))))

(println (asymmetric? r1))
;NIL false

(println (asymmetric? r2))
;NIL false

(println (asymmetric? r3))
;T

(println (asymmetric? r4))
;NIL false

(println (asymmetric? f1))
;T

(println (asymmetric? f2))
;T

(println (asymmetric? f3))
;T

(println (asymmetric? f4))
;T

; assymetric - contains no matching pairs in the relation
; For all a and b in X, if a is related to b, then b is not related to a.


