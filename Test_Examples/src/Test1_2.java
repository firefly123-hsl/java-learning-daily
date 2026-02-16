import java.util.Scanner;
class ListNode{
    int val;
    ListNode Next;
    ListNode(int val){
        this.val=val;
    }
}
public class Test1_2 {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.print("输入 l1（空格分隔，如 2 4 3）：");
        ListNode l1=buildList(sc.nextLine());
        System.out.print("输入 l2（空格分隔，如 2 4 3）：");
        ListNode l2=buildList(sc.nextLine());//这里是在调用后面的方法，把字符串变成链表构造
        Solution2 sol=new Solution2();
        ListNode result=sol.addTwoNumbers(l1,l2);
        System.out.print("结果链表：");
        printList(result);
        sc.close();
    }
    static ListNode buildList(String input){
        String[] parts=input.trim().split("\\s+");//把字符串s按照空格分隔成字符串数组
        if(parts.length==0||parts[0].isEmpty())return null;
        ListNode dummy=new ListNode(0),curr=dummy;
        for(String s:parts){
            curr.Next=new ListNode(Integer.parseInt(s));//这是把字符型转化为整型数据
            curr=curr.Next;
        }
        return dummy.Next;//dummy是链表的最开头，初始化的开头
    }
    static void printList(ListNode head){
        while (head != null) {
            System.out.print(head.val);
            if (head.Next != null) System.out.print(" → ");
            head = head.Next;
        }
        System.out.println();
    }
}
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {//只要存在l1和l2有没有遍历完全的就继续循环
            int n1 = (l1 != null) ? l1.val : 0;//只要l1不是空，就赋值，否则为0
            int n2 = (l2 != null) ? l2.val : 0;//只要l2不是空，就赋值，否则为0
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);//这一部分是在初始化开头
            } else {
                tail.Next = new ListNode(sum % 10);//这一部分是在给tail加链表新数据
                tail = tail.Next;
            }
            carry = sum / 10;//这是判断是否存在进位，除10会取整，为一说明有进位
            if (l1 != null) l1 = l1.Next;
            if (l2 != null) l2 = l2.Next;//这一部分是在把l1，l2同步到下一位，如果有的话
        }
        if (carry > 0) {
            tail.Next = new ListNode(carry);//最后一位如果是相加进位，那就再加一位在链表后面
        }
        return head;//返回的是一个链表的首尾地址
    }
}
