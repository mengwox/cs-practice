;Exercise 1.7: The good-enough? test used in computing square roots
;will not be very effective for finding the square roots of very small numbers.
;Also, in real computers, arithmetic operations are almost always performed with limited precision.
;This makes our test inadequate for very large
;numbers. Explain these statements, with examples showing
;how the test fails for small and large numbers.
;An alternative strategy for implementing good-enough?
;is to watch how guess changes from one iteration to the next and to
;stop when the change is a very small fraction of the guess.
;Design a square-root procedure that uses this kind of end test.
;Does this work better for small and large numbers?

;older, perform slower
(define (good-enough? a b)
    (< (abs (- b (square a))) 0.0000001))

;newer, perform faster than older
(define (good-enough? a b)
    (< (abs (- (/ b a) a)) 0.0000001))

(sqrt-iter 1.0 99999999999)

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