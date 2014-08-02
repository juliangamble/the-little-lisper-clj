(ns Ch10Ex01)

; YourDSL - Can you evaluate this S-expr?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 10
; Exercise 1
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-10-what-is-value.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

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

(defn third_ [l]
  (println "third_" l)
  (cond
   (empty? l) '()
   true (first (rest (rest l)))))

;(defn fourth_ [l]
;  (println "fourth_" l)
;  (cond
;   (empty? l) '()
;   true (first (rest (rest (rest l))))))

(defn *self-evaluating []) ;cyclic reference
(defn *identifier []) ;cyclic reference

(defn atom-to-action [e]
  (println "atom-to-action" e)
  (cond
   (number? e) *self-evaluating ;note use of clojure function
   true *identifier))

(quote a)

(defn build [a b]
  ;(println "build" a b)
  (cons a (cons b '())))

(def e4
     '(3 (quote a)(quote b)))

(defn *self-evaluating [e table]
  e)

(defn text-of-quotation [l] 
  (println "text-of-quotation " l)
  (second_ l))


(defn *quote [e table]
  (println "*quote " e table)
      (text-of-quotation e))

(defn lookup-in-entry-help [name names values entry-f]
  (cond
   (empty? names) (entry-f name)
   (= (first names) name)
   (first values)
  true (lookup-in-entry-help
      name
      (rest names)
      (rest values)
      entry-f)))

(defn lookup-in-entry [name entry entry-f]
  (println "lookup-in-entry " name entry entry-f)
  (lookup-in-entry-help
   name
   (first_ entry)
   (second_ entry)
   entry-f))

(defn lookup-in-table [name table table-f]
  (println "lookup-in-table " name table table-f)
  (cond
   (empty? table) (table-f name)
   true (lookup-in-entry
       name
       (first table)
       (fn [name]
         (lookup-in-table
          name
          (rest table)
          table-f)))))

(defn *identifier [e table]
  (println "*identifier" e table)
  (lookup-in-table
;   e table 'initial-table))
   e table initial-table))


(defn initial-table [name]
  (println "initial-table " name)
  (cond
   (= name (quote true)) true
   (= name (quote nil)) nil
  true (build 
      (quote primitive)
      name)))

(defn primitive? [l]
  (println "primitive? " l)  
  (=
   (first_ l)
   (quote primitive)))

(defn non-primitive? [l]
  (println "non-primitive? " l)  
  (=
   (first_ l)
   (quote non-primitive)))

(defn zero_? [n]
  (= 0 n))

(defn add1 [n]
  (+ 1 n))

(defn sub1 [n]
  (- n 1))

(defn apply-primitive [name vals]
  (println "apply-primitive " name vals)    
  (cond
   (= name (quote car))
    (first (first_ vals))
   (= name (quote cdr))
    (rest (first_ vals))
   (= name (quote cons))
    (cons (first_ vals) (second_ vals))
   ;(= name (quote eq))
   ; (= (first_ vals) (second_ vals))
   (= name (quote =))
    (= (first_ vals) (second_ vals))
   (= name (quote atom))
    (not (seq? (first_ vals) ))   
   (= name (quote not))
    (not (first_ vals) )   
   (= name (quote null))
    (empty? (first_ vals) )
   (= name (quote number))
    (number? (first_ vals) ) ;note use of fn  
   (= name (quote zero))
    (zero_? (first_ vals) )      
   (= name (quote add1))
    (add1 (first_ vals) ) 
   (= name (quote sub1))
    (sub1 (first_ vals) )
;    (= name (quote cond))
;    (cond (first_ vals) (second_ vals) (third_ vals) (fourth_ vals))
   true (str "no matching primitive to apply for " name vals)
    ))

(defn body-of [l]
  (third_ l))

(defn extend-table [a b]
  (cons a b))

(defn new-entry [a b]
  (build a b))

(defn formals-of [l]
  (second_ l))

(defn table-of [l]
  (println "table-of " l)  
  (first_ l))

(defn *application [] ) ; note cyclic dependency
(defn *quote [] ) ; note cyclic dependency
(defn *lambda [] ) ; note cyclic dependency
(defn *cond [] ) ; note cyclic dependency

(defn list-to-action [e]
  (println "list-to-action " e)
  (cond
   (not (seq? (first e)))
    (cond
     (= (first e) (quote quote))
      *quote
     (= (first e) (quote lambda))
      *lambda
     (= (first e) (quote cond))
      *cond
     true *application)
   true *application))


(defn expression-to-action [e]
  (println "expression-to-action" e)
  (cond
   (not(seq? e)) (atom-to-action e)
   true (list-to-action e)))

(defn meaning [e table]
    (println "meaning " e table)
    (println ">" (expression-to-action e))
  ((expression-to-action e) e table))

(defn function-of [l] 
    (println "function-of " l)  
  (first l))

(defn evlis [args table]
  (cond
   (empty? args) (quote ())
   true (cons (meaning (first args) table)
            (evlis (rest args) table))))

(defn arguments-of [l] 
    (println "arguments-of " l)  
  (rest l))

(defn apply-closure [closure vals]
  (meaning (body-of closure)
           (extend-table
            (new-entry
             (formals-of closure) vals)
            (table-of closure))))

(defn apply_ [fun vals]
  (println "apply_ " fun vals)
  (cond
   (primitive? fun)
    (apply-primitive
     (second_ fun) vals)
   (non-primitive? fun)
    (apply-closure
     (second_ fun) vals)))

(defn *application [e table]
  (println "*application " e table)
  (apply_
   (meaning (function-of e) table)
   (evlis (arguments-of e) table)))

(defn value_ [e]
  (println "value_ " e)
  (meaning e (quote ())))

(defn question-of [l]
  (println "question-of " l)  
  (first_ l))

(defn answer-of [l]
  (second_ l))

(defn evcon [lines table]
  (println "evcon " lines table)
  (cond
   (meaning
;     (question-of (first lines))) table
;     (question-of (first lines)) table)
     (first lines) table)
   (second lines)
;   (meaning
;     (answer-of (first lines))) table
;     (answer-of (first lines)) table)
   true (evcon (rest (rest lines)) table)))

(defn cond-lines [l] 
  (println "cond-lines " l)
  (rest l))

(defn *cond [e table]
  (println "*cond " e table)
  (evcon (cond-lines e) table))
;  (evcon e table))


