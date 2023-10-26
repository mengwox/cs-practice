package easy;

/**
 * 392.判断子序列
 */
public class IsSubSequence {
	public boolean isSubsequence(String s, String t) {
		int slen = s.length(), tlen = t.length();
		if (slen > tlen) {
			return false;
		}
		if (slen == 0) {
			return true;
		}
		int sIndex = 0, tIndex = 0;
		while (sIndex < slen && tIndex < tlen) {
			if (s.charAt(sIndex) == t.charAt(tIndex)) {
				sIndex++;
			}
			tIndex++;
		}
		return sIndex == slen;
	}
}
