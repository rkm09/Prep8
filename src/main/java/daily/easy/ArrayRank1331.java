package daily.easy;

import java.util.*;

public class ArrayRank1331 {
    public static void main(String[] args) {
        int[] arr = {40,10,20,30};
        System.out.println(Arrays.toString(arrayRankTransform(arr)));
    }


//    deduplication with tree set; time: O(nlogn), space: O(n)
    public static int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        Set<Integer> set = new TreeSet<>();
        for (int k : arr)
            set.add(k);

        int rank = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int k : set)
            map.put (k , rank++);

        int[] res = new int[n];
        for (int i = 0; i < n; i++)
            res[i] = map.get(arr[i]);

        return res;
    }

//    tree map; time: O(nlogn), space: O(n)
    public static int[] arrayRankTransform1(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> numToIndices = new TreeMap<>();
        for (int i = 0; i < n; i++)
            numToIndices.computeIfAbsent(arr[i], k -> new ArrayList<>())
                    .add(i);

        int rank = 1;
        int[] res = new int[n];
        for (int num : numToIndices.keySet()) {
            for (int index : numToIndices.get(num)) {
                res[index] = rank;
            }
            rank++;
        }
        return res;
    }


    //    MLE at [-1000000000,10,20,30,1000000000]
//    cannot use counting sort as elements can be 10^9 / -10^9
    public static int[] arrayRankTransformX(int[] arr) {
        int n = arr.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int k : arr) {
            min = Math.min(min, k);
            max = Math.max(max, k);
        }
        int range = max - min + 1;
        int[] freq = new int[range];
        for (int k : arr)
            freq[k - min]++;

        int k = 1;
        for (int i = 0; i < range; i++) {
            if (freq[i] != 0)
                freq[i] = k++;
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++)
            res[i] = freq[arr[i] - min];


        return res;
    }
}

/*
Given an array of integers arr, replace each element with its rank.
The rank represents how large the element is. The rank has the following rules:
Rank is an integer starting from 1.
The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
Rank should be as small as possible.
Example 1:
Input: arr = [40,10,20,30]
Output: [4,1,2,3]
Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
Example 2:
Input: arr = [100,100,100]
Output: [1,1,1]
Explanation: Same elements share the same rank.
Example 3:
Input: arr = [37,12,28,9,100,56,80,5,12]
Output: [5,3,4,2,8,6,7,1,3]

Constraints:
0 <= arr.length <= 10^5
-10^9 <= arr[i] <= 10^9
 */
