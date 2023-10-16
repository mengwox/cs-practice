package easy;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

class RemoveDuplicatesTest {
	private final RemoveDuplicates solution = new RemoveDuplicates();

	@Test
	void removeDuplicates() {
		Function<int[], Integer> function = solution::removeDuplicates;
		assertion(new int[]{1, 1, 2, 2, 4, 5, 6}, new int[]{1, 2, 4, 5, 6}, function);
		assertion(new int[]{1, 1, 2}, new int[]{1, 2}, function);
	}

	private void assertion(int[] nums, int[] expectedNums, Function<int[], Integer> function) {
		int k = function.apply(nums);
		assert k == expectedNums.length;
		for (int i = 0; i < k; i++) {
			assert nums[i] == expectedNums[i];
		}
	}
}