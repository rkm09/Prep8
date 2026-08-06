package daily.easy;

public class SmallestNumber3345 {
    public static void main(String[] args) {
        System.out.println(smallestNumber(10,2));
    }

    public static int smallestNumber(int n, int t) {
        int len = (int) Math.log10(n) + 1;
        while (len < 2 && n % t != 0) {
            n++;
            len = (int) Math.log10(n) + 1;
        }
        if (len < 2) return n;
        int digit1 = n / 10;
        int digit2 = n % 10;
        while ((digit1 * digit2) % t != 0) {
            if (digit2 == 9) {
                digit1++;
                digit2 = 0;
            } else {
                digit2++;
            }
        }

        return 10 * digit1 + digit2;
    }
}

/*
You are given two integers n and t. Return the smallest number greater than or equal to n such that the product of its digits is divisible by t.
Example 1:
Input: n = 10, t = 2
Output: 10
Explanation:
The digit product of 10 is 0, which is divisible by 2, making it the smallest number greater than or equal to 10 that satisfies the condition.
Example 2:
Input: n = 15, t = 3
Output: 16
Explanation:
The digit product of 16 is 6, which is divisible by 3, making it the smallest number greater than or equal to 15 that satisfies the condition.

Constraints:
1 <= n <= 100
1 <= t <= 10
 */
