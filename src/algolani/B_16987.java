package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_16987 {//20분컷
	int N;
	int duralbility[];
	int weight[];
	int answer;
	public B_16987() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		duralbility = new int[N];
		weight = new int[N];
		for(int i = 0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			duralbility[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}
		if(N == 1) {
			System.out.println(0);
		}else {
			dfs(0, 0);
			System.out.println(answer);
		}
}catch (Exception e) {e.printStackTrace();}
	}
	void dfs(int idx, int broken) {
		answer = Math.max(answer, broken);
		if(idx>=N || answer == N) {
			return;
		}
		if(duralbility[idx] <= 0) {
			dfs(idx+1, broken);
		}else {
			for(int i=0;i<N;i++) {
				if(i!=idx && duralbility[i] > 0) {
					if(duralbility[idx]>0 && duralbility[i] > 0) {
						int tempBroken = 0;
						duralbility[idx] -= weight[i];
						duralbility[i] -= weight[idx];
						if(duralbility[idx] <=0) {
							tempBroken++;
						}
						if(duralbility[i] <= 0) {
							tempBroken++;
						}
						dfs(idx+1, broken+tempBroken);
						duralbility[idx] += weight[i];
						duralbility[i] += weight[idx];
					}
				}
			}
		}
	}
}