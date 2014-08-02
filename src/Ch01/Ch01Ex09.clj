(ns Ch01Ex09)

; Kiwis, mangoes, lemons. How do you traverse down a nested list?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 1
; Exercise 9
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-1-toys.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

(def l '((kiwis mangoes lemons) and (more)))
(first (rest (rest (first l))))
(println (first (rest (rest (first l)))))

(def l '(() (eggs and (bacon)) (for) (breakfast)))
(first (rest (first (rest l))))
(println (first (rest (first (rest l)))))

(def l '(() () () (and (coffee)) please))
(first (rest (rest (rest l))))
(println (first (rest (rest (rest l)))))

; You traverse down a nested list by knowing when to take the first element
; and when to take the remainder

