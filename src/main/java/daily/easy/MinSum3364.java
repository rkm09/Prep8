package daily.easy;

import java.util.List;

public class MinSum3364 {
    static void main() {
        List<Integer> nums = List.of(3, -2, 1, 4);
        List<Integer> nums1 = List.of(5,8,-6);
//        System.out.println(minimumSumSubarray(nums, 2,3));
        System.out.println(minimumSumSubarray(nums1, 1, 3));
    }

    public static int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size(), minSum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j <= n; j++) {
                sum += nums.get(j);
                if (sum > 0 && (j >= l - 1 + i && j <= r - 1 + i))
                    minSum = Math.min(minSum, sum);
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}

/*
You are given an integer array nums and two integers l and r. Your task is to find the minimum sum of a subarray whose
size is between l and r (inclusive) and whose sum is greater than 0.
Return the minimum sum of such a subarray. If no such subarray exists, return -1.
A subarray is a contiguous non-empty sequence of elements within an array.
Example 1:
Input: nums = [3, -2, 1, 4], l = 2, r = 3
Output: 1
Explanation:
The subarrays of length between l = 2 and r = 3 where the sum is greater than 0 are:
[3, -2] with a sum of 1
[1, 4] with a sum of 5
[3, -2, 1] with a sum of 2
[-2, 1, 4] with a sum of 3
Out of these, the subarray [3, -2] has a sum of 1, which is the smallest positive sum. Hence, the answer is 1.
Example 2:
Input: nums = [-2, 2, -3, 1], l = 2, r = 3
Output: -1
Explanation:
There is no subarray of length between l and r that has a sum greater than 0. So, the answer is -1.
Example 3:
Input: nums = [1, 2, 3, 4], l = 2, r = 4
Output: 3
Explanation:
The subarray [1, 2] has a length of 2 and the minimum sum greater than 0. So, the answer is 3.

Constraints:
1 <= nums.length <= 100
1 <= l <= r <= nums.length
-1000 <= nums[i] <= 1000
 */