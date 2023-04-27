;Exercise 1.8:
;Newton’s method for cube roots is based on
;the fact that if y is an approximation to the cube root of x,
;then a better approximation is given by the value:
;[x/(y*y) + 2y]/3
(define (cube-root x y)
    (/ (+ (* 2 y) (/ x (* y y))) 3))

;newer, perform faster than older
(define (good-enough? a b)
    (< (abs (- (/ b (square-xn a 2)) a)) 0.000000001))

;优化预期值y,以y作为x的3次方根
(define (improve y x)
    (/ (+ (* 2 y) (/ x (square-xn y 2))) 3))
;定义一个函数名为square-xyn,计算y=x的n次方
(define (square-xyn x y n)
    (if (< n 1)
        y
        (square-xyn x (* x y) (- n 1))))
;定义个函数名为square-xn,调用square-xyn函数,并初始化y=1
(define (square-xn x n)
    (square-xyn x 1 n))
;求x的绝对值
(define (cube-iter guess x)
    (if (good-enough? guess x)
        guess
        (cube-iter (improve guess x) x)))

(cube-iter 1.0 99999999999)
(cube-iter 1.0 0.001)

;formal variables also called 'a bound variable'
;but, such as < > = improve, those aren't a bound variable. they called 'free variable'