public class Test1_5 {
    static class Solution {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) {
                return "";
            }
            int start = 0;
            int end = 0;

            // 遍历每个字符，把它当作中心点
            for (int i = 0; i < s.length(); i++) {

                // 情况1：奇数长度回文（中心是一个字符）
                int len1 = expandAroundCenter(s, i, i);

                // 情况2：偶数长度回文（中心是两个字符之间）
                int len2 = expandAroundCenter(s, i, i + 1);

                // 取两种情况中较长的
                int len = Math.max(len1, len2);

                // 如果找到更长的回文串，更新起始和结束位置
                if (len > end - start) {
                    // 计算新的起始位置
                    // i是当前中心位置，len是回文长度
                    // 起始位置 = 中心 - (长度-1)/2
                    start = i - (len - 1) / 2;
                    // 结束位置 = 中心 + 长度/2
                    end = i + len / 2;
                }
            }

            // 返回最长回文子串（substring是左闭右开）
            return s.substring(start, end + 1);
        }

        private int expandAroundCenter(String s, int left, int right) {
            // 当左右指针未越界，且左右字符相等时，继续扩展
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;   // 左指针向左移动
                right++;  // 右指针向右移动
            }

            // 返回回文串的长度
            // 因为while循环退出时，left和right已经越界或不相等
            // 所以实际长度是 right - left - 1
            return right - left - 1;
        }

        // 测试代码
        public static void main(String[] args) {
            Solution solution = new Solution();

            // 测试示例1
            String s1 = "babad";
            System.out.println("输入: " + s1);
            System.out.println("输出: " + solution.longestPalindrome(s1));
            // 可能输出: "bab" 或 "aba"

            System.out.println();

            // 测试示例2
            String s2 = "cbbd";
            System.out.println("输入: " + s2);
            System.out.println("输出: " + solution.longestPalindrome(s2));
            // 输出: "bb"
        }
    }
}
