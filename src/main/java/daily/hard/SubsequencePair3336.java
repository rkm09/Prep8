package daily.hard;

public class SubsequencePair3336 {
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(subsequencePairCount(nums));
    }

//    dp; time: O(N.M^2logM), space: O(N.M^2)
    public static int subsequencePairCount(int[] nums) {
        int max = 0;
        for (int num : nums)
            max = Math.max(max, num);
        int[][] dp = new int[max + 1][max + 1];
//        base case: both sequences are empty
        dp[0][0] = 1;
        for (int num : nums) {
//            allocate a temporary next-state dp array to avoid operating on dirty data
            int[][] nextDp = new int[max + 1][max + 1];
            for (int i = 0; i <= max; i++) {
                for (int j = 0; j <= max; j++) {
//                    unreachable state; not part of nums
                    if (dp[i][j] == 0)
                        continue;
                    long currentWays = dp[i][j];
//                    option 1: do not include num in either seq1 or seq2
                    nextDp[i][j] = (int) ((nextDp[i][j] + currentWays) % MOD);
//                    option 2: include num in seq1
                    int nextGcd1 = gcd(i, num);
                    nextDp[nextGcd1][j] = (int) ((nextDp[nextGcd1][j] + currentWays) % MOD);
//                    option 3: include num inm se2
                    int nextGcd2 = gcd(j, num);
                    nextDp[i][nextGcd2] = (int) ((nextDp[i][nextGcd2] + currentWays) % MOD);
                }
            }
            dp = nextDp;
        }

//        accumulate the answers where both non-empty sequences share the same gcd;
        int totalPairs = 0;
        for (int g = 1; g <= max; g++)
            totalPairs = (totalPairs + dp[g][g]) % MOD;

        return totalPairs;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}

/*
You are given an integer array nums.
Your task is to find the number of pairs of non-empty subsequences (seq1, seq2) of nums that satisfy the following conditions:
The subsequences seq1 and seq2 are disjoint, meaning no index of nums is common between them.
The GCD of the elements of seq1 is equal to the GCD of the elements of seq2.
Return the total number of such pairs.
Since the answer may be very large, return it modulo 10^9 + 7.
Example 1:
Input: nums = [1,2,3,4]
Output: 10
Explanation:
The subsequence pairs which have the GCD of their elements equal to 1 are:
([1], [2, 3])
([2, 3], [1])
([1], [3, 4])
([3, 4], [1])
([1], [2, 3, 4])
([2, 3, 4], [1])
([1, 2], [3, 4])
([3, 4], [1, 2])
([1, 4], [2, 3])
([2, 3], [1, 4])
Example 2:
Input: nums = [10,20,30]
Output: 2
Explanation:
The subsequence pairs which have the GCD of their elements equal to 10 are:
([10], [20, 30])
([20, 30], [10])
Example 3:
Input: nums = [1,1,1,1]
Output: 50

Constraints:
1 <= nums.length <= 200
1 <= nums[i] <= 200
 */
