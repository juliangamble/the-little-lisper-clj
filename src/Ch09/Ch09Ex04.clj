(ns Ch09Ex04)

; Rewrite member for the ycombinator
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 9
; Exercise 4
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-9-lamdba-ultimate.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

;assuming reference to Y  - refers to Y2 and not to a hypothetical Y function with 
;a list of arguments

;assuming reference to member refers to chapter 2 (for member function) and not 3 (typo)

(defn Y [f]
  ((fn [x] (x x))
   (fn [x]
     (f (fn [& args]
          (apply (x x) args))))))

(defn member? [f]
  (fn [a lat]
      (cond
        (empty? lat) false
        true (or
        (= (first lat) a)
        (f a (rest lat))))))
(println ((Y member?) 'a '(a b c d e f g)))


;(setf (symbol-function 'member-Y)
;  (Y2 (function (lambda (recurring-function)
;                  (function (lambda (a lat)
;                              (cond
;                               ((null lat) nil)
;                               (t (or
;                                   (eq (car lat) a)
;                                   (funcall recurring-function a (cdr lat)))))))))))
      ;l)))
(println ((Y member?) 'a '(a b c d e f g)))
;(member-Y 'a '(a b c d e f g))
;T

(println ((Y member?) 'b '(a b c d e f g)))
;(member-Y 'b '(a b c d e f g))
;T

(println ((Y member?) 'c '(a b c d e f g)))
;(member-Y 'c '(a b c d e f g))
;T

(println ((Y member?) 'z '(a b c d e f g)))
;(member-Y 'z '(a b c d e f g))
;NIL false

(defn rember [f] 
  (fn [a lat]
  (cond 
   (empty? lat) '()
   true (cond
       (= (first lat) a) (rest lat)
       true (cons (first lat)
                (f
                 a (rest lat)))))))

;but rember does come from chapter 3 (not a typo)
;(setf (symbol-function 'rember-Y)
;  (Y2 (function (lambda (recurring-function)
;                  (function (lambda (a lat)
;                              (cond 
;                               ((null lat) '())
;                               (t (cond
;                                   ((eq (car lat) a) (cdr lat))
;                                   (t (cons (car lat)                                            
;                                   (funcall recurring-function a (cdr lat)))))))))))))

(println ((Y rember) 'and '(bacon lettuce and tomato)))
;(rember-Y 'and '(bacon lettuce and tomato))
;(BACON LETTUCE TOMATO)

;insertR
;(defun YN (F)
;   ((lambda (future)
;      (funcall F (function(lambda (&rest args)
;                     (apply (funcall future future) args)))))
;    #'(lambda (future)
;        (funcall F (function(lambda (&rest args)
;                       (apply (funcall future future) args))))) ) ) 

(defn insertR [g] 
  (fn [new old lat]
  (cond
   (empty? lat) '()
   true (cond
       (= (first lat) old)
        (cons old
              (cons new (rest lat)))
       true (cons (first lat)
                (g
                 new old (rest lat)))))))

;(setf (symbol-function 'insertR-Y)
;  (YN (function (lambda (recurring-function)
;                  (function (lambda (new old lat)
;                              (cond
;                               ((null lat) '())
;                               (t (cond
;                                   ((eq (car lat) old)
;                                    (cons old
;                                          (cons new (cdr lat))))
;                                   (t (cons (car lat)
;                                            (funcall recurring-function
;                                             new old (cdr lat)))))))))))))

(println ((Y insertR) 'toasted 'club '(large club sandwich)))

;(println (insertR-Y 'toasted 'club '(large club sandwich)))
;(LARGE CLUB TOASTED SANDWICH)


;subst-2
 
;(setf (symbol-function 'subst2-Y)
;  (YN (function (lambda (recurring-function)
;                  (function (lambda (new o1 o2 lat)
;                              (cond
;                               ((null lat) '())
;                               (t (cond
;                                   ((eq (car lat) o1)
;                                    (cons new (cdr lat)))
;                                   ((eq (car lat) o2)
;                                    (cons new (cdr lat)))
;                                   (t (cons (car lat)
;                                            (funcall recurring-function new
;                                                     o1 o2 (cdr lat)))))))))))))

(defn subst2 [h] 
  (fn [new_ o1 o2 lat]
  (cond
  (empty? lat) '()
  true (cond 
      (= (first lat) o1)
       (cons new_ (rest lat))
      (= (first lat) o2)
       (cons new_ (rest lat))
      true (cons (first lat)
               (h new_ 
                       o1 o2 (rest lat)))))))

(println ((Y subst2) 'vanilla 'chocolate 'banana '(banana icecream with chocolate topping)))
;(subst2-Y 'vanilla 'chocolate 'banana '(banana icecream with chocolate topping))
;(VANILLA ICECREAM WITH CHOCOLATE TOPPING)

(println ((Y subst2) 'vanilla 'chocolate 'topping '(banana icecream with chocolate topping)))
;(subst2-Y 'vanilla 'chocolate 'topping '(banana icecream with chocolate topping))
;(BANANA ICECREAM WITH VANILLA TOPPING)
