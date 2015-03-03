/**
 * Created with IntelliJ IDEA.
 * User: jiangpeng
 * Date: 15-3-3
 * Time: 下午10:02
 */
public class SolutionTLE {

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

    public static void main(String[] args) {
        long current = System.currentTimeMillis();
        System.out.print("start ------------------\n");
        TreeNode treeRoot = sortedListToBST(createMockListNode(5000));
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
        while (node != null) {
            length++;
            node = node.next;
        }

        return sortedListToBST(head, 0, length - 1);
    }

    private static TreeNode sortedListToBST(ListNode head, int left, int right) {
//        System.out.print(String.format("%s,%s,%s\n", head.val, left, right));
        if (left < right) {
            int middleOffset = (right - left) / 2;
            //重新遍历获取middle node较为耗时
            ListNode middleNode = findNode(head, middleOffset);
            TreeNode root = new TreeNode(middleNode.val);
            root.left = sortedListToBST(head, left, left + middleOffset - 1);
            root.right = sortedListToBST(middleNode.next, left + middleOffset + 1, right);
            return root;
        } else {
            if (head == null) {
                return null;
            } else {
                return new TreeNode(head.val);
            }
        }
    }

    private static ListNode findNode(ListNode head, int offset) {
        if (head == null || offset < 0) {
            return null;
        }

        ListNode listNode = head;
        for (int index = 0; index < offset; index++) {
            listNode = listNode.next;
        }

        return listNode;
    }
}
