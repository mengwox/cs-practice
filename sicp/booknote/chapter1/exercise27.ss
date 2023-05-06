;当前时间,单位:纳秒
(define (runtime)
  (let ((time (current-time)))
    (+ (* (inexact->exact (floor (time-second time))) 1000000000)
      (inexact->exact (floor (time-nanosecond time))))))

;求x的平方
(define (square x)
  (* x x))

;判断n是否是偶数
(define (even? n)
  (= (remainder n 2) 0))

;费马小定理: 在a < n的条件下,
;如果false: a^n mod n != a mod n
;如果true: a^n mod n === a mod n
;(expmod a n n)
(define (expmod base exp m)
  (cond
    ((= exp 0) 1)
    ((even? exp)
      (remainder
        (square (expmod base (/ exp 2) m))
        m))
    (else
      (remainder
        (* base (expmod base (- exp 1) m))
        m))))
;Exercise 1.27: Demonstrate that the Carmichael numbers
;listed in Footnote 1.47 really do fool the Fermat test.
;That is, write a procedure that takes an integer n
;and tests whether a^n is congruent to a modulo n for every a < n, and
;try your procedure on the given Carmichael numbers.
;费马小定理-测试方法
(define (fermat-test n)
  (define (try-it a)
    (= (expmod a n n) a))
  (define (try-all n start)
    (if (not (try-it start))
      (display "done")
      (try-it (+ start 1))))
  (try-all n 2))

(define (fermat-test n)
  (define (try-all n a)
    (cond
      ;遍历结束
      ((= a n) #t)
      ;a^n mod n != a mod n
      ((not (= (expmod a n n) a)) #f)
      (else (try-all n (+ a 1)))))
  (try-all n 2))

;测试
;正常素数:1999,期望结果:#t
(fermat-test 1999)
;正常非素数:6603,期望结果:done/#f
(fermat-test 6603)
;Carmichael numbers: 561, 1105, 1729, 2465, 2821, and 6601
(fermat-test 561)
(fermat-test 1105)
(fermat-test 1729)
(fermat-test 2465)
(fermat-test 2821)
(fermat-test 6601)