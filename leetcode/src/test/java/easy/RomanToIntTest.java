package easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 13.罗马数字转整数
 * {@link RomanToInt}
 */
class RomanToIntTest {
	private final RomanToInt solution = new RomanToInt();

	@Test
	void romanToInt() {
		assertEquals(3, solution.romanToInt("III"));
		assertEquals(4, solution.romanToInt("IV"));
		assertEquals(9, solution.romanToInt("IX"));
		assertEquals(58, solution.romanToInt("LVIII"));
		assertEquals(1994, solution.romanToInt("MCMXCIV"));
	}
}