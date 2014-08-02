(ns Ch01Ex02)

; First steps towards learning LISP - What distinguishes a list?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 1
; Exercise 2
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-1-toys.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
'(atom atom)
(println '(atom atom))

'(ten atom)
(println '(ten atom))

'(different atom)
(println '(different atom))

'(atoms atom)
(println '(atoms atom))

'(writingThemDown atom)
(println '(writingThemDown atom))

'(f atom)
(println '(f atom))

'(1 atom)
(println '(1 atom))

'(x atom)
(println '(x atom))

'(nearlyThere atom)
(println '(nearlyThere atom))

'(j atom)
(println '(j atom))

'(atom ten)
(println '(atom ten))

'(b ten)
(println '(b ten))

'(different ten)
(println '(different ten))

'(atoms ten)
(println'(atoms ten))

'(writingThemDown ten)
(println '(writingThemDown ten))

'(f ten)
(println '(f ten))

'(1 ten)
(println '(1 ten))

'(x ten)
(println '(x ten))

'(nearlyThere ten)
(println '(nearlyThere ten))

'(j (ten))
(println '(j (ten)))

; A list is a sequence of atoms or lists
; Note that the syntax 
'(atom atom)
(println '(atom atom))

; is shorthand for
(list (quote atom) (quote atom))
(println (list (quote atom) (quote atom)))


