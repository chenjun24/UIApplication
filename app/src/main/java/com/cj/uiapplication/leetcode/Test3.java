package com.cj.uiapplication.leetcode;


import android.graphics.SurfaceTexture;

/**
 *剑指 Offer 05. 替换空格
 *
 *请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *  
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Test3 {

    public static void main(String[] args) {
      String s = "We are happy.";
        String s1 = replaceSpace(s);
        Util.println(s1);
    }

    public static String replaceSpace(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i<s.length();i++){
            if (s.charAt(i) == ' '){
                builder.append("%20");
            }else {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
