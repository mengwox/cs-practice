;Exercise 1.17: e exponentiation algorithms in this section are based on performing exponentiation by means of
;repeated multiplication. In a similar way, one can perform
;integer multiplication by means of repeated addition. e
;following multiplication procedure (in which it is assumed
;that our language can only add, not multiply) is analogous
;to the expt procedure:
(define (mul-iter a b)
    (if (= b 0)
        0
        (+ a (mul-iter a (- b 1)))))

(define (even? n)
    (= (remainder n 2) 0))
;when b is even, a*b=2*a*(b/2)
;a'=2*a,b'=b/2
;when b is odd, a*b=a+a*(b-1)
(define (halve x) (/ x 2))
(define (double x) (* x 2))
(define (mul-iter a b)
    (cond
        ((= b 0) 0)
        ((even? b) (mul-iter (double a) (halve b)))
        (else (+ a (mul-iter a (- b 1))))))
