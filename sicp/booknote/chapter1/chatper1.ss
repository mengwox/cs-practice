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
;   ...
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
; (and <e1> ... <en>)
; (or <e1> ... <en>)
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
    (< (sqrt-abs (- b (square a))) 0.0001))

;牛顿法求y的平方根,并以x作为预期值
(define (sqrt-pre x y)
    (if (good-enough? x y)
        x
        (sqrt-pre (improve x y) y)))

;求平方根
(define (my-sqrt y)
    (sqrt-pre 1.0 y))

;教材标准写法
;最外层,求x的平方根
(define (sqrt x)
    (sqrt-iter 1.0 x))
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
    (< (abs (- b (square a))) 0.001))
