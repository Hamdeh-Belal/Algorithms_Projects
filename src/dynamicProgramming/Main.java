package dynamicProgramming;

public class Main {
	// DP function to calculate the minimum cost to reach the destination city `n`
	// from the source city 0
	public static int findMinCost(int[][] cost) {
		// base case
		if (cost == null || cost.length == 0) {
			return 0;
		}

		// `N × N` matrix
		int N = cost.length;

		// `lookup[i]` stores the minimum cost to reach city `i` from city 0
		int[] lookup = new int[N];

		// Initialize `lookup[]` with the direct ticket price from the source city
		for (int i = 0; i < N; i++) {
			lookup[i] = cost[0][i];
		}

		// repeat loop till `lookup[]` is filled with all minimum values
		boolean isFilled = false;
		while (!isFilled) {
			isFilled = true;
			// fill `lookup[]` in a bottom-up manner
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (lookup[i] > lookup[j] + cost[j][i]) {
						lookup[i] = lookup[j] + cost[j][i];
						isFilled = false;
					}
				}
			}
		}

		// return the minimum cost to reach city `N-1` from city 0
		return lookup[N - 1];
	}

	public static void main(String[] args) {
		int[][] cost = { { 0, 25, 20, 10, 105 },
						 { 20, 0, 15, 80, 80 }, 
						 { 30, 15, 0, 70, 90 }, 
						 { 10, 10, 50, 0, 100 },
						 { 40, 50, 5, 10, 0 } };

		System.out.print("The minimum cost is " + findMinCost(cost));
	}
}