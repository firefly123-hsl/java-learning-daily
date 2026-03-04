import java.util.*;
public class Test1_6 {
    static class Solution {
        public String convert(String s, int numRows) {
            int n = s.length(), r = numRows;
            if (r == 1 || r >= n) {
                return s;
            }
            StringBuffer[] mat = new StringBuffer[r];
            for (int i = 0; i < r; ++i) {
                mat[i] = new StringBuffer();
            }
            for (int i = 0, x = 0, t = r * 2 - 2; i < n; ++i) {
                mat[x].append(s.charAt(i));
                if (i % t < r - 1) {
                    ++x;
                } else {
                    --x;
                }
            }
            StringBuffer ans = new StringBuffer();
            for (StringBuffer row : mat) {
                ans.append(row);
            }
            return ans.toString();
        }
        public static void main(String[] args) {
            Solution solution = new Solution();

            // 测试用例1
            String s1 = "PAYPALISHIRING";
            int numRows1 = 3;
            System.out.println("输入: s = \"" + s1 + "\", numRows = " + numRows1);
            System.out.println("输出: \"" + solution.convert(s1, numRows1) + "\"");
            System.out.println("期望: \"PAHNAPLSIIGYIR\"");
            System.out.println();

            // 测试用例2
            String s2 = "PAYPALISHIRING";
            int numRows2 = 4;
            System.out.println("输入: s = \"" + s2 + "\", numRows = " + numRows2);
            System.out.println("输出: \"" + solution.convert(s2, numRows2) + "\"");
            System.out.println("期望: \"PINALSIGYAHRPI\"");
            System.out.println();

            // 测试用例3：边界情况
            String s3 = "A";
            int numRows3 = 1;
            System.out.println("输入: s = \"" + s3 + "\", numRows = " + numRows3);
            System.out.println("输出: \"" + solution.convert(s3, numRows3) + "\"");
            System.out.println("期望: \"A\"");
        }
    }

}