(defn *lambda [e table]
  (println "*lambda " e table)
  (build (quote non-primitive)
         (cons table (rest e))))

;-----------------
; 10.1
;-----------------


; ------------------------------
;truth values
(println (list-to-action '(= 1 1)))
(println ((list-to-action '(= 1 1)) '(= 1 1) ()))
(println (expression-to-action '=))
;*application
(println (expression-to-action '(= 1 1)))
(println ((expression-to-action '(= 1 1)) '(= 1 1) ()))
(*application '(= 1 1) ())
(println (meaning '(= 1 1) ()))

(println (value_ '(= 1 1)))
; T


(println (value_ '(= 1 2)))
; NIL false

;truth values
;(println (value_ '(cond (= 1 2) 'non-normality true 'normality)))
(println (value_ '(cond (= 1 2) 'non-normality true 'normality)))
; NORMALITY

;numbers
(println (value_ '23))
; 23

;quoted s-expression
(println (expression-to-action (add1 1)))
(println (value_ '(add1 1)))
; 2


(println (value_ '(sub1 1)))
(println (meaning '(sub1 1 ) ()))
(println ((expression-to-action '(sub1 1 ) )  '(sub1 1) () ))
(println (list-to-action '(sub1 1)))
(println ((list-to-action '(sub1 1))   ))


;-----------------
; 10.2
;-----------------

;(def e1
;'((lambda (x)
;          (cond
;           ((atom x)(quote 'done))
;           ((null x)(quote 'almost))
;           (t (quote 'never))))
;         (quote (add1 1))))

; What's the big idea behind this - is it laziness?

;(value_ e1)
;(value_ '(e1 a))
(value_ '((lambda (x)
          (cond
           ((atom x)(*quote 'done))
           ((null x)(*quote 'almost))
           (t (*quote 'never))))
         (*quote (add1 1))
       'a))



;-----------------
; 10.2
;-----------------


