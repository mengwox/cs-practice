;Exercise 1.15: e sine of an angle (specified in radians)
;can be computed by making use of the approximation sin x ≈ x
;if x is sufficiently small, and the trigonometric identity
(define (cube x) (* x x x))
(define (p x) (- (* 3 x) (* 4 (cube x))))
(define (sine angle)
    (if (not (> (abs angle) .1))
        angle
        (p (sine (/ angle 3.0)))))
;sine 12.15
;(p (sine 4.05))
;(p (p (sine 1.35)))
;(p (p (p (sine .45))))
;(p (p (p (p (sine .15)))))
;(p (p (p (p (p (sine .05))))))

;a:5
;b:log3(a)->log(n)