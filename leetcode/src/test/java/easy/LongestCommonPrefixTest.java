package easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestCommonPrefixTest {

	@Test
	void longestCommonPrefix() {
		LongestCommonPrefix prefix = new LongestCommonPrefix();
		assertEquals("fl", prefix.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
		assertEquals("", prefix.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
		assertEquals("a", prefix.longestCommonPrefix(new String[]{"ab", "a"}));
		assertEquals("aa", prefix.longestCommonPrefix(new String[]{"aaa", "aa", "aaa"}));
	}
}