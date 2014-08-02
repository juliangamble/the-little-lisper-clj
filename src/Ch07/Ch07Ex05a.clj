(ns Ch07Ex05a)

(defn operator [aexp_]
  (println "operator " aexp_)
  (first aexp_))


(defn isoperator [a]
;  (println "isoperator " a)
  (cond
   ;(empty? a) false
   (= a '+) true
   (= a '*) true
   (= a 'raise) true
   true false))


;(defn cnt-aexp_ [a]
;  (cond
;    (empty? a) 0
;    (list? (first a)) 0
;    (number? (first a))
;        (+ 1 (cnt-aexp_ (rest a)))
;    )
;  )

;(defn cnt-aexp [a]
;  (cond
;    (empty? a) 0
;    (isoperator (first a))
;        (cnt-aexp_ (rest a))
;    )
;  )

;(cnt-aexp '(+ 1 2 3 4))
;(cnt-aexp '(* 1 2 3 '()))

(defn cnt-operands [a]
  (cond
    (empty? a) 0
    true (+ 1 (cnt-operands (rest a)))    
    )
  )

(defn cnt-aexp [a]
  (cond
    (empty? a) 0
    (isoperator (first a))
      (cnt-operands (rest a))
    )
  )

(cnt-operands '(+ 1 2 3 4))
(cnt-operands '(* 1 2 3 '()))

(defn first-sub-exp [aexp_]
  (first (rest aexp_)))

(first-sub-exp '(+ 1 2))

(defn second-sub-exp [aexp_]
  (first (rest (rest aexp_))))

(second-sub-exp '(+ 1 2))

(defn third-sub-exp [aexp_]
  (first (rest (rest (rest aexp_)))))

(defn fourth-sub-exp [aexp_]
  (first (rest (rest (rest (rest aexp_))))))


(use 'clojure.contrib.math); note use of clojure library function exp

(defn value__ [aexp_]
  (println "value__" aexp_)
  (cond
   (number? aexp_) aexp_
  true 
  (cond
     (= (cnt-aexp aexp_) 2) 
	     (cond
			   (= (operator aexp_) '+)
			    (+ (value__ (first-sub-exp aexp_))
			       (value__ (second-sub-exp aexp_)))
			   (= (operator aexp_) '*)
			    (* (value__ (first-sub-exp aexp_))
			       (value__ (second-sub-exp aexp_)))
			   true
			    (expt (value__ (first-sub-exp aexp_))
			       (value__ (second-sub-exp aexp_))))
     (= (cnt-aexp aexp_) 3) 
       (cond
         (= (operator aexp_) '+)
          (+ (value__ (first-sub-exp aexp_))
             (value__ (second-sub-exp aexp_))
             (value__ (third-sub-exp aexp_)))
         (= (operator aexp_) '*)
          (* (value__ (first-sub-exp aexp_))
             (value__ (second-sub-exp aexp_))
             (value__ (third-sub-exp aexp_)))
         true
          (expt (value__ (first-sub-exp aexp_))
             (value__ (second-sub-exp aexp_))
             (value__ (third-sub-exp aexp_))))
     (= (cnt-aexp aexp_) 4) 
       (cond
         (= (operator aexp_) '+)
          (+ (value__ (first-sub-exp aexp_))
             (value__ (second-sub-exp aexp_))
             (value__ (third-sub-exp aexp_))
             (value__ (fourth-sub-exp aexp_)))          
         (= (operator aexp_) '*)
          (* (value__ (first-sub-exp aexp_))
             (value__ (second-sub-exp aexp_))
             (value__ (third-sub-exp aexp_))
             (value__ (fourth-sub-exp aexp_)))          
         true
          (expt (value__ (first-sub-exp aexp_))
             (value__ (second-sub-exp aexp_))
             (value__ (third-sub-exp aexp_))
             (value__ (fourth-sub-exp aexp_))))             
       )
  )
)

(operator '(+ 1 2))

(value__ '(+ 1 2 ))

(value__ '(+ 1 2 3))


(defn numbered? [aexp_]
  (cond
   (not (list? aexp_)) (number? aexp_)
   (= 2 (cnt-aexp aexp_))
       (and 
         (numbered? (first-sub-exp aexp_))
         (numbered? (second-sub-exp aexp_)))
   (= 3 (cnt-aexp aexp_))
       (and 
         (numbered? (first-sub-exp aexp_))
         (numbered? (second-sub-exp aexp_))
         (numbered? (third-sub-exp aexp_)))
   (= 4 (cnt-aexp aexp_))
       (and 
         (numbered? (first-sub-exp aexp_))
         (numbered? (second-sub-exp aexp_))
         (numbered? (third-sub-exp aexp_))
         (numbered? (fourth-sub-exp aexp_)))))

