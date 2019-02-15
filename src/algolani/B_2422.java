package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2422 {
	int N, M, answer;
	final int LIMIT = 3;
	boolean[][] forbid = new boolean[201][201]; //금지된 조합
	public B_2422() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int temp1 = 0;
		int temp2 = 0;
		for(int i = 0;i<M;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			temp1 = Integer.parseInt(st.nextToken());
			temp2 = Integer.parseInt(st.nextToken());
			forbid[temp1][temp2] = true;//금지된 조합
			forbid[temp2][temp1] = true;//입력
		}
		for(int i= 0;i<=N-LIMIT;i++) {//0(+1)부터 N-3(+1)까지
			find(i+1, i+1, 1);
		}
		System.out.println(answer);
}catch (Exception e) {}
	}
	void find(int num1, int curNum, int count) {
		if(count == LIMIT) {
			answer++;
			return;
		}else {
			for(int i = curNum+1;i<=N;i++) {//현재값+1 부터 N까지
				if(!forbid[num1][i] && !forbid[curNum][i]) {//금지된 조합이 아니라면
					find(num1, i, count+1);//첫번째값은 고정, 다음값 넣고, 숫자개수 하나 증가해줌
				}
			}
		}
	}
}
