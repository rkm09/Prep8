package daily.medium;

public class MinMoves453 {
    static void main() {
        int[] nums = {1,2,3};
        System.out.println(minMoves(nums));
    }

//    simulation; time: O(n), space: O(1)
    public static int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE, moves = 0;
        for (int num : nums)
            min = Math.min(min, num);
        for (int num : nums) {
            moves += num - min;
        }

        return moves;
    }
}

/*
Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
In one move, you can increment n - 1 elements of the array by 1.
Example 1:
Input: nums = [1,2,3]
Output: 3
Explanation: Only three moves are needed (remember each move increments two elements):
[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
Example 2:
Input: nums = [1,1,1]
Output: 0

Constraints:
n == nums.length
1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
The answer is guaranteed to fit in a 32-bit integer.
 */


/*
Incrementing n - 1 elements by 1 is equivalent to decrementing 1 element by 1 relative to the other elements.
Trying to raise all elements up to the maximum  can be slow and lead to overflow issues. We can rather
find the minimum element in the array and then calculate the number of decrements needed for every other element to
reach that minimum value. The sum of differences between each element and the minimum element gives the total
number of moves.
 */