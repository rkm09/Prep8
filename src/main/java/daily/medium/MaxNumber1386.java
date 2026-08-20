package daily.medium;

import java.util.HashMap;
import java.util.Map;

public class MaxNumber1386 {
    public static void main(String[] args) {
        int[][] reserved = {{1,2},{1,3},{1,8},{2,6},{3,1},{3,10}};
        System.out.println(maxNumberOfFamilies(3, reserved));
    }

//    bit manipulation; time: O(n), space: O(n)
    public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int left = 0b11110000; // targeting untouched (2,3,4,5) starting from units place
        int middle = 0b11000011; // (4,5,6,7)
        int right = 0b00001111; // (6,7,8,9)
        Map<Integer, Integer> occupied = new HashMap<>();
        for (int[] reserved : reservedSeats) {
            int row = reserved[0], seat = reserved[1];
            if (seat > 1 && seat < 10) {
                int original = occupied.getOrDefault(row, 0);
                int value = original | (1 << (seat - 2));
                occupied.put(row, value);
            }
        }

        int ans = (n - occupied.size()) * 2;
        for (int row : occupied.keySet()) {
            int bitmask = occupied.get(row);
            if (
            ((bitmask | left) == left)
                || ((bitmask | middle) == middle)
                    || ((bitmask | right) == right)
            ) ans++;
        }

        return ans;
    }
}

/*
A cinema has n rows of seats, numbered from 1 to n. Each row has 10 seats, numbered from 1 to 10.
You are given a 2D integer array reservedSeats, where reservedSeats[i] = [rowi, seati] means that seat seati in row rowi is already reserved.
A four-person group must be assigned to four seats in the same row. The group can be seated in one of the following seat blocks:
seats 2, 3, 4, 5
seats 4, 5, 6, 7
seats 6, 7, 8, 9
A block can be used only if none of its seats are reserved. Each seat can be assigned to at most one group.
Return an integer denoting the maximum number of four-person groups that can be assigned.
Example 1:
Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
Output: 4
Explanation: The figure above shows an optimal allocation of four groups. Seats marked in blue are already reserved, and each set of four contiguous seats marked in orange is assigned to one group.
Example 2:
Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
Output: 2
Example 3:
Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
Output: 4

Constraints:
1 <= n <= 10^9
1 <= reservedSeats.length <= min(10 * n, 10^4)
reservedSeats[i] == [rowi, seati]
1 <= rowi <= n
1 <= seati <= 10
All reservedSeats[i] are distinct.
 */


/*
For each row, there are only three possible ways to arrange a family:
Seats 2, 3, 4, 5;
Seats 4, 5, 6, 7;
Seats 6, 7, 8, 9.
Therefore, seats 1 and 10 in each row are irrelevant. Even if they are reserved, they do not affect the answer. Thus, we can ignore all reservations at seats 1 and 10.
We can also observe that if a row has no reserved seats among positions 2 to 9, it can accommodate exactly two families: one family in seats 2, 3, 4, 5, and another in seats 6, 7, 8, 9. If at least one of these seats is reserved, the row can accommodate at most one family.
Therefore, we can use 8 binary bits to represent the reservation status of seats 2 to 9 in a row. If seat i is reserved, then the (i−2)-th bit is set to 1; otherwise, it is set to 0.
For example, in Example 1, the binary representation of each row is as follows:
First row: Seats 2, 3, and 8 are reserved, so the binary representation is (01000011).
Second row: Seat 6 is reserved, so the binary representation is (00010000).
Third row: Seats 1 and 10 are reserved, so the binary representation is (00000000).
We can use a HashMap to store the reservation status of each row. For each key-value pair in the HashMap, the key represents the row number, and the value represents the corresponding bitmask.
 */