package org.helloalgo.mawenhao.queue;

import java.util.EmptyStackException;

/**
 * 基于链表,实现队列
 *
 * @author mawenhao 2023/1/22
 */
public class LinkedListQueue {
    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();
        System.out.println(queue.isEmpty());
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    // 头结点, 队首
    private LinkedNode head;
    // 尾结点, 队尾
    private LinkedNode last;
    // 记录队列的长度
    private int size = 0;

    /**
     * offer()    元素入队,将元素放在队尾
     *
     * @param element
     * @return
     */
    public boolean offer(Integer element) {
        LinkedNode node = new LinkedNode(element);
        if (last != null) {
            // 队列不为空,从队尾开始添加
            last.next = node;
            last = node;
        } else {
            // 初始化
            head = node;
            last = node;
        }
        size++;
        return true;
    }

    /**
     * poll()    元素出队,将队首的元素移除队列
     *
     * @return
     */
    public Integer poll() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        // 队列不为空,从队首开始删除元素
        LinkedNode temp = head;
        head = head.next;
        size--;
        return temp.element;
    }

    /**
     * peek()    访问队首的元素
     *
     * @return
     */
    public Integer peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return head.element;
    }

    /**
     * size()    获取队列的长度
     *
     * @return
     */
    public Integer size() {
        return this.size;
    }

    /**
     * isEmpty()    判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    static class LinkedNode {
        private LinkedNode next;
        private Integer element;

        public LinkedNode(LinkedNode next, Integer element) {
            this.next = next;
            this.element = element;
        }

        public LinkedNode(LinkedNode next) {
            this.next = next;
        }

        public LinkedNode(Integer element) {
            this.element = element;
        }
    }
}
