package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 696.计数二进制子串
 */
public class CountBinarySubstrings {
	public int countBinarySubstrings(String s) {
		int count = 0;
		int len = s.length();
		int index = 0;
		List<Integer> list = new ArrayList<>();
		while (index < len) {
			char temp = s.charAt(index);
			int indexCount = 0;
			while (index < len && temp == s.charAt(index)) {
				indexCount++;
				index++;
			}
			list.add(indexCount);
		}
		for (int i = 1; i < list.size(); i++) {
			count += Math.min(list.get(i - 1), list.get(i));
		}
		return count;
	}

	//最优解
	public int officialSolution(String s) {
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