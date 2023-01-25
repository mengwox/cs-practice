package org.helloalgo.mawenhao;

/**
 * 基于数组,实现队列Queue
 *
 * @author mawenhao 2023/1/25
 */
public class ArrayQueue {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.size());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    private Integer[] nums;

    private int front = 0, rear = 0, size = 0;

    public ArrayQueue() {
        nums = new Integer[16];
    }

    /**
     * poll()    元素出队,将队首的元素移除队列
     *
     * @return
     */
    public Integer poll() {
        checkIndex(front);
        Integer temp = nums[front];
        nums[front++] = null;
        size--;
        return temp;
    }

    /**
     * offer()    元素入队,将元素放在队尾
     *
     * @param element
     * @return
     */
    public boolean offer(Integer element) {
        checkIndex(rear);
        nums[rear++] = element;
        size++;
        return true;
    }

    public int size() {
        return this.size;
    }

    private void checkIndex(int index) {
        if (index >= 0 && index < nums.length) {
            return;
        }
        throw new IndexOutOfBoundsException("index:" + index + "超出数组容器索引!");
    }
}
