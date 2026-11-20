package daily.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxSelected3041 {
    static void main() {
        int[] nums = {2,1,5,1,1};
        System.out.println(maxSelectedElements(nums));
    }

//    dp + sorting; time: O(NlogN), space: O(N)
    public static int maxSelectedElements(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        for (int x : nums) {
            dp.put(x + 1,
                    Math.max(dp.getOrDefault(x + 1, 0), dp.getOrDefault(x, 0) + 1));
            dp.put(x,
                    Math.max(dp.getOrDefault(x, 0), dp.getOrDefault(x - 1, 0) + 1));
            ans = Math.max(ans, Math.max(dp.get(x), dp.get(x + 1)));
        }

        return ans;
    }
}

/*
You are given a 0-indexed array nums consisting of positive integers.
Initially, you can increase the value of any element in the array by at most 1.
After that, you need to select one or more elements from the final array such that those elements are consecutive when
sorted in increasing order. For example, the elements [3, 4, 5] are consecutive while [3, 4, 6] and [1, 1, 2, 3] are
not. Return the maximum number of elements that you can select.
Example 1:
Input: nums = [2,1,5,1,1]
Output: 3
Explanation: We can increase the elements at indices 0 and 3. The resulting array is nums = [3,1,5,2,1].
We select the elements [3,1,5,2,1] and we sort them to obtain [1,2,3], which are consecutive.
It can be shown that we cannot select more than 3 consecutive elements.
Example 2:
Input: nums = [1,4,7,10]
Output: 1
Explanation: The maximum consecutive elements that we can select is 1.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^6
 */


/*
We are allowed to increase each element of nums by at most 1. Our goal is to select the maximum number of elements such
that, when sorted in ascending order, they form a sequence of consecutive integers (i.e., every pair of adjacent
elements differs by exactly 1).
First, sort nums in ascending order. For two originally distinct integers a<b, we have a+1≤b, so their relative order
cannot be inverted by any modification. For duplicate elements, an unchanged element (x) naturally precedes an
increased element (x+1). Therefore, we can process elements in sorted order and consider how to append each one to an
existing consecutive sequence.
We can solve this using dynamic programming. Let dp[v] be the maximum length of a consecutive sequence ending in v that
can be formed by selecting and legally modifying some of the elements processed so far. Store these states in a hash
table, treating missing states as 0.

For the current element x, if we include it in the sequence, there are two possibilities:
Increase x by 1: We can append it to a consecutive sequence ending at x, yielding a sequence of length dp[x]+1. Thus,
we update dp[x+1]=max(dp[x+1],dp[x]+1).
Leave x unchanged: We can append it to a consecutive sequence ending at x−1, yielding a sequence of length dp[x−1]+1.
Thus, we update dp[x]=max(dp[x],dp[x−1]+1).

Taking the maximum with the original state preserves the option of not selecting the current element. When the
preceding state is 0, these transitions produce a sequence of length 1, corresponding to selecting only the current
element.
Both transitions must use the states from before processing the current element. Therefore, we must update dp[x+1]
before dp[x]. If we reverse the order, the newly updated dp[x] may already include the current element, so using it to
update dp[x+1] would count the same element twice.
We maintain the maximum value across all states throughout the traversal, which gives the final answer after all
elements have been processed.
 */