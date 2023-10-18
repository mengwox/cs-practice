package easy;

/**
 * 125.验证回文串
 */
public class PalindromeStr {
	public boolean isPalindrome(String s) {
		int start = 0;
		int end = s.length() - 1;
		while (start <= end) {
			int sc = s.charAt(start);
			int ec = s.charAt(end);
			if (isNotValid(sc)) {
				start++;
				continue;
			}
			if (isNotValid(ec)) {
				end--;
				continue;
			}
			if (isUpperWord(sc)) {
				sc = sc + 32;
			}
			if (isUpperWord(ec)) {
				ec = ec + 32;
			}
			if (sc != ec) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	private boolean isNotValid(int word) {
		return (word < 65 || word > 90) && (word < 97 || word > 122) && (word < 48 || word > 57);
	}

	private boolean isUpperWord(int word) {
		return word >= 65 && word <= 90;
	}
}
