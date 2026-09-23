package daily.medium;

public class MinOperations1658 {
    public static void main(String[] args) {
        int[] nums = {1,1,4,2,3};
        System.out.println(minOperations(nums, 5));
    }

//    sliding window; time: O(n), space: O(1)
    public static int minOperations(int[] nums, int x) {
        int totalSum = 0, n = nums.length;
        for (int num : nums)
            totalSum += num;
        int targetSum = totalSum - x;
//        array sum is smaller than x, invalid case
        if (targetSum < 0)
            return -1;
//        all elements will have to be removed
        if (targetSum == 0)
            return n;
        int maxLength = -1, currentSum = 0, left = 0;
        for (int right = 0; right < n; right++) {
            currentSum += nums[right];
            while (currentSum > targetSum && left <= right)
                currentSum -= nums[left++];
            if (currentSum == targetSum)
                maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength == -1 ? -1 : n - maxLength;
    }
}

/*
You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or
the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.
Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.
Example 1:
Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
Example 2:
Input: nums = [5,6,7,8,9], x = 4
Output: -1
Example 3:
Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^4
1 <= x <= 10^9
 */


/*
The Key Intuition: Invert the Problem
Instead of finding elements at the edges that sum to x, invert the goal:
Find a contiguous subarray in the middle that sums to ({totalSum} - x) with the maximum possible length.
[  Left Operations  |  Contiguous Subarray (target)  |  Right Operations  ]
|<--- remove --->|                                  |<--- remove --->|
                    \______________________________/
                                 sum
If the middle subarray has length L, the number of edge operations required is (N - L).
Maximizing L minimizes the number of operations (N - L).Since all elements in nums are positive (nums[i] >= 1),
the subarray sum increases monotonically as the window expands, making this a classic Sliding Window / Two Pointers
problem.
 */