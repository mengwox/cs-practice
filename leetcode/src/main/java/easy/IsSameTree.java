package easy;

import easy.inner.TreeNode;

/**
 * No.100 相同的树
 */
public class IsSameTree {
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null) {
			return false;
		}
		if (p.val != q.val) {
			return false;
		}
		boolean res = isSameTree(p.left, q.left);
		if (!res) {
			return false;
		}
		return isSameTree(p.right, q.right);
	}
}
