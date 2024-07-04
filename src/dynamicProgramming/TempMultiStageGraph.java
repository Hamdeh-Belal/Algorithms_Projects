package dynamicProgramming;

public class TempMultiStageGraph {
	public static void main(String[] args) {
		int stage = 6;
		int n = 14;
		int min = Integer.MAX_VALUE;
		int cost[] = new int[15];
		int d[] = new int[15];
		int path[] = new int[15];
		//A=1 B=2 C=3 D=4 E=5 F=6 I=7 J=8 K=9 L=10
				//      A B C  D E F G H I J K L
		int c[][]= {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,22,8,12,0,0,0,0,0,0,0,0,0,0,0},//START
				
				/**/{0,0,0,0,8,10,0,0,0,0,0,0,0,0,0},//A
				
				/**/{0,0,0,0,25,10,0,0,0,0,0,0,0,0,0},//B
				
					{0,0,0,0,13,13,0,0,0,0,0,0,0,0,0},//C
					
					{0,0,0,0,0,0,25,30,18,27,0,0,0,0,0},//D
					
					{0,0,0,0,0,0,12,10,8,7,0,0,0,0,0},//E
					
					{0,0,0,0,0,0,0,0,0,0,28,13,15,0,0},//F
					
					{0,0,0,0,0,0,0,0,0,0,8,10,10,0,0},//G
					
					{0,0,0,0,0,0,0,0,0,0,20,10,10,0,0},//H
					
					{0,0,0,0,0,0,0,0,0,0,15,10,7,0,0},//I
					
					{0,0,0,0,0,0,0,0,0,0,0,0,0,10,0},//J
					
					{0,0,0,0,0,0,0,0,0,0,0,0,0,10,0},//K
					
					{0,0,0,0,0,0,0,0,0,0,0,0,0,10,0} //L
		};
		int hotel[]={0,70,80,80,50,70,50,70,70,60,50,70,60,0};
		String names[]={"Strart","A","B","C","D","E","F","I"+"J","K","L","End"};
		for (int i = 0; i < 9; i++) {
			cost[i] = 0;
			d[i] = 0;
			path[i] = 0;

		}
		for (int i = n - 1; i >= 1; i--) {
			min = Integer.MAX_VALUE;
			for (int k = i + 1; k <= n; k++) {
				if (c[i][k] != 0 && (c[i][k] + cost[k]+hotel[n-i]) < min) {
					min = c[i][k] + cost[k]+hotel[n-i];
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
			System.out.print(path[i]+names[i]+"->");
		}

	}

}
