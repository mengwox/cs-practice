;Exercise 1.21:
;Use the smallest-divisor procedure to find the smallest divisor of each of the following numbers:
;199, 1999, 19999
(define (smallest-divisor n)
  (find-divisor n 2))

(define (find-divisor n test)
  (cond
    ((> (square test) n) n)
    ((divides? n test) test)
    (else (find-divisor n (+ test 1)))))
(define (square x) (* x x))
(define (divides? a b) (= (remainder a b) 0))

(smallest-divisor 199)
;199

(smallest-divisor 1999)
;1999

(smallest-divisor 19999)
;7