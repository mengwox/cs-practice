;Exercise 1.29 Simpson's Rule
;通用格式函数定义如下
(define (sum term next a b)
  (if (> a b)
    0
    (+ (term a)
      (sum term next (next a) b))))