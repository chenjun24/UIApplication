package com.cj.uiapplication.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *  
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Test4 {

    public static void main(String[] args) {
       ListNode node2 = new ListNode(2,null);
       ListNode node1 = new ListNode(3,node2);
       ListNode head = new ListNode(1,node1);
       //[1,3,2]
        Util.printListNode(head);
        int[] ints = reversePrint(head);
        int[] ints1 = reversePrint1(head);
        Util.println(Arrays.toString(ints));
        Util.println(Arrays.toString(ints1));

    }

    /**
     * 辅助 利用栈 的先进后出 特点
     * @param head
     * @return
     */
    public static int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null){
            stack.push(temp.value);
            temp = temp.next;
        }
        int[] arr = new int[stack.size()];
        for (int i=0;i<arr.length;i++){
            arr[i] = stack.pop();
        }
        return arr;
    }

    // 1 3 2
    /**
     * 递归实现
     * @param head
     * @return
     */
    public static int[] reversePrint1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        fun(head,list);
        int[] arr = new int[list.size()];
        for (int i=0;i<arr.length;i++){
            arr[i] = list.get(i);
        }
        return arr;
    }

    private static void fun(ListNode temp,List<Integer> list){
        if (temp == null) return;
        //递归 回溯
        if (temp.next != null){
            fun(temp.next,list);
        }
        list.add(temp.value);
    }


}
