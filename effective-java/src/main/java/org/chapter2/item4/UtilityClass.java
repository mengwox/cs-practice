package org.chapter2.item4;

/**
 * 4.通过私有化构造器, 强化一个类的不被实例化的能力
 *
 * @author mawenhao 2022/10/28
 */
public class UtilityClass {
    private UtilityClass() {
        //限制反射实例化
        throw new AssertionError();
    }

    public static void helloWorld() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        UtilityClass.helloWorld();
    }
}