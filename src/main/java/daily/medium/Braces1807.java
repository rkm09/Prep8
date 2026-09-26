package daily.medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Braces1807 {
    public static void main(String[] args) {
        String s = "(name)is(age)yearsold";
        List<String> k1 = List.of("name","ken");
        List<String> k2 = List.of("age", "two");
        System.out.println(evaluate(s, List.of(k1, k2)));
    }

//    simulation; time: O(n + m), space: O(n + m)
    public static String evaluate(String s, List<List<String>> knowledge) {
        int n = s.length();
//        optimize hashmap to avoid resizing
        Map<String, String> keyMap = new HashMap<>((int) (knowledge.size() / 0.75) + 1);
        for (List<String> key : knowledge)
            keyMap.put(key.getFirst(), key.getLast());
        boolean addKey = false;
//        pre-allocate buffer sizes to avoid resizing under the hood
        StringBuilder key = new StringBuilder(n);
//        keys are usually short
        StringBuilder res = new StringBuilder(32);
//        note: in this special case alone it is more optimal to convert a charArray first, since otherwise
//        there are bound checks for everytime you call s.charAt();
        char[] arr = s.toCharArray();
        for (int i = 0 ; i < n; i++) {
            char c = arr[i];
            switch (c) {
                case '(' -> addKey = true;
                case ')' -> {
                    res.append(keyMap.getOrDefault(key.toString(), "?"));
                    addKey = false;
                    key.setLength(0);
                }
                default -> {
                    if (addKey)
                        key.append(c);
                    else
                        res.append(c);
                }
            }
        }

        return res.toString();
    }

//  a slightly different flavor of the java21 switch, but less performant as we box it
    public static String evaluate1(String s, List<List<String>> knowledge) {
        int n = s.length();
        Map<String, String> keyMap = new HashMap<>();
        for (List<String> key : knowledge)
            keyMap.put(key.getFirst(), key.getLast());
        final boolean[] addKeyRef = {false};
        StringBuilder key = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (int i = 0 ; i < n; i++) {
            Character c = s.charAt(i);
            switch (c) {
                case '(' -> addKeyRef[0] = true;
                case ')' -> {
                    res.append(keyMap.getOrDefault(key.toString(), "?"));
                    addKeyRef[0] = false;
                    key.setLength(0);
                }
                case Character ch when addKeyRef[0] ->
                    key.append(ch);
                default ->
                    res.append(c);
            }
        }

        return res.toString();
    }

    public static String evaluate2(String s, List<List<String>> knowledge) {
        Map<String, String> keyMap = new HashMap<>();
        for (List<String> key : knowledge)
            keyMap.put(key.getFirst(), key.getLast());
//        match '(' followed by one or more characters that are not ')'
        Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(s);
        StringBuilder res = new StringBuilder();
        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = keyMap.getOrDefault(key, "?");
//            quote replacement ensures characters '$' or '/' do not break the regex engine
//            appendReplacement takes care of intermediate insert
            matcher.appendReplacement(res, Matcher.quoteReplacement(replacement));
        }
//        appends any remaining text after the last match
        matcher.appendTail(res);

        return res.toString();
    }

//    TLE;
    public static String evaluateX(String s, List<List<String>> knowledge) {
        Map<String, String> keyMap = new HashMap<>();
        for (List<String> key : knowledge)
            keyMap.put(key.getFirst(), key.getLast());
//        match '(' followed by one or more characters that are not ')'
        Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String key = matcher.group(1);
//            TLE happens here, coz everytime s.replace is called java scans the entire string s from the beginning
//            and creates a brand-new string in memory. If a test case has multiple brackets, it will easily
//            make the complexity O(n^2) => 10^10!
            s = s.replace("(" + key + ")", keyMap.getOrDefault(key, "?"));
        }

        return s;
    }
}

/*
You are given a string s that contains some bracket pairs, with each pair containing a non-empty key.
For example, in the string "(name)is(age)yearsold", there are two bracket pairs that contain the keys "name" and "age".
You know the values of a wide range of keys. This is represented by a 2D string array knowledge where each knowledge[i] = [eyi, valuei] indicates that key keyi has a value of valuei.
You are tasked to evaluate all the bracket pairs. When you evaluate a bracket pair that contains some key keyi, you will:
Replace keyi and the bracket pair with the key's corresponding valuei.
If you do not know the value of the key, you will replace keyi and the bracket pair with a question mark "?" (without the quotation marks).
Each key will appear at most once in your knowledge. There will not be any nested brackets in s.
Return the resulting string after evaluating all the bracket pairs.
Example 1:
Input: s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]]
Output: "bobistwoyearsold"
Explanation:
The key "name" has a value of "bob", so replace "(name)" with "bob".
The key "age" has a value of "two", so replace "(age)" with "two".
Example 2:
Input: s = "hi(name)", knowledge = [["a","b"]]
Output: "hi?"
Explanation: As you do not know the value of the key "name", replace "(name)" with "?".
Example 3:
Input: s = "(a)(a)(a)aaa", knowledge = [["a","yes"]]
Output: "yesyesyesaaa"
Explanation: The same key can appear multiple times.
The key "a" has a value of "yes", so replace all occurrences of "(a)" with "yes".
Notice that the "a"s not in a bracket pair are not evaluated.

Constraints:
1 <= s.length <= 10^5
0 <= knowledge.length <= 10^5
knowledge[i].length == 2
1 <= keyi.length, valuei.length <= 10
s consists of lowercase English letters and round brackets '(' and ')'.
Every open bracket '(' in s will have a corresponding close bracket ')'.
The key in each bracket pair of s will be non-empty.
There will not be any nested bracket pairs in s.
keyi and valuei consist of lowercase English letters.
Each keyi in knowledge is unique.
 */
