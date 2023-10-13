package easy;

/**
 * 14.最长公共前缀
 */
public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		//边界
		String item = strs[0];
		if (strs.length == 1) {
			return item;
		}
		int len = item.length();
		int count = 0;
		for (int i = 0; i < len; i++) {
			int c = 0;
			for (String str : strs) {
				if (i < str.length() && item.charAt(i) == str.charAt(i)) {
					c++;
				}
			}
			if (c != strs.length) {
				//并不是所有元素都相同
				break;
			}
			//所有元素index=i的char都相同
			count++;
		}
		return item.substring(0, count);
	}
}
