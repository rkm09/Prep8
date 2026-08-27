package daily.medium;

public class LexGreater3720 {
    public static void main(String[] args) {
        LexGreater3720 l = new LexGreater3720();
        String s = "abc";
        String target = "bba";
        System.out.println(l.lexGreaterPermutation(s, target));
    }

//    sequential enumeration; time: O(n * n), space: O(1)
    public String lexGreaterPermutation(String s, String target) {
//        be careful, you are doing an int array not char array, take int version wherever needed
        int[] cnt = new int[26];
        for (char c: s.toCharArray())
            cnt[c - 'a']++;

        StringBuilder res = new StringBuilder();
        int n = target.length();

        for (int i = 0; i < n; i++) {
            int targetChar = target.charAt(i) - 'a';

//            case 1: first try to place the same character as the target[i] at the current position
            if (cnt[targetChar] > 0) {
                cnt[targetChar]--;
//                check if the remaining characters can form a string greater than target[i+1:]
                 if (canFormGreater(cnt, target, i + 1)) {
                     res.append(target.charAt(i));
                     continue;
                 }
//                 cannot form a larger string, backtrack
                 cnt[targetChar]++;
            }

//            case 2: place a character greater than target[i] at the current position
            for (int j = targetChar + 1; j < 26; j++) {
                if (cnt[j] > 0) {
                    cnt[j]--;
                    res.append((char) ('a' + j));
//                    fill the remaining positions with the smallest lexicographical order
                    res.append(getMinString(cnt));
                    return res.toString();
                }
            }

//            no feasible solution found, return empty
            return "";
        }

        return "";
    }

//    check if the remaining characters can form a string greater than the suffix of target
    private boolean canFormGreater(int[] cnt, String target, int start) {
        String maxStr = getMaxString(cnt);
        String suffix = target.substring(start);

        return maxStr.compareTo(suffix) > 0;
    }

    private String getMaxString(int[] cnt) {
        StringBuilder res = new StringBuilder();
        for (int i = 25; i >= 0; i--)
            if (cnt[i] > 0)
                res.append(String.valueOf((char) ('a' + i)).repeat(cnt[i]));

        return res.toString();
    }

    private String getMinString(int[] cnt) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++)
            if (cnt[i] > 0)
                res.append(String.valueOf((char) ('a' + i)).repeat(cnt[i]));

        return res.toString();
    }
}

/*
You are given two strings s and target, both having length n, consisting of lowercase English letters.
Return the lexicographically smallest permutation of s that is strictly greater than target. If no permutation of s is lexicographically strictly greater than target, return an empty string.
A string 'a' is lexicographically strictly greater than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b.
Example 1:
Input: s = "abc", target = "bba"
Output: "bca"
Explanation:
The permutations of s (in lexicographical order) are "abc", "acb", "bac", "bca", "cab", and "cba".
The lexicographically smallest permutation that is strictly greater than target is "bca".
Example 2:
Input: s = "leet", target = "code"
Output: "eelt"
Explanation:
The permutations of s (in lexicographical order) are "eelt", "eetl", "elet", "elte", "etel", "etle", "leet", "lete", "ltee", "teel", "tele", and "tlee".
The lexicographically smallest permutation that is strictly greater than target is "eelt".
Example 3:
Input: s = "baba", target = "bbaa"
Output: ""
Explanation:
The permutations of s (in lexicographical order) are "aabb", "abab", "abba", "baab", "baba", and "bbaa".
None of them is lexicographically strictly greater than target. Therefore, the answer is "".

Constraints:
1 <= s.length == target.length <= 300
s and target consist of only lowercase English letters.
 */