package easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 9.回文数
 * {@link Palindrome#isPalindrome} 测试
 */
class PalindromeTest {
	private final Palindrome solution = new Palindrome();

	@Test
	public void isPalindrome() {
		assertFalse(solution.isPalindrome(122));
		assertFalse(solution.isPalindrome(-121));
		assertFalse(solution.isPalindrome(10));

		assertTrue(solution.isPalindrome(1001));
		assertTrue(solution.isPalindrome(121));
		assertTrue(solution.isPalindrome(0));
	}
}