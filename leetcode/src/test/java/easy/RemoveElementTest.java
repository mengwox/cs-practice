package easy;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;


class RemoveElementTest {
	private final RemoveElement solution = new RemoveElement();

	@Test
	void removeElement() {
		assertion(new int[]{3, 2, 2, 3}, 3, new int[]{2, 2}, solution::removeElement);
		assertion(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2, new int[]{0, 1, 3, 0, 4}, solution::removeElement);
	}

	private void assertion(int[] nums, int val, int[] expectedNums,
	                       BiFunction<int[], Integer, Integer> function) {
		int len = function.apply(nums, val);
		assert len == expectedNums.length;
		for (int i = 0; i < len; i++) {
			assert nums[i] == expectedNums[i];
		}
	}
}