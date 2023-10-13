package easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 9.回文数
 * {@link Palindrome#isPalindrome} 测试
 */
class PalindromeTest {
	@Test
	public void isPalindrome() {
		Palindrome palindrome = new Palindrome();
		assertFalse(palindrome.isPalindrome(122));
		assertFalse(palindrome.isPalindrome(-121));
		assertFalse(palindrome.isPalindrome(10));
		assertTrue(palindrome.isPalindrome(1001));
		assertTrue(palindrome.isPalindrome(121));
		assertTrue(palindrome.isPalindrome(0));
	}
}