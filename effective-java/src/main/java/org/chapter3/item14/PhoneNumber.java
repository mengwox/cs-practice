package org.chapter3.item14;

import lombok.Getter;
import lombok.NonNull;

import java.util.Comparator;

/**
 * 14. 考虑实现Comparable接口
 *
 * @author mawenhao 2022/11/18
 */
public record PhoneNumber(@Getter short areaCode, @Getter short prefix,
                          @Getter short lineNum) implements Comparable<PhoneNumber> {
    private static final Comparator<PhoneNumber> COMPARATOR = Comparator
            .comparingInt((PhoneNumber pn) -> pn.areaCode)
            .thenComparingInt(pn -> pn.prefix)
            .thenComparingInt(pn -> pn.lineNum);

    @Override
    public int compareTo(@NonNull PhoneNumber pn) {
        return COMPARATOR.compare(this, pn);
    }

    public static void main(String[] args) {
        PhoneNumber phoneNumber = new PhoneNumber((short) 12, (short) 12, (short) 12);
    }
}
