package misc;

public class LongStringDS {
    private static final long B = 313;
    private static final long M1 = 1_000_000_007;
    private static final long M2 = 1_000_000_009;
    public static void main(String[] args) {

    }

    private static class Node {
        final long len;
        final long hash1;
        final long hash2;
        final Node left;
        final Node right;
        Node(long len, long hash1, long hash2, Node left, Node right) {
            this.len = len;
            this.hash1 = hash1;
            this.hash2 = hash2;
            this.left = left;
            this.right = right;
        }

//        fast exponentiation modulo M

    }
}
