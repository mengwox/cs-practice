;Exercise1.28 Miller-Rabin test
;如果n是一个素数,那么对于任意一个小于n的正整数a(除1和n-1外)
;有:a^(n-1) mod n === 1 mod n

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

(define (remainder-check x n)
  (if (and (not (or (= x 1)
                  (= x (- n 1))))
        (= (remainder (* x x) n) 1))
    0
    (remainder (* x x) n)))

(define (expmod base exp m)
  (cond
    ((= exp 0) 1)
    ((even? exp)
      (remainder-check (expmod base (/ exp 2) m) m))
    (else (remainder-check
            (* base (expmod base (- exp 1) m)) m))))
;miller-rabin随机一个数a,范围:[2,n-2]
(define (miller-rabin-test n)
  (define (try-it a)
    (= (expmod a (- n 1) n) 1))
  (try-it (+ 1 (random (- n 1)))))
;miller-rabin全遍历
(define (miller-rabin-all n)
  (define (try-all n a)
    (cond
      ;遍历到n-1时结束,返回true是素数
      ((= a (- n 1)) #t)
      ;a^(n-1) mod n != 1 mod n 则不是素数,返回false
      ((not (= (expmod a (- n 1) n) 1)) #f)
      ;继续迭代a
      (else (try-all n (+ a 1)))))
  (try-all n 2))

(expmod 29 2 1999)
(remainder-check (expmod 29 1 1999) 1999)
(expmod 29 1 1999)
(remainder-check (remainder-check 29*(expmod 29 0 1999) 1999) 1999)

(remainder-check 29*1 1999)
;测试
;fool number:
;561, 1105, 1729, 2465, 2821, and 6601
(miller-rabin-test 561)
(miller-rabin-test 1105)
(miller-rabin-test 1729)
(miller-rabin-test 2465)
(miller-rabin-test 2821)
(miller-rabin-test 6601)


(miller-rabin-all 561)
(miller-rabin-all 1105)
(miller-rabin-all 1729)
(miller-rabin-all 2465)
(miller-rabin-all 2821)
(miller-rabin-all 6601)
;prime number: 1009, 1013, 1019, 10007, 10009, 100037
(miller-rabin-test 1009)
(miller-rabin-test 1013)
(miller-rabin-test 1019)
(miller-rabin-test 10007)
(miller-rabin-test 10009)
(miller-rabin-test 100037)

(miller-rabin-all 1009)
(miller-rabin-all 1013)
(miller-rabin-all 1019)
(miller-rabin-all 10007)
(miller-rabin-all 10009)
(miller-rabin-all 100037)