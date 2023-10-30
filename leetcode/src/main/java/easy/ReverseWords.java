package easy;

/**
 * 557.反转字符串中的单词 II
 */
public class ReverseWords {
	public String reverseWords(String s) {
		char[] chars = s.toCharArray();
		int start = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == ' ') {
				swap(chars, start, i - 1);
				start = i + 1;
			} else if (i == chars.length - 1) {
				swap(chars, start, i);
			}
		}
		return new String(chars);
	}

	private void swap(char[] chars, int start, int end) {
		while (start < end) {
			char temp = chars[end];
			chars[end] = chars[start];
			chars[start] = temp;
			start++;
			end--;
		}
	}
}
