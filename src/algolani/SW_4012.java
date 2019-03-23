package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4012 {
	int N, answer;
	int[][] map;
	int[][] dp;
	public SW_4012() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			answer = 9999;
			N = Integer.parseInt(br.readLine());//N is even number
			map = new int[N][N];
			dp = new int[N][N];
			for(int i= 0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[j][i] != 0 && map[i][j] != 0) {
						dp[i][j] = dp[j][i] = map[i][j] + map[j][i];
					}
				}
			}
			dfs(0, 0, 0);
			System.out.println("#"+t+" "+ answer);
		}
}catch (Exception e) {}
	}
	boolean dfs(int count, int bit, int idx) {
		for(int i= idx;i<N;i++) {
			if((bit&1<<i) == 0) {
				if(count+1==(N/2)) {
					int temp = getAnswer(bit|1<<i);
					if(temp<answer) {
						answer = temp;
						if(answer == 0) {
							return true;
						}
					}
				}else if(dfs(count+1, bit|1<<i, i+1)) {
					return true;
				}
			}
		}
		return false;
	}
	int getAnswer(int bit) {
		int temp1 = 0, temp2 = 0;
		for(int i = 0;i<N-1;i++) {
			if((bit&(1<<i)) != 0) {
				for(int j=i+1;j<N;j++) {
					if((bit&(1<<j)) != 0) {
						temp1 += dp[i][j];
					}
				}
			}else {
				for(int j=i+1;j<N;j++) {
					if((bit&(1<<j)) == 0) {
						temp2 += dp[i][j];
					}
				}
			}
		}
		return Math.abs(temp1-temp2);
	}
}
