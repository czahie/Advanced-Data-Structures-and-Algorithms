import java.util.Random;

public class RandomizedHeap {
    
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    TreeNode root;
    Random random;
    public RandomizedHeap(int[] nums) {
        random = new Random();
        for (int num: nums) {
            insert(num);
        }
    }

    public TreeNode merge(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        
        if (t1.val > t2.val) {
            TreeNode temp = t1;
            t1 = t2;
            t2 = temp;
        }

        if (random.nextInt(2) % 2 == 0) {
            TreeNode temp = t1.left;
            t1.left = t1.right;
            t1.right = temp;
        }
        
        t1.left = merge(t1.left, t2);
        root = t1;
        return t1;
    }

    public void insert(int val) {
        TreeNode node = new TreeNode(val);
        root = merge(root, node);
    }

    public int getMin() {
        return root.val;
    }

    public int removeMin() {
        int res = root.val;
        root = merge(root.left, root.right);
        return res;
    }

    public boolean removeVal(int val) {
        return removeVal(val, root);
    }

    private boolean removeVal(int val, TreeNode node) {
        if (node == null) {
            return false;
        }

        if (node.val == val) {
            removeMin();
            return true;
        }

        return removeVal(val, node.left) || removeVal(val, node.right);
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 4, 6};
        RandomizedHeap heap = new RandomizedHeap(nums);
        int[] nums2 = new int[]{1, 3, 5};
        RandomizedHeap heap2 = new RandomizedHeap(nums2);
        heap.merge(heap.root, heap2.root);
        heap.removeMin();
        System.out.println(heap.getMin());
        heap.removeMin();
        System.out.println(heap.getMin());

        // System.out.println(heap.getMin());
        // System.out.println(heap.removeVal(4));
        // System.out.println(heap.getMin());
        // System.out.println(heap.removeVal(2));
        // System.out.println(heap.removeMin());
        // System.out.println(heap.getMin());
    }
}
