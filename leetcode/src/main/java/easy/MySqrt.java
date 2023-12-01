package easy;

/**
 * No.69 求x的平方根
 */
public class MySqrt {
	//耗时超过94.03%
	public int mySqrt(int x) {
		if (x < 1) {
			return 0;
		}
		double guess = 1;
		while (!goodEnough(guess, x)) {
			guess = (guess + x / guess) / 2;
		}
		int res = (int) guess;
		return res * res > x ? res - 1 : res;
	}

	private boolean goodEnough(double guess, int x) {
		double res = guess * guess;
		double les = x - res;
		if (res > x) {
			les = res - x;
		}
		return les <= 1;
	}
}
