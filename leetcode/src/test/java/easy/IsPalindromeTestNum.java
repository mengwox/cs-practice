package easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsPalindromeTestNum {
	private final IsPalindrome solution = new IsPalindrome();

	@Test
	void isPalindrome() {
		assertTrue(solution.isPalindrome("ab_a"));
		assertTrue(solution.isPalindrome("A man, a plan, a canal: Panama"));
		assertTrue(solution.isPalindrome(" "));
		assertFalse(solution.isPalindrome("race a car"));
		assertFalse(solution.isPalindrome("0P"));
	}
}