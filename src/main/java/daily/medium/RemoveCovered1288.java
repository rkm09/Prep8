package daily.medium;


import java.util.Arrays;

public class RemoveCovered1288 {
    public static void main(String[] args) {
        int[][] intervals1 = {{1,4},{3,6},{2,8}};
        int[][] intervals2 = {{1,2},{1,4},{3,4}};
        int[][] intervals = {{1,4},{2,3}};
        System.out.println(removeCoveredIntervals(intervals));
    }

//    sorting intervals; time: O(nlogn), space: O(1)
    public static int removeCoveredIntervals(int[][] intervals) {
//        this way is slower due to autoboxing and method chaining overhead
//        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[0])
//                .thenComparingInt((int[] a) -> -a[1]));
        Arrays.sort(intervals, (a,b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]); // ascending
            else return Integer.compare(b[1], a[1]); //descending
        });

        int maxEnd = 0, remaining = 0;
        for (int[] interval : intervals) {
            if (interval[1] > maxEnd) {
                remaining++;
                maxEnd = interval[1];
            }
        }

        return remaining;
    }
}

/*
Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that are covered by another interval in the list.
The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
Return the number of remaining intervals.
Example 1:
Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
Example 2:
Input: intervals = [[1,4],[2,3]]
Output: 1

Constraints:
1 <= intervals.length <= 1000
intervals[i].length == 2
0 <= li < ri <= 10^5
All the given intervals are unique.
 */
