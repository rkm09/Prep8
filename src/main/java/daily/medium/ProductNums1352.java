package daily.medium;

import java.util.ArrayList;
import java.util.List;


public class ProductNums1352 {
    static void main() {}
}

class ProductOfNumbers {
    List<Integer> prefixProd;
    int size;
    public ProductOfNumbers() {
        prefixProd = new ArrayList<>();
        prefixProd.add(1);
        size = 0;
    }

    public void add(int num) {
       if (num == 0) {
           prefixProd = new ArrayList<>();
           prefixProd.add(1);
           size = 0;
       } else {
           prefixProd.add(prefixProd.get(size) * num);
           size++;
       }

    }

    public int getProduct(int k) {
        if (k > size) return 0;
        return prefixProd.get(size) / prefixProd.get(size - k);
    }
}


/* TLE at 33/35.
class ProductOfNumbers {

    Deque<Integer> stream;
    public ProductOfNumbers() {
        stream = new ArrayDeque<>();
    }

    public void add(int num) {
        stream.push(num);
    }

    public int getProduct(int k) {
        int prod = 1;
        Deque<Integer> nums = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            int next = stream.pop();
            nums.push(next);
            prod *= next;
        }

        while (!nums.isEmpty())
            stream.push(nums.pop());
        return prod;
    }
}
*/

/*
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */

/*
Design an algorithm that accepts a stream of integers and retrieves the product of the last k integers of the stream.
Implement the ProductOfNumbers class:
ProductOfNumbers() Initializes the object with an empty stream.
void add(int num) Appends the integer num to the stream.
int getProduct(int k) Returns the product of the last k numbers in the current list. You can assume that the
current list always has at least k numbers.
The test cases are generated such that, at any time, the product of any contiguous sequence of numbers will fit into a
single 32-bit integer without overflowing.
Example:
Input
["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
[[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
Output
[null,null,null,null,null,null,20,40,0,null,32]
Explanation
ProductOfNumbers productOfNumbers = new ProductOfNumbers();
productOfNumbers.add(3);        // [3]
productOfNumbers.add(0);        // [3,0]
productOfNumbers.add(2);        // [3,0,2]
productOfNumbers.add(5);        // [3,0,2,5]
productOfNumbers.add(4);        // [3,0,2,5,4]
productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers is 5 * 4 = 20
productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
productOfNumbers.add(8);        // [3,0,2,5,4,8]
productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers is 4 * 8 = 32

Constraints:
0 <= num <= 100
1 <= k <= 4 * 10^4
At most 4 * 10^4 calls will be made to add and getProduct.
 */