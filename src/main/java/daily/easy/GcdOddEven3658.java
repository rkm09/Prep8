package daily.easy;

public class GcdOddEven3658 {
    public static void main(String[] args) {
        System.out.println(gcdOfOddEvenSums(5));
    }

//    euclidean algo; time: O(logN), space: O(1)
    public static int gcdOfOddEvenSums(int n) {
        // when you simplify using ap: n / 2 * (a + l), note that you need to divide at the end, lest 5 / 2 = 2;
        int sumEv = n * (n + 1);
        int sumOd = n * n;

        return gcd(sumEv, sumOd);
    }

    private static int gcdR(int a, int b) {
        return (b == 0) ? a : gcdR(b, a % b);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}

/*
You are given an integer n. Your task is to compute the GCD (greatest common divisor) of two values:
sumOdd: the sum of the smallest n positive odd numbers.
sumEven: the sum of the smallest n positive even numbers.
Return the GCD of sumOdd and sumEven.
Example 1:
Input: n = 4
Output: 4
Explanation:
Sum of the first 4 odd numbers sumOdd = 1 + 3 + 5 + 7 = 16
Sum of the first 4 even numbers sumEven = 2 + 4 + 6 + 8 = 20
Hence, GCD(sumOdd, sumEven) = GCD(16, 20) = 4.
Example 2:
Input: n = 5
Output: 5
Explanation:
Sum of the first 5 odd numbers sumOdd = 1 + 3 + 5 + 7 + 9 = 25
Sum of the first 5 even numbers sumEven = 2 + 4 + 6 + 8 + 10 = 30
Hence, GCD(sumOdd, sumEven) = GCD(25, 30) = 5.

Constraints:
1 <= n <= 1000
 */