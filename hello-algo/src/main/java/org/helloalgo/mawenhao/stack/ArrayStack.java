package org.helloalgo.mawenhao.stack;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * 基于数组实现栈
 *
 * @author mawenhao 2023/1/22
 */
public class ArrayStack {
    /**
     * 基于数组实现栈,存储数据容器
     */
    private ArrayList<Integer> array;

    /**
     * 无参构造
     */
    public void Stack() {
        array = new ArrayList<>(16);
    }

    /**
     * 入栈
     *
     * @param element 待入栈元素
     * @return true:操作成功
     */
    boolean push(Integer element) {
        return array.add(element);
    }

    /**
     * 出栈
     *
     * @return 弹出当前栈顶元素
     */
    Integer pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array.remove(size() - 1);
    }

    /**
     * 访问栈顶元素
     *
     * @return
     */
    Integer peek() {
        return array.get(size() - 1);
    }

    /**
     * 获取栈的长度
     *
     * @return 栈的长度
     */
    int size() {
        return array.size();
    }

    /**
     * 判断栈是否为空
     *
     * @return true:栈为空
     */
    boolean isEmpty() {
        return size() == 0;
    }
}
