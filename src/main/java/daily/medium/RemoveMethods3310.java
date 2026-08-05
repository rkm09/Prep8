package daily.medium;

import java.util.*;

public class RemoveMethods3310 {
    public static void main(String[] args) {
        int[][] invocations = {{1,2},{0,1},{3,2}};
        int[][] invocations1 = {{1,2},{0,1},{2,0}};
        System.out.println(remainingMethods(3, 2, invocations1));
//        System.out.println(remainingMethods(4, 1, invocations));
    }

    public static List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> adjList = new ArrayList<>();
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++)
            adjList.add(new ArrayList<>());
        for (int[] invocation : invocations) {
            int u = invocation[0];
            int v = invocation[1];
            adjList.get(u).add(v);
            inDegree[v]++;
        }
        Set<Integer> suspicious = new HashSet<>();
        dfs(adjList, inDegree, k, suspicious);

        List<Integer> res = new ArrayList<>();
        boolean canRemove = true;
        for (int i = 0; i < n; i++) {
            if (suspicious.contains(i) && inDegree[i] > 0) {
                canRemove = false; break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (suspicious.contains(i) && canRemove)
                continue;
            res.add(i);
        }

        return res;
    }

    private static void dfs (List<List<Integer>> adjList, int[] inDegree, int k, Set<Integer> suspicious) {
        if (suspicious.contains(k))
            return;

        suspicious.add(k);
        for (int neighbour : adjList.get(k)) {
            inDegree[neighbour]--;
            dfs(adjList, inDegree, neighbour, suspicious);
        }
    }
}

/*
You are maintaining a project that has n methods numbered from 0 to n - 1.
You are given two integers n and k, and a 2D integer array invocations, where invocations[i] = [ai, bi] indicates that method ai invokes method bi.
There is a known bug in method k. Method k, along with any method invoked by it, either directly or indirectly, are considered suspicious and we aim to remove them.
A group of methods can only be removed if no method outside the group invokes any methods within it.
Return an array containing all the remaining methods after removing all the suspicious methods. You may return the answer in any order. If it is not possible to remove all the suspicious methods, none should be removed.
Example 1:
Input: n = 4, k = 1, invocations = [[1,2],[0,1],[3,2]]
Output: [0,1,2,3]
Explanation:
Method 2 and method 1 are suspicious, but they are directly invoked by methods 3 and 0, which are not suspicious. We return all elements without removing anything.
Example 2:
Input: n = 5, k = 0, invocations = [[1,2],[0,2],[0,1],[3,4]]
Output: [3,4]
Explanation:
Methods 0, 1, and 2 are suspicious, and they are not directly invoked by any other method. We can remove them.
Example 3:
Input: n = 3, k = 2, invocations = [[1,2],[0,1],[2,0]]
Output: []
Explanation:
All methods are suspicious. We can remove them.

Constraints:
1 <= n <= 10^5
0 <= k <= n - 1
0 <= invocations.length <= 2 * 10^5
invocations[i] == [ai, bi]
0 <= ai, bi <= n - 1
ai != bi
invocations[i] != invocations[j]
 */
