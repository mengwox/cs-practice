;Exercise 1.11: A function f is defined by the rule that
;if n < 3, f(n)=n
;if n >= 3, f(n)=f(n-1)+f(n-2)+f(n-3)
;Write a procedure that computes f by means of a recursive process.
(define (f n)
    (if (< n 3)
        n
        (+ (f (- n 1)) (f (- n 2)) (f (- n 3)))))
;Write a procedure that computes f by means of an iterative process.
(define (f n)
    (define (f-iter a b c n)
        (cond
            ((= n 0) a)
            ((= n 1) b)
            ((= n 2) c)
            (else (f-iter b c (+ a b c) (- n 1)))))
    (f-iter 0 1 2 n))