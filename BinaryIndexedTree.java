public class BinaryIndexedTree { //  works best for range sum queries (with updates)
    
    private int[] tree;
    private int n;
    public BinaryIndexedTree(int[] nums) {
        n = nums.length;
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            update(i + 1, nums[i]);
        }
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    public void update(int idx, int delta) {
        while (idx <= n) {
            tree[idx] += delta;
            idx += lowbit(idx);
        }
    }

    public int query(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= lowbit(idx);
        }
        return sum;
    }

    public int query(int left, int right) { // adjust based on requirement (inclusive? exclusive?)
        return query(right) - query(left);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BinaryIndexedTree bit = new BinaryIndexedTree(nums);
        System.out.println(bit.query(10));
         System.out.println(bit.query(5, 10));
    }
}
