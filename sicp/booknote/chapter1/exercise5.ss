;Exercise 1.5:
;Ben Bitdiddle has invented a test to determine
;whether the interpreter he is faced with is using applicativeorder evaluation or normal-order evaluation.
;He defines the following two procedures:
(define (p) (p))
(define (test x y)
    (if (= x 0) 0 y))
;Then he evaluates the expression
(test 0 (p))
;What behavior will Ben observe with an interpreter that
;uses applicative-order evaluation? What behavior will he
;observe with an interpreter that uses normal-order evaluation? Explain your answer.
;(Assume that the evaluation rule for the special form
; if is the same whether the interpreter is using normal or applicative order:
; e predicate expression is evaluated first, and the result determines
;whether to evaluate the consequent or the alternative expression.)

;定义了一个名为p的函数,返回它自己
;定义了一个名为test的函数,入参有2个:x和y. 如果x==0,返回0;否则,返回y
;(test 0 (p))
;执行结果是死循环
;说明Lisp方言Scheme的interpreter用的evalution model is applicative-order