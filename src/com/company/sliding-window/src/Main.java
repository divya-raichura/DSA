import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1,2, 1};
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        permutations(arr, new boolean[arr.length], new ArrayList<>(), ans);
        System.out.println(ans);
        System.out.println(ans.size());
    }

    // this method uses extra space
    static void permutations(int[] arr, boolean[] visited, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> ans) {
        if (list.size() == arr.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(arr[i]);
                permutations(arr, visited, list, ans);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}