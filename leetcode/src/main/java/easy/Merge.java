package easy;

import java.util.Arrays;

/**
 * <p>88.合并两个有序数组</p>
 */
public class Merge {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int i1 = m - 1;
		int i2 = n - 1;
		while (i1 >= 0 && i2 >= 0) {
			if (nums1[i1] > nums2[i2]) {
				i1--;
			} else {
				int index = m + n - 2;
				while (index > i1) {
					nums1[index + 1] = nums1[index];
					index--;
				}
				nums1[i1 + 1] = nums2[i2--];
			}
		}

		if (i2 >= 0) {
			int index = m + n - 1;
			while (index >= 0) {
				if (index > i2) {
					nums1[index] = nums1[index - i2 - 1];
				} else {
					nums1[index] = nums2[index];
				}
				index--;
			}
		}
		System.out.println(Arrays.toString(nums1));
	}
}
