package com.cj.uiapplication.leetcode;

import java.util.HashSet;

/**
 * 剑指 Offer 04. 二维数组中的查找
 *
 *
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *  
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 *
 * 限制：
 *
 * 0 <= n <= 1000
 *
 * 0 <= m <= 1000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test2 {

    public static void main(String[] args) {
        int[][] matrix = { {1,4,7,11,15},
                           {2,5,8,12,19},
                           {3,6,9,16,22},
                           {10,13,14,17,24},
                           {18,21,23,26,30}};
        boolean b5 = findNumberIn2DArray(matrix, 5);
        boolean b20 = findNumberIn2DArray(matrix, 20);

        boolean b51 = findNumberIn2DArray1(matrix, 5);
        boolean b201 = findNumberIn2DArray1(matrix, 20);
        Util.println(b5);
        Util.println(b20);

        Util.println(b51);
        Util.println(b201);
    }

    /**
     * 从右上角开始查找
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = 0;
        int column = matrix[0].length-1;
        while (row<matrix.length && column>=0){
            if (matrix[row][column] == target){
                return true;
            }else if (matrix[row][column]>target){
                column--;
            }else {
                row++;
            }
        }
        return false;
    }

    /**
     * 从左下角开始查找
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length-1;
        int column = 0;
        while (row>=0 && column<matrix[0].length){
            if (matrix[row][column] == target){
                return true;
            }else if (matrix[row][column]>target){
                row--;
            }else {
                column++;
            }
        }
        return false;
    }
}
