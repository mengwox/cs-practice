package org.helloalgo.mawenhao.queue;

/**
 * 进阶: 基于数组,实现环状队列Queue
 *
 * @author mawenhao 2023/1/25
 */
public class AnnularArrayQueue {
    public static void main(String[] args) {
        AnnularArrayQueue queue = new AnnularArrayQueue(16);
        for (int i = 0; i < 32; i++) {
            queue.offer(i);
        }
        System.out.println(queue.size());
    }

    private Integer[] nums;

    private int front = 0, rear = 0, capacity = 0;

    public AnnularArrayQueue(int capacity) {
        this.capacity = capacity;
        nums = new Integer[this.capacity];
    }

    public int capacity() {
        return nums.length;
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
        return true;
    }

    public int size() {
        // 这里存在问题, 拓展: 实现数组的扩容机制
        return (capacity + rear - front) % capacity;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= nums.length) {
            System.out.println("index:" + index + "超出数组容器索引!");
            front = front % 16;
            rear = rear % 16;
        }
    }
}
