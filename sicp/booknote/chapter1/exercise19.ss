;Exercise 1.19: There is a clever algorithm for computing
;the Fibonacci numbers in a logarithmic number of steps.
;Recall the transformation of the state variables a and b in
;the fib-iter process of Section 1.2.2: a ← a +b and b ← a.
;where Tpq transforms the pair (a, b) according to a ← bq + aq + ap and b ← bp + aq
;TODO 解法想不出来,涉及数学知识
(define (fib n)
  (fib-iter 1 0 0 1 n))
(define (fib-iter a b p q count)
  (cond
    ((= count 0) b)
    ((even? count)
      (fib-iter a
        b
        (+ (* p p) (* q q))
        (+ (* 2 p q) (* q q))
        (/ count 2)))
    (else (fib-iter
            (+ (* b q) (* a q) (* a p))
            (+ (* b p) (* a q))
            p
            q
            (- count 1)))))