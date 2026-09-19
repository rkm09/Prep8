package daily.medium;

public class CheckOverlap1401 {
    public static void main(String[] args) {
        System.out.println(checkOverlap(1,0,0,1,-1,3,1));
    }

//    minimum distance from circle's centre; time: O(1), space: O(1)
    public static boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
//        find the closest point to the center, inside or on the rectangle
        int nearestX = Math.max(x1, Math.min(xCenter, x2));
        int nearestY = Math.max(y1, Math.min(yCenter, y2));
//        calculate the vector points from the closest point on the rectangle to the circle's center
        int deltaX = xCenter - nearestX;
        int deltaY = yCenter - nearestY;
//        compare squared distance with squared radius to avoid floating point errors
        return (deltaX * deltaX + deltaY * deltaY <= radius * radius);
    }
}

/*
You are given a circle represented as (radius, xCenter, yCenter) and an axis-aligned rectangle represented as
(x1, y1, x2, y2), where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates of
the top-right corner of the rectangle.
Return true if the circle and rectangle are overlapped otherwise return false. In other words, check if there is any point (xi, yi) that belongs to the circle and the rectangle at the same time.
Example 1:
Input: radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
Output: true
Explanation: Circle and rectangle share the point (1,0).
Example 2:
Input: radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
Output: false
Example 3:
Input: radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
Output: true

Constraints:
1 <= radius <= 2000
-10^4 <= xCenter, yCenter <= 10^4
-10^4 <= x1 < x2 <= 10^4
-10^4 <= y1 < y2 <= 10^4
 */


/*
To check whether the circle and rectangle overlap, find the point $(x_n, y_n)$ inside or on the boundary of the
rectangle that is closest to the center of the circle $(x_c, y_c)$. If the Euclidean distance between this closest
point and $(x_c, y_c)$ is less than or equal to the radius, then they overlap.
clamping logic:
int nearestX;
if (xCenter < x1) {
    nearestX = x1;     // Circle is to the left: pick left edge
} else if (xCenter > x2) {
    nearestX = x2;     // Circle is to the right: pick right edge
} else {
    nearestX = xCenter; // Circle is horizontally within bounds
}

Axis Independence in Distance Problems:
Because the axes are perpendicular (orthogonal), minimizing the distance in $X$
and minimizing the distance in $Y$ independently guarantees that you minimize the combined Euclidean distance
 */