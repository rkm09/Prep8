package daily.medium;

public class MinCost3789 {
    static void main() {
        System.out.println(minimumCost(3,2,1,3,2));
    }

//    greedy; time: O(1), space: O(1)
    public static long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long totalCost;
//        type 3 unnecessary in this case
        if (cost1 + cost2 <= costBoth)
            totalCost = (long) cost1 * need1 + (long) cost2 * need2;
        else {
            int needMin = Math.min(need1, need2);
            totalCost = (long) needMin * costBoth;
            need1 -= needMin;
            need2 -= needMin;
            if (need1 > 0)
                totalCost += (long) need1 * Math.min(cost1, costBoth);
            else if (need2 > 0)
                totalCost += (long) need2 * Math.min(cost2, costBoth);
        }

        return totalCost;
    }

    public static long minimumCost1(int cost1, int cost2, int costBoth, int need1, int need2) {
        if (need1 == 0 && need2 == 0) return 0;
        long needMin = Math.min(need1, need2);
        long type3 = needMin * costBoth + (need1 - needMin) * Math.min(cost1, costBoth) +
                (need2 - needMin) * Math.min(cost2, costBoth);
        return Math.min((long) cost1 * need1 + (long) cost2 * need2, type3);
    }
}

/*
You are given five integers cost1, cost2, costBoth, need1, and need2.
There are three types of items available:
An item of type 1 costs cost1 and contributes 1 unit to the type 1 requirement only.
An item of type 2 costs cost2 and contributes 1 unit to the type 2 requirement only.
An item of type 3 costs costBoth and contributes 1 unit to both type 1 and type 2 requirements.
You must collect enough items so that the total contribution toward type 1 is at least need1 and the total
contribution toward type 2 is at least need2.
Return an integer representing the minimum possible total cost to achieve these requirements.
Example 1:
Input: cost1 = 3, cost2 = 2, costBoth = 1, need1 = 3, need2 = 2
Output: 3
Explanation:
After buying three type 3 items, which cost 3 * 1 = 3, the total contribution to type 1 is 3 (>= need1 = 3) and to type
2 is 3 (>= need2 = 2).
Any other valid combination would cost more, so the minimum total cost is 3.
Example 2:
Input: cost1 = 5, cost2 = 4, costBoth = 15, need1 = 2, need2 = 3
Output: 22
Explanation:
We buy need1 = 2 items of type 1 and need2 = 3 items of type 2: 2 * 5 + 3 * 4 = 10 + 12 = 22.
Any other valid combination would cost more, so the minimum total cost is 22.
Example 3:
Input: cost1 = 5, cost2 = 4, costBoth = 15, need1 = 0, need2 = 0
Output: 0
Explanation:
Since no items are required (need1 = need2 = 0), we buy nothing and pay 0.

Constraints:
1 <= cost1, cost2, costBoth <= 10^6
0 <= need1, need2 <= 10^9
 */