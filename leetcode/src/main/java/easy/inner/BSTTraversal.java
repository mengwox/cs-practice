package easy.inner;

//二叉搜索树的3种遍历方式-GPT生成
public class BSTTraversal {

	public static void main(String[] args) {
		BSTTraversal traversal = new BSTTraversal();

		// 构建一个二叉搜索树
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(10);

		System.out.println("中序遍历结果：");
		traversal.inorderTraversal(root);
		System.out.println();

		System.out.println("前序遍历结果：");
		traversal.preorderTraversal(root);
		System.out.println();

		System.out.println("后序遍历结果：");
		traversal.postorderTraversal(root);
	}

	// 中序遍历：左子树 -> 根节点 -> 右子树
	public void inorderTraversal(TreeNode root) {
		if (root == null) {
			return;
		}
		inorderTraversal(root.left);
		System.out.print(root.val + " ");
		inorderTraversal(root.right);
	}

	// 前序遍历：根节点 -> 左子树 -> 右子树
	public void preorderTraversal(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.print(root.val + " ");
		preorderTraversal(root.left);
		preorderTraversal(root.right);
	}

	// 后序遍历：左子树 -> 右子树 -> 根节点
	public void postorderTraversal(TreeNode root) {
		if (root == null) {
			return;
		}
		postorderTraversal(root.left);
		postorderTraversal(root.right);
		System.out.print(root.val + " ");
	}
}
