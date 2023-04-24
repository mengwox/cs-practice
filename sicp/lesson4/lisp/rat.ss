;+RAT
;n1/d1 + n2/d2 = (n1*d2 + n2*d1) / (d1*d2)
;
;(MAKE-RAT N D) --> (F N D)
;(NUMBER (F N D)) --> N
;(DENOM (F N D)) --> D

;+RAT
(define (+rat x y)
    (make-rat
        (+ (* (number x) (denom y))
            (* (number y) (denom x)))
        (* (denom x) (denom y))))

;*RAT
;n1/d1 * n2/d2 = (n1*n2) / (d1*d2)
(define (*rat x y)
    (make-rat
        (* (number x) (number y))
        (* (denom x) (denom y))))

(define (make-rat n d)
    (cons n d))

(define (number x)
    (car x))

(define (denom x)
    (cdr x))

;demo: 1 / 2 + 1 / 4
(define a (make-rat 1 2))
(define b (make-rat 1 4))
(define ans (+rat a b))

;(NUMBER ANS) --> 6
;(DENOM ANS) --> 8
;存在的问题: 没有化简
;对procedure:MAKE-RAT ,添加化简
(define (make-rat n d)
    (let (g gcd (n d)))
    (cons (/ n g) (/d g)))

;思想:
;不要想着在大型系统中 ,先行完全想好实现的细节.而是要先想好这个实现是用来做什么的?
;即 ,面向接口编程.
;
;LISP语法:
;自带procedure:CONS ,CAR ,CDR ,LET
;(CONS X Y) 用来表示一对序列队.第一部分 (CAR) 指向X ,第二部分 (CDR) 指向Y ;
;(CAR (CONS X Y)) --> X
;(CDR (CONS X Y)) --> Y
;
;LET ,可以理解为局部上下文. 只在当前定义的procedure有效.
;DEFINE ,可以理解为全局上下文 ,可以在整个LISP环境中有效.
;
;
;=============================================LISP实现向量=============================================
;make-vector代表一个x坐标 ,y坐标组成的点
(define (make-vector x y)
    (cons x y))

;XCONS 对 点S ,返回S的x坐标
(define (xcons s)
    (car s))

;YCONS 对 点S ,返回S的y坐标
(define (ycons s)
    (cdr s))

;MAKE-SEG ,代表一个由 点P 到 点Q 的向量
(define (make-seg p q)
    (cons p q))

(define (seg-start s)
    (car s))
(define (seg-end s)
    (cdr s))

;求点P到点Q的线段 ,中点的坐标
(define (midpoint s)
    (let
        a (seg-start s)
        b (seg-end s))
    (make-vector
        (average (xcons a) (xcons b))
        (average (ycons a) (ycons b))))

;求点P到点Q的线段 ,长度是多少
(define (length s)
    (let
        a (seg-start s)
        b (seg-end s)
        dx (- (xcons b) (xcons a))
        dy (- (ycons b) (ycons a)))
    (sqrt (+ (square dx) (square dy))))

(define (length s)
    (let
        dx (- (car (cdr s)) (car (car s)))
        dy (- (cdr (cdr s)) (cdr (car s))))
    (sqrt (+ (square dx) (square dy))))