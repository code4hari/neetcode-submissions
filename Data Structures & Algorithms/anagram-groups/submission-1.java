class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
           HashMap<String, List<String>> map = new HashMap <>();
           for (String word : strs){
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sorted = new String(charArray);
            if (!map.containsKey(sorted)){
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(word);
           }return new ArrayList<>(map.values());
    }
}
