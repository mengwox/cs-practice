package easy;

import easy.inner.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 653.两数之和 IV - 输入二叉搜索树
 * <br/>
 * 最优解之一
 */
public class FindTarget2 {
	public boolean findTarget(TreeNode root, int k) {
		Deque<TreeNode> ld = new ArrayDeque<>(), rd = new ArrayDeque<>();
		TreeNode temp = root;
		while (temp != null) {
			ld.add(temp);
			temp = temp.left;
		}
		temp = root;
		while (temp != null) {
			rd.add(temp);
			temp = temp.right;
		}
		TreeNode l = ld.peekLast(), r = rd.peekLast();
		while (l.val < r.val) {
			int t = l.val + r.val;
			if (t == k) return true;
			if (t < k) l = getNext(ld, true);
			else r = getNext(rd, false);
		}
		return false;
	}

	TreeNode getNext(Deque<TreeNode> d, boolean isLeft) {
		if (d == null || d.pollLast() == null) {
			return null;
		}
		TreeNode node = isLeft ? d.pollLast().right : d.pollLast().left;
		while (node != null) {
			d.addLast(node);
			node = isLeft ? node.left : node.right;
		}
		return d.peekLast();
	}
}

