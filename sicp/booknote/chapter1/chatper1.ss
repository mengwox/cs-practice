(define pi 3 .14159)

(define radius 10)

;圆的面积
(* pi (* radius radius))

;圆的周长
(* 2 (* pi radius))

(define circumference (* 2 (* pi radius)))

; 1.1.6 Conditional Expressions and Predicates
; 表达式及判断
; cond条件语句格式:
; (cond
;   (<p1> <e1>)
;   (<p2> <e2>)
;  ...
;   (<pn> <en>))
; 例如,求一个数的绝对值
(define (abs-cond x)
  (cond
    ((< x 0) (- x))
    ((= x 0) 0)
    ((> x 0) x)))

;测试
(abs-cond 10)
(abs-cond -10)
(abs-cond 0)

; if条件语句格式: (if (predicate) (consequent) (alternative))
; (if (条件判断) (判断结果true时) (判断结果false时))
(define (abs-if x)
  (if (< x 0)
    (- x)
    x))

;测试
(abs-if 10)
(abs-if -10)
(abs-if 0)

; and or not 与或非
; (and <e1>... <en>)
; (or <e1>... <en>)
; (not <e>)

; 1.1.7 Example:Square Roots By Newton's Method
; 牛顿法求平方根

;求平方
(define (square x)
  (* x x))
;优化预期值x=(x+y)/2效率太低-->优化为:x=(x+y/x)/2
(define (improve x y)
  (/ (+ x (/ y x)) 2))
(define (sqrt-abs x)
  (if (< x 0) (- x) x))
;判断预期值的精度是否足够好
(define (good-enough? a b)
  (< (sqrt-abs (- b (square a))) .0001))

;牛顿法求y的平方根,并以x作为预期值
(define (sqrt-pre x y)
  (if (good-enough? x y)
    x
    (sqrt-pre (improve x y) y)))

;求平方根
(define (my-sqrt y)
  (sqrt-pre 1 .0 y))

;教材标准写法
;最外层,求x的平方根
(define (sqrt x)
  (sqrt-iter 1 .0 x))
;设置预期值guess,递归计算x的平方根
(define (sqrt-iter guess x)
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x) x)))
;优化guess预期值
(define (improve guess x)
  (avg guess (/ x guess)))
;求x和y的平均值
(define (avg x y)
  (/ (+ x y) 2))
;求x的绝对值
(define (abs x)
  (if (< x 0) (- x) x))
;求平方
(define (square x)
  (* x x))
;判断guess是否达到预期
(define (good-enough? a b)
  (< (abs (- b (square a))) .001))

;Internal definitions and block structure
(define (sqrt x)
  (define (sqrt-iter guess)
    (if (good-enough? guess)
      guess
      (sqrt-iter (improve guess))))
  (define (good-enough? guess)
    (< (abs (- (/ x guess) guess)) .0000001))
  (define (improve guess)
    (avg (/ x guess) guess))
  (define (avg a b) (/ (+ a b) 2))
  (sqrt-iter 1 .0))
;如上的写法,可以避免当有其他函数也叫good-enough?时的迷惑
;这样的写法结构叫做'block structure'
;其实和java的匿名内部class类似
;local name 和 free name的区别
;applicative order model evalution和normal order model evalution的区别

;Figure1.4, 求n的阶乘
;尾递归实现
(define (factorial n)
  (if (= n 1)
    1
    (* n (factorial (- n 1)))))
;线性递归实现
;a linear recursive process
(define (factorial n)
  (define (fact-iter res n)
    (if (< n 2)
      res
      (fact-iter (* res n) (- n 1))))
  (fact-iter 1 n))
;线性递归,从小到大计算
(define (factorial n)
  (define (iter product counter)
    (if (> counter n)
      product
      (iter
        (* product counter)
        (+ counter 1)
        n)))
  (fact-iter 1 1 n))

;1.2.2 Tree Recursion
;树递归
;Fibonacci numbers斐波那契数列
(define (fib n)
  (cond
    ((= n 0) 0)
    ((= n 1) 1)
    (else (+ (fib (- n 1))
            (fib (- n 2))))))

