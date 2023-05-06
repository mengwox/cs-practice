;准备工作

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
;费马小定理-测试方法
(define (fermat-test n)
  (define (try-it a)
    (= (expmod a n n) a))
  (try-it (+ 1 (random (- n 1)))))
;fool number:
;561, 1105, 1729, 2465, 2821, and 6601

;判断n是否是素数,并执行times次fermat-test函数
(define (fast-prime? n times)
  (cond ((= times 0) #t)
    ((fermat-test n) (fast-prime? n (- times 1)))
    (else #f)))

;Exercise 1.22:
;Most Lisp implementations include a primitive called runtime
;that returns an integer that specifies the amount of time the system has been running
;(measured, for example, in microseconds). The following timedprime-test procedure
;, when called with an integer `n`, prints `n` and checks to see if `n` is prime.
;If n is prime, the procedure prints three asterisks followed by
;the amount of time used in performing the test
(define (report-prime elapsed-time)
  (display " *** ")
  (display elapsed-time))

(define (start-prime-test n start-time)
  (if (fast-prime? n 10)
    (report-prime (- (runtime) start-time))
    "nothing"))

(define (timed-prime-test n)
  (newline)
  (display n)
  (start-prime-test n (runtime)))

;对于range[start~end]的数,打印出其中的素数
(define (search-for-primes start-range end-range)
  (if (even? start-range)
    ;偶数不做测试是否是素数,直接+1变为奇数
    (search-for-primes (+ start-range 1) end-range)
    (cond
      ((> start-range end-range)
        (newline)
        (display "done"))
      (else (timed-prime-test start-range) (search-for-primes (+ start-range 2) end-range)))))

;测试
(timed-prime-test 1999)

;测试结果
;1009 *** 1600
;1013 *** 1300
;1019 *** 1400
(search-for-primes 1000 1019)
(search-for-primes 1009 1009)
;10007 *** 3000
;10009 *** 2700
;10037 *** 4000
(search-for-primes 10000 10037)
;100003 *** 9900
;100019 *** 9800
;100043 *** 9100
(search-for-primes 100000 100043)
;1000003 *** 27800
;1000033 *** 26200
;1000037 *** 26200
(search-for-primes 1000000 1000037)
;10000019 *** 81500
;10000079 *** 75200
;10000103 *** 69800
(search-for-primes 10000000 10000103)
;100000007 *** 257500
;100000037 *** 255500
;100000039 *** 256700
(search-for-primes 100000000 100000039)
;1000000007 *** 954400
;1000000009 *** 843600
;1000000021 *** 813000
(search-for-primes 1000000000 1000000021)
;10000000019 *** 1824700
;10000000033 *** 1870200
;10000000061 *** 1804600
(search-for-primes 10000000000 10000000061)