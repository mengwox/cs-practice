package easy;

/**
 * 35.搜索插入位置
 * logN, 二分查找
 */
public class SearchInsert {
	public int searchInsert(int[] nums, int target) {
		if (nums[0] >= target) {
			return 0;
		}
		if (nums[nums.length - 1] < target) {
			return nums.length;
		}
		return search(nums, 0, nums.length, target);
	}

	private int search(int[] nums, int left, int right, int target) {
		int index = (left + right) / 2;
		int value = nums[index];
		if (value == target) {
			return index;
		} else if (value > target) {
			return search(nums, left, index, target);
		} else {
			if (nums[index + 1] > target) {
				return index + 1;
			}
			return search(nums, index, right, target);
		}
	}
}
