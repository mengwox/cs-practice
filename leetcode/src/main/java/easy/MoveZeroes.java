package easy;

/**
 * 283. 移动零
 */
public class MoveZeroes {
	public void moveZeroes(int[] nums) {
		int len = nums.length, left = 0, right = 0;
		while (right < len) {
			if (nums[right] != 0) {
				swap(nums, left, right);
				left++;
			}
			right++;
		}
	}

	private void swap(int[] nums, int left, int right) {
		int temp = nums[right];
		nums[right] = nums[left];
		nums[left] = temp;
	}
}
