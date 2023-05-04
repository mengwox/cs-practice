;Exercise 1.18:
;Using the results of Exercise 1.16 and Exercise 1.17, devise a procedure that generates an iterative process
;for multiplying two integers in terms of adding, doubling, and halving and uses a logarithmic number of steps.
(define (even? n)
    (= (remainder n 2) 0))
;when b is even, a*b=2*a*(b/2)
;a'=2*a,b'=b/2
;when b is odd, a*b+c=a+c+a*(b-1)
(define (halve x) (/ x 2))
(define (double x) (* x 2))
;a*b+c
(define (mul-iter a b c)
    (cond
        ((= b 0) 0)
        ((even? b) (mul-iter (double a) (halve b) c))
        (else (mul-iter a (- b 1) (+ c a)))))