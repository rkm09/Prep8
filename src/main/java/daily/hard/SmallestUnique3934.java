package daily.hard;

import java.util.HashMap;
import java.util.Map;

public class SmallestUnique3934 {
    static void main() {
        int[] nums = {2,1,2,3,3};
        System.out.println(smallestUniqueSubarray(nums));
    }

    public static int smallestUniqueSubarray(int[] nums) {
        final long MOD = 1000000007L;
        final long BASE = 911382323L;

        int n = nums.length;
        long[] h = new long[n + 1];
        long[] p = new long[n + 1];
        p[0] = 1;

        // Compute prefix hashes and powers of BASE
        for (int i = 0; i < n; ++i) {
            h[i + 1] = (h[i] * BASE + nums[i]) % MOD;
            p[i + 1] = (p[i] * BASE) % MOD;
        }

        int lo = 1,
                hi = n;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (check(mid, n, h, p, MOD)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private static boolean check(int len, int n, long[] h, long[] p, long MOD) {
        Map<Long, Integer> cnt = new HashMap<>();

        for (int l = 0; l + len <= n; ++l) {
            int r = l + len;
            long key = (h[r] - ((h[l] * p[len]) % MOD) + MOD) % MOD;
            cnt.merge(key, 1, Integer::sum);
        }

        for (int times : cnt.values()) {
            if (times == 1) {
                return true;
            }
        }
        return false;
    }
}


/*
You are given an integer array nums.
Find the minimum length of a subarray that is not identical to any other subarray in nums.
Return an integer denoting the minimum possible length of such a subarray.
Two subarrays are considered identical if they have the same length and the same elements in corresponding positions.
Example 1:
Input: nums = [3,3,3]
Output: 3
Explanation:
Subarrays of length 1: [3] → appears 3 times
Subarrays of length 2: [3, 3] → appears 2 times
Subarrays of length 3: [3, 3, 3] → appears once
The subarray [3, 3, 3] is unique, so the smallest unique subarray length is 3.
Example 2:
Input: nums = [2,1,2,3,3]
Output: 1
Explanation:
Subarrays of length 1:
[2] → appears 2 times
[1] → appears once
[3] → appears 2 times
The subarray [1] is unique, so the smallest unique subarray length is 1.
Example 3:
Input: nums = [1,1,2,2,1]
Output: 2
Explanation:
Subarrays of length 1:
[1] → appears 3 times
[2] → appears 2 times
Subarrays of length 2:
[1, 1] → appears once
[1, 2] → appears once
[2, 2] → appears once
[2, 1] → appears once
There is at least one subarray of length 2 that is unique, so the smallest unique subarray length is 2.

Constraints:
1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
 */

/*
This approach uses rolling hash (the Rabin-Karp algorithm). If you are unfamiliar with rolling hash, refer to 1392.
Longest Happy Prefix and its official editorial.
Although the input is an integer array, the problem is structurally equivalent to finding unique substrings in a
string, allowing us to leverage string algorithms like rolling hash and suffix arrays.
A brute-force approach would check all O(n^2) subarrays, count their occurrences using a hash table, and return
the minimum length of any subarray with a
frequency of 1. However, enumerating and hashing all subarrays naively takes O(n^3) time, which is far too slow \
for n=10^5.
To optimize this, observe that the existence of a unique subarray is monotonic with respect to its length.
If a subarray of length k occurs exactly once, any subarray containing it (formed by extending it within array bounds)
must also occur at most once—and therefore exactly once. Conversely, if no subarray of length k is unique, no shorter subarray can be unique either.
Because a unique subarray exists for all lengths greater than or equal to the minimum valid length, we can binary
search over the answer length k∈[1,n].
For a fixed length k, naively computing the hash of each subarray by traversing its elements would take O(k) time
per subarray, resulting in O(n⋅k) time per check. Since adjacent subarrays overlap significantly, we can compute
each subarray's hash in O(1) time using a rolling hash.
A polynomial rolling hash treats each element of a sequence as a digit in base B modulo M. Formally, for a sequence
a=[a0,a1,…,am−1]:
H(a)=(∑ i=0 to m−1 ai⋅B^(m−1−i))modM.
By precomputing prefix hashes h[i]=H(nums[0…i−1]) and powers p[i]=B
i modM, we can extract the hash of any contiguous subarray nums[l…r−1] of length k=r−l in O(1) time:
H(nums[l…r−1])=(h[r]−h[l]⋅p[k]modM+M)modM.
Binary search the subarray length, then use rolling hash to calculate the hashes of equal-length subarrays from left to right. Store these hashes in an ordinary hash table and check whether any subarray occurs exactly once.
Note: The test data for this problem does not require double hashing to prevent collisions.
 */