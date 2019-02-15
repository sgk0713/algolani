package algolani;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14620 {
	int N, answer = 3001;//지점당 최대 가격 * 5평수  * 3씨앗수 + 1
	final int SEED_NUM = 3;
	int[][] map = new int[10][10];//입력값받는 배열
	int[][] totalPriceMap = new int[10][10];//위치값에 드는 비용저장용 배열
	int[] dirR = {0, -1, 0, 1};
	int[] dirC = {-1, 0, 1, 0};
	B_14620() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
		
			String temp;
			StringTokenizer st;
			for(int i = 0;i< N;i++) {
				temp = br.readLine();
				st  = new StringTokenizer(temp, " ");
				for(int j = 0;j< N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 1; i< N-1;i++) {//좌표마다 지불해야되는 총 비용 계산, 테두리는 심을수없으므로 1부터 N-2까지 범위 설정
				for(int j = 1; j< N-1; j++) {
					totalPriceMap[i][j] = 
							map[i][j] // 현재 위치 비용
							+ map[i-1][j] // 북향 비용
							+ map[i+1][j] // 남향 비용
							+ map[i][j-1] // 서향 비용
							+ map[i][j+1];// 동향 비용
				}
			}
		} catch (IOException e) {}
		for(int i = 1; i< N-1;i++) {//테두리를 제외한 모든 경우의 수를 확인한다
			for(int j = 1; j< N-1; j++) {
				setMap(i,j,-1);
				dfs(i, j, 1, totalPriceMap[i][j]);//위치마다 조합들을 본다
				setMap(i,j,0);
			}
		}
		System.out.println(answer);
	}
	void dfs(int r, int c, int count, int sum) {
		if(sum >= answer) {
			return;
		}
		if(count == SEED_NUM) {
			if(answer > sum)
				answer = sum;
			return;
		}
		for(int i = 1; i < N-1;i++) {
			for(int j = 1;j < N-1;j++) {
				if(IsAvailable(i, j)) {
					setMap(i, j, -1);
					dfs(i, j, count+1, sum + totalPriceMap[i][j]);
					setMap(i, j, 0);
				}
				
			}
		}
	}
	
	boolean IsAvailable(int r, int c) {//심을 수 있는 곳인지 체크한다
		if(map[r][c] == -1)
			return false;
		for(int i = 0; i< dirR.length;i++) {
			if(map[r+dirR[i]][c+dirC[i]] == -1) {
				return false;
			}
		}
		return true;
	}
	void setMap(int r, int c, int value) {
		map[r][c] = value;
		for(int i = 0; i< dirR.length;i++) {
			map[r+dirR[i]][c+dirC[i]] = value;
		}
	}
}
