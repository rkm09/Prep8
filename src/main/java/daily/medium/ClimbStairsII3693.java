package daily.medium;

public class ClimbStairsII3693 {
    static void main() {
        int[] costs = {1,2,3,4};
        System.out.println(climbStairs(4, costs));
    }

//    dp; time: O(n), space: O(1)
    public static int climbStairs(int n, int[] costs) {
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < n; i++) {
            int next = Math.min(Math.min(a + 9, b + 4), c + 1)
                     + costs[i];
            a = b;
            b = c;
            c = next;
        }

        return c;
    }
}

/*
You are climbing a staircase with n + 1 steps, numbered from 0 to n.
You are also given a 1-indexed integer array costs of length n, where costs[i] is the cost of step i.
From step i, you can jump only to step i + 1, i + 2, or i + 3. The cost of jumping from step i to step j is defined as:
costs[j] + (j - i)2
You start from step 0 with cost = 0.
Return the minimum total cost to reach step n.
Example 1:
Input: n = 4, costs = [1,2,3,4]
Output: 13
Explanation:
One optimal path is 0 → 1 → 2 → 4
Jump | Cost | Calculation |	Cost
0 → 1	costs[1] + (1 - 0)2 = 1 + 1	2
1 → 2	costs[2] + (2 - 1)2 = 2 + 1	3
2 → 4	costs[4] + (4 - 2)2 = 4 + 4	8
Thus, the minimum total cost is 2 + 3 + 8 = 13
Example 2:
Input: n = 4, costs = [5,1,6,2]
Output: 11
Explanation:
One optimal path is 0 → 2 → 4
Jump | 	Cost Calculation |	Cost
0 → 2	costs[2] + (2 - 0)2 = 1 + 4	5
2 → 4	costs[4] + (4 - 2)2 = 2 + 4	6
Thus, the minimum total cost is 5 + 6 = 11
Example 3:
Input: n = 3, costs = [9,8,3]
Output: 12
Explanation:
The optimal path is 0 → 3 with total cost = costs[3] + (3 - 0)2 = 3 + 9 = 12

Constraints:
1 <= n == costs.length <= 10^5
1 <= costs[i] <= 10^4
 */


/*
Let f[i] be the minimum total cost to reach step i. Initially, we are on step 0 with a total cost of 0, so f[0]=0.
To reach step i (i≥1), the last jump can only start from step i−1, i−2, or i−3. The jump distances are 1, 2, and 3,
respectively, with squared costs of 1^2=1, 2^2=4, and 3^2=9. We also need to pay the cost costs[i] of step i (where
costs is 1-indexed as defined in the problem). Taking the minimum over all valid preceding steps gives the transition:
f[i]=min(f[i−1]+1, f[i−2]+4, f[i−3]+9)+costs[i]
When i<3, some starting steps do not exist, so we ignore the corresponding terms. The final answer is f[n].
Computing this recurrence directly requires O(n) space for the array f. Notice that f[i] depends only on f[i−1],
f[i−2], and f[i−3]; earlier states are never used again. Therefore, we can keep only the three most recent states in
three rolling variables, shifting them by one position whenever we compute a new state. This reduces the space
complexity to O(1).
 */