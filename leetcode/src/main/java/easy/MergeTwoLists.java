package easy;

import easy.inner.ListNode;

public class MergeTwoLists {
	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode result = new ListNode(0);
		ListNode index = result;
		while (list1 != null && list2 != null) {
			int i1 = list1.val;
			int i2 = list2.val;
			int min;
			if (i1 <= i2) {
				min = i1;
				list1 = list1.next;
			} else {
				min = i2;
				list2 = list2.next;
			}
			index.next = new ListNode(min);
			index = index.next;
		}
		if (list1 != null) {
			index.next = list1;
		} else {
			index.next = list2;
		}
		return result.next;
	}
}
