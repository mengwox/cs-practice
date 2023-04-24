;计算平方根
;step1:计算x/y, 判断x/y与y的差的绝对值是否<0.000001
;step2: 如果是, 则返回y
;step3: 如果不是, 则y= (x/y + y) / 2, 重新从step1开始

;procedure定义:计算guess与x之差的绝对值是否足够好(小于0.001)
(define (good-enough? guess x)
  (< (abs (- (square guess) x))
    0 .001))

;procedure定义:改善guess
(define (improve guess x)
  (average guess (/ x guess)))

;procedure定义:尝试使用guess值,计算x的平方根
(define (try guess x)
  (if (good-enough? guess x)
    guess
    (try (improve guess x) x)))

;procedure定义:计算平方根
(define (sqrt x)
  (try 1 x))

;procedure定义:计算平方根, 完全封装写法:

;procedure1:
(define (abs x)
  (if (< x 0)
    (- x)
    x))

;procedure2:
(define (square x) (* x x))

;procedure3:
(define (average x y) (/ (+ x y) 2))

;procedure4:
(define (sqrt x)
  (define (improve guess)
    (average guess (/ x guess)))
  (define (good-enough? guess)
    (< (abs (- (square guess) x))
      0 .001))
  (define (try guess)
    (if (good-enough? guess)
      guess
      (try (improve guess))))
  (try 1))