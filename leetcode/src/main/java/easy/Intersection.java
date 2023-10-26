package easy;

import java.util.HashSet;
import java.util.Set;

public class Intersection {
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<>();
		for (int ele : nums1) {
			if (set.contains(ele)) {
				continue;
			}
			if (nums2Contains(nums2, ele)) {
				set.add(ele);
			}
		}
		return set.stream().mapToInt(Integer::intValue).toArray();
	}

	private boolean nums2Contains(int[] nums, int ele) {
		for (int num : nums) {
			if (ele == num) {
				return true;
			}
		}
		return false;
	}
}
