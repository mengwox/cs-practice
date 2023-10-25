package easy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MoveZeroesTest {
	private final MoveZeroes solution = new MoveZeroes();

	private void invoke(int[] nums) {
		solution.moveZeroes(nums);
		System.out.println("move after:" + Arrays.toString(nums));
	}

	@Test
	void moveZeroes() {
		invoke(new int[]{0});
		invoke(new int[]{1});
		invoke(new int[]{0, 1, 2, 0, 3, 0, 0, 0, 1});
		invoke(new int[]{0, 0, 0, 1, 3, 12});
		invoke(new int[]{0, 0, 0, 12, 1, 3});
		invoke(new int[]{12, 1, 3, 0, 0, 0});
	}
}