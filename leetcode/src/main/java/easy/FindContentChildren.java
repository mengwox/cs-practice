package easy;

import java.util.Arrays;

/**
 * 455.分发饼干
 */
public class FindContentChildren {
	public int findContentChildren(int[] g, int[] s) {
		int start = 0, value = 0;
		Arrays.sort(g);
		Arrays.sort(s);
		for (int i = 0; start < g.length && i < s.length; i++) {
			if (g[start] <= s[i]) {
				start++;
				value++;
			}
		}
		return value;
	}
}
