package org.chapter2.item3.enumtype;

import lombok.Getter;

/**
 * 单例模式-枚举实现
 *
 * @author mawenhao 2022/10/28
 */
public enum Elvis {
    INSTANCE("value", "chsDesc", "engDesc");

    @Getter
    private final String value;
    @Getter
    private final String chsDesc;
    @Getter
    private final String engDesc;

    Elvis(String value, String chsDesc, String engDesc) {
        this.value = value;
        this.chsDesc = chsDesc;
        this.engDesc = engDesc;
    }

    public static Elvis getInstance() {
        return INSTANCE;
    }

    public void helloWorld() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        Elvis instance = Elvis.getInstance();
        instance.helloWorld();
    }
}