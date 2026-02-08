package Variable;

//1208. Get Equal Substrings Within Budget
class Get_Equal_Substrings_Within_Budget {
    public static void main(String[] args) {

    }


    public int equalSubstring(String s, String t, int maxCost) {
        int[] costs = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            costs[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int start = 0, e = 0;
        int cost = 0, ans = 0;
        while (e < costs.length) {
            cost += costs[e];
            if (cost > maxCost) {
                cost -= costs[start++];
            }
            ans = Math.max(ans, e - start + 1);
            e++;
        }
        return ans;
    }
}