package daily.medium;

import java.util.Arrays;

public class MinimizeSum2567 {
    static void main() {
        int[] nums = {1,4,7,8,5};
        System.out.println(minimizeSum(nums));
    }

//    sorting; time: O(nlogn)
    public static int minimizeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int dropTwoLargest = nums[n - 3] - nums[0];
        int dropTwoSmallest = nums[n - 1] - nums[2];
        int dropOneEach = nums[n - 2] - nums[1];

        return Math.min(Math.min(dropTwoLargest, dropTwoSmallest), dropOneEach);
    }
}

/*
You are given an integer array nums.
The low score of nums is the minimum absolute difference between any two integers.
The high score of nums is the maximum absolute difference between any two integers.
The score of nums is the sum of the high and low scores.
Return the minimum score after changing two elements of nums.
Example 1:
Input: nums = [1,4,7,8,5]
Output: 3
Explanation:
Change nums[0] and nums[1] to be 6 so that nums becomes [6,6,7,8,5].
The low score is the minimum absolute difference: |6 - 6| = 0.
The high score is the maximum absolute difference: |8 - 5| = 3.
The sum of high and low score is 3.
Example 2:
Input: nums = [1,4,3]
Output: 0
Explanation:
Change nums[1] and nums[2] to 1 so that nums becomes [1,1,1].
The sum of maximum absolute difference and minimum absolute difference is 0.

Constraints:
3 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
 */

/*
Since we can change two elements of nums to any value, we can always set two elements equal to each other (for example,
setting one element equal to another existing element). That makes the low score (minimum absolute difference between
any two elements) equal to 0.
Because the low score can always be made 0, minimizing low score + high score reduces to minimizing the high score
(max(nums) - min(nums)).
When nums is sorted (a[0] <= a[1] <= ... <= a[n - 1]), changing two elements to shrink the range [min, max] gives us
three ways to drop extremes:
Change the two largest elements (a[n - 1] and a[n - 2]) to match a[n - 3] → new high score is a[n - 3] - a[0].
Change the two smallest elements (a[0] and a[1]) to match a[2] → new high score is a[n - 1] - a[2].
Change the smallest (a[0]) and the largest (a[n - 1]) elements to match inner elements → new high score is a[n - 2] - a[1].
 */