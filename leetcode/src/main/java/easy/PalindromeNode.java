package easy;

import easy.inner.ListNode;

/**
 * 234.回文链表
 */
public class PalindromeNode {
	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		int size = size(head);
		int count = 0;
		ListNode index = head;
		while (index != null) {
			if (count == size / 2) {
				break;
			}
			index = index.next;
			count++;
		}
		index = reserve(index);
		ListNode i1 = head, i2 = index;
		boolean result = true;
		while (i1 != null && i2 != null) {
			if (i1.val != i2.val) {
				result = false;
				break;
			}
			i1 = i1.next;
			i2 = i2.next;
		}
		reserve(index);
		return result;
	}

	private int size(ListNode node) {
		ListNode index = node;
		int size = 0;
		while (index != null) {
			index = index.next;
			size++;
		}
		return size;
	}

	//链表反转
	private ListNode reserve(ListNode node) {
		ListNode index = node;
		ListNode nextIndex = index.next;
		while (nextIndex != null) {
			ListNode temp = nextIndex.next;
			nextIndex.next = index;
			if (index == node) {
				index.next = null;
			}

			index = nextIndex;
			nextIndex = temp;
		}
		return index;
	}
}
