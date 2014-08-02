(ns Ch07Ex01)

; Starting writing your own arithmetic DSL in Lisp
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 7
; Exercise 1
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-7-shadows.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def l1 '())
(def l2 '(3 + (66 6)))
(def aexp4 5)
; ------------------------------
(defn mk+exp [aexp1_ aexp2_]
  (cons aexp1_
        (cons '+
              (cons aexp2_ '()))))

(println (mk+exp 1 2))
;(1 + 2)

(defn mk*exp [aexp1_ aexp2_]
  (cons aexp1_
        (cons '*
              (cons aexp2_ '()))))

(println (mk*exp 1 2))
;(1 * 2)

(defn mkRaiseexp [aexp1_ aexp2_] ;note use of raise instead of ^
  (cons aexp1_
        (cons 'raise
              (cons aexp2_ '()))))

(println (mkRaiseexp 1 2))
;(1 ^ 2)

(def aexp1 (mk+exp 1 (mk*exp 3 4)))
(def aexp2 (mk+exp (mkRaiseexp 3 4) 5))
(def aexp3 (mk*exp 3 (mk*exp 4 (mk*exp 5 6))))
