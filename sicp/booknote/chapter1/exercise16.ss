;Exercise 1.16: Design a procedure that evolves an iterative exponentiation process that
;uses successive squaring and uses a logarithmic number of steps, as does fast-expt.

;a*b^n
;when n is even, a*b^n= a*(b*b)^(n/2)
;a'=a,b'=b*b,n'=n/2
;when n is odd, a*b^n=(a*b)*b^(n-1)=(a*b)*(b*b)^(n-1)/2
;a'=a*b,b'=b*b,n'=(n-1)/2
(define (fast-expt a b n)
    (cond
        ((= n 0) a)
        ((even? n) (fast-expt a (square b) (/ n 2)))
        (else (fast-expt (* a b) b (- n 1)))))