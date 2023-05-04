;求最大公约数GCD
(define (gcd a b)
  (if (= b 0)
    a
    (gcd b (remainder a b))))

;Exercise1.1.20:
;How many remainder operations are actually performed in the normal-order evaluation of (gcd 206 40)?
;In the applicative-order evaluation?
;Answer:
;(gcd 206 40)
;the normal-order evaluation 18次
;详细trace见https://sicp-solutions.net/post/sicp-solution-exercise-1-20/
;the applicative-order evaluation 4次
(gcd 206 40)
(gcd 40 6);-->1次
(gcd 6 4);-->1次
(gcd 4 2);-->1次
(gcd 2 0);-->1次