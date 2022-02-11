public class SegmentTree { // range query for sum, min, max, gcd, lcm, and more advanced usages
    
    int[] tree;
    int[] nums;
    int n; 

    public SegmentTree(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        tree = new int[4 * n];
        build(1, 0, n - 1);
    }

    private void build(int idx, int segLeft, int segRight) { // O(n)
        if (segLeft == segRight) {
            tree[idx] = nums[segLeft];
            return;
        }
        int segMid = (segRight - segLeft) / 2 + segLeft;
        build(idx * 2, segLeft, segMid);
        build(idx * 2 + 1, segMid + 1, segRight);
        tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
    }

    public int query (int left, int right) {
        return query(left, right, 0, n - 1, 1);
    }

    private  int query(int left, int right, int segLeft, int segRight, int idx) {
        if (left > right) {
            return 0;
        }
        if (left == segLeft && right == segRight) {
            return tree[idx];
        }
        int segMid = (segRight - segLeft) / 2 + segLeft;
        return query(left, Math.min(right, segMid), segLeft, segMid, idx * 2) + query(Math.max(left, segMid + 1), right, segMid + 1, segRight, idx * 2 + 1);
    }

    public void update(int arrIdx, int val) {
        update(arrIdx, val, 1, 0, n - 1);
    }

    private void update(int arrIdx, int val, int idx, int segLeft, int segRight) {
        if (segLeft == segRight) {
            tree[idx] = val;
            return;
        }
        int segMid = (segRight - segLeft) / 2 + segLeft;
        if (segMid >= arrIdx) {
            update(arrIdx, val, idx * 2, segLeft, segMid);
        } else {
            update(arrIdx, val, idx * 2 + 1, segMid + 1, segRight);
        }
        tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        SegmentTree st = new SegmentTree(nums);
        System.out.println(st.query(4, 9));
        st.update(9, 11);
        System.out.println(st.query(4, 9));
    } 
}
