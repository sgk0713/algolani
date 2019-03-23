package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_15649 {
	int N, M;
	StringBuilder sb;
	public B_15649() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		dfs(0);
}catch (Exception e) {}
	}
	void dfs(int count) {
		if(count==M) {
			System.out.println(sb.toString());
			return;
		}else {
			for(int i =1;i<=N;i++) {
				sb.append(i+" ");
				dfs(count+1);
				sb.delete(sb.length()-2, sb.length());
			}
		}
	}
}
