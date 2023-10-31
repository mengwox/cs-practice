package easy.inner;

// 二叉搜索树节点的定义
public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	public TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}
}

