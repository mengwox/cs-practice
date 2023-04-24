定义名为average的procedure: 计算x和y的平均值
(define (average x y) (/ (+ x y) 2))

定义名为mean-square的procedure: 计算x的平方和y的平方之和的平均值
(define (mean-square x y)
    (average (square x) (square y)))

定义procedure: 绝对值
(define (abs x)
    (cond (< x 0) (- x))
    (cond (= x 0) (0))
    (cond (> x 0) (x)))

或
(define (abs x)
    (cond
        ((< x 0) (- x))
        ((= x 0) 0)
        ((> x 0) x)))

或
(define (abs x)
    (if (< x 0)
        (- x)
        x))

procedure定义:计算平方
(define (square x) (* x x))

procedure定义:计算平均值
(define (average x y) (/ (+ x y) 2))

procedure定义:计算一个数的绝对值
(define (abs x)
    (if (< x 0)
        (- x)
        x))