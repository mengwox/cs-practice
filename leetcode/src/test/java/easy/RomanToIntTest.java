package easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 13.罗马数字转整数
 * {@link RomanToInt}
 */
class RomanToIntTest {
	@Test
	void romanToInt() {
		RomanToInt roman = new RomanToInt();

		assertEquals(3, roman.romanToInt("III"));
		assertEquals(4, roman.romanToInt("IV"));
		assertEquals(9, roman.romanToInt("IX"));
		assertEquals(58, roman.romanToInt("LVIII"));
		assertEquals(1994, roman.romanToInt("MCMXCIV"));
	}
}