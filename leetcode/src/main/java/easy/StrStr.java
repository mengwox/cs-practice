package easy;

/**
 * <p>28.找出字符串中第一个匹配项的下标</p>
 */
public class StrStr {
	public int strStr(String haystack, String needle) {
		int slow = 0;
		for (int fast = 0; fast < haystack.length(); fast++) {
			if (haystack.charAt(fast) != needle.charAt(fast - slow)) {
				fast = slow++;
			} else if (fast - slow == needle.length() - 1) {
				return slow;
			}
		}
		return -1;
	}
}
