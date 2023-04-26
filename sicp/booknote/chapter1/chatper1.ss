(define pi 3.14159)

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

