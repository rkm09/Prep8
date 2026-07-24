package daily.medium;


public class UniqueXOR3514 {
    public static void main(String[] args) {
        int[] nums1 = {6,7,8,9};
        int[] nums = {420,1073,386,496}; // 8
        System.out.println(uniqueXorTriplets(nums));
    }

//    enumeration with dp; time: O(m.n), space: O(m)
    public static int uniqueXorTriplets(int[] nums) {
        int maxVal = 0;
        for (int num : nums)
            maxVal = Math.max(maxVal, num);
//        find the smallest power of 2 strictly greater than maxVal (upper bit bound)
        int xorRangeBound = 1;
        while (xorRangeBound <= maxVal)
            xorRangeBound <<= 1;
//        look up tables tracking reachability at each subset size step
        boolean[] isSingleReachable = new boolean[xorRangeBound];
        boolean[] isPairReachable = new boolean[xorRangeBound];
        boolean[] isTripletReachable = new boolean[xorRangeBound];
//        step 1 & step 2: mark single elements and build all reachable pair xor's (a ^ b)
        for (int value : nums) {
            isSingleReachable[value] = true;
            for (int singleXor = 0; singleXor < xorRangeBound; singleXor++) {
                if (isSingleReachable[singleXor])
                    isPairReachable[singleXor ^ value] = true;
            }
        }
//        step 3: extend pairs with a 3rd element to build all reachable triplets xor's (a ^ b ^ c)
        for (int value : nums) {
            for (int pairXor = 0; pairXor < xorRangeBound; pairXor++) {
                if (isPairReachable[pairXor])
                    isTripletReachable[pairXor ^ value] = true;
            }
        }
//        count unique reachable triplet xor results
        int uniqueTripletCount = 0;
        for (boolean isReachable : isTripletReachable)
            if (isReachable)
                uniqueTripletCount++;

        return uniqueTripletCount;
    }
}

/*
You are given an integer array nums.
An XOR triplet is defined as the XOR of three elements nums[i] XOR nums[j] XOR nums[k] where i <= j <= k.
Return the number of unique XOR triplet values from all possible triplets (i, j, k).
Example 1:
Input: nums = [1,3]
Output: 2
Explanation:
The possible XOR triplet values are:
(0, 0, 0) → 1 XOR 1 XOR 1 = 1
(0, 0, 1) → 1 XOR 1 XOR 3 = 3
(0, 1, 1) → 1 XOR 3 XOR 3 = 1
(1, 1, 1) → 3 XOR 3 XOR 3 = 3
The unique XOR values are {1, 3}. Thus, the output is 2.
Example 2:
Input: nums = [6,7,8,9]
Output: 4
Explanation:
The possible XOR triplet values are {6, 7, 8, 9}. Thus, the output is 4.

Constraints:
1 <= nums.length <= 1500
1 <= nums[i] <= 1500
 */
