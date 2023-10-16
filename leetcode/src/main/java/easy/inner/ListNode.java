package easy.inner;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode() {
	}

	public ListNode(int val) {
		this.val = val;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	public ListNode setVal(int val) {
		this.val = val;
		return this;
	}

	public ListNode setNext(ListNode next) {
		this.next = next;
		return this;
	}

	@Override
	public String toString() {
		List<Integer> list = new ArrayList<>();
		ListNode index = this;
		while (index != null) {
			list.add(index.val);
			index = index.next;
		}
		return list.toString();
	}
}
