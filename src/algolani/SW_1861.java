package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1861 {
	//정사각형 방 김선국
	int longest, N;
	int[][] arr;
	boolean[][] isChecked;
	int[] dirR = {-1,1,0,0};
	int[] dirC = {0,0,-1,1};
	SW_1861(){try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		String tempStr;
		for(int t = 1; t<= T; t++) {
			int answer1 = 10001 , answer2 = 0;//초기화
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			isChecked = new boolean[N][N];
			for(int i = 0; i< N;i++) {
				tempStr = br.readLine();
				st = new StringTokenizer(tempStr, " ");
				for(int j = 0; j< N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//입력 완료 ---
			
			
			int tempAns = 0;//현재값에서 가장 먼거리를 임시 저장하는 변수
			for(int i = 0; i< N;i++) {
				for(int j = 0; j< N; j++) {
					if((tempAns = dfs(i, j, 1)) > answer2) {//기존의 거리보다 클 경우, answer2는 가장 긴 거리를 나타냄
						answer1 = arr[i][j];//방번호 갱신
						answer2 = tempAns;//거리 갱신
					}else if(tempAns == answer2) {//기존의 거리와 같을 경우
						answer1 = Math.min(answer1, arr[i][j]);//기존의 방번호보다 작은지 검사
					}
					
				}
			}
			
			System.out.println("#"+t + " " + answer1 + " " + answer2);
		}
		
	}catch (Exception e) {
	}}
	int dfs(int r, int c, int count) {
		int rr, cc;
		int ans = count;
		isChecked[r][c] = true;
		for(int i= 0 ; i< 4;i++) {
			rr = r + dirR[i];//방향갱신
			cc = c + dirC[i];
			if(rr>=0 && rr < N && cc >= 0 && cc < N//범위안이면
			&& arr[rr][cc] - 1 == arr[r][c]//1크면
			&& isChecked[rr][cc] == false) {//들리지않았으면
				isChecked[rr][cc] = true;
				ans = Math.max(ans, dfs(rr, cc, count+1));//거리 갱신 
				isChecked[rr][cc] = false;
			}
		}
		isChecked[r][c] = false;
		return ans;//거리 리턴
	}
}
