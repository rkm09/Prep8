package daily.medium;

import java.util.Arrays;

public class MaxIceCream1833 {
    public static void main(String[] args) {
        System.out.println(maxIceCream(new int[]{1,3,2,4,1}, 7));
        //System.out.println(maxIceCream(new int[]{7,3,3,6,6,6,10,5,9,2}, 56));
    }

    public static int maxIceCream(int[] costs, int coins) {
        int max = 0, n = costs.length, min = -1;
//        find both min and max to get the exact range
        for (int cost : costs) {
            max = Math.max(max, cost);
            min = Math.min(min, cost);
        }
//        if coins are less than min, return
        if (coins < min) return 0;

        int range = max - min + 1;
        int[] freq = new int[range];
        for (int cost : costs)
            freq[cost - min]++;

        int[] sorted = new int[n];
        int idx = 0;
        for (int i = 0; i < range; i++) {
            while (freq[i]-- > 0) {
                sorted[idx++] = i + min;
            }
        }

        int res = 0;
        for (int j = 0; j < n; j++) {
            coins -= sorted[j];
            if (coins < 0) return res;
            res++;
        }

        return res;
    }

}

/*
It is a sweltering summer day, and a boy wants to buy some ice cream bars.
At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price of the ith ice cream bar in coins.
The boy initially has coins to spend, and he wants to buy as many of ice cream bars as possible.
Note: The boy can buy the ice cream bars in any order.
Return the maximum number of ice cream bars the boy can buy with coins.
You must solve the problem by counting sort.
Example 1:
Input: costs = [1,3,2,4,1], coins = 7
Output: 4
Explanation: The boy can buy ice cream bars at indices 0,1,2,4 for a total price of 1 + 3 + 2 + 1 = 7.
Example 2:
Input: costs = [10,6,8,7,7,8], coins = 5
Output: 0
Explanation: The boy cannot afford any of the ice cream bars.
Example 3:
Input: costs = [1,6,3,1,2,5], coins = 20
Output: 6
Explanation: The boy can buy all the ice cream bars for a total price of 1 + 6 + 3 + 1 + 2 + 5 = 18.

Constraints:
costs.length == n
1 <= n <= 10^5
1 <= costs[i] <= 10^5
1 <= coins <= 10^8
 */