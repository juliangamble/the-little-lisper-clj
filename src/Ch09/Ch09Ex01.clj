(ns Ch09Ex01)

; Can you rewrite your function to map it to a list?
; ------------------------------
; The Little Lisper 3rd Edition
; Chapter 9
; Exercise 1
; Common Lisp
; http://twitter.com/thelittlelisper
; http://thelittlelisper.blogspot.com/2010/06/little-lisper-chapter-9-lamdba-ultimate.html
; http://thelittlelisper.blogspot.com/2010/06/little-lisper.html
; ------------------------------

(defn map_ [l func]
  (cond
   (= '() l) '()
   ;true (cons (funcall func (first l))
   true (cons (func (first l))
            (map_ (rest l) func))))

;firsts
;(println (map_ '((paella spanish)(wine red)(and beans)) #'first))
(println (map_ '((paella spanish)(wine red)(and beans)) #'first)) ;var-quote is #'
;http://clojure.org/special_forms#Special Forms--(var symbol)
(println (map_ '((paella spanish)(wine red)(and beans)) (var first)))
(println (map_ '((paella spanish)(wine red)(and beans)) first)) ; also works...

;(PAELLA WINE AND)

(def spanish-food '((paella spanish)(wine red)(and beans)))

(println (map_ spanish-food #'first))
;(PAELLA WINE AND)

;seconds
(defn seconds-closure [l]
  (first (rest l)))

(println (map_ spanish-food #'seconds-closure))
;(SPANISH RED BEANS)
(println (map_ spanish-food seconds-closure))
;(SPANISH RED BEANS)
    
;------------
(defn mapper [m function]
  (cond
   (= '() m) '()
   true (cons (function (first m))
            (mapper (rest m) function))))

(println (map_ '((blue red)(green red)(white red)) #'first))
