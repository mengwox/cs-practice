package easy;

import easy.inner.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * No.94 二叉树的中序遍历
 */
public class InorderTraversal {
	//耗时超过100%
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		inOrder(root, result);
		return result;
	}

	private void inOrder(TreeNode node, List<Integer> list) {
		// 进入结点后,已访问完左子树,即将访问右子树
		if (node == null) {
			return;
		}
		// 访问优先级: 左子树-->结点-->右子树
		inOrder(node.left, list);
		list.add(node.val);
		inOrder(node.right, list);
	}
}
