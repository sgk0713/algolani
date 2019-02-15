//SW_2112
package algolani;

import java.util.Arrays;
import java.util.Scanner;

public class SW_2112 {

	int D, W, K, ans;
	int[][] map = new int[13][20];
	
	public SW_2112() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int number = 1;
		while(T-- > 0) {
			ans = 9999;
			for(int i = 0; i< 13;i++) {
				Arrays.fill(map[i], -1);
			}
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			int c=0;
			for(int i= 0; i < D; i++) {
				for(int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			dfs(0, 0);
			System.out.println("#"+number++ + " " + ans);
		}
	}
	
	void dfs(int d, int count) {
		if(count >= ans) {
			return;
		}
		if(d >= D) {
			check(count);
			return;
		}
		
		dfs(d+1, count);
		int[] temp = new int[20];
		
		for(int i = 0; i<W; i++) {
			temp[i] = map[d][i];
		}
		
		Arrays.fill(map[d], 0);
		dfs(d+1, count+1);
		
		
		Arrays.fill(map[d], 1);
		dfs(d+1, count+1);
		
		for(int i = 0; i<W; i++) {
			map[d][i] = temp[i];
		}
	}
	
	void check(int count) {
		int tmpK;
		for(int i = 0; i< W;i++) {
			tmpK = 1;
			for(int j = 0;j<D-1;j++) {
				if(tmpK >= K) {
					break;
				}
				if(map[j][i] == map[j+1][i]) {
					tmpK++;
				}else {
					tmpK = 1;
				}
			}
			if(tmpK < K) {
				return;
			}
		}
		if(count < ans) {
			ans = count;
		}
	}
	
}
