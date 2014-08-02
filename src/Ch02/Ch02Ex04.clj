(ns Ch02Ex04)

; Changing the recipe (of the member? function)
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 2
; Exercise 4
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-2-do-it-do-it.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------
(def l1 '(german chocolate cake))
(def l2 '(poppy seed cake))
(def l3 '((linzer) (torte)()))
(def l4 '((bleu cheese)(and)(red)(wine)))
(def l5 '(()()))
(def a1 'coffee)
(def a2 'seed)
(def a3 'poppy)

(defn lat? [l] 
  (cond
   (empty? l) true
   (not (list? (first l))) (recur (rest l)) 
   true false))
; ------------------------------
; method defined in chapter
(defn member? [a lat]
  (cond
   (empty? lat) false
    true (or
       (= (first lat) a)
       (recur a (rest lat)))))

(member? a1 l1);false
(println (member? a1 l1))

(member? a2 l2);true
(println (member? a2 l2))

; Redefine using the if function
(defn member?  [a lat]
      (if (empty? lat)
          false
        (or
         (= (first lat) a)
         (recur a (rest lat)))))

(member? a1 l1);false
(println (member? a1 l1))

(member? a2 l2);true
(println (member? a2 l2))

; Different definition using if => same result

; method defined in the chapter
(defn lat? [l]
  (cond
   (empty? l) true
   (not (list (first l))) (recur (rest l))
   true false))

(lat? l1); true
(println (lat? l1))

(lat? l3); false
(println (lat? l3))

; Redefine using the if function
(defn lat? [l] 
  (if (empty? l)
      true
    (if (not (list (first l)))
        (recur (rest l))
    false)))

(lat? l1); true
(println (lat? l1))

(lat? l3); false
(println (lat? l3))
; Different definition using if => same result

