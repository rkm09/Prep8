package daily.medium;

public class SumGame1927 {
    public static void main(String[] args) {
        String num = "5023";
        System.out.println(sumGame(num));
    }

//    verified guess based on induction; time: O(n), space: O(1)
    public static boolean sumGame(String num) {
        int n = num.length();
        int sumLeft = 0, sumRight = 0;
        int qLeft = 0, qRight = 0;
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?')
                qLeft++;
            else
                sumLeft += num.charAt(i) - '0';
        }
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?')
                qRight++;
            else
                sumRight += num.charAt(i) - '0';
        }

        int diff = sumLeft - sumRight;
        int qDiff = qLeft - qRight;

        return (qDiff % 2 != 0) || (diff != -qDiff / 2 * 9);
    }
}

/*
Alice and Bob take turns playing a game, with Alice starting first.
You are given a string num of even length consisting of digits and '?' characters. On each turn, a player will do the following if there is still at least one '?' in num:
Choose an index i where num[i] == '?'.
Replace num[i] with any digit between '0' and '9'.
The game ends when there are no more '?' characters in num.
For Bob to win, the sum of the digits in the first half of num must be equal to the sum of the digits in the second half. For Alice to win, the sums must not be equal.
For example, if the game ended with num = "243801", then Bob wins because 2+4+3 = 8+0+1. If the game ended with num = "243803", then Alice wins because 2+4+3 != 8+0+3.
Assuming Alice and Bob play optimally, return true if Alice will win and false if Bob will win.
Example 1:
Input: num = "5023"
Output: false
Explanation: There are no moves to be made.
The sum of the first half is equal to the sum of the second half: 5 + 0 = 2 + 3.
Example 2:
Input: num = "25??"
Output: true
Explanation: Alice can replace one of the '?'s with '9' and it will be impossible for Bob to make the sums equal.
Example 3:
Input: num = "?3295???"
Output: false
Explanation: It can be proven that Bob will always win. One possible outcome is:
- Alice replaces the first '?' with '9'. num = "93295???".
- Bob replaces one of the '?' in the right half with '9'. num = "932959??".
- Alice replaces one of the '?' in the right half with '2'. num = "9329592?".
- Bob replaces the last '?' in the right half with '7'. num = "93295927".
Bob wins because 9 + 3 + 2 + 9 = 5 + 9 + 2 + 7.

Constraints:
2 <= num.length <= 10^5
num.length is even.
num consists of only digits and '?'.
 */


/*
The Intuitive Bottom Line:
Because 0 and 9 are opposite ends of the single-digit spectrum:9 is the midpoint of the maximum possible pair sum
(18 / 2 = ).Whenever Alice picks a digit A, she leaves A distance from 0, and (9 - A) distance from 9.
By choosing B = 9 - A, Bob always mirrors Alice's distance from the opposite boundary, keeping the combined total
anchored precisely at 9.No other target sum allows Bob to absorb both an aggressive 9 and a passive 0 from Alice!

The magic of 9 boils down to one simple fact: 
9 is the maximum value of a single base-10 digit (0 through 9).In a game where two players share a pair of turns on 
a [0-9] spectrum, 9 is the only sum where the second player has total control over the outcome.
Here is the exact mathematical reason why X and 9 - X work, and why no other number can be guaranteed:
1. The Min-Max Range of Two Turns
When Alice plays a digit A in [0, 9] and Bob plays a digit B in [0, 9], what is the range of their combined sum 
S = A + Bm {Minimum Possible Sum} = 0 + 0 = 0, {Maximum Possible Sum} = 9 + 9 = 18. Since Alice gets to move first, 
she gets to pick A. Bob must pick B after seeing A.If Bob wants a combined sum of S, his required response is 
B = S - A. However, Bob is constrained: B must be a valid digit between 0 and 9 (0 \le B \le 9).
2. Testing Target Sums (S)
Let's see what happens if Bob tries to force a target sum other than 9.
Attempting S = 4:To force A + B = 4, Bob needs B = 4 - A.
If Alice plays A = 1 \implies B = 3 (Valid ✅)
If Alice plays A = 5 \implies B = 4 - 5 = -1 (Impossible ❌, Bob can't pick negative numbers)
Outcome: Alice picks A = 9, overshooting 4 immediately. Bob loses control.
Attempting S = 14:To force A + B = 14, Bob needs B = 14 - A.
If Alice plays A = 8 \implies B = 6 (Valid ✅)
If Alice plays A = 2 \implies B = 14 - 2 = 12 (Impossible ❌, 12 isn't a single digit)
Outcome: Alice picks A = 0, undershooting 14. Even if Bob plays B = 9, 0 + 9 = 9 < 14. Bob loses control.3. 
Why S = 9 Is UniqueTo guarantee that Bob can always find a valid digit B no matter what A Alice chooses, 
the inequality 0 \le B \le 9 must hold for every A \in [0, 9].
Substitute B = S - A:0 \le S - A \le 9. 
To isolate S, add A across the entire inequality:A \le S \le 9 + A. 
This condition must hold simultaneously for Alice's extreme choices:
When Alice plays her lowest choice (A = 0):0 \le S \le 9 + 0 \implies S \le 9
When Alice plays her highest choice (A = 9):9 \le S \le 9 + 9 \implies S \ge 9
Combining S \le 9 and S \ge 9 leaves exactly one mathematical solution:S = 9.
 */