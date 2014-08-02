(ns Ch01Ex03)
; First steps towards Learning LISP - Cons a piece of cake onto your mouth
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 1
; Exercise 3
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-1-toys.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
; One way to solve the problem
(cons 'all (cons 'these '(problems )))
(println (cons 'all (cons 'these '(problems ))))
; Perhaps we should incorporate some reuse...

;Define variables
(def a 'all)
(def b 'these)
(def c 'problems)
(def d '())
;Validate variables
(println a)
(println b)
(println c)
(println d)
; So the original becomes
(cons a (cons (cons b (cons (cons c d) d)) d))
(println (cons a (cons (cons b (cons (cons c d) d)) d)))

;alternatively could be
(cons a (cons b (list c )))
(println (cons a (cons b (list c ))))
;Actual solutions
(cons a (cons (cons b (cons c d)) d))
(println (cons a (cons (cons b (cons c d)) d)))

(cons a (cons (cons b d) (cons c d)))
(println (cons a (cons (cons b d) (cons c d))))

(cons (cons a (cons b d)) (cons c d))
(println (cons (cons a (cons b d)) (cons c d)))

(cons (cons a (cons b (cons c d))) d)
(println (cons (cons a (cons b (cons c d))) d))

;Alternatively this could be
(cons a (list (list b c)))
(list a (list b ) c)
(list (list a b ) c)
(list (list a b c))
;
(println (cons a (list (list b c))))
(println (list a (list b ) c))
(println (list (list a b ) c))
(println (list (list a b c)))





