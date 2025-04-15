// TC: O(n^2)
// SC: O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    
    class TrieNode {
        TrieNode[] children;
        List<String> startWith;
        
        public TrieNode() {
            children = new TrieNode[26];
            startWith = new ArrayList<>();
        }
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }
                curr.startWith.add(word);
                curr = curr.children[c - 'a'];
            }
        }
        return root;
    }
    
    private List<String> allStartWith(TrieNode root, String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.children[c - 'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }
        return curr.startWith;
    }
    
    List<List<String>> result;
    
    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        List<String> li = new ArrayList<>();
        for (String word : words) {
            li.add(word);
            backtrack(root, li, word.length());
            li.remove(li.size() - 1);
        }
        return result;
    }
    
    private void backtrack(TrieNode root, List<String> li, int l) {
        // Base case: when the current square has reached the required number of words.
        if (li.size() == l) {
            result.add(new ArrayList<>(li));
            return;
        }
        
        int i = li.size();
        StringBuilder prefix = new StringBuilder();
        // Build the prefix for the current column.
        for (String liWord : li) {
            prefix.append(liWord.charAt(i));
        }
        
        // Get all words that start with the current prefix.
        List<String> candidates = allStartWith(root, prefix.toString());
        for (String word : candidates) {
            li.add(word);              // action
            backtrack(root, li, l);      // recurse
            li.remove(li.size() - 1);    // backtrack
        }
    }
}
