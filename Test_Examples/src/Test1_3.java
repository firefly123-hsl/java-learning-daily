import java.util.Set;
import java.util.HashSet;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        int rk = -1, ans = 0;//rk是右指针用来界定右边界的

        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                occ.remove(s.charAt(i - 1));//这一步是用来不断更换开头字符的，不断右移
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {//运用的是Set类是不重复型性质
                occ.add(s.charAt(rk + 1));//charAt方法是提取该位置的字符，是属于String类方法
                ++rk;
            }
            ans = Math.max(ans, rk - i + 1);//每次i++前，更新最大ans，rk-i+1这个是左右两指针之间的距离公式，表示不重复
        }
        return ans;
    }

    // ============ 本地测试入口 ============
    public static void main(String[] args) {
        Solution solution = new Solution();//实验组

        // 测试用例数组
        String[] testCases = {
                "abcabcbb",  // 预期: 3 ("abc")
                "bbbbb",     // 预期: 1 ("b")
                "pwwkew",    // 预期: 3 ("wke")
                "",          // 预期: 0
                "abcdef",    // 预期: 6 (整个串)
                "aab",       // 预期: 2 ("ab")
                "dvdf"       // 预期: 3 ("vdf")
        };

        System.out.println("🔍 开始测试 lengthOfLongestSubstring：\n");
        for (String s : testCases) {
            int result = solution.lengthOfLongestSubstring(s);
            System.out.printf("输入: \"%s\" → 输出: %d%n",
                    s.isEmpty() ? "(空串)" : s, result);
        }
    }
}