package easy;

import easy.inner.ListNode;

/**
 * No.83 删除排序链表中的重复元素
 */
public class DeleteDuplicates {
	//耗时超过100%
	public ListNode deleteDuplicates(ListNode head) {
		ListNode index = head;
		//record,用于记录重复时的前一个结点
		ListNode record = head;
		while (index != null) {
			if (record != index) {
				if (record.val == index.val) {
					record.next = index.next;
				} else {
					record = index;
				}
			}
			index = index.next;
		}
		return head;
	}
}
