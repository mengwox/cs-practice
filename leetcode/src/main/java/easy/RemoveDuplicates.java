package easy;

/**
 * <p>26.删除有序数组中的第一个匹配项</p>
 * 双指针
 */
public class RemoveDuplicates {
	public int removeDuplicates(int[] nums) {
		if (nums.length == 1) {
			return 1;
		}
		int slow = 0;
		int fast = 1;
		while (fast < nums.length) {
			if (nums[slow] == nums[fast]) {
				fast++;
			} else {
				nums[++slow] = nums[fast++];
			}
		}
		return slow + 1;
	}
}
