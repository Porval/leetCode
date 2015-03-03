/**
 * Created with IntelliJ IDEA.
 * User: jiangpeng
 * Date: 15-3-3
 * Time: 下午10:58
 */
public class Solution {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static ListNode mCurrentNode = null;

    public static void main(String[] args) {
        long current = System.currentTimeMillis();
        System.out.print("start ------------------\n");
        TreeNode treeRoot = sortedListToBST(createMockListNode(5));
        System.out.print("end ---------------------\n");
        System.out.print(String.format("cost time %s ms", (System.currentTimeMillis() - current)));
    }

    private static ListNode createMockListNode(int count) {
        if (count > 0) {
            ListNode header = new ListNode(0);
            ListNode tail = header;
            for (int index = 1; index < count; index++) {
                ListNode node = new ListNode(index);
                tail.next = node;
                tail = node;
            }
            return header;
        }

        return null;
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        int length = 0;
        ListNode node = head;
        mCurrentNode = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        return sortedListToBST(0, length - 1);
    }

    private static TreeNode sortedListToBST(int left, int right) {
//        System.out.print(String.format("%s,%s,%s\n", head.val, left, right));
        if (left > right) {
            return null;
        }

        int middleOffset = left + (right - left) / 2;
        TreeNode leftNode = sortedListToBST(left, middleOffset - 1);

        //利用ListNode有序性质，建立treeNode的先后次序根据List排列顺序建立
        TreeNode root = new TreeNode(mCurrentNode.val);
        mCurrentNode = mCurrentNode.next;
        root.left = leftNode;

        root.right = sortedListToBST(middleOffset + 1, right);
        return root;
    }
}
