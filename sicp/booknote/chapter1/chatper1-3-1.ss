;Procedures as Arguments
;求a~b的和
(define (sum-intergers a b)
  (if (> a b)
    0
    (+ a (sum-integers (+ a 1) b))))

;求a~b的立方和
(define (cube x)
  (* x x x))
(define (sum-cubes a b)
  (if (> a b)
    0
    (+ (cube a) (sum-cubes (+ a 1) b))))

;求a~b的π/8和
(define (pi-sum a b)
  (if (> a b)
    0
    (+ (/ 1.0 (* a (+ a 2)))
      (pi-sum (+ a 4) b))))

;通用格式
(define (<name> a b)
  (if (> a b)
    0
    (+ (<term> a)
      (<name> (<next> a) b))))
;所以,通用格式函数定义如下
(define (sum term next a b)
  (if (> a b)
    0
    (+ (term a)
      (sum term next (next a) b))))

;那么sum-integers可以转成
(define (inc x) (+ x 1))
(define (identity x) x)
(define (sum-integers a b)
  (sum identity inc a b))

;sum cube
(define (sum-cube a b)
  (sum cube inc a b))
;sum-pi
(define (sum-pi a b)
  (define (term-pi a)
    (+ (/ 1.0 (* a (+ a 2)))))
  (define (next-pi a) (+ a 4))
  (sum term-pi next-pi a b))

(* 8 (sum-pi 1 1000000))