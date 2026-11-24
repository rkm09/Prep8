package daily.easy;

public class MaxLength3411 {
    static void main() {
        int[] nums = {1,2,1,2,1,1,1};
        System.out.println(maxLength(nums));
    }

//    enumeration; time: O(n^2logM), space: O(1)
    public static int maxLength(int[] nums) {
        int n = nums.length, res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            long gcdVal = nums[i];
            long lcmVal = nums[i];
            long prodVal = nums[i];
            for (int j = i + 1; j < n; j++) {
                prodVal *= nums[j];
                gcdVal = gcd(gcdVal, nums[j]);
                lcmVal = (lcmVal * nums[j]) / gcd(lcmVal, nums[j]);
                if (prodVal > gcdVal * lcmVal)
                    break;
                if (prodVal == gcdVal * lcmVal)
                    res = Math.max(res, j - i + 1);
            }
        }

        return res;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
/*
You are given an array of positive integers nums.
An array arr is called product equivalent if prod(arr) == lcm(arr) * gcd(arr), where:
prod(arr) is the product of all elements of arr.
gcd(arr) is the GCD of all elements of arr.
lcm(arr) is the LCM of all elements of arr.
Return the length of the longest product equivalent subarray of nums.
Example 1:
Input: nums = [1,2,1,2,1,1,1]
Output: 5
Explanation:
The longest product equivalent subarray is [1, 2, 1, 1, 1], where prod([1, 2, 1, 1, 1]) = 2, gcd([1, 2, 1, 1, 1]) = 1, and lcm([1, 2, 1, 1, 1]) = 2.
Example 2:
Input: nums = [2,3,4,5,6]
Output: 3
Explanation:
The longest product equivalent subarray is [3, 4, 5].
Example 3:
Input: nums = [1,2,3,1,4,5,1]
Output: 5

Constraints:
2 <= nums.length <= 100
1 <= nums[i] <= 10
 */


/*
Time complexity: O(n^2logM).
There are O(n^2) subarrays. Computing the greatest common divisor and least common multiple for each takes O(logM) time,
 giving O(n^2logM) overall.
 */