package easy;

import easy.inner.ListNode;

/**
 * 160.相交链表
 */
public class GetIntersectionNode {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode list1 = headA;
		ListNode list2 = headB;
		while (list1 != list2) {
			list1 = list1 != null ? list1.next : headB;
			list2 = list2 != null ? list2.next : headA;
		}
		return list1;
	}
}
