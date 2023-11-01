package easy;

public class ValidPalindrome {
	public boolean validPalindrome(String str) {
		int start = 0, end = str.length() - 1;
		while (start < end) {
			if (str.charAt(start) == str.charAt(end)) {
				start++;
				end--;
			} else {
				return check(str, start + 1, end) || check(str, start, end - 1);
			}
		}
		return true;
	}

	private boolean check(String str, int start, int end) {
		while (start < end) {
			if (str.charAt(start) != str.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
}
