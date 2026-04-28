class Solution {
    public boolean hasDuplicate(int[] nums) {
        // initialize a set to store the values of the array
        HashSet<Integer> number = new HashSet<>();
        // traverse the array and check if the value is in the set. if it is then we can return true and
        // if it is not we can keep traversing the array until we have gone through the whole array.
        for (int num : nums){
            if (number.contains(num)){
                return true;
            }
            number.add(num);
        }return false;
    }

}