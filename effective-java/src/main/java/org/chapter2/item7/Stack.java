package org.chapter2.item7;

import lombok.SneakyThrows;
import org.chapter3.item10.PhoneNumber;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 只要类是自己管理内存, 就应该警惕内存泄漏问题. 元素一旦被释放, 则该元素中包含的任何对象引用都应该被清空.
 *
 * @author mawenhao 2022/10/28
 */
public class Stack implements Cloneable {
    private Object[] elements;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @Override
    protected Stack clone() throws CloneNotSupportedException {
        Stack stack = (Stack) super.clone();
        stack.elements = this.elements.clone();
        return stack;
    }

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

    @SneakyThrows
    public static void main(String[] args) {
        Stack stack = new Stack();
        PhoneNumber stackNum = new PhoneNumber((short) 12, (short) 12, (short) 12);
        stack.push(stackNum);
        Stack clone = stack.clone();
        System.out.println(stack == clone);//false
        System.out.println(stack.equals(clone));//false
        System.out.println(Arrays.equals(stack.elements, clone.elements));//true
    }
}
