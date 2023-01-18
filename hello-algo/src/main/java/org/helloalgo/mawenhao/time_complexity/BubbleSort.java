package org.helloalgo.mawenhao.time_complexity;

import java.util.Arrays;

/**
 * 冒泡排序
 * 复杂度:O(n²)
 *
 * @author mawenhao 2023/1/10
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {3, 8, 5, 10, 70, 50};
        System.out.println("操作次数:" + bubbleSort(nums));
    }

    static int bubbleSort(int[] nums) {
        int count = 0;
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    // swap i and j
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    count += 3;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
        return count;
    }
}
