package org.chapter2.item3.staticfactory;

/**
 * TODO
 *
 * @author mawenhao 2022/10/27
 */
public class Elvis {
    private static final Elvis INSTANCE = new Elvis();

    public static Elvis getInstance() {
        return INSTANCE;
    }

    private Elvis() {
        synchronized (Elvis.class) {
            if (INSTANCE != null) {
                throw new UnsupportedOperationException("单例不能被重新创建");
            }
        }
    }
}
