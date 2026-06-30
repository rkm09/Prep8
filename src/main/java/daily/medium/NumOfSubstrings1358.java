package daily.medium;

public class NumOfSubstrings1358 {
    public static void main(String[] args) {
        String s = "aaacb";
        System.out.println(numberOfSubstrings(s));
    }

//    last position; time: O(n), space: O(1)
    public static int numberOfSubstrings(String s) {
        int len = s.length(), total = 0;
//        track positions of a, b, c
        int[] lastPos = {-1, -1, -1};
        for (int pos = 0; pos < len; pos++) {
//            update the last position of the current character
            lastPos[s.charAt(pos) - 'a'] = pos;
//            add the count of valid substrings, if any char is missing it will remain -1 and so 1-1 = 0 will get added
            total += 1 + Math.min(lastPos[0], Math.min(lastPos[1], lastPos[2]));
        }

        return total;
    }

//    sliding window; time: O(n), space: O(1)
    public static int numberOfSubstrings1(String s) {
        int left = 0, right = 0, len = s.length();
//        track a, b, c
        int[] freq = new int[3];
        int total = 0;
        while (right < len) {
//            add current character to frequency array
            char ch = s.charAt(right);
            freq[ch - 'a']++;
//            while we have all the characters, update total
            while (hasAllCharacters(freq)) {
//                all substrings from current window to the end are valid, add them to the total
                total += len - right;    // account for look-ahead
//                remove the leftmost character and move the left pointer
                char leftChar = s.charAt(left);
                freq[leftChar - 'a']--;
                left++;  // remain in the while loop and account for look-behind / internal crunching, since right will not revisit what it has moved past
            }
            right++;
        }

        return total;
    }

    private static boolean hasAllCharacters(int[] freq) {
        return freq[0] > 0 && freq[1] > 0 && freq[2] > 0;
    }


//  TLE! fails because of a test case like 'aaaa...bc'. the problem is the resetting and starting from scratch each time
    public static int numberOfSubstringsX(String s) {
        int n = s.length(), count = 0;
        for (int i = 0; i < n; i++) {
            int a = 0, b = 0, c = 0;
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j);
                switch (ch) {
                    case 'a' : a++; break;
                    case 'b' : b++; break;
                    default: c++;
                }
                if (a > 0 && b > 0 && c > 0) {
                    count += n - j; break;
                }
            }
        }

        return count;
    }
}

/*
Given a string s consisting only of characters a, b and c.
Return the number of substrings containing at least one occurrence of all these characters a, b and c.
Example 1:
Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
Example 2:
Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
Example 3:
Input: s = "abc"
Output: 1

Constraints:
3 <= s.length <= 5 x 10^4
s only consists of a, b or c characters.
 */

/*
The last-position tracking approach is brilliant because it shifts the perspective from "How big is my window?" to
"Where is the earliest point I can look back to and still see all three characters?"
The Intuition Shift
Frequency Array (freq[0]++): Answers the question: "How many of each character do I have right now?"
Position Array (lastPos[0] = pos): Answers the question: "Where did I last see each character?"
If you only incremented a counter (lastPos[...]++), you would know how many times you've seen 'a', 'b', and 'c' so far,
but you wouldn't know where they are. To count valid substrings efficiently without a sliding window, you need to know exactly how far back you can stretch a substring before it loses one of the required letters.
Why this is beautiful:
Instead of shrinking or expanding a window using a inner loop, you are doing a single $O(1)$ calculation at every single index. It treats each index i as the absolute end of a group of valid substrings
and effortlessly counts how many of them can exist.
 */