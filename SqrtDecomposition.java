public class SqrtDecomposition { // range queries in O(sqrt(n, 2)), applies to sum / min / max, including update

    int[] b;
    int len;
    int[] nums;
    public SqrtDecomposition(int[] nums) {
        this.nums = nums;
        // precompute
        int n = nums.length;
        this.len = (int) Math.sqrt(n);
        b = new int[len + 1];

        for (int i = 0; i < n; i++) {
            b[i / len] += nums[i];
        }
    }

    public int query(int l, int r) {
        int sum = 0;
        int li = l / len;
        int ri = r / len;
        if (li == ri) { // same block
            for (int i = l; i <= r; i++) {
                sum += nums[i];
            }
            return sum;
        }
        for (int i = l; i < (li + 1) * len; i++) {
            sum += nums[i];
        }
        for (int i = li + 1; i < ri; i++) {
            sum += b[i];
        }
        for (int i = ri * len; i <= r; i++) {
            sum += nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        SqrtDecomposition sd = new SqrtDecomposition(nums);
        System.out.println(sd.query(3, 4));
    }
}