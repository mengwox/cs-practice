package easy;

/**
 * [2562]找出数组的串联值
 */
public class FindTheArrayConcatVal {
	public static void main(String[] args) {
		FindTheArrayConcatVal test = new FindTheArrayConcatVal();
		System.out.println(test.improveFindTheArrayConcVal(new int[]{7, 52, 2, 4}) == 596);
		System.out.println(test.improveFindTheArrayConcVal(new int[]{5, 14, 13, 8, 12}) == 673);
	}

	public long improveFindTheArrayConcVal(int[] nums) {
		long result = 0L;
		//边界条件
		if (nums.length == 1) {
			return nums[0];
		}
		int start = 0, end = nums.length - 1;
		while (start < end) {
			result += nums[end];
			result += nums[start] * Math.pow(10, getBits2(nums[end]));

			start++;
			end--;
		}
		return start == end ? result + nums[start] : result;
	}

	//计算num的位数, 方式一: 除10
	private double getBits(int num) {
		int bits = 0;
		while (num / 10 != 0) {
			num = num / 10;
			bits++;
		}
		return bits + 1;
	}

	//计算num的位数, 方式二: log10
	private int getBits2(int num) {
		return (int) Math.log10(num) + 1;
	}
}
