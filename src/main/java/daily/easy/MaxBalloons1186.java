package daily.easy;

import java.util.HashMap;
import java.util.Map;

public class MaxBalloons1186 {
    public static void main(String[] args) {
        String text = "loonbalxballpoon";
        String text1 = "krhizmmgmcrecekgyljqkldocicziihtgpqwbticmvuyznragqoyrukzopfmjhjjxemsxmrsxuqmnkrzhgvtgdgtykhcglurvppvcwhrhrjoislonvvglhdciilduvuiebmffaagxerjeewmtcwmhmtwlxtvlbocczlrppmpjbpnifqtlninyzjtmazxdbzwxthpvrfulvrspycqcghuopjirzoeuqhetnbrcdakilzmklxwudxxhwilasbjjhhfgghogqoofsufysmcqeilaivtmfziumjloewbkjvaahsaaggteppqyuoylgpbdwqubaalfwcqrjeycjbbpifjbpigjdnnswocusuprydgrtxuaojeriigwumlovafxnpibjopjfqzrwemoinmptxddgcszmfprdrichjeqcvikynzigleaajcysusqasqadjemgnyvmzmbcfrttrzonwafrnedglhpudovigwvpimttiketopkvqw";
        System.out.println(maxNumberOfBalloons(text1));
    }

//    frequency array; fastest; time: O(n), space: O(1)
    public static int maxNumberOfBalloons(String text) {
        int count = Integer.MAX_VALUE;
        int[] freq = new int[26];
        for (char c : text.toCharArray())
            freq[c - 'a']++;
        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');
            if (c == 'l' || c == 'o' || c == 'b' || c == 'a' || c == 'n') {
                if (c == 'l' || c == 'o') freq[i] /= 2;
                if (freq[i] == 0) return 0;
                count = Math.min (count, freq[i]);
            }
        }

        return count;
    }

//    slow because of hashmap and string.contains
    public static int maxNumberOfBalloons1(String text) {
        String target = "balloon";
        int count = Integer.MAX_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (target.contains(c + ""))
                map.put(c, map.getOrDefault(c, 0) + 1);
        }

        if (map.size() != 5) return 0;

        for (var entry : map.entrySet()) {
            char c = entry.getKey();
            int val = entry.getValue();
            if (c == 'l' || c == 'o')
                val /= 2;
            if (val == 0) return 0;
            count = Math.min(count, val);
        }

        return count;
    }

    public static int maxNumberOfBalloons2(String text) {
        String target = "balloon";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : text.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        int countB = 0, countA = 0, countL = 0, countO = 0, countN = 0;
        for (var entry : map.entrySet()) {
            char c = entry.getKey();
            int val = entry.getValue();
            String key = String.valueOf(c);
            if (target.contains(key)) {
                switch(c) {
                    case 'l': countL = val/2; break;
                    case 'o': countO = val/2; break;
                    case 'b': countB = val; break;
                    case 'a': countA = val; break;
                    default: countN = val;
                }
            }
        }

        int count = Math.min(countA, Math.min(countB, countN));
        count = Math.min(count, Math.min(countL, countO));

        return count;
    }
}

/*
Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
You can use each character in text at most once. Return the maximum number of instances that can be formed.
Example 1:
Input: text = "nlaebolko"
Output: 1
Example 2:
Input: text = "loonbalxballpoon"
Output: 2
Example 3:
Input: text = "leetcode"
Output: 0

Constraints:
1 <= text.length <= 10^4
text consists of lower case English letters only.
 */