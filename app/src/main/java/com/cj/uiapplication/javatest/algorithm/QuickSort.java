package com.cj.uiapplication.javatest.algorithm;

import java.util.Arrays;

/**
 * 快速排序
 * 3,1,9,5,7,4,6,6,34,2,23,44
 * 3,1,2,5,7,4,6,6,34,9,23,44
 * 2,1,3,5,7,4,6,6,34,9,23,44
 * 1,2,3,5,7,4,6,6,34,9,23,44
 * 1,2,3,5,7,4,6,6,34,9,23,44
 * 1,2,3,5,6,4,6,7,34,9,23,44
 * 1,2,3,5,6,4,6,7,34,9,23,44
 * 1,2,3,5,4,6,6,7,34,9,23,44
 * 1,2,3,5,4,6,6,7,23,9,34,44
 * 1,2,3,5,4,6,6,7,9,23,34,44
 * 1,2,3,5,4,6,6,7,9,23,34,44
 *
 */
public class QuickSort {
    public static void main(String[] args){
          int[] arr = {3,1,9,5,7,4,6,6,34,2,23,44};
          System.out.println(Arrays.toString(arr));
          quick(arr,0,arr.length-1);
       // System.out.println("----------------------------------");
        System.out.println(Arrays.toString(arr));
    }

    private static void quick(int[] arr,int left,int right){
        if (left>right)
            return;
          int temp;
          int key = arr[left];//基准值
          int i = left;
          int j = right;
          while (i<j){
             // System.out.println("i:"+i+" j:"+j);
               while (arr[j]>=key && j>i){
                   j--;
               }
              while (arr[i]<=key&&i<j){
                  i++;
              }
             // System.out.println("i="+i+" arr[i]:"+arr[i]+" |  j="+j+" arr[j]:"+arr[j]);
              if (i<j){
                  temp = arr[i];
                  arr[i] = arr[j];
                  arr[j] = temp;
              }
          }
          arr[left] = arr[i];
          arr[i] = key;
          //递归左边的
        quick(arr,left,j-1);
        //递归右边的
        quick(arr,j+1,right);
    }
}
