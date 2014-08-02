(ns Ch08Ex07)

; Expression evals - Is your relation of pairs prepped?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 8
; Exercise 7
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

(println (rin 'a d1))
;((A A) (A B))
;note typo in notes - said x when they meant 'a

(println (rin 'a d2))
;((A C) (A D))
;note typo in notes - said x when they meant 'a

(println (rin 'a f2))
;NIL no answer


