package org.chapter2.item3.field;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * 3.私有构造器或枚举类强化Singleton属性
 *
 * @author mawenhao 2022/10/27
 */
public class Elvis implements Serializable {
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
        synchronized (Elvis.class) {
            if (INSTANCE != null) {
                throw new UnsupportedOperationException("单例不能被重新创建");
            }
        }
    }

    @Serial
    private Object readResolve() {
        return INSTANCE;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Elvis.INSTANCE);
        Class<Elvis> clazz = Elvis.class;
        Constructor<Elvis> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Elvis elvis = constructor.newInstance();
        System.out.println(elvis);
    }
}
