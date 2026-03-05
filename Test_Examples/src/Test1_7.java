import java.util.*;
public class Test1_7 {
    static class Solution{
        public int reverse(int x){
            int rev =0;
            while(x!=0){
                if(rev<Integer.MIN_VALUE/10||rev>Integer.MAX_VALUE/10){//跟32位整数最高和最低两个边界/10比较大小，就可以判断是否是一个合格的回文数（不超过32位），因为给的x本身也是int型，32位
                    return 0;
                }
                int digit=x%10;//取出最新的个位
                x/=10;//更新x的具体值
                rev=rev*10+digit;//更新回文数
            }
            return rev;//最终x==0，意味着所有位已经调换完毕，若未返回0则直接返回rev
        }
    }
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 📌 先打印 int 边界值（方便调试参考）
        System.out.println("========== int 类型边界参考 ==========");
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);  // 2147483647
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);  // -2147483648
        System.out.println("MAX_VALUE / 10    = " + (Integer.MAX_VALUE / 10));  // 214748364
        System.out.println("MIN_VALUE / 10    = " + (Integer.MIN_VALUE / 10));  // -214748364
        System.out.println();

        // 🧪 测试用例集合
        testCase(solution, 123, 321, "正常正数");
        testCase(solution, -123, -321, "正常负数");
        testCase(solution, 120, 21, "末尾有0");
        testCase(solution, 0, 0, "输入为0");
        testCase(solution, 1534236469, 0, "正数溢出");
        testCase(solution, -2147483648, 0, "负数溢出（边界值）");
        testCase(solution, 2147483647, 0, "MAX_VALUE反转溢出");
        testCase(solution, 1463847412, 2147483641, "最大合法反转");
        testCase(solution, -1463847412, -2147483641, "最小合法反转");
    }

    /**
     * 统一测试方法
     */
    private static void testCase(Solution sol, int input, int expected, String description) {
        int result = sol.reverse(input);
        String status = (result == expected) ? "✅ 通过" : "❌ 失败";

        System.out.printf("%s | %s%n", status, description);
        System.out.printf("   输入: %d%n", input);
        System.out.printf("   输出: %d | 期望: %d%n", result, expected);
        if (!status.contains("通过")) {
            System.out.println("   ⚠️  结果不匹配，请检查逻辑！");
        }
        System.out.println();
    }
}
