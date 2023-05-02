;Exercise 1.12: e following pattern of numbers is called Pascal’s triangle.
;The numbers at the edge of the triangle are all 1,
;and each number inside the triangle is the sum of the two numbers above it.
;Write a procedure that computes elements of Pascal’s triangle by means of a recursive process.
;pascal(n, x)= pascal(n-1, x) + pascal(n-1,x-1);
(define (pascal x y)
    (cond
        ((< x 0) 0)
        ((or (= y 0) (= x y)) 1)
        (else (+ (pascal (- x 1) (- y 1)) (pascal (- x 1) y)))))