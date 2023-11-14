package easy;

/**
 * No.66
 */
public class PlusOne {
	public int[] plusOne(int[] digits) {
		int bit = 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			int sum = digits[i] + bit;
			bit = sum / 10;
			digits[i] = sum % 10;
		}
		if (bit == 1) {
			int[] res = new int[digits.length + 1];
			res[0] = 1;
			for (int i = 1; i < (digits.length + 1); i++) {
				res[i] = digits[i - 1];
			}
			//更简洁
			//			System.arraycopy(digits, 0, res, 1, digits.length);
			return res;
		} else {
			return digits;
		}
	}
}
