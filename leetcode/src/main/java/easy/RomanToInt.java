package easy;

/**
 * 13.罗马数字转整数
 */
public class RomanToInt {

	public int romanToInt(String s) {
		int len = s.length();
		int result = 0;
		int preInt = 0;
		for (int i = len - 1; i >= 0; i--) {
			int curInt = getValue(s.charAt(i));
			if (preInt > curInt) {
				result -= curInt;
			} else {
				result += curInt;
			}
			preInt = curInt;
		}
		return result;
	}

	private int getValue(char c) {
		switch (c) {
			case 'I':
				return 1;
			case 'V':
				return 5;
			case 'X':
				return 10;
			case 'L':
				return 50;
			case 'C':
				return 100;
			case 'D':
				return 500;
			case 'M':
				return 1000;
			default:
				return 0;
		}
	}
}
