package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_14226 {//이모티콘
	int N, answer = Integer.MAX_VALUE;
	public B_14226() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		System.out.println(dfs(1, -1, 0));
}catch (Exception e) {e.printStackTrace();}
	}
	int dfs(int curItem, int copy, int count) {
		if(count > answer) {
			return answer;
		}
		if(curItem == N) {
			return count;
		}else if(curItem > N){
			return count + curItem-N;
		}else {
			//복사해서 붙여넣기
			answer = Math.min(answer, dfs(curItem+curItem, curItem, count + 2));
		
			if(curItem-1 > 0) {//이모티콘 하나 삭제 하기
				answer = Math.min(answer, dfs(curItem-1, copy, count + 1));
			}
			if(copy > 0) {//붙여넣기
				answer = Math.min(answer, dfs(curItem+copy, copy, count + 1));
			}
		}
		return answer;
	}
}
