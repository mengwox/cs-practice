package easy;

/**
 * No.67 二进制求和
 */
public class AddBinary {
	//耗时超过31.4%
	public String addBinary(String a, String b) {
		StringBuilder sb = new StringBuilder();
		int les = 0;
		int indexa = a.length() - 1;
		int indexb = b.length() - 1;
		while (indexa >= 0 || indexb >= 0) {
			int ai = 0, bi = 0;
			if (indexb >= 0 && b.charAt(indexb) == '1') {
				bi = 1;
			}
			if (indexa >= 0 && a.charAt(indexa) == '1') {
				ai = 1;
			}
			int cur = ai + bi + les;
			if (cur > 1) {
				les = cur / 2;
				cur %= 2;
			} else {
				les = 0;
			}
			sb.insert(0, cur);
			indexa--;
			indexb--;
		}
		if (les > 0) {
			sb.insert(0, les);
		}
		return sb.toString();
	}
}
