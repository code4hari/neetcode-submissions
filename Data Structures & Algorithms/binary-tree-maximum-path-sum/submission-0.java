class Solution {
    // global max — I'm using a field instead of passing it through recursion
    // because it's cleaner, and Integer.MIN_VALUE handles the case where
    // every node is negative and we still need to return the least-negative one
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // kick off the DFS — the return value of this top-level call
        // is just the best downward path from root, which we don't actually need;
        // the answer lives in maxSum after the traversal
        maxGain(root);
        return maxSum;
    }

    // this helper returns the max gain we can get by going DOWN from this node
    // into exactly one branch — that's what a parent can extend through
    private int maxGain(TreeNode node) {
        // base case — null contributes nothing, return 0 so it gets ignored
        if (node == null) return 0;

        // recursively get the best downward sum from each child,
        // but clamp to 0 — if a subtree would hurt us, we just don't take it
        // this is the trick that makes negative values work correctly
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // here's the key moment — consider this node as the PEAK of a path
        // that bends through it: left + node + right
        // this is the only place we can use both children at once
        int priceAsPeak = node.val + leftGain + rightGain;

        // update the global answer if this peak beats anything we've seen
        maxSum = Math.max(maxSum, priceAsPeak);

        // but what we return to the parent is different —
        // parent can only extend through ONE branch, so pick the better one
        return node.val + Math.max(leftGain, rightGain);
    }
}