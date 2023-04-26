;Exercise 1.4:
;Observe that our model of evaluation allows
;for combinations whose operators are compound expressions. Use this observation to describe the behavior of the
;following procedure:
(define (a-plus-abs-b a b)
    ((if (> b 0) + -) a b))

; 定义一个a-plus-abs-b函数,入参有2个:a,b
; 如果b>0,返回操作符+;否则返回操作符-
; 结果为 a + |b|
; 即a与b的绝对值相加