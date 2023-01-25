package org.helloalgo.mawenhao;

import java.util.EmptyStackException;

/**
 * 基于链表实现栈
 *
 * @author mawenhao 2023/1/22
 */
public class LinkedStack {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        System.out.println(stack.push(1));
        System.out.println(stack.push(2));
        System.out.println(stack.push(3));
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    // 头结点作为栈顶
    private LinkedNode head;
    // size记录栈的长度
    private int size;

    public LinkedStack() {
        head = new LinkedNode();
        size = 0;
    }

    // 入栈
    public boolean push(Integer element) {
        head = new LinkedNode(head, element);
        size++;
        return true;
    }

    // 出栈
    public Integer pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Integer res = peek();
        head = head.next;
        size--;
        return res;
    }

    // 访问栈顶元素
    public Integer peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return head.element;
    }

    public Integer size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    static class LinkedNode {
        private LinkedNode next;
        private Integer element;

        public LinkedNode() {
        }

        public LinkedNode(Integer element) {
            this.element = element;
        }

        public LinkedNode(LinkedNode next, Integer element) {
            this.next = next;
            this.element = element;
        }
    }
}
