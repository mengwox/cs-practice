package org.chapter3.item14;

/**
 * 14. 考虑实现Comparable接口
 *
 * @author mawenhao 2022/11/18
 */
public final class CaseInsensitiveString implements Comparable<CaseInsensitiveString> {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = s;
    }

    @Override
    public int compareTo(CaseInsensitiveString cis) {
        return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
    }
}
