package daily.easy;

public class AliceWin3360 {
    static void main() {
        System.out.println(canAliceWin(12));
    }

//    simulation; time: O(1) coz n <= 50, space: O(1)
    public static boolean canAliceWin(int n) {
        int take = 10, k = 0;
        while (n >= take) {
            n -= take;
            take--;
            k++;
        }

        return k % 2 == 1;
    }

//    cleaner version;
    public static boolean canAliceWin11(int n) {
        return (n >= 10) && (n < 19 || n > 26)
                && (n < 34 || n > 39) && (n < 45 || n > 48);
    }

/*    first draft (correct but redundant) note how the cleaner version exactly extracts the equivalent of return false
      precondition.
    public static boolean canAliceWinX(int n) {
        --> if ((n < 10) || (n >= 19 && n <= 26)
                || (n >= 34 && n <= 39) || (n >= 45 && n <= 48)) return false;  <-- && becomes || vice versa
                >= becomes < and so on..
        if ((n <= 18) || (n >= 27 && n <= 33)
                || (n >= 40 && n <= 44) || (n >= 49)) return true;
        return false;
    }
*/
}

/*
Alice and Bob are playing a game where they take turns removing stones from a pile, with Alice going first.
Alice starts by removing exactly 10 stones on her first turn.
For each subsequent turn, each player removes exactly 1 fewer stone than the previous opponent.
The player who cannot make a move loses the game.
Given a positive integer n, return true if Alice wins the game and false otherwise.
Example 1:
Input: n = 12
Output: true
Explanation:
Alice removes 10 stones on her first turn, leaving 2 stones for Bob.
Bob cannot remove 9 stones, so Alice wins.
Example 2:
Input: n = 1
Output: false
Explanation:
Alice cannot remove 10 stones, so Alice loses.

Constraints:
1 <= n <= 50
 */

/*
Time complexity: O(1).
Since n≤50 and the first 8 moves require 10+9+⋯+3=52 stones, the loop executes at most 7 times.
 */
