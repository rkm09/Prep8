package daily.easy;

public class SumAndMultiply3754 {
    public static void main(String[] args) {
        int n = 10203004;
        System.out.println(sumAndMultiply(n));
    }

//    time: O(logn), space: O(1)
    public static long sumAndMultiply(int n) {
        long sum = 0, num = 0, pow10 = 1;
        while (n > 0) {
            int rdr = n % 10;
            sum += rdr;
            if (rdr > 0) {
                num += pow10 * rdr;
                pow10 *= 10;
            }
            n /= 10;
        }

        return num * sum;
    }

    public static long sumAndMultiply1(int n) {
        int num = 0, sum = 0;
        while (n > 0) {
            while (n % 10 == 0)
                n /= 10;
            sum += n % 10;
            num = 10 * num + n % 10;
            n /= 10;
        }

        int res = 0;
        while(num > 0) {
            res = 10 * res + num % 10;
            num /= 10;
        }

        return (long) res * sum;
    }

//    time: O(logn), space: O(logn)
    public static long sumAndMultiply2(int n) {
        String s = String.valueOf(n);
        long sum = 0, num = 0;
        for (int i = 0; i < s.length(); i++) {
            int rdr = s.charAt(i) - '0';
            sum += rdr;
            if (rdr > 0)
                num = 10 * num + rdr;
        }

        return sum * num;
    }
}

/*
You are given an integer n.
Form a new integer x by concatenating all the non-zero digits of n in their original order. If there are no non-zero digits, x = 0.
Let sum be the sum of digits in x.
Return an integer representing the value of x * sum.
Example 1:
Input: n = 10203004
Output: 12340
Explanation:
The non-zero digits are 1, 2, 3, and 4. Thus, x = 1234.
The sum of digits is sum = 1 + 2 + 3 + 4 = 10.
Therefore, the answer is x * sum = 1234 * 10 = 12340.
Example 2:
Input: n = 1000
Output: 1
Explanation:
The non-zero digit is 1, so x = 1 and sum = 1.
Therefore, the answer is x * sum = 1 * 1 = 1.

Constraints:
0 <= n <= 109
 */
