(define make-vector cons)
(define xcons car)
(define ycons cdr)

;两个向量之和:
(define (+vect v1 v2)
    (make-vector
        (+ (xcons v1) (xcons v2))
        (+ (ycons v1) (ycons v2))))

;缩放向量:
(define (scale s v)
    (make-vector
        (* s (xcons v))
        (* s (ycons v))))

;线段:
(define make-segment cons)
(define seg-start car)
(define seg-end cdr)

;一个起点为(2,3), 终点为(5,1)的线段
(make-segment
    (make-vector 2 3)
    (make-vector 5 1))

;具有闭包性质的数组:
(cons 1
    (cons 2
        (cons 3
            (cons 4 nil))))

(define 1 -to-4 (list 1 2 3 4))

(car (cdr 1 -to-4))

;在Chez Scheme, 用'()表示nil
;也可以:定义Procedure名为nil
(define nil '())

;将数组中每个元素缩放后,构成一个新的数组
;s:缩放Procedure, l:原数组
(define (scale-list s l)
    (if (null? l)
        nil
        (cons
            (s (car l))
            (scale-list s (cdr l)))))

(define (scale-list s l)
    (if (null? l)
        nil
        (cons
            (* s (car l))
            (scale-list s (cdr l)))))

;遍历LIST:
(define (for-each proc list)
    (cond ((null? list) "done")
        (else (proc (car list))
            (for-each proc (cdr list)))))

;Map结构: 将一个List, 构建为Map.
(define (map p l)
    (if (null? l)
        nil
        (cons (p (car l))
            (map p (cdr l)))))

(map (lambda (x) (+ 10 x)) 1 -to-4)

;====================================================================
;一门新的语言, 基本元素只有一个,那就是图像.
;
;操作:
;Rotate: 旋转90°
;Flip: 翻转
;Beside: 给定2个图像, 根据double值,确定第一个图像占用的面积(水平)
;    例如: BESIDE A B 0.5, 意味着A和B图像,都占用一半的面积
;ABOVE: 给定2个图像, 根据double值,确定第一个图像占用的面积(竖直)

(beside g
    (above empty g .5)
    .5)

(define p
    (beside g
        (rot-180 (flip g))
        .5))

(define q
    (above p (flip p) .5))

;====================================================================
;构成矩形RECTANGLE的3个要素:
;1. ORIGIN, 原点
;2. HORIZ, 矩形的水平向量. the horizontal part of rectangle
;3. VERT, 矩形的竖直向量. the vertical part of rectangle
;
;make-rect
;horiz
;origin
;vert
;
;正方形-->缩放成:矩形, 线性代数变换公式如:
;(origin, x,y)--> (origin, x*horiz, y*vert)
;
;正方形-->缩放成:矩形, LISP解释:
(define (coord-map rect)
    (lambda (point)
        (+vect
            (+vect
                (scale (xcons point) (horiz rect))
                (scale (ycons point) (vert rect)))
            (origin rect))))

;对一个名为seglist的线段数组, 画图:
(define (make-picture seglist)
    (lambda (rect)
        (for-each
            (lambda (seg)
                (drawline
                    ((coord-map rect) (seg-start seg))
                    ((coord-map rect) (seg-end seg))))
            seglist)))

;BESIDE, Lisp实现代码:
(define (beside p1 p2 a)
    (lambda (rect)
        (p1 (make-rect
                (origin rect)
                (scale a (horiz rect))
                (vert rect)))
        (p2 (make-rect
                (+vect
                    (origin rect)
                    (scale a (horiz rect)))
                (scale (- 1 a) (horiz rect))
                (vert rect)))))

;ROTATE, Lisp实现代码:
;逆时针旋转90°:
(define (rotate90 pict)
    (lambda (rect)
        (pict (make-rect
                  (+vect (origin rect)
                      (horiz rect))
                  (vert rect)
                  (scale -1 (horiz rect))))))
