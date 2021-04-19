package com.cj.uiapplication.leetcode;

public class Util {
    public static void println(Object object){
        System.out.println(object);
    }
    public static void print(Object object){
        System.out.print(object);
        System.out.print(",");
    }

    public static void printListNode(ListNode listNode){
        while (listNode != null){
            print(listNode.value);
            listNode = listNode.next;
        }
        println("");
    }
}
