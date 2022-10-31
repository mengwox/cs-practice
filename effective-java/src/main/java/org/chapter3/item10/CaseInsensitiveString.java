package org.chapter3.item10;

import lombok.AllArgsConstructor;

/**
 * 10.覆盖equals时请遵守通用约定
 *
 * @author mawenhao 2022/10/30
 */
@AllArgsConstructor
public final class CaseInsensitiveString {
    private final String s;

    @Override
    public boolean equals(Object o) {
        return o instanceof CaseInsensitiveString
                && s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
    }

    public static void main(String[] args) {
        CaseInsensitiveString caseInsensitiveString = new CaseInsensitiveString("123");
        String s = "123";
        System.out.println(caseInsensitiveString.equals(s));
        System.out.println(s.equals(caseInsensitiveString));
    }
}