(define (fib n)
  (fib-iter 1 0 n))

(define (fib-iter a b count)
  (if (= count 0)
    b
    (fib-iter (+ a b) a (- count 1))))

;Example:Counting change
;How many different ways can we make change of $1.00, given half-dollars, quarters, dimes, nickels, and pennies?
;1美元有多少种组合方式? 由50美分,25美分,10美分,5美分,1美分组成
(define (count-change amount)
  (cc amount 5))
(define (cc amount kinds-of-coins)
  (cond ((= amount 0) 1)
    ((or (< amount 0) (= kinds-of-coins 0)) 0)
    (else (+ (cc amount
               (- kinds-of-coins 1))
            (cc (- amount
                  (first-denimination kinds-of-coins))
              kinds-of-coins)))))

(define (first-denimination kinds-of-coins)
  (cond ((= kinds-of-coins 1) 1)
    ((= kinds-of-coins 2) 5)
    ((= kinds-of-coins 3) 10)
    ((= kinds-of-coins 4) 25)
    ((= kinds-of-coins 5) 50)))
;请优化以上代码,采用线性递归解决问题
;TODO

;1.2.3 Orders of Growth
;Exercise 1.14,1.15
;1.2.4 Exponentiation
;求幂
(define (expt b n)
  (if (= n 0)
    1
    (* b (expt b (- n 1)))))

(define (expt b n)
  (define (expt-iter b res n)
    (if (= n 0)
      res
      (expt-iter b (* res b) (- n 1))))
  (expt-iter b 1 n))
;n/2整除,则expt b n = (expt b (n/2))的平方
;n/2不整除,则expt b n = (expt b (n-1)/2)的平方 * b
(define (fast-expt b n)
  (cond
    ((= n 0) 1)
    ((even? n) (square (fast-expt b (/ n 2))))
    (else (* b (fast-expt b (- n 1))))))
(define (even? n)
  (= (remainder n 2) 0))

;1.2.5 Greatest Common Divisors
;求最大公约数GCD
(define (gcd a b)
  (if (= b 0)
    a
    (gcd b (remainder a b))))

;(gcd 206 40)
;the normal-order evaluation 18次
;详细trace见https://sicp-solutions.net/post/sicp-solution-exercise-1-20/
;the applicative-order evaluation 4次
(gcd 206 40)
(gcd 40 6) ;-->1次
(gcd 6 4) ;-->1次
(gcd 4 2) ;-->1次
(gcd 2 0) ;-->1次

;1.2.6 Example:Testing for Primality

;求x的平方
(define (square x)
  (* x x))

;判断b是否被a整除
(define (divides? b a)
  (= (remainder b a) 0))

;迭代n
(define (next n)
  (if (= n 2)
    3
    (+ n 2)))

;以test-diviors作为测试数据,测试是否是n的最小公倍数
(define (find-diviors n test-diviors)
  (cond
    ;如果test-diviors的平方 大于 n,则返回n
    ((> (square test-diviors) n) n)
    ;n能被test-diviors整除,则返回test-diviors作为最小公倍数
    ((divides? n test-diviors) test-diviors)
    ;否则,test-diviors自增1,继续迭代find-diviors
    (else (find-diviors n (next test-diviors)))))

;求n的最小公倍数
;时间复杂度n的平方根
(define (smallest-diviors n)
  (find-diviors n 2))

;判断n是否是素数
(define (prime? n)
  (= (smallest-diviors n) n))

;费马小定理 Fermat's Little Theorem
;如果n是一个素数, 且a是一个任意的小于n的随机数
;那么a^n mod n === a mod n
;congruent mod函数定义
;base^m mod m === base mod m
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

;判断n是否是素数,并执行times次fermat-test函数
(define (fast-prime? n times)
  (cond ((= times 0) #t)
    ((fermat-test n) (fast-prime? n (- times 1)))
    (else #f)))