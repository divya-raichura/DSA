package Variable;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStrAllUnique {
    public static void main(String[] args) {
        solution1("abcabcbb");
        solution2("abcabcbb");
    }

    static void solution1(String str) {
        char[] arr = str.toCharArray();
        int i = 0, j = 0, ans = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (j < arr.length) {
            map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);

            while (map.size() < j - i + 1) { // note: k unique character me > tha, idar < hoga
                // don't blindly copy-paste
                int oldValue = map.get(arr[i]);
                int newValue = oldValue - 1;

                if (newValue == 0) {
                    map.remove(arr[i]);
                } else {
                    map.put(arr[i], newValue);
                }

                i++;
            }

            if (map.size() == j - i + 1) {
                ans = Math.max(ans, j - i + 1);
            }

            j++;
        }

        System.out.println(ans);
    }

    // my
    static void solution2(String s) {
        int i = 0, j = 0, ans = 0;
        char[] arr = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();

        while (j < arr.length) {

            map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);

            if (map.get(arr[j]) > 1) {
                while (map.get(arr[j]) > 1) {
                    if (arr[i] == arr[j]) {
                        int oldValue = map.get(arr[i]);
                        int newValue = oldValue - 1;

                        map.put(arr[i], newValue);
                    } else {
                        map.remove(arr[i]);
                    }
                    i++;
                }
            }

            ans = Math.max(map.size(), ans);
            j++;
        }

        System.out.println(ans);
    }
}
