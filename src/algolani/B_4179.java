package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_4179 {
	int[][] map;
	int answer = 10000000, R, C;
	final int J = 1;
	final int FIRE = -1;
	final int WALL = -1;
	int[] dirR = {0, 1, 0, -1};
	int[] dirC = {1, 0, -1, 0};
	Queue<Coord> jQueue = new LinkedList<>();//J를 위한 bfs 큐
	Queue<Coord> fQueue = new LinkedList<>();//FIRE를 위한 bfs큐
	public B_4179() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		boolean isBlocked = true;
		
		for(int i = 0;i<R;i++) {
			String tempStr = br.readLine();
			for(int j = 0;j<C;j++) {
				char temp = tempStr.charAt(j);
				if(temp == '#') {
					map[i][j] = WALL;
				}else if(temp == 'J') {
					map[i][j] = J;
					jQueue.offer(new Coord(i, j));
				}else if(temp == 'F') {
					map[i][j] = FIRE;
					fQueue.offer(new Coord(i, j));
				}
				if((i == 0 || i == R-1 || j == 0 || j == C-1)//edge에 길이 있는지 체크
				&& (temp == '.' || temp == 'J')) {
					isBlocked = false;
					if(temp == 'J') {//edge에 J가 있으면 답은 1
						jQueue.clear();
						fQueue.clear();
						i = R+2;
						j = C+2;
						answer = 1;
						break;
					}
				}
				
			}
		}
		if(isBlocked == true) {//edge에 길이 없으면 절대 탈출 불가
			jQueue.clear();
			fQueue.clear();
			System.out.println("IMPOSSIBLE");
		}
		while(!jQueue.isEmpty()) {//J의 이동거리로 맵을 채운다
			Coord tempCoord = jQueue.poll();
			for(int i = 0;i<4;i++) {
				int rr = tempCoord.r + dirR[i];
				int cc = tempCoord.c + dirC[i];
				int curValue = map[tempCoord.r][tempCoord.c];
				if(rr>=0 && rr <R && cc >=0 && cc<C
				&& map[rr][cc] != WALL
				&& map[rr][cc] != FIRE
				&& map[rr][cc] == 0) {
					map[rr][cc] = curValue+1;
					jQueue.offer(new Coord(rr, cc));
				}
			}
		}
		int count=1;
		while(!fQueue.isEmpty()) {//불의 확산 거리를 맵을 채운다
			int qSize = fQueue.size();//각 불에 대해 동일하게 값을 주어 확산 시킨다
			for(int k = 0; k < qSize; k++) {
				Coord tempCoord = fQueue.poll();
				for(int i = 0;i<4;i++) {
					int rr = tempCoord.r + dirR[i];
					int cc = tempCoord.c + dirC[i];
					if(rr>=0 && rr <R && cc >=0 && cc<C
					&& map[rr][cc] != WALL
					&& map[rr][cc] >= (count+1)) {//다음칸(J가 이동한 거리)가 불이 갈 거리보다 크거나 같으면 J가 못갔을 길
						map[rr][cc] = 0;//0으로 만들어주어 J의 이동거리를 무산 시킨다
						fQueue.offer(new Coord(rr, cc));
					}
				}
//				showMap();
			}
			count++;//다음 이동거리를 위해 증가
		}
		
		if(isBlocked == false) {//막힌 맵이 아니라면
			findAnswer();//답을 구함
			if(answer == 10000000) {
				System.out.println("IMPOSSIBLE");
			}else {
				System.out.println(answer);
			}
		}
}catch (Exception e) {}	
	}
	class Coord {
		int r, c;
		public Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	void findAnswer() {
		if(answer == 1) {//edge에 J가 있을 경우 답은 1로 정해짐
			return;
		}
		for(int i = 0;i<R;i++) {//가장자리에 양수의 최소값을 구한다
			for(int j = 0;j<C;j++) {
				if((i == 0 || i == R-1 || j == 0 || j == C-1)
				&& map[i][j] > 0){
					answer = Math.min(answer, map[i][j]);
				}
			}
		}
	}
	void showMap() {//디버깅용
		for(int i = 0;i<map.length;i++) {
			for(int j = 0;j<map[0].length;j++) {
				if(map[i][j] == WALL) {
					System.out.print("-");
				}else {
				System.out.print(map[i][j]);
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
