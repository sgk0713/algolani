package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_3109 {
	int R, C;
	boolean[][] map;
	boolean[][] isChecked;
	int answer;
	int[] dirR = {-1,0,1};//위,가만,아래
	public B_3109() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];
		isChecked = new boolean[R][C];
		for(int i = 0;i<R;i++) {
			String str = br.readLine();
			for(int j = 0;j<C;j++) {
				if(str.charAt(j) == '.') {
					map[i][j] = true;
				}
			}
		}

		for(int i = 0;i<R;i++) {
			if(map[i][1] == true) {//길이있는 곳이라면
				findPipe(i, 1);
			}
		}
		System.out.println(answer);
}catch (Exception e) {}
	}
	boolean findPipe(int r, int c) {
		if(c == C-2) {
			answer++;//끝전에 도달했다면 답 증가
			return true;
		}
		for(int i =0;i<3;i++) {//위 그대로 아래 순으로 순회한다, 순서 중요
			int rr = r+dirR[i];//행값 갱신
			if(rr >= 0 && rr<R //행이 범위안이고
					&& isChecked[rr][c+1] == false //다음 갈곳이 들리지않은 곳이고
					&& map[rr][c+1] == true) {//갈수있는 좌표라면
				isChecked[rr][c+1] = true;//갔다고 표시하고
				//**이걸 아래에서 false로 다시 복구하지않는 이유는 
				//갔다 되돌아온다는 뜻은 길이 없다는 뜻이기때문에
				//다음 값이 또 들리게 되면 어차피 못가는길을 가게 되는 것
				if(findPipe(rr, c+1)) {//진입
					return true;
				}
			}
		}
		return false;
	}
}
