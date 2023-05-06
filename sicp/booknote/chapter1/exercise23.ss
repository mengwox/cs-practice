;准备工作

;当前时间,单位:纳秒
(define (runtime)
  (let ((time (current-time)))
    (+ (* (inexact->exact (floor (time-second time))) 1000000000)
      (inexact->exact (floor (time-nanosecond time))))))

;求x的平方
(define (square x)
  (* x x))

;判断b是否被a整除
(define (divides? b a)
  (= (remainder b a) 0))

;判断n是否是偶数
(define (even? n)
  (= (remainder n 2) 0))

;迭代n
(define (next n)
  (if (= n 2)
    3
    (+ n 2)))

(define (find-diviors n test-diviors)
  (cond
    ;如果test-diviors的平方 大于 n,则返回n
    ((> (square test-diviors) n) n)
    ;n能被test-diviors整除,则返回test-diviors作为最小公倍数
    ((divides? n test-diviors) test-diviors)
    ;否则,test-diviors自增1,继续迭代find-diviors
    (else (find-diviors n (next test-diviors)))))

(define (smallest-diviors n)
  (find-diviors n 2))

(define (prime? n)
  (= (smallest-diviors n) n))

(define (report-prime elapsed-time)
  (display " *** ")
  (display elapsed-time))

(define (start-prime-test n start-time)
  (if (prime? n)
    (report-prime (- (runtime) start-time))
    "nothing"))

(define (timed-prime-test n)
  (newline)
  (display n)
  (start-prime-test n (runtime)))

;测试
(timed-prime-test 1999)

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

;测试结果
;1009 *** 1600
;1013 *** 1300
;1019 *** 1400
(search-for-primes 1000 1019)
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