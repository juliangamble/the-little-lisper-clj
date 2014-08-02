(ns Chapter10WhatIsTheValueOfAllThis)

(def null?
  (fn [a]
    (or
      (nil? a)
      (= () a))))
(println "")
(println "defined null? as a utility function")

(def atom?
  (fn [a]
    (println "   atom?" a)
    (not (seq? a))))

(def build 
  (fn [a b]
  (println "   build" a b)
  (cons a (cons b '()))))

(println "")
(println "build")
(println (build 'a 'b))
;//=>(a b)

; ok - so first thing we build a key-value pair for our map data structure
(def new-entry build)
(println "")
(println "new-entry")
(println (new-entry 'a 'b))

;now we'll build a function to get a value out of the map by key value

;first we'll write a handler that gets passed in saying 
; what the user wants to happen when the entry is not found
(def lookup-in-entry-help 
  (fn [name names values entry-f]
    (cond
      (empty? names) (entry-f name)
      (= (first names) name)
      (first values)
      true (lookup-in-entry-help
             name
             (rest names)
             (rest values)
             entry-f))))


(println "")
(println "lookup-in-entry-help")
(println (lookup-in-entry-help 'entree '(mains dessert) '(chicken icecream) println))
;//=>entree
(println (lookup-in-entry-help 'entree '(entree mains dessert) '(garlicbread chicken icecream) println))
;//=>garlicbread

(def couldntfind
  (fn [a]
    (println "couldn't find" a)))

(println "")
(println "couldntfind")
(println (couldntfind 'wally))
;//=>couldn't find wally
(println (lookup-in-entry-help 'entree '(mains dessert) '(chicken icecream) couldntfind))
;//=>couldn't find entree            

(def first_
  (fn [p]
    (println "first_" p)
    (cond
      true (first p))))

(println "")
(println "first_")
(println (first_ '(a b)))
;//=>a

(def second_
  (fn [p]
    (println "second_" p)
    (cond
      true (first (rest p)))))

(println "")
(println "second_")
(println (second_ '(a b)))
;//=>b

(def third_
  (fn [p]
    (cond
      true (first (rest (rest p))))))

(println "")
(println "third_")
(println (third_ '(a b c)))
;//=>c


; this is the function that looks up the value
(def lookup-in-entry 
  (fn [name entry entry-f]
    (println "lookup-in-entry " name entry entry-f)
    (lookup-in-entry-help
      name
      (first_ entry)
      (second_ entry)
      entry-f)))

(println "")
(println "lookup-in-entry")
(println (lookup-in-entry 'entree '((entree mains dessert) (garlicbread chicken icecream)) println))
;//=>garlicbread
(println (lookup-in-entry 'entree '((mains dessert) (chicken icecream)) couldntfind))
;//=>couldn't find entree

(def extend-table cons)

(println "")
(println "extend-table")
(println (extend-table '((breakfast lunch)(toast sandwiches)) '(((mains dessert)(beef fruit)))))
;//=>(((breakfast lunch) (toast sandwiches)) ((mains dessert) (beef fruit)))

(def lookup-in-table
  (fn [name table table-f]
    '(println "lookup-in-table " name table table-f) 
    (cond
      (null? table) (table-f name)
      true (lookup-in-entry
             name
             (first table)
             (fn [name]
               (lookup-in-table
                 name
                 (rest table)
                 table-f))))))

(println "")
(println "lookup-in-table")
(println (lookup-in-table 'mains '( ((mains dessert) (steak gelato))) println))
;//=> steak
(println (lookup-in-table 'mains '(((starter entree) (bread soup)) ((mains dessert) (steak gelato))) println))
;//=>steak

(def *self-evaluating
  (fn [e table]
    (println "    *self-evaluating" e table)
    e))

(println "")
(println "*self-evaluating")
(println (*self-evaluating '(+ 1 1) '()))
;//=>(+ 1 1)
(println (*self-evaluating '1 '()))
;//=> 1

(def initial-table
  (fn [name]
    (cond
      (= name 't) true
      (= name 'nil) nil
      true (build 'primitive name))))

(println "")
(println "intial-table")
(println (initial-table 'a))
;//=>(primitive a)




(def *identifier
  (fn [e table]
    (lookup-in-table
      e table initial-table)))

(println "")
(println "*identifier")
(println (*identifier 'a '()))
;//=>(primitive a)


(def atom-to-action
  (fn [e]
    (println "   atom-to-action" e)
    (cond
      ;(number? e) '*self-evaluating
      (number? e) *self-evaluating
      ;true '*identifier)))
      true *identifier)))

(println "")
(println "atom-to-action")
(println (atom-to-action '1))
;//=>*self-evaluating
(println (atom-to-action 'cons))
;//=>*identifier


(def text-of-quotation second_)
;thoughts - should be rest and not second in case (quote one two)

(println "")
(println "text-of-quotation")
(println (text-of-quotation '(quote helloWorld)))
;//=> helloWorld
(println (text-of-quotation '(quote hello world)))
;//=>hello


(def *quote
  (fn [e table]
    (text-of-quotation e)))

(println "")
(println "*quote")
(println (*quote '(quote helloWorld) '()))
;//=> helloWorld



(def *lambda
  (fn [e table]
    (println "  *lambda " e table)
    (build 'non-primitive
           (cons table (rest e)))))

(println "")
(println "*lambda")
(println (*lambda '(*lambda (b) (println b)) '()))
;//=>(non-primitive (() (b) (println b)))


(def question-of first_)

(println "")
(println "question-of")
(println (question-of '((= 1 a) (println true))))
;//=>(= 1 a)


(def answer-of second_)

(println "")
(println "answer-of")
(println (answer-of '((= 1 a) (println true))))
;//=>(println true)

; stub this out to make it easier to load later
(def meaning)


(def evcon
  (fn [lines table]
    (cond
      (meaning 
        (question-of (first lines)) table)
      (meaning 
        (answer-of (first lines)) table)
      true (evcon (rest lines) table))))


(def cond-lines rest)


(def *cond
  (fn [e table]
    (evcon (cond-lines e) table)))

; stub this out for later definition (cyclical dependencies)
(def *application)


(def list-to-action
  (fn [e]
    (println "list-to-action" e)
    (cond
      (atom? (first e)) (cond
                          ;(= (first e) 'quote) quote
                          (= (first e) 'quote) *quote
                          (= (first e) 'lambda) *lambda
                          ;(= (first e) 'lambda) *fn
                          (= (first e) 'cond) *cond
                          true *application)
      true *application)))

(println "")
(println "list-to-action")
(println (list-to-action '(null? 'null)))
;//=>*application
(println (list-to-action '(lambda 'a)))
;//=>*lambda


(def expression-to-action
  (fn [e]
    (println "  expression-to-action" e)
    (cond
      (atom? e) (atom-to-action e)
      true (list-to-action e))))

(println "")
(println "expression-to-action")
(println (expression-to-action '(lambda a)))
;//=>*lambda
(println (expression-to-action 'a))
;//=>*identifier
(println (expression-to-action '1))
;//=> *self-evaluating

(def meaning
  (fn [e table]
    (println " meaning" e table)
    (println (expression-to-action e))
    ((expression-to-action e) e table)))




(def value
  (fn [e]
    (meaning e '())))



;(def *self-evaluating
;  (fn [e table]
;    (fn []
;      e)))





(def table-of first_)

(println "")
(println "table-of")
(println (table-of '((first second) (third forth))))
;//=>(first second)
(println (table-of '(*lambda (args0) functionBody)))
;//=>*lambda


(def formals-of second_)

(println "")
(println "formals-of")
(println (formals-of '((first second) (third forth))))
;//=>(third forth)
(println (formals-of '(*lambda (args0) functionBody)))
;//=>(args0)

(def body-of third_)

(println "")
(println "third_")
(println (body-of '(*lambda (args0) functionBody)))
;//=>functionBody




(def evlis
  (fn [args table]
    (println "evlis" args table)
    (cond
      (null? args) '()
      true (cons (meaning (first args) table)
                 (evlis (rest args) table)))))

(println "")
(println "evlis")
(println (evlis '(+ 1 a) '()))
;//=>(() () ())


(def function-of  first_)

(println "")
(println "function-of")
(println (function-of '(+ 1 1)))
;//=> +


(def arguments-of rest)

(println "")
(println "arguments-of")
(println (arguments-of '(+ 1 1)))


(def primitive?
  (fn [l]
    (= (first_ l) 'primitive)))

(println "")
(println "primitive?")
(println (primitive? '(primitive a)))
;//=> true



(def non-primitive?
  (fn [l]
    (= (first_ l) 'non-primitive)))

(println "")
(println "non-primitive?")
(println (non-primitive? '(non-primitive a)))
;//=> true


(def add1
  (fn [n]
    (println "add1" n)
    (+ 1 n)))

(println "")
(println "add1")
(println (add1 2))
;//=> 3

(def sub1
  (fn [n]
    (- n 1)))

(println "")
(println "sub1")
(println (sub1 2))
;//=> 1

(def apply-primitive
  (fn [name vals]
    (println "apply-primitive" name vals)
    (cond
      (= name 'car ) (first (first_ vals))
      (= name 'cdr ) (rest (first_ vals))
      (= name 'cons ) (cons (first_ vals) (second_ vals))
      (= name 'eq ) (= (first_ vals) (second_ vals))
      (= name 'atom? ) (atom? (first_ vals))
      (= name 'not ) (not (first_ vals))
      (= name 'null? ) (null? (first_ vals))
      (= name 'number? ) (number? (first_ vals))
      (= name 'zero? ) (zero? (first_ vals))
      (= name 'add1 ) (add1 (first_ vals))
      (= name 'sub1 ) (sub1 (first_ vals)))))

(def apply-closure
  (fn [closure vals]
    (meaning (body-of closure)
             (extend-table
               (new-entry
                 (formals-of closure) vals)
               (table-of closure)))))


(def apply_
  (fn [fun vals]
    (println "apply_" fun vals)
    (cond
      (primitive? fun) (apply-primitive (second_ fun) vals)
      (non-primitive? fun) (apply-closure (second_ fun) vals))))

(println "")
(println "apply_")
;(println (apply_ 'add1 '(1)))


(def *application
  (fn [e table]
    (println "*application" e table)
    (println " *application - (function-of e)" (function-of e))
    (println "  *application - (meaning (function-of e) table)" (meaning (function-of e) table))
    (println " *application - (arguments-of e)" (arguments-of e))
    (println "  *application - (evlis (arguments-of e) table)" (evlis (arguments-of e) table))
    ;(apply
    (apply_
      (meaning (function-of e) table)
      (evlis (arguments-of e) table))))

(println "")
(println "*application")
;(println (*application '(+ 1 1) '()))
;*** not a good example


(println "")
(println "meaning")
(println (meaning '(+ 1 1) '()))
;//=>()
(println (meaning '1 '()))

(println "")
(println "evcon")
(println (evcon '((= 1 a) (println true)) '()))
;//=>()

(println "")
(println "cond-lines")
(println (cond-lines '(cond ((= 1 a) (println true)) (true 1))))
;//=>(((= 1 a) (println true)) (true 1))

(println "")
(println "*cond")
(println (*cond '(cond ((= 1 a) (println true)) (true 1)) '()))
;//=>()

(println "")
(println "value")
(println (value '(+ 1 1)))
;//=>()



(println "")
(println "apply-primitive")
(println (apply-primitive 'atom? '(1) ))
;//=> true



(println "")
(println "apply-closure")
(println (apply-closure '((((u v w) (1 2 3)) ((x y z) (4 5 6))) (x y) (cons z x)) '((a b c) (d e f))))
;//=> (((x y) ((a b c) (d e f))) ((u v w) (1 2 3)) ((x y z) (4 5 6)))


(def cons_
  (fn [u v]
    (fn [b]
      (cond
        b u
        true v))))

(println "")
(println "cons_")
(println (cons_ 'apple '()))
;//=> #<Chapter10WhatIsTheValueOfAllThis$cons_$fn__862 Chapter10WhatIsTheValueOfAllThis$cons_$fn__862@272b72f4>



(def lunch (cons_ 'apple '()))

(println "")
(println "lunch")
(println (lunch 'apple ))
;//=> 'apple
(println (lunch '1 ))
;//=> apple
(println (lunch nil ))
;//=> ()


(def car_
  (fn [l]
    (l true)))

(println "")
(println "car_")
(println (car_ lunch))
;//=> apple


(def cdr_
  (fn [l]
    (l nil)))

(println "")
(println "cdr_")
(println (cdr_ lunch))
;//=> ()

; need to do examples of eval
; can't because we haven't done a define - all we can do is lambdas 
;(which is possible to do - but lots more work)


;testing
(value '(add1 1))
 (meaning '(add1 1) '())
  (expression-to-action '(add1 1) )
   (list-to-action '(add1 1) )
 (*application '(add1 1) '())
  (meaning '1 ())
  ;//=> () ???
   (expression-to-action '1)
   ;//=> *self-evaluating
    (atom-to-action '1)
    ;//=> *self-evaluating
     (*self-evaluating 1 '())
     ;//=> 1
    ;(atom-to-action (*self-evaluating 1 '() ))
    ;//=> *self-evaluating XXX
    ;((atom-to-action (*self-evaluating 1 '() )) '1 ())
    ;//=> () ??? XXX
    ((atom-to-action '1) '1 '())
     ;//=> () ???
    (println ((atom-to-action '1) '1 '()))
    ;(eval ((atom-to-action '1) '1 '()))
    ;(apply (atom-to-action '1) '1 '())
    ;((atom-to-action '1) '1 '())
  (meaning '1 ())
  ;//=> 1
 (*application '(add1 1) '())
  ;//=>2
  (list-to-action '(add1 1) )
  ;//=> #<fn>
  ((list-to-action '(add1 1)) '(add1 1) '())
  ;//=> 2
 (meaning '(add1 1) '())
 ;//=> 2
(value '(add1 1))
 ;//=> 2
 
;(value '(eq (true) (false)))
(value '(eq 2 1))
;//=> false

(value '(eq 1 1))
;//=> true
 
(value '(quote hello))
;//=> hello

(value '((lambda (x) 1) 2))
;//=> 1

(value '((lambda (x) x) 2))
;//=> 2

(value '((lambda (x) (add1 x)) 2))
;//=> 3

(value '(((lambda (y) (lambda (x) 1) y) 4)  3))
;//=> 1


(value '(((lambda (y) (lambda (x) x) y) 4)  3))
;//=> 3

(value '(((lambda (x y) (lambda (u) (cond (u x) (t y)))) 1 '()) nil))
;//=> ()

(value '((lambda (x) ((lambda (x) (add1 x)) (add1 4))) 6))
;//=> 6





