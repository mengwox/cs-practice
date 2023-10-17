package easy;

import org.junit.jupiter.api.Test;

class StrStrTest {
	private final StrStr solution = new StrStr();

	@Test
	void strStr() {
		assert 0 == solution.strStr("leetcode", "leet");
		assert -1 == solution.strStr("leetcode", "leeto");
		assert 4 == solution.strStr("mississippi", "issip");
		assert 2 == solution.strStr("hello", "ll");
	}
}