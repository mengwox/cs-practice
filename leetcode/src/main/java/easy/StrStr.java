package easy;

/**
 * <p>28.找出字符串中第一个匹配项的下标</p>
 */
public class StrStr {
	public int strStr(String haystack, String needle) {
		int index = 0;
		for (int i = 0; i < haystack.length(); i++) {
			if (haystack.charAt(i) != needle.charAt(i - index)) {
				i = index++;
			} else if (i - index == needle.length() - 1) {
				return index;
			}
		}
		return -1;
	}
}
