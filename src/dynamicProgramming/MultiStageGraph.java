package dynamicProgramming;

public class MultiStageGraph {
	
	public static void main(String[] args) {
		int stage = 4;
		int n = 8;
		int min = Integer.MAX_VALUE;
		int cost[] = new int[9];
		int d[] = new int[9];
		int path[] = new int[9];
		int c[][]=  {{0,0,0,0,0,0,0,0,0},
					{0,0,2,1,3,0,0,0,0},
					{0,0,0,0,0,2,3,0,0},
					{0,0,0,0,0,6,7,0,0},
					{0,0,0,0,0,0,8,9,0},
					{0,0,0,0,0,0,0,0,6},
					{0,0,0,0,0,0,0,0,4},
					{0,0,0,0,0,0,0,0,5}};
		
		for (int i = 0; i < 9; i++) {
			cost[i] = 0;
			d[i] = 0;
			path[i] = 0;

		}
		for (int i = n - 1; i >= 1; i--) {
			min = Integer.MAX_VALUE;
			for (int k = i + 1; k <= n; k++) {
				if (c[i][k] != 0 && (c[i][k] + cost[k]) < min) {
					min = c[i][k] + cost[k];
					d[i] = k;
				}
			}
			cost[i] = min;
		}
		path[1] = 1;
		path[stage] = n;
		for (int i = 2; i < stage; i++) {
			path[i] = d[path[i - 1]];
		}
		for (int i = stage; i > 0; i--) {
			System.out.println(path[i]);
		}

	}

}
