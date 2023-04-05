package org.helloalgo.mawenhao.tree;

import lombok.Data;

/**
 * 二叉树
 *
 * @author mawenhao 2023/1/25
 */
@Data
public class TreeNode {
    public TreeNode(Integer element) {
        this.element = element;
    }

    private TreeNode left;
    private TreeNode right;
    private Integer element;

    /**
     * 前序遍历
     *
     * @param root 二叉树
     */
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        // 访问优先级: 结点-->左子树-->右子树
        System.out.printf("%d\t", root.getElement());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    /**
     * 中序遍历
     *
     * @param root 二叉树
     */
    public static void inOrder(TreeNode root) {
        // 进入结点后,已访问完左子树,即将访问右子树
        if (root == null) {
            return;
        }
        // 访问优先级: 左子树-->结点-->右子树
        inOrder(root.getLeft());
        System.out.printf("%d\t", root.getElement());
        inOrder(root.getRight());
    }

    /**
     * 后序遍历
     *
     * @param root 二叉树
     */
    public static void postOrder(TreeNode root) {
        // 进入结点后,已访问完左子树和右子树，即将返回
        if (root == null) {
            return;
        }
        // 访问优先级: 左子树-->右子树-->结点
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.printf("%d\t", root.getElement());
    }
}
