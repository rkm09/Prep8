package daily.medium;

public class MaxSum3723 {
    public static void main(String[] args) {
        MaxSum3723 m = new MaxSum3723();
        System.out.println(m.maxSumOfSquares(2,17));
    }

//    greedy; time: O(n), space: O(1)
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num)
            return "";
        int rdr = sum % 9;
        StringBuilder res = new StringBuilder();
        int nines = sum / 9;
        res.append("9".repeat(nines));
        if (rdr > 0)
            res.append(rdr);
        int zeros = num - res.length();
        res.append("0".repeat(zeros));

        return res.toString();
    }

//    TLE; backtracking;
    public String maxSumOfSquaresX(int num, int sum) {
        int[] max = new int[1];
        backTrack(num, new StringBuilder(), sum, max, 0);
        return max[0] > 0 ? String.valueOf(max[0]) : "";
    }

    private void backTrack(int size, StringBuilder curr, int sum, int[] maxNum, int maxSum) {
        int len = curr.length();
        if (len == size) {
            int digitSum = 0;
            int currSum = 0;
            for (int i = 0; i < len; i++) {
                int digit = Integer.parseInt(curr.charAt(i) + "");
                digitSum += digit;
                if (digitSum > sum)
                    return;
                currSum += digit * digit;
            }
            if (digitSum == sum) {
                if (currSum > maxSum) {
                    maxSum = currSum;
                    maxNum[0] = Integer.parseInt(curr + "");
                }
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (i == 0 && curr.isEmpty())
                continue;
            curr.append(i);
            backTrack(size, curr, sum, maxNum, maxSum);
            curr.setLength(curr.length() - 1);
        }
    }
}

/*
You are given two positive integers num and sum.
A positive integer n is good if it satisfies both of the following:
The number of digits in n is exactly num.
The sum of digits in n is exactly sum.
The score of a good integer n is the sum of the squares of digits in n.
Return a string denoting the good integer n that achieves the maximum score. If there are multiple possible integers, return the maximum ​​​​​​​one. If no such integer exists, return an empty string.
Example 1:
Input: num = 2, sum = 3
Output: "30"
Explanation:
There are 3 good integers: 12, 21, and 30.
The score of 12 is 12 + 22 = 5.
The score of 21 is 22 + 12 = 5.
The score of 30 is 32 + 02 = 9.
The maximum score is 9, which is achieved by the good integer 30. Therefore, the answer is "30".
Example 2:
Input: num = 2, sum = 17
Output: "98"
Explanation:
There are 2 good integers: 89 and 98.
The score of 89 is 64 + 81 = 145.
The score of 98 is 81 + 64 = 145.
The maximum score is 145. The maximum good integer that achieves this score is 98. Therefore, the answer is "98".
Example 3:
Input: num = 1, sum = 10
Output: ""
Explanation:
There are no integers that have exactly 1 digit and whose digits sum to 10. Therefore, the answer is "".

Constraints:
1 <= num <= 2 * 10^5
1 <= sum <= 2 * 10^6
 */


/*
Greedy:
The maximum digit sum of an integer with exactly num digits is 9 × num. If sum > 9 × num, no such good integer exists,
so we return an empty string.
Since the square function f(x) = x^2 is convex, concentrating values into larger digits maximizes the sum of squares
for a fixed total sum (for example, 9^2 + 0^2 = 81 is greater than 5^2 + 4^2 = 41).
Therefore, the optimal multiset of digits consists of ⌊sum/9⌋ copies of digit 9, at most one non-zero remainder digit
equal to sum mod 9, and 0s for the remaining positions.
To also maximize the integer value among candidates with the maximum score, we place the largest digits in the most
significant positions (descending order). Thus, we place all ⌊sum/9⌋ nines at the most significant positions, followed
by sum mod 9 (if non-zero), and finally fill any remaining positions with 0s.
 */