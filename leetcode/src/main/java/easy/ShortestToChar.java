package easy;

import java.util.ArrayList;

/**
 * 821.字符的最短距离
 * todo 待补充最优解
 */
public class ShortestToChar {
	//非最优解
	public int[] shortestToChar(String s, char c) {
		int[] res = new int[s.length()];
		ArrayList<Integer> indexes = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c) {
				indexes.add(i);
			}
		}
		for (int i = 0; i < s.length(); i++) {
			int min = s.length();
			for (Integer index : indexes) {
				min = Math.min(min, Math.abs(index - i));
			}
			res[i] = min;
		}
		return res;
	}
}
