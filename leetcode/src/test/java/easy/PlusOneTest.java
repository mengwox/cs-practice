package easy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class PlusOneTest {
	private static final PlusOne SOLUTION = new PlusOne();

	@Test
	void plusOne() {
		System.out.println(Arrays.toString(SOLUTION.plusOne(new int[]{9, 9, 9})));
		System.out.println(Arrays.toString(SOLUTION.plusOne(new int[]{9, 9, 8})));
		System.out.println(Arrays.toString(SOLUTION.plusOne(new int[]{0})));
	}
}