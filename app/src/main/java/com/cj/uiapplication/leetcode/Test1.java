package com.cj.uiapplication.leetcode;

import java.util.HashSet;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 * 限制：
 * 2 <= n <= 100000
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test1 {

    public static void main(String[] args) {
      int[] arr = {2, 3, 1, 0, 3, 5, 3};
        int i1 = fun1(arr);
        int i2 = fun2(arr);
        Util.println("重复的数字是：i1--"+i1);
        Util.println("重复的数字是：i2--"+i2);
    }

    /**
     * 空间复杂度O(1)
     * @param arr
     * @return
     */
    public static int fun1(int[] arr){
       //2, 3, 1, 0, 2, 5, 3
       //1, 3, 2, 0, 2, 5, 3
       //3, 1, 2, 0, 2, 5, 3
       //0, 1, 2, 3, 2, 5, 3
       //0, 1, 2, 3, 2, 5, 3   i=4 arr[4] = 2; arr[4]==arr[2] 结束
        for (int i=0;i<arr.length;i++){
            while (arr[i]!=i){
                //先排序
                if (arr[i] == arr[arr[i]]){
                    return arr[i];
                }
                int temp = arr[arr[i]];
                arr[arr[i]] = arr[i];
                arr[i] = temp;
            }
        }
        return -1;
    }

    /**
     * 常规解法
     * @param arr
     * @return
     */
    public static int fun2(int[] arr){
        HashSet<Integer> set = new HashSet<>();
        for (Integer i:arr){
            if (set.contains(i))
                return i;
            set.add(i);
        }
        return -1;
    }
}
