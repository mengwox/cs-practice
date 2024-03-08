package easy;

import easy.inner.TreeNode;

/**
 * 101. 对称二叉树
 */
public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return valid(root.left, root.right);
    }

    //校验, 递归方式
    private boolean valid(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return valid(left.left, right.right) && valid(left.right, right.left);
    }
}
