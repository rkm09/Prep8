package daily.medium;

import java.util.ArrayList;
import java.util.List;

public class SequentialDigits1291 {
    public static void main(String[] args) {
        System.out.println(sequentialDigits(100,300));
    }

//    string manipulation;
    public static List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        int lowLen = (int)Math.log10(low) + 1;
        int highLen = (int)Math.log10(high) + 1;
        int n = 10;
        String numStr = "123456789";
        for (int length = lowLen; length < highLen + 1; length++) {
            for (int start = 0; start < n - length; start++) {
                  int num = Integer.parseInt(numStr.substring(start, start + length));
                  if (num >= low && num <= high)
                      res.add(num);
            }
        }

        return res;
    }

}

/*
An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
Return a sorted list of all the integers in the length [low, high] inclusive that have sequential digits.
Example 1:
Input: low = 100, high = 300
Output: [123,234]
Example 2:
Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]

Constraints:
10 <= low <= high <= 10^9
 */