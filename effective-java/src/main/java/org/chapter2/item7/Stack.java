package org.chapter2.item7;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.WeakHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 只要类是自己管理内存, 就应该警惕内存泄漏问题. 元素一旦被释放, 则该元素中包含的任何对象引用都应该被清空.
 *
 * @author mawenhao 2022/10/28
 */
public class Stack {
    private Object[] elements;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object element = elements[--size];
        //设置为null,方便GC
        elements[size] = null;
        return element;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public static void main(String[] args) {
        WeakHashMap<String, String> map = new WeakHashMap<>();
    }
}
