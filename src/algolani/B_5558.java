package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_5558 {
	//치-즈
	//시작지점에서 bfs로 치즈를 찾다가 먹을 수 있는 치즈를 찾으면 큐를 비우고 그 위치에서 다시 bfs로 다음 치즈를 찾는 방식
	char[][] map = new char[1000][1000];
	final char START = 'S';
	final char OBSTACLE = 'X';
	int HP = 1, answer = 0, cheese = 0;
	int[] dirR = {0, 1, 0, -1};//동 남 서 북
	int[] dirC = {1, 0, -1, 0};//우 하 좌 상
	boolean[][] isChecked;
	Queue<Pair_5558> q = new LinkedList<>();
	public B_5558() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tempStr = br.readLine();
		StringTokenizer st = new StringTokenizer(tempStr, " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		cheese = Integer.parseInt(st.nextToken());
		isChecked = new boolean[R][C];
		for(int i= 0;i<R;i++) {
			tempStr = br.readLine();
			for(int j = 0;j<C;j++) {
				map[i][j] = tempStr.charAt(j);
				if(q.isEmpty() && map[i][j] == START) {//시작지점을 큐에 넣는다
					q.offer(new Pair_5558(i, j, 0));
					isChecked[i][j] = true;//해당 좌표를 들린것으로 표시
				}
			}
		}
		//입력 완료
		while(!q.isEmpty()) {
			Pair_5558 tPair = q.poll();
			for(int i= 0;i<4;i++) {//방향 설정
				int rr = tPair.r + dirR[i];
				int cc = tPair.c + dirC[i];
				int move = tPair.move;
				if(rr>=0 && rr < R && cc >=0 && cc < C//범위안에 있고
				&& map[rr][cc] != OBSTACLE//장애물이 아니고
				&& isChecked[rr][cc] == false) {//들리지않았으면
					isChecked[rr][cc] = true;//갈곳을 들렸다고 체크
					
					if(map[rr][cc] == ((HP)+'0')) {//HP초기값은 1, 알맞은경도의 치즈를 찾았을때
						q.clear();//일단 큐를 비우고
						if(HP == cheese) {//먹은 치즈와 총 치즈의 수와 맞는지 비교
							answer = move+1;//이동횟수답에 저장후
							break;//방향 설정 for문 탈출 후 while문 자동탈출
						}
						HP++;//아니면 먹어야할 치즈 경도증가
						q.offer(new Pair_5558(rr, cc, move+1));//해당 좌표를 큐에 갱신해주고
						isChecked = new boolean[R][C];//방문 맵 초기화
						break;//방향 설정 for문 탈출
					}else {//그냥 길일 때
						q.offer(new Pair_5558(rr, cc, move+1));//큐에 추가
					}
				}
			}
		}
		System.out.println(answer);//답 출력
}catch (Exception e) {}
	}
	class Pair_5558{
		int r, c, move;
		public Pair_5558(int r, int c, int move) {
			this.r = r;
			this.c = c;
			this.move = move;
		}
	}
}
