package daily.easy;

public class LargestAltitude1732 {
    public static void main(String[] args) {
        System.out.println(largestAltitude(new int[] {-4,-3,-2,-1,4,3,2}));
    }

//    prefix sum; time: O(n), space: O(1)
    public static int largestAltitude(int[] gain) {
        int currentAltitude = 0;
//        to start with, highest is at current
        int highestAltitude = currentAltitude;
        for (int g : gain) {
            currentAltitude += g;
            highestAltitude = Math.max(highestAltitude, currentAltitude);
        }

        return highestAltitude;
    }

//    prefix sum with space; time: O(n), space: O(n)
    public static int largestAltitude1(int[] gain) {
        int n = gain.length, max = 0;
        int[] altitudes = new int[n + 1];
        for (int i = 0; i < n; i++) {
            altitudes[i + 1] = altitudes[i] +  gain[i];
            max = Math.max(max, altitudes[i + 1]);
        }

        return max;
    }
}

/*
There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes. The biker starts his trip on point 0 with altitude equal 0.
You are given an integer array gain of length n where gain[i] is the net gain in altitude between points i and i + 1 for all (0 <= i < n).
Return the highest altitude of a point.
Example 1:
Input: gain = [-5,1,5,0,-7]
Output: 1
Explanation: The altitudes are [0,-5,-4,1,1,-6]. The highest is 1.
Example 2:
Input: gain = [-4,-3,-2,-1,4,3,2]
Output: 0
Explanation: The altitudes are [0,-4,-7,-9,-10,-6,-3,-1]. The highest is 0.

Constraints:
n == gain.length
1 <= n <= 100
-100 <= gain[i] <= 100
 */