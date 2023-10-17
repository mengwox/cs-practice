package easy;

import easy.inner.ListNode;
import org.junit.jupiter.api.Test;

class MergeTwoListsTest {
	private final MergeTwoLists solution = new MergeTwoLists();

	@Test
	void mergeTwoLists() {
		ListNode list1, list2;
		list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
		list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
		System.out.println(solution.mergeTwoLists(list1, list2));
	}
}