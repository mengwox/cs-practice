package easy;

/**
 * 27.移除元素
 */
public class RemoveElement {
	/**
	 * 双指针之快慢指针
	 */
	public int removeElement(int[] nums, int val) {
		int slow = 0;
		for (int fast = 0; fast < nums.length; fast++) {
			if (nums[fast] != val) {
				nums[slow] = nums[fast];
				slow++;
			}
		}
		return slow;
	}
}
