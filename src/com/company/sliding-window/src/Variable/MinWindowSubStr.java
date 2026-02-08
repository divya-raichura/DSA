package Variable;

import java.util.HashMap;

public class MinWindowSubStr {

    /*
     *
     *
     * https://leetcode.com/problems/minimum-window-substring/description/
     *
     * https://www.youtube.com/watch?v=iwv1llyN6mo&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=13
     *
     * str and ptr given
     * we have to find min substr in str such it contains all letters of ptr
     * now that letters can be shuffled and non-contiguous inside str
     * and all the letters in ptr should be in str, if a letter of ptr appears in str more than once
     * then it is ok, but it should appear atleast once
     * eg:
     * str = tcxascoaottca
     * ptr = toc
     *
     * ottc is the ans
     *
     *
     * also note:
     * if ptr = ttoc
     * then in str substring too, 't' should appear twice
     *
     * */

    public static void main(String[] args) {
        String s = "tcxascoaottca";
        String p = "ttoc";

        System.out.println(solution1(s, p));
        System.out.println(solution1("a", "aa"));
        System.out.println(solution1("a", "a"));
    }

    // cleaner code
    static String solution(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int i = 0, j = 0, count = map.size();
        int minStart = 0, minLength = Integer.MAX_VALUE;

        while (j < s.length()) {
            char ch = s.charAt(j);
            if (map.containsKey(ch)) {
                int freq = map.get(ch);
                map.put(ch, --freq);
                if (freq == 0) {
                    count--;
                }
            }

            while (count == 0) {
                int currLength = j - i + 1;
                if (currLength < minLength) {
                    minStart = i;
                    minLength = currLength;
                }

                ch = s.charAt(i);
                if (map.containsKey(ch)) {
                    int freq = map.get(ch);
                    map.put(ch, ++freq);
                    if (freq == 1) {
                        count++;
                    }
                }
                i++;
            }

            j++;
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);
    }

    // my (correct)
    static String solution1(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int i = 0, j = 0, ans = Integer.MAX_VALUE;
        int count = map.size();
        char[] arr = s.toCharArray();
        int si = 0;
        int ei = 0;

        while (j < arr.length) {
            if (map.containsKey(s.charAt(j))) {
                map.put(s.charAt(j), map.get(s.charAt(j)) - 1);

                if (map.get(s.charAt(j)) == 0) {
                    count--;
                }
            }

            while (count == 0) {
//                ans = Math.min(ans, j - i + 1);
                if (j - i + 1 < ans) {
                    si = i;
                    ei = j;
                }

                ans = Math.min(ans, j - i + 1);

                do {
                    if (map.containsKey(arr[i])) {
                        map.put(s.charAt(i), map.get(s.charAt(i)) + 1);

                        if (map.get(s.charAt(i)) == 1) {
                            count++;
                        }
                    }
                    i++;
                } while (i < arr.length && !map.containsKey(arr[i]));
            }

            j++;
        }

        System.out.println(ans);

        StringBuilder str = new StringBuilder();

        if (ans != Integer.MAX_VALUE) { // "a","aa"
            for (int k = si; k <= ei; k++) {
                str.append(s.charAt(k));
            }
        }

//        System.out.println(str);
        return str.toString();
    }
}
