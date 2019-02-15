package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_2117 {
	//홈 방범 서비스
	int M, N, maxK, answer = 0, totNum;
	int[][] map = new int[20][20];
	boolean[][] isChecked = new boolean[20][20];
	int[] dirR = {-1, 1, 0, 0};//방향 설정
	int[] dirC = {0, 0, -1, 1};
	Queue<Pair_2117> q = new LinkedList<>(); 
	SW_2117(){try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		String tempStr = null;
		StringTokenizer st;
		Pair_2117 tp;
		int rr, cc;
		for(int t = 1; t <= T; t++) {
			totNum = answer = 0;
			tempStr = br.readLine();
			st = new StringTokenizer(tempStr, " ");
			N = Integer.parseInt(st.nextToken());//맵의 크기
			M = Integer.parseInt(st.nextToken());//지불 할 수 있는 비용
			maxK = N*2 - 1;//전체맵을 커버할 수 있는 최대K값
			for(int i = 0; i< N;i++) {
				tempStr = br.readLine();
				st = new StringTokenizer(tempStr, " ");
				for(int j = 0; j< N; j++) {
					totNum += map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			if(M*totNum -getPrice(N)>=0) {
				System.out.println("#"+t+" "+totNum);
				continue;
			}
			int tempAnswer;
			for(int i = 0; i< N;i++) {
				for(int j = 0; j< N; j++) {
					q.clear();
					for(int k = 0; k< N; k++) {
						Arrays.fill(isChecked[k], false);
					}
					
					q.offer(new Pair_2117(i, j));
					isChecked[i][j] = true;
					tempAnswer = map[i][j];
					int tempK = 1;
					while(!q.isEmpty()) {
						int qSize = q.size();
						if(M*tempAnswer - getPrice(tempK) >= 0) {
							answer = Math.max(tempAnswer, answer);
						}
						tempK++;
						if(tempK > maxK) {
							break;
						}
						for(int r = 0; r < qSize; r++) {
							tp = q.poll();
							for(int k = 0; k< 4; k++) {
								rr = tp.r + dirR[k];
								cc = tp.c + dirC[k];
								if(rr >= 0 && rr < N && cc >= 0 && cc < N//지도 범위 안에 있고
										&& Math.abs(i-rr)+Math.abs(j-cc) < tempK //현재 번위안에 있고
										&& isChecked[rr][cc] == false) {//들리지않은곳이면
									isChecked[rr][cc] = true;//들렸다고 체크
									tempAnswer += map[rr][cc];//범위내 가구수 카운트
									q.offer(new Pair_2117(rr, cc));
								}
							}
							
						}
					}
				}
			}
			System.out.println("#"+t+" "+answer);
		}
	}catch (Exception e) {
	}}
	int getPrice(int K) {
		return K*K + (K-1)*(K-1); 
	}
	class Pair_2117{
		int r, c;
		Pair_2117(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}
