package easy;

import easy.inner.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PalindromeNodeTest {
	private final PalindromeNode solution = new PalindromeNode();

	@Test
	void isPalindrome() {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
		assertTrue(solution.isPalindrome(head));

		head = new ListNode(1);
		assertTrue(solution.isPalindrome(head));

		head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(1))));
		assertFalse(solution.isPalindrome(head));
	}
}