(ns Ch09Ex06)

; Can you abstract these two functions into an accumulator?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 9
; Exercise 6
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-9-lamdba-ultimate.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

; assume addvec is the text and multivec is the exercise from chapter 5

(defn accum [func terminator lat]
  (cond
   (empty? lat) terminator
   true (func (first lat)
               (accum func terminator (rest lat)))))

(println (accum + 0 '(1 2 3 4 5 6) ))
;21

(println (accum * 1 '(1 2 3 4 5 6) ))
;720



