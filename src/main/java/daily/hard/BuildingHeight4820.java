package daily.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BuildingHeight4820 {
    public static void main(String[] args) {
        System.out.println(maxBuilding(5, new int[][]{{2,1},{4,1}}));
    }

//    transitivity of restrictions; time: O(MlogM), space: O(M) where M - length of the restrictions
    public static int maxBuilding(int n, int[][] restrictions) {
        List<int[]> restList = new ArrayList<>();
//        convert constraints to a list for manipulation
        for (int[] r : restrictions)
            restList.add(new int[] {r[0], r[1]});
//        append the restriction on building 1
        restList.add(new int[]{1,0});
//        sort by position
        restList.sort(Comparator.comparingInt(a -> a[0]));
//        add restriction (n, n - 1)
        if (restList.getLast()[0] != n)
            restList.add(new int[] {n, n - 1});
        int m = restList.size();
//        pass restrictions from left to right
        for (int i = 1; i < m; i++) {
            int dist = restList.get(i)[0] - restList.get(i - 1)[0];
            restList.get(i)[1] = Math.min(restList.get(i)[1], restList.get(i - 1)[1] + dist);
        }
//        pass restrictions from right to left
        for (int i = m - 2; i >= 0; i--) {
            int dist = restList.get(i + 1)[0] - restList.get(i)[0];
            restList.get(i)[1] = Math.min(restList.get(i)[1], restList.get(i + 1)[1] + dist);
        }
        int ans = 0;
//        calculate the max height
        for (int i = 0; i < m - 1; i++) {
            int dist = restList.get(i + 1)[0] - restList.get(i)[0];
            int best = (restList.get(i + 1)[1] + restList.get(i)[1] + dist) / 2;
            ans = Math.max(ans, best);
        }

        return ans;
    }
}

/*
You want to build n new buildings in a city. The new buildings will be built in a line and are labeled from 1 to n.
However, there are city restrictions on the heights of the new buildings:
The height of each building must be a non-negative integer.
The height of the first building must be 0.
The height difference between any two adjacent buildings cannot exceed 1.
Additionally, there are city restrictions on the maximum height of specific buildings. These restrictions are given as a 2D integer array restrictions where restrictions[i] = [idi, maxHeighti] indicates that building idi must have a height less than or equal to maxHeighti.
It is guaranteed that each building will appear at most once in restrictions, and building 1 will not be in restrictions.
Return the maximum possible height of the tallest building.
Example 1:
Input: n = 5, restrictions = [[2,1],[4,1]]
Output: 2
Explanation: The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,1,2], and the tallest building has a height of 2.
Example 2:
Input: n = 6, restrictions = []
Output: 5
Explanation: The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,3,4,5], and the tallest building has a height of 5.
Example 3:
Input: n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
Output: 5
Explanation: The green area in the image indicates the maximum allowed height for each building.
We can build the buildings with heights [0,1,2,3,3,4,4,5,4,3], and the tallest building has a height of 5.

Constraints:
2 <= n <= 10^9
0 <= restrictions.length <= min(n - 1, 10^5)
2 <= idi <= n
idi is unique.
0 <= maxHeighti <= 10^9
 */

/*
Derivation of final height:
1) The horizontal distance:  dist = y - x
2) The height difference: deltaH = abs(h1 - h2)
since the slope is exactly 1, it takes deltaH steps to be added to the shorter building to level them up
3) Splitting the remaining distance:
We know there will be a peak formed exactly in the middle if the two restricted heights are exactly equal, and if not
it will be somewhere within these two limits.
Once we have used deltaH steps to level them up, the remaining horizontal steps to build the shared peak is:
remaining_dist = dist - deltaH
Since the two sides are now leveled up, they will climb to each other and meet exactly in the middle
The extra height they can gain together is half of this remaining distance.
extra_height = remaining_height / 2
    = (dist - deltaH) / 2
4) Final formula:
To find the absolute maximum height of the peak, you take the height of the taller building and add that extra height to it.
Max Peak = max(H1, H2) + extra_height
    = H1 + (dist - deltaH) / 2.    // assuming H1 is greater
    = H1 + dist / 2 - (H1 - H2) / 2
    = dist / 2 + (2 * H1 - H1 + H2) / 2
    = dist / 2 + (H1 + H2) / 2
    = (H1 + H2 + dist) / 2    // notice it magically becomes taller height agnostic
 */