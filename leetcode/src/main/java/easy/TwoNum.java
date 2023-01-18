package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * 提示：
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 * <p>
 * Related Topics
 * 数组
 * 哈希表
 * <p>
 * 👍 13879
 * 👎 0
 *
 * @author mawenhao 2023/1/18
 */
public class TwoNum {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        TwoNum tn = new TwoNum();
        System.out.println(Arrays.toString(tn.twoSum2(nums, target)));
    }

    /**
     * 暴力解法, 时间复杂度n^2
     *
     * @param nums   整数数组 nums
     * @param target 整数目标值 target
     * @return 答案
     */
    public int[] twoSum1(int[] nums, int target) {
        int[] res = new int[2];
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 利用哈希表特性,实现最佳解法
     *
     * @param nums   整数数组 nums
     * @param target 整数目标值 target
     * @return 答案
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        Integer exist;
        for (int i = 0; i < nums.length; i++) {
            exist = map.get(target - nums[i]);
            if (exist != null) {
                return new int[]{exist, i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
