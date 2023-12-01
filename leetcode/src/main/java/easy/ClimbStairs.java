package easy;

/**
 * No.70 爬楼梯
 */
public class ClimbStairs {
	//耗时超过100%
	public int climbStairs(int n) {
		int pre1 = 1, pre2 = 2;
		if (n == 1) {
			return pre1;
		} else if (n == 2) {
			return pre2;
		}
		int res = 0;
		while (n > 2) {
			res = pre1 + pre2;
			n = n - 1;
			pre1 = pre2;
			pre2 = res;
		}
		return res;
	}
}
