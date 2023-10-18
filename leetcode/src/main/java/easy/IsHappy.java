package easy;

/**
 * 202.快乐数
 */
public class IsHappy {
	public boolean isHappy(int n) {
		if (n == 1) {
			return true;
		}
		int slow = n, fast = n;
		do {
			slow = next(slow);
			fast = next(next(fast));
		}
		while (slow != fast);
		return slow == 1;
	}

	private int next(int n) {
		int total = 0;
		while (n > 0) {
			int les = n % 10;
			total += les * les;
			n /= 10;
		}
		return total;
	}
}
