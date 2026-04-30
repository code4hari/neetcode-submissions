public class Solution {

    public int search(int[] nums, int target) {
        // 1. handle null/empty defensively — return -1 since target can't be found
        // 2. set up two pointers, left and right, for binary search
        // 3. while left <= right, compute mid
        // 4. if nums[mid] == target, return mid
        // 5. determine which half is sorted by comparing nums[left] and nums[mid]
        // 6. if target is within the sorted half's range, search there; otherwise search the other half
        // 7. if loop exits, target wasn't found, return -1
        // looks good, let me implement this

        // edge case — null or empty array means there's nothing to search
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            // compute mid this way to avoid integer overflow on very large arrays
            int mid = left + (right - left) / 2;

            // found the target — return its index
            if (nums[mid] == target) {
                return mid;
            }

            // figure out which side is sorted
            // if nums[left] <= nums[mid], the left half from left to mid is sorted
            // the equals handles the case where left == mid (single element window)
            if (nums[left] <= nums[mid]) {
                // left half is sorted — check if target falls in [nums[left], nums[mid])
                if (target >= nums[left] && target < nums[mid]) {
                    // target must be in the sorted left half
                    right = mid - 1;
                } else {
                    // target is in the unsorted right half
                    left = mid + 1;
                }
            } else {
                // right half from mid to right is sorted
                // check if target falls in (nums[mid], nums[right]]
                if (target > nums[mid] && target <= nums[right]) {
                    // target must be in the sorted right half
                    left = mid + 1;
                } else {
                    // target is in the unsorted left half
                    right = mid - 1;
                }
            }
        }

        // exhausted the search space without finding target
        return -1;
    }
}