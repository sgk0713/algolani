package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16441 {
	//아기돼지와 늑대
	//bfs로 동시다발적으로 늑대의 위치에서 갈 수 있는 곳을 탐색한다
	//ICE를 밟을 경우 아이스길의 끝을 Q에 넣어주며
	//잔디를 밟을 경우 방문한지 체크하고 Q에 넣어준다
	//모든 잔디를 방문할때마다 safetyZone 배열에 false를 넣어주어
	//출력시에safetyZone 배열이 true인 것은 P로 출력하고 나머지 맵을 출력한다
	int R, C;
	char[][] map;
	boolean[][] safetyZone;
	boolean[][] isChecked;
	final char WALL = '#';
	final char GRASS = '.';
	final char ICE = '+';
	int[] dirR = {0, 1, 0, -1};//동 북 서 남
	int[] dirC = {1, 0, -1, 0};//0 1  2  3 번 인덱스
	Queue<Pair_16441> q = new LinkedList<>();
	public B_16441() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tempStr = br.readLine();
		StringTokenizer st = new StringTokenizer(tempStr, " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		safetyZone = new boolean[R][C];
		isChecked = new boolean[R][C];
		
		for(int i= 0;i<R;i++) {
			tempStr = br.readLine();
			for(int j = 0;j<C;j++) {
				map[i][j] = tempStr.charAt(j);
				if(map[i][j] == 'W') {//늑대의 위치를 큐에 담아둔다.
					q.offer(new Pair_16441(i, j));
				}else if(map[i][j] == GRASS) {//잔디인것은 일단 safetyzone으로 표시해둔다
					safetyZone[i][j] = true;
				}
			}
		}
		
		while(!q.isEmpty()) {//bfs시작
			Pair_16441 tempPair = q.poll();
			int r = tempPair.r;
			int c = tempPair.c;
			
			for(int i = 0;i<4;i++) {//0~3 번 인덱스 동 남 서 북 순서
				int rr = r + dirR[i];
				int cc = c + dirC[i];
				
				while(map[rr][cc] == ICE) {//방문할 위치가 아이스라면(잔디면 while문끝)
					rr += dirR[i];//같은 방향으로 한칸 더 간다
					cc += dirC[i];
					if(map[rr][cc] == WALL) {//옮겼는데 만약 벽이라면
						rr += dirR[(i+2)%4];//그 전 위치로 옮겨주고while문 아웃
						cc += dirC[(i+2)%4];
						break;
					}
				}
				
				if(map[rr][cc] != WALL//방문할 위치가벽이 아니고
				&& isChecked[rr][cc] == false) {//방문했던 위치가 아니라면
					isChecked[rr][cc] = true;//방문했다고 표시
					safetyZone[rr][cc] = false;//더이상 안전한 위치가 아니라고표시
					q.offer(new Pair_16441(rr, cc));//큐에 삽입
				}
			}//BFS while문 끝
		}
		//답안 출력
		for(int i = 0;i<R;i++) {
			for(int j = 0;j<C;j++) {
				if(safetyZone[i][j] == true) {//세이프티존이면
					System.out.print('P');//P출력
				}else {//그외엔 맵의 값 출력
					System.out.print(map[i][j]);
				}
			}
			System.out.println();
		}
}catch (Exception e) {e.printStackTrace();}	
	}
	class Pair_16441{
		int r, c;
		public Pair_16441(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
