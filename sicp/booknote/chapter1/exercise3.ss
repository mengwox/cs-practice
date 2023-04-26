;Exercise 1.3: Define a procedure that takes three numbers as arguments
;and returns the sum of the squares of the two larger numbers.
(define (two-max x y)
    (if (< x y)
        y
        x))
(define (two-min x y)
    (if (< x y)
        x
        y))

(define (three-two-sum x y z)
    (if (and (> x z) (> y z))
        (+ x y)
        (+ (two-max x y) z)))

;测试:以下结果都应该是5,测试通过
; 1,2,3
(three-two-sum 1 2 3)
; 3,3,2
(three-two-sum 3 3 2)
; 3,1,2
(three-two-sum 3 1 2)
; 1,3,2
(three-two-sum 1 3 2)