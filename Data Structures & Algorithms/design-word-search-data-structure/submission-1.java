class WordDictionary {

    // Each TrieNode has up to 26 children (one per lowercase letter)
    // and a flag marking whether a word ends at this node.
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    private final TrieNode root;

    public WordDictionary() {
        // Root is an empty node — it doesn't represent any character itself.
        root = new TrieNode();
    }

    public void addWord(String word) {
        // Walk down the trie, creating nodes as needed for each character.
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        // Mark the end of a complete word so we can distinguish prefixes from full words.
        node.isEnd = true;
    }

    public boolean search(String word) {
        // Kick off the recursive DFS from the root at index 0.
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int i, TrieNode node) {
        // If the node became null somewhere along the path, this branch fails.
        if (node == null) return false;
        // If we've consumed the whole word, success only if this node marks a word end.
        if (i == word.length()) return node.isEnd;

        char c = word.charAt(i);
        if (c == '.') {
            // Wildcard — try every possible child. If any branch succeeds, we're done.
            for (TrieNode child : node.children) {
                if (child != null && dfs(word, i + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            // Regular character — just descend into the matching child.
            int idx = c - 'a';
            return dfs(word, i + 1, node.children[idx]);
        }
    }

    // --- uncomment to test locally ---
    // public static void main(String[] args) {
    //     WordDictionary wd = new WordDictionary();
    //     wd.addWord("bad");
    //     wd.addWord("dad");
    //     wd.addWord("mad");
    //     System.out.println(wd.search("pad")); // false
    //     System.out.println(wd.search("bad")); // true
    //     System.out.println(wd.search(".ad")); // true
    //     System.out.println(wd.search("b..")); // true
    //
    //     // Edge cases
    //     WordDictionary wd2 = new WordDictionary();
    //     System.out.println(wd2.search("a"));   // false — empty dictionary
    //     wd2.addWord("a");
    //     System.out.println(wd2.search("."));   // true — single-char wildcard
    //     System.out.println(wd2.search("ab"));  // false — longer than any word
    // }
}