package daily.medium;

public class StoneGame877 {
    public static void main(String[] args) {
        int[] piles = {5,3,4,5};
        System.out.println(stoneGame(piles));
    }

//    dp; time: O(n^2), space: O(n^2)
    public static boolean stoneGame(int[] piles) {
        int n = piles.length;
        Integer[][] memo = new Integer[n][n];
        return maxDiff(piles, 0, n - 1, memo) >= 0;
    }

    private static int maxDiff(int[] piles, int i, int j, Integer[][] memo) {
        if (i == j)
            return piles[i];
        if (memo[i][j] != null)
            return memo[i][j];
        int leftPick = piles[i] - maxDiff(piles, i + 1, j, memo);
        int rightPick = piles[j] - maxDiff(piles, i, j - 1, memo);
        memo[i][j] = Math.max(leftPick, rightPick);

        return memo[i][j];
    }

//    maths; time: O(1)
    public static boolean stoneGame1(int[] piles) {
        return true;
    }
}

/*
Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.
Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.
Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.
Example 1:
Input: piles = [5,3,4,5]
Output: true
Explanation:
Alice starts first, and can only take the first 5 or the last 5.
Say she takes the first 5, so that the row becomes [3, 4, 5].
If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alice, so we return true.
Example 2:
Input: piles = [3,7,2,3]
Output: true

Constraints:
2 <= piles.length <= 500
piles.length is even.
1 <= piles[i] <= 500
sum(piles[i]) is odd.
 */


/*
Even length causes one side as even index and one side as odd index always. so the first person can calculate, even index
sum and odd index sum and whichever is greater can stick to always picking that.
 */