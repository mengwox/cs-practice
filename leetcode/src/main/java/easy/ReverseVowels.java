package easy;

/**
 * 345.反转字符串的元音字母
 */
public class ReverseVowels {
	public String reverseVowels(String s) {
		char[] chars = s.toCharArray();
		int len = chars.length, left = 0, right = len - 1;
		while (left < right) {
			if (notVowels(chars[left])) {
				left++;
				continue;
			}
			if (notVowels(chars[right])) {
				right--;
				continue;
			}
			swap(chars, left++, right--);
		}
		return String.valueOf(chars);
	}

	private boolean notVowels(char c) {
		return c != 'a' && c != 'e' && c != 'o' && c != 'i' && c != 'u'
				&& c != 'A' && c != 'E' && c != 'O' && c != 'I' && c != 'U';
	}

	private void swap(char[] c, int left, int right) {
		char temp = c[left];
		c[left] = c[right];
		c[right] = temp;
	}
}
