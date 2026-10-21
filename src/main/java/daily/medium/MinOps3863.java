package daily.medium;

public class MinOps3863 {
    static void main() {
        String s = "dog";
        System.out.println(minOperations(s));
    }

//    case analysis; time: O(n), space: O(1)
    public static int minOperations(String s) {
        int n = s.length();
        char min = 'z', max = 'a';
        boolean isSorted = true;
        for (int i = 0; i < n; i++) {
            min = (char) Math.min(min, s.charAt(i));
            max = (char) Math.max(max, s.charAt(i));
            if ((i > 0) && s.charAt(i) < s.charAt(i - 1))
                isSorted = false;
        }
//        case 1: already sorted
        if (isSorted) return 0;
//        case 2: not already sorted, and length is 2 => cannot sort
        if (n == 2) return -1;
//        case 3: one or both of min, max are at their respective position
        if (s.charAt(0) == min || s.charAt(n - 1) == max)
            return 1;
//        case 4: either both are placed at extremes wrongly (ret 3) or (they are part of n - 2 mid (ret 2))
        for (int i = 1; i < n - 1; i++) {
            char c = s.charAt(i);
//            in one op one of min or max goes to its right pos + case 3
            if (c == min || c == max)
                return 2;
        }

        return 3;
    }
}

/*
You are given a string s consisting of lowercase English letters.
In one operation, you can select any substring of s that is not the entire string and sort it in non-descending alphabetical order.
Return the minimum number of operations required to make s sorted in non-descending order. If it is not possible, return -1.
Example 1:
Input: s = "dog"
Output: 1
Explanation:
Sort substring "og" to "go".
Now, s = "dgo", which is sorted in ascending order. Thus, the answer is 1.
Example 2:
Input: s = "card"
Output: 2
Explanation:
Sort substring "car" to "acr", so s = "acrd".
Sort substring "rd" to "dr", making s = "acdr", which is sorted in ascending order. Thus, the answer is 2.
Example 3:
Input: s = "gf"
Output: -1
Explanation:
It is impossible to sort s under the given constraints. Thus, the answer is -1.

Constraints:
1 <= s.length <= 10^5
s consists of only lowercase English letters.
 */