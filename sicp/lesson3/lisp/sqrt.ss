(define (average x y)
    (/ (+ x y) 2))

(define average-damp
    (lambda (f)
        (lambda (x) (average (f x) x))))
;等同于:
(define (average-damp f)
    (define (foo x)
        (average (f x) x))
    foo)

(define (fixed-point f start)
    (define tolerance 0 .00001)
    (define (close-enough? u v)
        (< (abs (- u v)) tolerance))
    (define (iter old new)
        (if (close-enough? old new)
            new
            (iter new (f new))))
    (iter start (f start)))


(define (sqrt x)
    (fixed-point
        (average-damp (lambda (y) (/ x y)))
        1))
