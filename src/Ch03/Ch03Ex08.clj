(ns Ch03Ex08)

; red wine - Is this a natural recursion?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 3
; Exercise 8
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-3-cons.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def l1 '((paella spanish)(wine red)(and beans)))
(def l2 '())
(def l3 '(cincinnati chili))
(def l4 '(texas hot chili))
(def l5 '(soy sauce and tomato sauce))
(def l6 '((spanish)()(paella)))
(def l7 '((and hot)(but dogs)))
(def a1 'chili)
(def a2 'hot)
(def a3 'spicy)
(def a4 'sauce)
(def a5 'soy)
; ------------------------------

;Typical elements - the first element in the Third Commandment that you cons with
;3.1 Typical element - (cdr (car l))
;3.2 Typical element - a
;3.3 Typical element - (car lat)
;3.4 Typical element - (car lat)
;3.5 Typical element - (car lat)
;3.6 Typical element - (car lat)


;Natural recursion - what you cons onto
;3.1 Natural recursion - (firsts (cdr l)) - (seconds (cdr l))
;3.2 Natural recursion - (dupla a (cdr c))
;3.3 Natural recursion - (rember a (cdr lat))
;3.4 Natural recursion - (subst-sauce a (cdr lat))
;3.5 Natural recursion - (subst2 new o1 o2 o3 (cdr lat))
;3.6 Natural recursion - (substN new slat (cdr lat))
