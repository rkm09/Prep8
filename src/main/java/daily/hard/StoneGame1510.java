package daily.hard;

public class StoneGame1510 {
    public static void main(String[] args) {
        System.out.println(winnerSquareGame(2));
    }

//    dp; time: O(NSqrt(N)), space: O(n)
    public static boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k * k <= i; k++) {
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

//    dp; top down; memoized; time: O(NSqrt(N)), space: O(n)
    public static boolean winnerSquareGame1(int n) {
        Boolean[] memo = new Boolean[n + 1];
        return canWin(n, memo);
    }

    private static boolean canWin(int remaining, Boolean[] memo) {
        if (remaining == 0)
            return false;
        if (memo[remaining] != null)
            return memo[remaining];

        for (int k = 1; k * k <= remaining; k++) {
            if (!canWin(remaining - k * k, memo)) {
                memo[remaining] = true;
                return true;
            }
        }

        memo[remaining] = false;
        return false;
    }

}

/*
Alice and Bob take turns playing a game, with Alice starting first.
Initially, there are n stones in a pile. On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.
Also, if a player cannot make a move, he/she loses the game.
Given a positive integer n, return true if and only if Alice wins the game otherwise return false, assuming both players play optimally.
Example 1:
Input: n = 1
Output: true
Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
Example 2:
Input: n = 2
Output: false
Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
Example 3:
Input: n = 4
Output: true
Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).

Constraints:
1 <= n <= 10^5
 */


/*
Time Complexity: O(NSqrt(N)).
For each integer i from 1 to n, we iterate through all perfect squares k^2 <= i, which takes O(Sqrt(i)) steps.
Summing this over all i gives O(NSqrt(N)), easily running well within the limits for n = 10^5.

Core Idea: Winning vs. Losing States
Losing State (false): A position i is a losing state if every possible move leads to a winning state for the
opponent. If it's your turn and i = 0, you have no moves left, so i = 0 is inherently a losing state
(dp[0] = false).
Winning State (true): A position i is a winning state if there exists at least one move that forces the opponent
into a losing state.
Step-by-Step LogicState Definition:
Let dp[i] be a boolean value representing whether the player whose turn it is at i stones will win the game:
dp[i] = true: Current player wins.
dp[i] = false: Current player loses.
After removing k^2 stones, the opponent gets a pile of size i - k^2.

If dp[i - k * k] == false, it means the opponent is handed a losing position.Therefore, the current player
can just choose to remove k^2 stones to guarantee a victory! If dp[i - k * k] == false for at least one choice
of k, we set dp[i] = true and stop checking further choices of k for that particular i.
Base Case:
dp[0] = false (a player facing 0 stones automatically loses because no moves are available).

*/