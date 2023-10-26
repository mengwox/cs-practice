package easy;

import java.util.Arrays;

/**
 * 350.两个数组的交集II
 */
public class Intersect {
	public int[] intersect(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int len1 = nums1.length, len2 = nums2.length;
		int[] ans = new int[Math.min(len1, len2)];
		int i1 = 0, i2 = 0, index = 0;
		while (i1 < len1 && i2 < len2) {
			int n1 = nums1[i1];
			int n2 = nums2[i2];
			if (n1 == n2) {
				ans[index++] = n1;
				i1++;
				i2++;
			} else if (n1 < n2) {
				i1++;
			} else {
				i2++;
			}
		}
		return Arrays.copyOfRange(ans, 0, index);
	}
}
