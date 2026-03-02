public class Test1_4 {

    // ============ 核心算法方法 ============
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totallength = length1 + length2;
        if (totallength == 0) {
            return 0.0; // 或根据需求抛出异常
        }
        if (totallength % 2 == 1) { // 奇数个元素
            int midIndex = totallength / 2;
            return getKthElement(nums1, nums2, midIndex + 1);
        } else { // 偶数个元素，取中间两个的平均值
            int midIndex = totallength / 2;
            return (getKthElement(nums1, nums2, midIndex) +
                    getKthElement(nums1, nums2, midIndex + 1)) / 2.0;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;

        while (true) {
            // 边界情况1：nums1已遍历完
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            // 边界情况2：nums2已遍历完
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            // 边界情况3：找第1小的数
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况：二分排除
            int half = k / 2;
            int newindex1 = Math.min(index1 + half, length1) - 1;
            int newindex2 = Math.min(index2 + half, length2) - 1;

            if (nums1[newindex1] <= nums2[newindex2]) {
                k -= (newindex1 - index1 + 1);  // 排除nums1中 [index1, newindex1] 共 (newindex1-index1+1) 个元素
                index1 = newindex1 + 1;
            } else {
                k -= (newindex2 - index2 + 1);
                index2 = newindex2 + 1;
            }
        }
    }

    // ============ 本地测试入口 ============
    public static void main(String[] args) {
        Test1_4 solution = new Test1_4();

        // 测试用例：{数组1, 数组2, 预期结果}
        Object[][] testCases = {
                // 格式：new int[]{...}, new int[]{...}, 预期double值
                {new int[]{1, 3}, new int[]{2}, 2.0},                    // 奇数总长: [1,2,3] → 中位数2
                {new int[]{1, 2}, new int[]{3, 4}, 2.5},                // 偶数总长: [1,2,3,4] → (2+3)/2=2.5
                {new int[]{0, 0}, new int[]{0, 0}, 0.0},                // 全相同元素
                {new int[]{}, new int[]{1}, 1.0},                        // nums1为空
                {new int[]{2}, new int[]{}, 2.0},                        // nums2为空
                {new int[]{}, new int[]{}, 0.0},                         // 双空（特殊边界，按需处理）
                {new int[]{1, 3, 5, 7, 9}, new int[]{2, 4, 6, 8, 10}, 5.5}, // 等长交错: [1..10] → (5+6)/2=5.5
                {new int[]{1, 2, 3}, new int[]{4, 5, 6, 7, 8, 9}, 5.0}, // 长度不等: 中位数是第5小=5
                {new int[]{1, 1, 3, 3}, new int[]{1, 1, 3, 3}, 1.0},    // 重复元素
                {new int[]{1, 2}, new int[]{1, 2, 3}, 2.0},             // 有重复+奇数总长
        };

        System.out.println("🔍 开始测试 findMedianSortedArrays：\n");
        int passed = 0;

        for (int i = 0; i < testCases.length; i++) {
            int[] nums1 = (int[]) testCases[i][0];
            int[] nums2 = (int[]) testCases[i][1];
            double expected = (double) testCases[i][2];

            double result = solution.findMedianSortedArrays(nums1, nums2);
            boolean isPass = Math.abs(result - expected) < 1e-6;  // 浮点数比较用误差范围

            if (isPass) passed++;

            // 格式化输出
            System.out.printf("✅ 用例 %d: %s\n", i + 1, isPass ? "通过" : "❌ 失败");
            System.out.printf("   输入: nums1 = %s, nums2 = %s\n",
                    arrayToString(nums1), arrayToString(nums2));
            System.out.printf("   输出: %.1f | 预期: %.1f\n\n", result, expected);
        }

        // 测试总结
        System.out.println("═══════════════════════════════");
        System.out.printf("📊 测试完成: %d/%d 用例通过\n", passed, testCases.length);
        if (passed == testCases.length) {
            System.out.println("🎉 全部通过！代码逻辑正确 ✅");
        } else {
            System.out.println("⚠️  有失败用例，请检查逻辑 🔍");
        }
    }

    // 辅助方法：打印数组
    private static String arrayToString(int[] arr) {
        if (arr == null || arr.length == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}