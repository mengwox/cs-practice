;Fibonacci斐波那契数列:
;f(x+2) = f(x+1) + f(x)
;f(0) = 0;
;f(1) = 1;
;
;such like:
;f(x):
;0 1 1 2 3 5 8 13 21 34 ...
;x:
;0 1 2 3 4 5 6 7  8  9  ...
;
;Recursion of Fibonacci: time = O(fib(n)), space = O(n).效率低下
(define (fib x)
    (if (< x 2)
        x
        (+ (fib (- x 1))
            (fib (- x 2)))))

;Iteration of Fibonacci: 斐波那契数列, 迭代模型实现
;x是斐波那契数列的第x项. 要知道f(x)的值,就需要知道f(x-1)和f(x-2)的值.
;定义y从2开始. p为第y-1项的值, pp为第y-2项的值
(define (fib x)
    (define (try-fib y p pp)
        (cond
            ((< x 2) x)
            ((< x y) p)
            ((= x y) (+ p pp))
            ((> x y) (try-fib (1 + y) (+ p pp) p))))
    (try-fib 2 1 0))

;验证成功