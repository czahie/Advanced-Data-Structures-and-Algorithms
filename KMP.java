import java.util.Arrays;

public class KMP {
    
    public static void main(String[] args) { // find pattern 
        String s = "ababcabcabababd";
        String pattern = "ababd";

        // build prefix function
        int n = pattern.length();
        int[] prefixFunc = new int[n];

        for (int i = 1 ; i < n; i++) {
            int j = prefixFunc[i - 1];
            while (j > 0 && s.charAt(j) != s.charAt(i)) {
                j = prefixFunc[j - 1];
            }
            if (s.charAt(j) == s.charAt(i)) {
                j++;
            }
            prefixFunc[i] = j;
        }

        System.out.println(Arrays.toString(prefixFunc));
        
        int m = s.length();
        int j = 0;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) == pattern.charAt(j)) {
                j++;
                if (j == n) { // find
                    System.out.println("The pattern starts from index " + (i - j + 1));
                }
            } else {
                j = prefixFunc[j];
            }
        }

    }
}
