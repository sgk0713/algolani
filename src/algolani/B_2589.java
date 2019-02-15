package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2589 {
	//보물섬
	
	int R, C, tempTime=0, answer=0;
	int[] dirR = {0, 0, 1, -1};
	int[] dirC = {-1, 1, 0, 0};
	char[][] map = new char[50][50];
	boolean[][] isChecked = new boolean[50][50];
	Queue<Pair_2589> q = new LinkedList<>();
	
	B_2589(){try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tempStr = br.readLine();
		StringTokenizer st = new StringTokenizer(tempStr, " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		for(int i = 0; i< R; i++) {
			tempStr = br.readLine();
			for(int j = 0; j< C; j++) {
				map[i][j] = tempStr.charAt(j);
			}			
		}
		for(int i = 0; i< R; i++) {//각 육지마다 최단경로를 구한다.
			for(int j = 0;j < C;j++) {
				
				if(map[i][j] == 'L') {
					tempTime = 0;//초기화
					q.offer(new Pair_2589(i, j, 0));
					isChecked[i][j] = true;//방문 표시
					while(!q.isEmpty()) {//BFS
						Pair_2589 tempPair = q.poll();
						int tempR = tempPair.r;
						int tempC = tempPair.c;
						tempTime = Math.max(tempTime,  tempPair.count);//현재 위치에서 끝까지 가는 횟수 저장
						for(int k = 0; k< 4;k++) {//4방향 갱신 및 확인
							tempR = tempPair.r + dirR[k];
							tempC = tempPair.c + dirC[k];
							
							if(tempR >= 0 && tempR < R && tempC >=0 && tempC < C//범위안에 있고
							&& map[tempR][tempC] == 'L'//육지이고
							&& isChecked[tempR][tempC] == false) {//방문하지 않은 곳이라면
								isChecked[tempR][tempC] = true;//방문표시
								q.offer(new Pair_2589(tempR, tempC, tempPair.count+1));//이동횟수 증가하여 큐에 삽입
							}
						}
					}
					for(int k = 0; k < R;k++) {//방문 배열 초기화
						Arrays.fill(isChecked[k], false);
					}
					answer = Math.max(answer, tempTime);//가장 먼 최단경로로 답안 갱신
				}
				
			}
		}
		
		System.out.println(answer);
	}catch (Exception e) {}}
}
class Pair_2589{
	int r, c, count;
	public Pair_2589(int r, int c, int count) {
		this.r = r;
		this.c = c;
		this.count = count;
	}
}