package easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TwoNumTest {

	@Test
	void twoSum() {
		TwoNum twoNum = new TwoNum();
		assertArrayEquals(new int[]{0, 1}, twoNum.twoSum(new int[]{2, 7, 11, 15}, 9));
		assertArrayEquals(new int[]{1, 2}, twoNum.twoSum(new int[]{3, 2, 4}, 6));
		assertArrayEquals(new int[]{0, 1}, twoNum.twoSum(new int[]{3, 3}, 6));
	}
}