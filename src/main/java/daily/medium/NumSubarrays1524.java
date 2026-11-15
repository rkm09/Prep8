package daily.medium;

public class NumSubarrays1524 {
    private static final int MOD = 1_000_000_007;
    static void main() {
        int[] arr = {1,3,5};
        System.out.println(numOfSubarrays(arr));
    }

//    prefix sum; time: O(n), space: O(1)
    public static int numOfSubarrays(int[] arr) {
        int totalOddSubarrays = 0, prefixSum = 0;
//        evenCount starts as 1 as the initial sum is even (0);
        int evenCount = 1, oddCount = 0;
        for (int num : arr) {
            prefixSum += num;
            if (prefixSum % 2 == 0) {
                totalOddSubarrays = (totalOddSubarrays + oddCount) % MOD;
                evenCount++;
            } else {
                totalOddSubarrays = (totalOddSubarrays + evenCount) % MOD;
                oddCount++;
            }
        }

        return (totalOddSubarrays % MOD);
    }

//    TLE; brute force; time: O(n^2), space: O(1)
    public static int numOfSubarraysX(int[] arr) {
        int n = arr.length, count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum % 2 != 0)
                    count++;
            }
        }

        return count % MOD;
    }
}

/*
Given an array of integers arr, return the number of subarrays with an odd sum.
Since the answer can be very large, return it modulo 109 + 7.
Example 1:
Input: arr = [1,3,5]
Output: 4
Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
All sub-arrays sum are [1,4,9,3,8,5].
Odd sums are [1,9,3,5] so the answer is 4.
Example 2:
Input: arr = [2,4,6]
Output: 0
Explanation: All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
All sub-arrays sum are [2,6,12,4,10,6].
All sub-arrays have an even sum and the answer is 0.
Example 3:
Input: arr = [1,2,3,4,5,6,7]
Output: 16

Constraints:
1 <= arr.length <= 10^5
1 <= arr[i] <= 100
 */

/*
Instead of computing the sum of every possible subarray from scratch, we can leverage prefix sums to speed things up.
The key insight is that the sum of any subarray can be determined by the difference between two prefix sums.
To understand this, consider the prefix sum at an index, which represents the cumulative sum of elements from the
start of the array up to that index. The sum of a subarray starting at index i and ending at j is simply the
difference between the prefix sum at j and the prefix sum at i - 1. This means that whether a subarray sum is odd
or even depends only on the parity (odd/even property) of these two prefix sums.

From this, we can make a crucial observation:

If two prefix sums have the same parity (both even or both odd), their difference will be even, meaning the subarray
sum is even.
If two prefix sums have different parity (one is even, the other is odd), their difference will be odd, meaning the
subarray sum is odd.
This leads to an efficient way to count odd subarrays as we traverse the array. We maintain a cumulative prefixSum
while keeping track of how many times we've seen an even or odd prefix sum before the current index. As we process each element:

A subarray sum is odd if and only if the difference between its ending prefix sum and starting prefix sum has mixed
parity (one is odd, and the other is even).Instead of checking all $\mathcal{O}(n^2)$ subarrays, we can keep a running
prefix sum and track how many even and odd prefix sums we have seen so far:Initialize evenCount = 1 and oddCount = 0
(to account for the base case of a prefix sum of 0, which is even).As we iterate through the array, we update our
running sum.If the current prefix sum is odd, it will form an odd-sum subarray with every previous even prefix sum.
We add evenCount to our total and increment oddCount.If the current prefix sum is even, it will form an odd-sum
subarray with every previous odd prefix sum. We add oddCount to our total and increment evenCount.
 */