(ns Ch09Ex03)

; Can you rewrite your function for the ycombinator?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 9
; Exercise 3
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-9-lamdba-ultimate.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

;Y-combinator for functions of two arguments
;rewritten for lisp-2 namespace - and removed first lambda
;(defun Y2 (M)
;      ((lambda (future)
;         (funcall M (function (lambda (arg1 arg2)
;                                (funcall (funcall future future) arg1 arg2)))))       
;                                
;       (function (lambda (future)                                      
;                   (funcall M (lambda (arg1 arg2)                                
;                                (funcall (funcall future future) arg1 arg2)))))))

;(def Y 
;  (fn [r] 
;    (println "Y" r)
;    ((fn [f] (f f))
;      (fn [y] 
;        (r (fn [x] ((y y) x))))))) 

;(defn Y [r]
;    ((fn [f] (f f))
;      (fn [y] 
;        (r (fn [x] ((y y) x))))))


(defn Y [f]
  ((fn [x] (x x))
   (fn [x]
     (f (fn [& args]
          (apply (x x) args))))))

;(setf (symbol-function '_=)
;  (Y2 (function (lambda (recurring-function)
;                 (function (lambda (m n)
;                                   (cond
;                                    ((> n m) nil)                                    
;                                    ((< n m) nil)                                    
;                                    (t t))))))))

;(_= 1 2)
;NIL false

;(_= 2 2)
;T

(def zero 0)

(= zero 0)

(defn zero_? [n]
  (println "zero_?" n)
  (cond
   (= n ()) false
   (= zero n) true
   true false))

(zero_? 0)
(zero_? -1)
(zero_? 1)

(defn sub1 [n]
  (println "sub1" n)
  (cond
   (= n ()) ()
   true (- n 1)))

(sub1 12)
(sub1 zero)
(sub1 -1)

(println
((Y 
  (fn [fib] 
    (fn [n] 
      (cond 
        (< n 2) n 
        true (+ (fib (- n 1)) (fib (- n 2))) )))) 8)
)

;(println 
;((Y
;   (fn [pick]
;     (fn [n lat]
;       (println "pick" n lat)
;       (cond
;         (empty? lat) ()
;         (zero_? (sub1 n)) (first lat)
;         true (pick (sub1 n) (rest lat)) )))) 3 '(a b c d e f g h i j))
;)

(defn get_ [n lat]
    (cond
      (empty? lat) ()
        (= 0 (- n 1)) (first lat)
        true (get_ (- n 1) (rest lat))))
(get_ 3 '(a b c d e f g h i j))

(def get__ 
  (fn [f]
    (fn [n lat]
    (cond
      (empty? lat) ()
        (= 0 (- n 1)) (first lat)
        true (f (- n 1) (rest lat)))
      )))

(println ((Y get__) 3 '(a b c d e f g h i j)))

(def pick
     (fn [f]
       (fn [n lat]
         (println "f" n lat)
         (cond
         (empty? lat) ()
         (zero_? (sub1 n)) (first lat)
         true (f (sub1 n) (rest lat)) ))))

(println ((Y pick) 3 '(a b c d e f g h i j)))
;((Y fac) 10)


(zero_? 2)
;(setf (symbol-function 'pick)
;  (Y2 (function (lambda (recurring-function)
;                 (function (lambda (n lat)
;                             (cond
;                              ((null lat) nil)
;                              ((zero (sub1 n)) (car lat))
;                              (t (funcall recurring-function (sub1 n) (cdr lat))))))))))
 
;(pick 3 '(a b c d e f g h i j))                           

;(setf (symbol-function 'rempick)
;  (Y2 (function (lambda (recurring-function)
;                 (function (lambda (n lat)
;                             (cond
;                              ((null lat) '())
;                              ((zero (sub1 n)) (cdr lat))
;                              (t (cons (car lat)
;                                       (funcall recurring-function (sub1 n)(cdr lat)))))))))))

(def rempick
     (fn [f]
       (fn [n lat]
         (println "f" n lat)
         (cond
         (empty? lat) ()
         (zero_? (sub1 n)) (rest lat)
         true (cons (first late) (f (sub1 n) (rest lat)) ))))

(println ((Y rempick) 3 '(a b c d e f g h i j)))


;(rempick 3 '(a b c d e f g h i j))


;Thoughts
: There has to be a better way than symbol-function

