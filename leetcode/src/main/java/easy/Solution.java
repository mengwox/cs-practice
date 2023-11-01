package easy;

/**
 * 696.计数二进制子串
 * 最优解
 */
public class Solution {
	public int countBinarySubstrings(String s) {
		char[] cs = s.toCharArray();
		int ans = 0;
		int pre = 0, cur = 1;
		for (int i = 1; i < cs.length; i++) {
			if (cs[i] != cs[i - 1]) {
				ans += Math.min(pre, cur);
				pre = cur;
				cur = 1;
			} else cur++;
		}
		return ans + Math.min(pre, cur);
	}
}