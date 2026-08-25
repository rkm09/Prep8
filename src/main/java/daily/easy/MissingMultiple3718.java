package daily.easy;

import java.util.HashSet;
import java.util.Set;

public class MissingMultiple3718 {
    public static void main(String[] args) {
        int[] nums = {8,2,3,4,6};
        System.out.println(missingMultiple(nums, 2));
    }

//    counting sort; time: O(n), space: O(1)
    public static int missingMultiple(int[] nums, int k) {
        int[] freq = new int[101];
        int j = 1;
        for (int num : nums) {
            freq[num]++;
        }
        for (int i = 1; i <= 100; i++) {
            if (freq[i] == 0) continue;
            if (i == k * j)
                j++;
        }

        return k * j;
    }

//    set; time: O(n), space: O(n)
    public static int missingMultiple1(int[] nums, int k) {
        Set<Integer> seen = new HashSet<>();
        for (int num: nums)
            seen.add(num);
        int ans = k;
        while (seen.contains(ans))
            ans += k;

        return ans;
    }
}

/*
Given an integer array nums and an integer k, return the smallest positive multiple of k that is missing from nums.
A multiple of k is any positive integer divisible by k.
Example 1:
Input: nums = [8,2,3,4,6], k = 2
Output: 10
Explanation:
The multiples of k = 2 are 2, 4, 6, 8, 10, 12... and the smallest multiple missing from nums is 10.
Example 2:
Input: nums = [1,4,7,10,15], k = 5
Output: 5
Explanation:
The multiples of k = 5 are 5, 10, 15, 20... and the smallest multiple missing from nums is 5.

Constraints:
1 <= nums.length <= 100
1 <= nums[i] <= 100
1 <= k <= 100
 */
