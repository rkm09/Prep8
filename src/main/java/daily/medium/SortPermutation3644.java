package daily.medium;


public class SortPermutation3644 {
    static void main() {
        int[] nums = {3,2,1,0};
        System.out.println(sortPermutation(nums));
    }

//  traverse and compute bitwise; time: O(n), space: O(1)
    public static int sortPermutation(int[] nums) {
        int n = nums.length, res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                res &= nums[i];
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

/*
You are given an integer array nums of length n, where nums is a permutation of the numbers in the range [0..n - 1].
You may swap elements at indices i and j only if nums[i] AND nums[j] == k, where AND denotes the bitwise AND operation
and k is a non-negative integer.
Return the maximum value of k such that the array can be sorted in non-decreasing order using any number of such swaps.
If nums is already sorted, return 0.
Example 1:
Input: nums = [0,3,2,1]
Output: 1
Explanation:
Choose k = 1. Swapping nums[1] = 3 and nums[3] = 1 is allowed since nums[1] AND nums[3] == 1, resulting in a sorted permutation: [0, 1, 2, 3].
Example 2:
Input: nums = [0,1,3,2]
Output: 2
Explanation:
Choose k = 2. Swapping nums[2] = 3 and nums[3] = 2 is allowed since nums[2] AND nums[3] == 2, resulting in a sorted permutation: [0, 1, 2, 3].
Example 3:
Input: nums = [3,2,1,0]
Output: 0
Explanation:
Only k = 0 allows sorting since no greater k allows the required swaps where nums[i] AND nums[j] == k.

Constraints:
1 <= n == nums.length <= 10^5
0 <= nums[i] <= n - 1
nums is a permutation of integers from 0 to n - 1.
 */


/*
First, consider a key property: if two numbers a and b satisfy a&b=k, every set bit of k must also be set in both a and b. In other words, the set bits of k must be a subset of the set bits in both a and b, which implies a&k=k and b&k=k.
We ultimately need to transform the permutation nums into [0,1,2,…,n−1]. Elements already in their correct positions, where nums[i]=i, do not need to participate in any swaps. Every misplaced element must reach its correct position through swaps.
Every misplaced element nums[i] (where nums[i]=i)
must participate in at least one swap satisfying nums[i]&nums[j]=k for some index j. Therefore, it must satisfy nums[i]&k=k, meaning that every set bit of k must be present in all misplaced elements.
Thus, an upper bound on k is given by the bitwise AND of all misplaced elements:
k∗ = ⋀ nums[i]=inums[i].
In particular, if the array is already sorted, there are no elements to move, so return 0.
Next, we prove that k∗ is always feasible. Since bitwise AND retains only the set bits shared by all operands, every set bit of k∗
appears in every misplaced element. Thus, k∗ & x = k∗ for any misplaced element x.
 */