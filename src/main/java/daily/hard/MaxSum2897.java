package daily.hard;

import java.util.List;

public class MaxSum2897 {
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) {
        List<Integer> nums = List.of(2,6,5,8);
        System.out.println(maxSum(nums, 2));
    }

//    greedy; time: O(N.LogM), space: O(logM)
    public static int maxSum(List<Integer> nums, int k) {
        int[] cnt = new int[30];
//        count the number of '1' bits at each position
        for (int num : nums) {
            for (int i = 0; i < 30; i++) {
                if ((num & (1 << i)) != 0)
                    cnt[i]++;
            }
        }

        long res = 0;
        for (int i = 0; i < k; i++) {
            long curr = 0;
//            construct the current number from the most significant bit to the least significant bit
            for (int j = 29; j >= 0; j--) {
                if (cnt[j] > 0) {
                    curr |= 1L << j;
                    cnt[j]--;
                }
            }
            res = (res + curr * curr) % MOD;
        }

        return (int) res;
    }
}

/*
You are given a 0-indexed integer array nums and a positive integer k.
You can do the following operation on the array any number of times:
Choose any two distinct indices i and j and simultaneously update the values of nums[i] to (nums[i] AND nums[j]) and nums[j] to (nums[i] OR nums[j]).
Here, OR denotes the bitwise OR operation, and 'AND' denotes the bitwise AND operation.
You have to choose k elements from the final array and calculate the sum of their squares.
Return the maximum sum of squares you can achieve.
Since the answer can be very large, return it modulo 10^9 + 7.
Example 1:
Input: nums = [2,6,5,8], k = 2
Output: 261
Explanation: We can do the following operations on the array:
- Choose i = 0 and j = 3, then change nums[0] to (2 AND 8) = 0 and nums[3] to (2 OR 8) = 10. The resulting array is nums = [0,6,5,10].
- Choose i = 2 and j = 3, then change nums[2] to (5 AND 10) = 0 and nums[3] to (5 OR 10) = 15. The resulting array is nums = [0,6,0,15].
We can choose the elements 15 and 6 from the final array. The sum of squares is 225 + 36 = 261.
It can be shown that this is the maximum value we can get.
Example 2:
Input: nums = [4,5,4,7], k = 3
Output: 90
Explanation: We do not need to apply any operations.
We can choose the elements 7, 5, and 4 with a sum of squares: 72 + 52 + 42 = 90.
It can be shown that this is the maximum value we can get.

Constraints:
1 <= k <= nums.length <= 10^5
1 <= nums[i] <= 10^9
 */


/*
Greedy:

The problem asks us to choose k elements from the final array to maximize the sum of their squares, and return the result modulo 10^9+7.
The key is to understand what the operation does. For any two numbers a and b, the operation replaces them with a AND b and a OR b,
respectively. Considering each binary bit position:
Bit in a	Bit in b	Bit in a after the operation	Bit in b after the operation	Total number of 1 bits (before → after)
0	        0	        0	                            0	                            0 → 0
0	        1	        0	                            1	                            1 → 1
1	        0	        0	                            1	                            1 → 1
1	        1	        1	                            1	                            2 → 2
We can conclude that:
Only when a bit in a is 1 and the corresponding bit in b is 0 does the operation move the 1 from a to b, effectively swapping
the values of that bit. In each operation, 1 bits can only move from the first element to the second. Therefore, the
first element can only decrease or stay the same, while the second can only increase or stay the same.
The total number of 1 bits at each binary position remains unchanged, so the sum of all elements also remains unchanged.
Consider two numbers x<y. If an operation increases y by d and decreases x by d, the change in the sum of squares is:
Consider two numbers x<y. If an operation increases y by d and decreases x by d, the change in the sum of squares is:

(x−d)^2 + (y+d)^2 − (x^2 + y^2) = 2d(d+y−x).

Since d>0 and y>x, the sum of squares must increase. The larger d is, the greater the increase. Therefore, the optimal strategy is to make large
numbers as large as possible and small numbers as small as possible.
In other words, we want to use the operations to construct numbers that are as large as possible. One greedy way to
construct the largest possible number is: Traverse the bits from most significant to least significant.
If the current bit is 0, find another element whose bit at this position is 1 and perform the operation with the current element to set its bit to 1.
Since 1 bits can only move from other elements to the current element, the higher bits already processed will not be disturbed.
Since each number satisfies nums[i]< 2^30, we only need to consider the first 30 bits (positions 0 to 29). Let cnt[j]
denote the total number of 1 bits at position j. To choose k elements with the maximum sum of squares, construct k
numbers that are as large as possible: Traverse all numbers and count the number of 1 bits at each position j, recording them in cnt[j].
Repeat k times, constructing a number from the most significant bit to the least significant bit each time. If
cnt[j] > 0, set bit j of the current number to 1 and decrease cnt[j] by 1.
Add the square of each constructed number to the answer.
Finally, take the result modulo 10^9+7 and return it.

Let n be the length of the array nums, M be its maximum element, and k be the number of elements to choose.
Time complexity: O(nlogM).
Traversing nums to count the 1 bits at each binary position takes O(nlogM) time. Constructing k elements and
calculating the maximum sum of squares takes O(klogM) time. Since k≤n in this problem, the total time complexity is
O(nlogM).
Space complexity: O(logM).
Storing the number of 1 bits at each binary position takes O(logM) space.
 */