package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2798 {
	int N,M,answer;
	int[] arr;
	public B_2798() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i= 0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i= 0;i<=N-3;i++) {
			dfs(i, 1, arr[i]);
		}
		System.out.println(answer);
}catch (Exception e) {}
	}
	void dfs(int idx, int count, int sum) {
		if(answer == M || sum > M) {
			return;
		}
		if(count==3) {
			if(answer < sum) {
				answer = sum;
			}
			return;
		}
		for(int i=idx+1;i<N;i++) {
			dfs(i,count+1, sum+arr[i]);
		}
	}
}
