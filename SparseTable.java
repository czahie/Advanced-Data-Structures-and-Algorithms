public class SparseTable { // works best for range minimum, maximum, gcd queries
    
    private static final int MAX_LOG = 17;

    // sparseTable[i][j] = the minimum number from nums[i] to nums[i + (1 << j) - 1] inclusive
    private int[][] sparseTable;
    // floor of the binary log of a given number
    private int[] binLog;
    private int[] nums;
    private int n;
    
    public SparseTable(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        sparseTable = new int[n][MAX_LOG];
        binLog = new int[n + 1];
    
        // precompute binLog
        binLog[1] = 0;
        for (int i = 2; i <= n; i++) {
            binLog[i] = binLog[i / 2] + 1;
        } 

        // precompute sparse table. O(nlogn)
        preCompute();
    }

    private void preCompute() {
        for (int i = 0; i < n; i++) {
            sparseTable[i][0] = nums[i];
        }

        for (int j = 1; j <= MAX_LOG; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                sparseTable[i][j] = Math.min(sparseTable[i][j - 1], sparseTable[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    public int query(int left, int right) { // O(1)
        int len = right - left + 1;
        int log = binLog[len];
        return Math.min(sparseTable[left][log], sparseTable[right - (1 << log) + 1][log]);
    }



    // range minimum query
    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 5, 6, 10, 7, 3, 2, 1, 8};
        SparseTable st = new SparseTable(nums);
        System.out.println(st.query(5, 7));
    }
}
