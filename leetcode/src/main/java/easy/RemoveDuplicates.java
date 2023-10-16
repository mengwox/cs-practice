package easy;

public class RemoveDuplicates {
	/**
	 * 使用双指针(一快一慢)解决问题
	 *
	 * @param nums 待去除重复项的nums
	 * @return nums中唯一元素个数
	 */
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

	public int oldRemoveDuplicates(int[] nums) {
		int len = nums.length;
		int markNull = -100000;
		int temp = markNull;
		for (int i = 0; i < len; i++) {
			if (temp == markNull) {
				//null标识位
				temp = nums[i];
				continue;
			}
			if (temp != nums[i]) {
				//不存在重复项
				temp = nums[i];
				continue;
			}
			//存在重复项
			int duplicatesCount = 0;
			while (i + duplicatesCount < len) {
				if (temp != nums[i + duplicatesCount]) {
					break;
				}
				duplicatesCount++;
			}
			//按duplicatesCount,将剩余元素前移
			for (int j = i; j + duplicatesCount < len; j++) {
				nums[j] = nums[j + duplicatesCount];
			}
			//k扣除重复项
			len -= duplicatesCount;
			//重新记录temp
			temp = nums[i];
		}
		return len;
	}
}
