

public class Solution {
    public String minWindow(String s, String t) {
        // Edge case: if t is longer than s or either is empty, no valid window exists
        if (s == null || t == null || t.length() > s.length() || t.length() == 0) {
            return "";
        }
        
        // Build frequency map of characters needed from t
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        
        // Track what we currently have in our window
        HashMap<Character, Integer> window = new HashMap<>();
        
        // 'required' is how many unique chars need to hit their target count
        int required = need.size();
        // 'have' is how many unique chars currently meet their target count
        int have = 0;
        
        // Track the best window we've seen: [start, length]
        int resStart = 0;
        int resLen = Integer.MAX_VALUE;
        
        int left = 0;
        
        // Expand window with right pointer
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);
            
            // If this character just hit the required count, increment 'have'
            if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) {
                have++;
            }
            
            // Try to shrink from the left while window is still valid
            while (have == required) {
                // Update result if this window is smaller
                if (right - left + 1 < resLen) {
                    resStart = left;
                    resLen = right - left + 1;
                }
                
                // Remove leftmost char and shrink
                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                
                // If we've dropped below the required count, decrement 'have'
                if (need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar)) {
                    have--;
                }
                left++;
            }
        }
        
        return resLen == Integer.MAX_VALUE ? "" : s.substring(resStart, resStart + resLen);
    }
}