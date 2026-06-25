package daily.medium;

public class CountMajority3737 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,3};
        System.out.println(countMajoritySubarrays(nums, 2));
    }

//    Enumeration; time: O(n^2), space: O(1)
    public static int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = i; j < n; j++) {
                cnt += (nums[j] == target) ? 1 : -1;
                if (cnt > 0) ans++;
            }
        }

        return ans;
    }
}

/*
You are given an integer array nums and an integer target.
Return the number of subarrays of nums in which target is the majority element.
The majority element of a subarray is the element that appears strictly more than half of the times in that subarray.
Example 1:
Input: nums = [1,2,2,3], target = 2
Output: 5
Explanation:
Valid subarrays with target = 2 as the majority element:
nums[1..1] = [2]
nums[2..2] = [2]
nums[1..2] = [2,2]
nums[0..2] = [1,2,2]
nums[1..3] = [2,2,3]
So there are 5 such subarrays.
Example 2:
Input: nums = [1,1,1,1], target = 1
Output: 10
Explanation:
All 10 subarrays have 1 as the majority element.
Example 3:
Input: nums = [1,2,3], target = 4
Output: 0
Explanation:
target = 4 does not appear in nums at all. Therefore, there cannot be any subarray where 4 is the majority element. Hence the answer is 0.

Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 10^9
 */

/*
or a subarray, cnt represents the difference between the number of occurrences of target and the number of non-target
elements. Therefore, when cnt>0, the number of occurrences of target is greater than the number of non-target elements,
which means target appears more than half the length of the subarray.
Hence, target is the majority element of the subarray, and we increment the answer by 1.
 */
