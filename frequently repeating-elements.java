// TC: O(n)
// SC: O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Store our Frequencies in a HashMap
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        // Step 2: Make our bucket array for our bucket sort
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            buckets[entry.getValue()].add(entry.getKey());
        }
        
        // Step 3: Build our solution array to be returned
        int[] sol = new int[k];
        int index = 0;
        
        // Step 4: Do bucket sort to fill the solution array
        for (int i = nums.length; i > 0; i--) {
            for (int num : buckets[i]) {
                if (index == k) {
                    return sol;
                } else {
                    sol[index] = num;
                    index++;
                }
            }
        }
        
        // Edge case that will never happen due to the problem's constraints
        return sol;
    }
}
