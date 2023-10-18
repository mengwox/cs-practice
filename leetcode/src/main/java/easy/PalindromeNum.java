package easy;

/**
 * 9.回文数
 */
public class PalindromeNum {
	public boolean isPalindrome(int x) {
		//负数
		if (x < 0) {
			return false;
		}
		//10的倍数
		if (x % 10 == 0) {
			return x == 0;
		}
		return x == getReserveNum(x);
	}

	/**
	 * 获取x的回文数
	 * todo 效率较低,待优化
	 *
	 * @param x 原数
	 * @return 回文数
	 */
	private int getReserveNum(int x) {
		int bits = (int) Math.log10(x);
		int reserve = 0;
		int curNum = x % 10;
		for (; x / 10 > 0; x /= 10, curNum = x % 10) {
			reserve += curNum * Math.pow(10, bits--);
		}
		return reserve + x;
	}
}
