package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class SW_2115 {
	int N, M, C, answer;
	int[][] pick = new int[2][5];
	int[][] map = new int[10][10];
	boolean[][] isChecked = new boolean[10][10];
	public SW_2115() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1 ;t<=T;t++) {
			for(int i= 0;i<10;i++) {
				Arrays.fill(isChecked[i], false);
			}
			Arrays.fill(pick[0], 0);
			Arrays.fill(pick[1], 0);
			StringTokenizer st = new StringTokenizer(br.readLine());
			answer = 0;
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			for(int i= 0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0, 0, 0, 0);
			System.out.println("#"+t+" " + answer);
		}
}catch (Exception e) {}
	}
	void dfs(int r, int c, int count, int num) {
		if(count==2) {
			answer = Math.max(num, answer);
		}
		int idx = 0, i;
		if(c+M-1 < N){
			for(i= c;i<M;i++) {
				pick[count][idx++] = map[r][i];
			}
			Arrays.sort(pick[count]);
			int temp = 0;
			for(int j = 4;j>=5-M;j--) {
				temp += pick[count][j] * pick[count][j]; 
			}
			dfs()
		}else if(r+1 < N){
			for(i= 0;i<M;i++) {
				pick[count][idx++] = map[r+1][i];
			}
			Arrays.sort(pick[count]);
		}else {
			return;
		}
		
		
	}
	
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<N) {
			return true;
		}return false;
	}
}
