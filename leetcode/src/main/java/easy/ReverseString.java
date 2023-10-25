package easy;

/**
 * 344.反转字符串
 */
public class ReverseString {
	public void reserveString(char[] chars) {
		int len = chars.length, left = 0, right = len - 1;
		while (left < right) {
			swap(chars, left++, right--);
		}
	}

	private void swap(char[] chars, int left, int right) {
		char temp = chars[left];
		chars[left] = chars[right];
		chars[right] = temp;
	}
}
