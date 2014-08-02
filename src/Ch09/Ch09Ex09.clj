(ns Ch09Ex09)

; Can you combine an S-expr and a thunk to make a stream?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 9
; Exercise 9
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-9-lamdba-ultimate.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

(defn add1 []
    (fn [n]
      (+ 1 n)))

(println ((add1) 1))
;2

(defn build [a b]
  (println "build" a b)
  (cons a (cons b '())))

(defn sub1 [n]
  (println "sub1" n)
  (- n 1))

(defn str-maker [next n]
  (println "str-maker" next n)
  (build n (fn [] (str-maker next ((next) n)))))

(defn int_ [] 
  (println "int_")
  (str-maker 'add1 0))

(defn add2 []
  (fn [n]
    (+ 2 n)))

(println  ((add2) 1))
;3

(defn even [] 
  (println "even")
  (str-maker add2 0))

(defn odd [] 
  (println "odd")
  (str-maker add2 1))

(defn first_ [l]
  (println "first_" l)
  (cond
   (= () l) ()
   true (first l)))

(defn second_ [l]
  (println "second_" l)
  (cond
   (empty? l) '()
   true (first (rest l))))

(defn frontier [str n]
  (println "frontier" str n)
    (cond
     (= 0 n) ()
     true (cons (first_  (str)) (frontier (second_ (str)) (sub1 n)))))

(str-maker int_ 10)

(println 'int_)
(println (int_))
(println (second_ (int_)))
(println #(second_ (int_)))
(println #((second_ (int_)) int_ 9))
(println (#((second_ (int_)) int_ 9)))

(println ((second_ (int_)) int_ 9))

(println  (frontier int_ 10))
;(0 1 2 3 4 5 6 7 8 9)

(println (frontier int_ 100))
;(0 1 2 3 4 5 6 7 8 9 ...)

(println  (frontier even 23))
;(0 2 4 6 8 10 12 14 16 18 ...)

(println  (frontier (fn [] odd) 10))
;(1 3 5 7 9 11 13 15 17 19)
