(ns Ch02Ex02)

; (poppy seed cake) - How LISP know it’s a List of AToms?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 2
; Exercise 2
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-2-do-it-do-it.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def l1 '(german chocolate cake))
(def l2 '(poppy seed cake))
(def l3 '((linzer) (torte)()))
(def l4 '((bleu cheese)(and)(red)(wine)))
(def l5 '(()()))
(def a1 'coffee)
(def a2 'seed)
(def a3 'poppy)

(defn lat? [l] 
  (cond
   (empty? l) true
   (not (list? (first l))) (recur (rest l)) ; note replacement of lat? with recur
   true false))
; ------------------------------
; The function asks the following questions:
(empty? l1); false
(println (empty? l1))

(atom (first l1)); true
(println (atom (first l1)))

(lat? (rest l1));  true (through the recursive steps - final empty list returns null true)
(println (lat? (rest l1)))
; so (lat? l1) returns true

; You can see this as
(empty? '(german chocolate cake)); false
(println (empty? '(german chocolate cake)))

(atom (first '(german chocolate cake))); true
(println (atom (first '(german chocolate cake))))

(rest '(german chocolate cake)) ; (chocolate cake)
(println (rest '(german chocolate cake)))

(empty? '(chocolate cake)); false
(println (empty? '(chocolate cake)))

(atom (first '(chocolate cake))); true
(println (atom (first '(chocolate cake))))

(rest '(chocolate cake));  (cake)
(println (rest '(chocolate cake)))

(empty? '(cake)); false
(println (empty? '(cake)))

(atom (first '(chocolate cake))); true
(println (atom (first '(chocolate cake))))

(rest '(cake)); '()
(println (rest '(cake)))

(empty? '()); true => it is a List ATom
(println (empty? '()))

; The function asks the following questions:
(empty? l2); false
(println (empty? l2))

(atom (first l2)); true
(println (atom (first l2)))

(lat? (rest l2));  true
(println (lat? (rest l2)))
; so (lat? l2) returns true

; The function asks the following questions:
(empty? l3); false
(println (empty? l3))

(atom (first l3)); false - since a nest list not a list of atoms
(println (atom (first l3)))

;(true '());  true
; so (lat? l3) returns true

(def member?
(fn [a sob]
(cond
(empty? sob) false
true (or
(= (first sob) a)
(member? a (rest sob)))) ))

(def member?
(fn [a sob]
(cond
(empty? sob) false
true (or
(= (first sob) a)
(member? a (rest sob))))))

(def  sob?
(fn [s]
(cond
(empty? s) true
(symbol? (first s)) (sob? (rest s))
true false)))

(def  sob?
(fn [s]
(cond
(empty? s) true
(symbol? (first s)) (sob? (rest s))
true false)))

(def member?
(fn [a sob]
(cond
(empty? sob) false
true (or
(= (first sob) a)
(member? a (rest sob))))))

(def rember?
(fn [a sob]
(cond
(empty? sob) () 
true (cond
(= (first sob) a) (rest sob)
true (rember a (rest sob))))))

(def rember
(fn [a sob]
(cond
(empty? sob) ()
true (cond
(= (first sob) a) (rest sob)
true (cons (first sob)
(rember
a (rest sob)))))))

(def rember
(fn [a sob]
(cond
(empty? sob) ( )
(= (first sob) a) (rest sob)
true (cons (first sob)
(rember a (rest sob))))))

(def insertR
(fn [new old sob]
(cond
(empty? sob) ( )
true (cond 
(= (first sob) old) (rest sob)
true (insertR 
new old (rest sob))))))


(def insertR
(fn [new old sob]
(cond
(empty? sob) ( )
true (cond 
(= (first sob) old) 
 (cons new (rest sob))
true (cons (first sob)
(insertR 
new old (rest sob)))))))

(def insertR
(fn [new old sob]
(cond
(empty? sob) ( )
true (cond 
(= (first sob) old) 
 (cons old 
(cons new (rest sob)))
true (cons (first sob)
(insertR 
new old (rest sob)))))))

(def insertL
(fn [new old sob]
(cond
(empty? sob) ( )
true (cond 
(= (first sob) old) 
 (cons new 
(cons old (rest sob)))
true (cons (first sob)
(insertL 
new old (rest sob)))))))


(def subst
(fn [new old sob]
(cond
(empty? sob) ( )
true (cond 
(= (first sob) old) 
 (cons new (rest sob))
true (cons (first sob)
(subst 
new old (rest sob)))))))

(def insertR
(fn [new old sob]
(cond
(empty? sob) ( )
true (cond 
(= (first sob) old) 
 (cons new (rest sob))
true (cons (first sob)
(insertR 
new old (rest sob)))))))

(def subst
(fn [new old sob]
(cond
(empty? sob) ( )
true (cond 
(= (first sob) old) 
 (cons new (rest sob))
true (cons (first sob)
(subst 
new old (rest sob)))))))


(def subst2
(fn [new o1 o2 sob]
(cond
(empty? sob) ( )
true (cond 
(= (first sob) o1) 
 (cons new (rest sob))
(= (first sob) o2) 
 (cons new (rest sob))
true (cons (first sob)
(subst2 
o1 o2 (rest sob)))))))

((or (= (first sob) o1) (= (first sob) o2)) 
  (cons new (rest sob)))

(or (= (first sob) o1) (= (first sob) o2)) 
  (cons new (rest sob))

(def firsts
(fn [s]
(cond
(empty? s) () 
true (cons (first (first s))
(firsts (rest s))))))














()












