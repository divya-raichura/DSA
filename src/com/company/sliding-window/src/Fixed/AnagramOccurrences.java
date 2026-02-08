package Fixed;

import java.util.HashMap;
import java.util.Map;

public class AnagramOccurrences {
    public static void main(String[] args) {

        solution1("aaaaabaabaa", "aab");
        solution2("aaaaabaabaa", "aab");

    }

    /*
    *
    * BRUTE FORCE:
    *
    * find all the anagrams, then use string matching algorithm like kmp
    * but finding anagrams (permutations) is itself highest tc = n!
    *
    * */

    static void solution1(String str, String ptr) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : ptr.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int count = map.size(); // so we don't have to iterate on map to find if all characters are 0
        // if one character is 0, we decrease count by 1, if all are 0, count will be 0

        int i = 0, j = 0, ans = 0;

        while (j < str.length()) {
            // If the character is present in the map, decrease its frequency
            if (map.containsKey(str.charAt(j))) {
                map.put(str.charAt(j), map.get(str.charAt(j)) - 1);

                // If the frequency becomes 0, decrease the count
                if (map.get(str.charAt(j)) == 0) {
                    count--;
                }
            }

            if (j - i + 1 < ptr.length()) {
                j++;
            } else if (j - i + 1 == ptr.length()) {
                // If all characters are present (count is 0), increment the answer
                if (count == 0) {
                    ans++;
                }

                if (map.containsKey(str.charAt(i))) {
                    map.put(str.charAt(i), map.get(str.charAt(i)) + 1);

                    // If the frequency becomes non-zero, increment the count
                    if (map.get(str.charAt(i)) == 1) {
                        count++;
                    }
                }

                i++;
                j++;
            }
        }
        System.out.println(ans);
    }

    static void solution2(String str, String ptr) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : ptr.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int count = map.size(); // so we don't have to iterate on map to find if all characters are 0
        // if one character is 0, we decrease count by 1, if all are 0, count will be 0

        int i = 0, j = 0, ans = 0;

        while (j < str.length()) {
            // If the character is present in the map, decrease its frequency
            if (map.containsKey(str.charAt(j))) {
                map.put(str.charAt(j), map.get(str.charAt(j)) - 1);

                if (map.get(str.charAt(j)) == 0) {
                    count--;
                }
            }

            // If all characters are present (count is 0), increment the answer
            if (count == 0) {
                ans++;
            }

            // If we have a window of size ptr.length(), remove the leftmost character
            if (j - i + 1 == ptr.length()) {
                if (map.containsKey(str.charAt(i))) {
                    map.put(str.charAt(i), map.get(str.charAt(i)) + 1);

                    if (map.get(str.charAt(i)) == 1) {
                        count++;
                    }
                }
                i++;
            }

            j++;
        }

        System.out.println("Number of anagrams: " + ans);
    }

    // more complexity to compare elements of two map
    static void solution3(String s, String p) {
        int count = 0;
        int n = s.length();
        int k = p.length();

        // Create a frequency map for the pattern string
        Map<Character, Integer> pFreq = new HashMap<>();
        for (char c : p.toCharArray()) {
            pFreq.put(c, pFreq.getOrDefault(c, 0) + 1);
        }

        // Create a frequency map for the first window of the string
        Map<Character, Integer> sFreq = new HashMap<>();
        for (int i = 0; i < k; i++) {
            char c = s.charAt(i);
            sFreq.put(c, sFreq.getOrDefault(c, 0) + 1);
        }

        // Check if the first window is an anagram
        if (sFreq.equals(pFreq)) {
            count++;
        }

        // Slide the window and update the frequency map
        for (int i = k; i < n; i++) {
            char prevChar = s.charAt(i - k);
            char currChar = s.charAt(i);

            // Remove the previous character from the frequency map
            sFreq.put(prevChar, sFreq.get(prevChar) - 1);
            if (sFreq.get(prevChar) == 0) {
                sFreq.remove(prevChar);
            }

            // Add the current character to the frequency map
            sFreq.put(currChar, sFreq.getOrDefault(currChar, 0) + 1);

            // Check if the current window is an anagram
            if (sFreq.equals(pFreq)) {
                count++;
            }
        }

        System.out.println(count);
    }
}
