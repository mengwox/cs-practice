;判断guess是否达到预期
(define (good-enough? a b)
    (< (abs (- b (square a))) 0 .001))
;优化guess预期值
(define (improve guess x)
    (avg guess (/ x guess)))
;求x和y的平均值
(define (avg x y)
    (/ (+ x y) 2))
;求x的绝对值
(define (abs x)
    (if (< x 0) (- x) x))
;求平方
(define (square x)
    (* x x))
(define (sqrt-iter guess x)
    (if (good-enough? guess x)
        guess
        (sqrt-iter (improve guess x) x)))

;Alyssa P. Hacker doesn’t see why `if` needs to be provided as a special form.
;“Why can’t I just define it as an ordinary procedure in terms of cond?” she asks.
;Alyssa’s friend Eva Lu Ator claims this can indeed be done, and she defines a new version of if:
(define (new-if predicate then-clause else-clause)
    (cond (predicate then-clause)
        (else else-clause)))

;Eva demonstrates the program for Alyssa:
(new-if (= 2 3) 0 5)
;5
(new-if (= 1 1) 0 5)
;0
;Delighted, Alyssa uses new-if to rewrite the square-root
;program:
(define (sqrt-iter guess x)
    (new-if (good-enough? guess x)
        guess
        (sqrt-iter (improve guess x) x)))

;What happens when Alyssa attempts to use this to compute
;square roots? Explain.

;answer: interperter will process forever in death loop
;because Scheme is applicative-order evalution model
;right answer: Chez Scheme is applicative-order evalution,so new-if
;is a function. interperter will evalute all args of function before it evalute
;so interperter will evalute over and over when try to evaluted new-if's (sqrt-iter) args
;because sqrt-iter is a recursion