package com.cj.uiapplication.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 剑指 Offer 07. 重建二叉树
 * <p>
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 *  
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= 节点个数 <= 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test5 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.value = 3;
        TreeNode node1 = new TreeNode();
        node1.value = 9;
        TreeNode node2 = new TreeNode();
        node2.value = 20;
        TreeNode node3 = new TreeNode();
        node3.value = 15;
        TreeNode node4 = new TreeNode();
        node4.value = 7;

        TreeNode node5 = new TreeNode();
        node5.value = 1;
        TreeNode node6 = new TreeNode();
        node6.value = 4;

        root.left = node1;
        root.right = node2;

        node2.left = node3;
        node2.right = node4;

        node1.left = node5;
        node1.right = node6;

       // preOrder(root);//前序遍历 二叉树
       // preOrder1(root);//
       // inorder(root);//
        inorder1(root);
       // bfs(root);
    }

    /**
     * 二叉树前序遍历  先根 后左 再右
     *       3
     *      / \
     *     9  20
     *   / \  /  \
     *  1  4 15   7
     *  3,9,1,4,20,15,7
     *  递归显示
     */
    private static void preOrder(TreeNode root){
        if (root == null){
            return;
        }
        Util.print(root.value);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 非递归实现 借助栈实现
     * @param root
     */
    private static void preOrder1(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            Util.print(pop.value);
            if (pop.right != null){
                stack.push(pop.right);
            }
            if (pop.left != null){
                stack.push(pop.left);
            }
        }
    }

    /**
     * 二叉树中序遍历  先左 后根 再右
     *       3
     *      / \
     *     9  20
     *   / \  /  \
     *  1  4 15   7
     *
     * 1，9,4,3,15,20,7
     * 递归实现
     */
    private static void inorder(TreeNode root){
        if (root == null){
            return;
        }
        inorder(root.left);
        Util.print(root.value);
        inorder(root.right);
    }

    /**
     * 二叉树中序遍历  先左 后根 再右
     *      *       3
     *      *      / \
     *      *     9  20
     *      *   / \  /  \
     *      *  1  4 15   7
     * @param root
     *
     */
    private static void inorder1(TreeNode root){
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        while (current != null || !stack.isEmpty()){
            while (current != null){
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty()){
                current = stack.pop();
                Util.print(current.value);
                current =current.right;
            }
        }
        //   9 3
    }

    /**
     *  二叉树中序遍历  先左 后根 再右
     *      *       3
     *      *      / \
     *      *     9  20
     *      *   / \  /  \
     *      *  1  4 15   7
     * @param root
     * 广度搜索算法 3,9,20,1,4,15,7  借助队列实现
     */
    private static void bfs(TreeNode root){
        if (root == null){
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            Util.print(poll.value);
            if (poll.left !=null){
                queue.add(poll.left);
            }
            if (poll.right !=null){
                queue.add(poll.right);
            }
        }
    }
}
