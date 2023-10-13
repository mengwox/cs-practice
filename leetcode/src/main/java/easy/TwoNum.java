package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.两数之和
 */
public class TwoNum {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (map.get(target - num) != null) {
				if (i < map.get(target - num)) {
					return new int[]{i, map.get(target - num)};
				} else {
					return new int[]{map.get(target - num), i};
				}
			}
			map.put(num, i);
		}
		return null;
	}
}
