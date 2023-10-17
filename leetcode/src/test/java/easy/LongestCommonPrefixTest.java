package easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestCommonPrefixTest {
	private final LongestCommonPrefix solution = new LongestCommonPrefix();

	@Test
	void longestCommonPrefix() {
		assertEquals("fl", solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
		assertEquals("", solution.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
		assertEquals("a", solution.longestCommonPrefix(new String[]{"ab", "a"}));
		assertEquals("aa", solution.longestCommonPrefix(new String[]{"aaa", "aa", "aaa"}));
	}
}