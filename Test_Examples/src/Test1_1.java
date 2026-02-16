import java.util.Scanner;

public class Test1_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 提示并读取数组
        System.out.print("请输入数组元素（用空格分隔）：");
        String[] inputArr = scanner.nextLine().trim().split("\\s+");
        int[] nums = new int[inputArr.length];
        for (int i = 0; i < inputArr.length; i++) {
            nums[i] = Integer.parseInt(inputArr[i]);
        }

        // 读取目标值
        System.out.print("请输入目标值 target：");
        int target = scanner.nextInt();

        // 调用解法
        Solution1 solution = new Solution1();
        int[] result = solution.twoSum(nums, target);

        // 输出结果
        if (result.length == 2) {
            System.out.println("结果索引: [" + result[0] + ", " + result[1] + "]");
            System.out.println("对应数值: " + nums[result[0]] + " + " + nums[result[1]]
                    + " = " + (nums[result[0]] + nums[result[1]]));
        } else {
            System.out.println("未找到符合条件的两个数");
        }

        scanner.close();
    }
}

class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
