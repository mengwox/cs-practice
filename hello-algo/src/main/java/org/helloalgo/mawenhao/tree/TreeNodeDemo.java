package org.helloalgo.mawenhao.tree;

/**
 * 二叉树Demo
 *
 * @author mawenhao 2023/1/25
 */
public class TreeNodeDemo {
    public static void main(String[] args) {
        treeNodeThreeOrder();
        // 二叉搜索树
//        TreeNode root = buildBinarySearchTree();
        // search(root, 6);
//        System.out.println(insert(root, 16));
    }

    /**
     * 二叉搜索树-插入结点
     * 规则同:查找结点
     *
     * @param root 根结点
     * @param num  待插入元素
     */
    private static TreeNode insert(TreeNode root, int num) {
        if (root == null) {
            return null;
        }
        TreeNode pre = null;
        while (root != null) {
            pre = root;
            Integer element = root.getElement();
            if (element == num) {
                System.out.println("值:" + num + "已存在于:" + root);
                return null;
            } else if (element < num) {
                root = root.getRight();
            } else {
                root = root.getLeft();
            }
        }
        // pre为叶结点,新建一个结点,根据值大小,插入对应位置
        TreeNode node = new TreeNode(num);
        if (pre.getElement() < num) {
            pre.setRight(node);
        } else {
            pre.setLeft(node);
        }
        return node;
    }

    /**
     * 二叉搜索树-查找结点
     */
    private static void search(TreeNode root, Integer num) {
        if (root == null) {
            System.out.println("未找到" + num);
            return;
        }
        while (root != null) {
            Integer element = root.getElement();
            if (element.equals(num)) {
                System.out.println("找到" + root);
                break;
            } else {
                root = element > num ? root.getLeft() : root.getRight();
            }
        }
    }

    /**
     * 前序遍历,中序遍历,后序遍历3种方式演示
     */
    private static void treeNodeThreeOrder() {
        TreeNode root = new TreeNode(1);
        root.setLeft(new TreeNode(2));
        root.setRight(new TreeNode(3));
        root.getLeft().setLeft(new TreeNode(4));
        root.getLeft().setRight(new TreeNode(5));
        root.getRight().setLeft(new TreeNode(6));
        root.getRight().setRight(new TreeNode(7));
        System.out.println("前序遍历start:");
        TreeNode.preOrder(root);
        System.out.println("\n前序遍历end.");
        System.out.println("中序遍历start:");
        TreeNode.inOrder(root);
        System.out.println("\n中序遍历end.");
        System.out.println("后序遍历start:");
        TreeNode.postOrder(root);
        System.out.println("\n后序遍历end.");
    }

    /**
     * 构建二叉搜索树
     *
     * @return 二叉搜索树
     */
    private static TreeNode buildBinarySearchTree() {
        TreeNode root = new TreeNode(8);
        root.setLeft(new TreeNode(4));

        root.getLeft().setLeft(new TreeNode(2));
        root.getLeft().setRight(new TreeNode(6));

        root.getLeft().getLeft().setLeft(new TreeNode(1));
        root.getLeft().getLeft().setRight(new TreeNode(3));
        root.getLeft().getRight().setLeft(new TreeNode(5));
        root.getLeft().getRight().setRight(new TreeNode(7));

        root.setRight(new TreeNode(12));

        root.getRight().setLeft(new TreeNode(10));
        root.getRight().setRight(new TreeNode(14));

        root.getRight().getLeft().setLeft(new TreeNode(9));
        root.getRight().getLeft().setRight(new TreeNode(11));
        root.getRight().getRight().setLeft(new TreeNode(13));
        root.getRight().getRight().setRight(new TreeNode(15));

        return root;
    }
}
