package easy;

/**
 * 541.反转字符串
 */
public class ReverseStr {
	public String reverseStr(String s, int k) {
		char[] chars = s.toCharArray();
		int len = chars.length;
		for (int i = 0; i < len; i += 2 * k) {
			int rest = len - i;
			if (rest <= k) {
				swapK(chars, i, len - 1);
			} else {
				swapK(chars, i, i + k - 1);
			}
		}
		return String.valueOf(chars);
	}

	private void swapK(char[] chars, int start, int end) {
		while (start < end) {
			swap(chars, start, end);
			start++;
			end--;
		}
	}

	private void swap(char[] chars, int from, int to) {
		char temp = chars[to];
		chars[to] = chars[from];
		chars[from] = temp;
	}
}
