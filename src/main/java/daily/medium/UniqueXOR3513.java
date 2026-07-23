package daily.medium;

public class UniqueXOR3513 {
    public static void main(String[] args) {
        int[] nums = {3,1,2};
        System.out.println(uniqueXorTriplets(nums));
    }

//    bit manipulation; time: O(logN), space: O(1)
    public static int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n <= 2)
            return n;
        int ans = 1;
        while (ans <= n)
            ans <<= 1;

        return ans;
    }
}

/*
You are given an integer array nums of length n, where nums is a permutation of the numbers in the range [1, n].
An XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j <= k.
Return the number of unique XOR triplet values from all possible triplets (i, j, k).
Example 1:
Input: nums = [1,2]
Output: 2
Explanation:
The possible XOR triplet values are:
(0, 0, 0) → 1 XOR 1 XOR 1 = 1
(0, 0, 1) → 1 XOR 1 XOR 2 = 2
(0, 1, 1) → 1 XOR 2 XOR 2 = 1
(1, 1, 1) → 2 XOR 2 XOR 2 = 2
The unique XOR values are {1, 2}, so the output is 2.
Example 2:
Input: nums = [3,1,2]
Output: 4
Explanation:
The possible XOR triplet values include:
(0, 0, 0) → 3 XOR 3 XOR 3 = 3
(0, 0, 1) → 3 XOR 3 XOR 1 = 1
(0, 0, 2) → 3 XOR 3 XOR 2 = 2
(0, 1, 2) → 3 XOR 1 XOR 2 = 0
The unique XOR values are {0, 1, 2, 3}, so the output is 4.

Constraints:
1 <= n == nums.length <= 105
1 <= nums[i] <= n
nums is a permutation of integers from 1 to n.
 */


/*
- When working with bitwise XOR, the binary representation—specifically powers of 2 ($2^0, 2^1, 2^2, \dots$)—
determines the maximum reach of any combined value.
- Finding the Ceiling (Power of 2):
To know the absolute highest possible number we could generate, we look at $n$'s highest set bit.
Let $2^k$ be the largest power of 2 that is less than or equal to $n$. The maximum value any XOR combination can reach
is limited by the next power of 2: $2^{k+1} - 1$ (which is a number made of all $1$s up to that bit length).
Example A: $n = 5$ (Binary: $101_2$).
The highest bit is $2^2 = 4$.
The upper bound for any XOR result is $2^3 - 1 = 7$ ($111_2$).
When n≥3, the situation is completely different. Let the highest power of two not exceeding n be 2^k, that is, 2^k ≤ n <2^k+1
. We claim that every integer in the range [0,2^k+1−1] can be constructed.
For 0, we have 1⊕2⊕3=0. Since n≥3, all three numbers are in the array.
For any x∈[1,n], we have 1⊕1⊕x=x. Since both 1 and x are in the array, every such value can be constructed.
Therefore, every integer in the range [0,2^k+1−1] can be constructed when n≥3.
Hence, the number of distinct XOR values is 2^k+1, which is exactly the smallest power of two greater than n.
 */
