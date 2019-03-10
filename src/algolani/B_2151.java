package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B_2151 {
	int N;
	char[][] map;
	boolean[][][] isChecked;
	int startR=-1, startC, endR, endC, answer;
	int[] dirR = {0, 1, 0, -1};
	int[] dirC = {1, 0, -1, 0};
	final int EAST = 0;
	final int SOUTH = 1;
	final int WEST = 2;
	final int NORTH = 3;
	PriorityQueue<Node_2151> q = new PriorityQueue<>();
	public B_2151() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		isChecked = new boolean[4][N][N];//거울에 대해서만 체크할 배열, 거울을 설치하는 경우에 지나왔던 곳인지체크, 설치하지않고 지나갔던 곳인지체크해줘야함
		answer = N*N+1;
		for(int i = 0;i<N;i++) {
			String str = br.readLine();
			for(int j = 0;j<N;j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '#') {
					if(startR == -1) {
						startR = i;
						startC = j;
					}else {
						endR = i;
						endC = j;
					}
				}
			}
		}
		for(int i= 0;i< 4;i++) {
			int rr = startR + dirR[i];
			int cc = startC + dirC[i];
			if(isInBoundery(rr, cc) && map[rr][cc] != '*') {
				q.offer(new Node_2151(startR, startC, i, 0));
				isChecked[0][startR][startC] = true;
			}
		}
		while(!q.isEmpty()) {
			Node_2151 temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			int dir = temp.dir;
			int mirror = temp.mirror;
			if(isEnd(r, c)) {//현재값이 도착하는 좌표면
				if(mirror < answer) {//대소 비교후 갱신화 하고 다음 좌표를 봄
					answer = mirror;
				}
				continue;
			}
			int limit = 3;//거울을 만날 경우 설치해서 좌우로 가거나,설치하지않고 그냥 가는 경우 3가지가있음
			if(map[r][c] != '!') {//설치할 수 없는 곳일 경우, 직진 밖에 못함
				limit = 1;
			}
			int tempDir = dir;
			for(int i = 0;i<limit;i++) {
				tempDir += i;//0을 더하면 직진이고, 1을 더 하면 90꺾은 방향, 2더하면 다른 90도 꺾은 방향
				tempDir%=4;//인덱스 초과방지용
				int rr = r + dirR[tempDir];//해당하는 방향으로 좌표 설정
				int cc = c + dirC[tempDir];
				
				if(mirror > answer) {//갱신된 답보다 많은 거울을 사용하고 있는 경우 볼필요없음
					break;
				}
				//가려는 좌표가 범위안에 있고, 벽이 아니며, 들린곳이 아니라면(가려는 좌표가 한번 들린 거울설치가능한 좌표라면 true일것이기 때문에 확인) 
				if(isInBoundery(rr, cc) && map[rr][cc] != '*' && isChecked[i][rr][cc] == false) {
					if(map[rr][cc] == '!' && i == limit-1) {//가려는 좌표가 거울 설치 가능한 좌표라면
						isChecked[dir][rr][cc] = true;//거울 설치 가능한 지역을 지나 왔다고 표시
						isChecked[(dir+2)%4][rr][cc] = true;
					}
					if(i == 0) {//땅이든 설치가능 장소가 i==0이면 직진
						q.offer(new Node_2151(rr, cc, tempDir, mirror));
					}else {//거울설치할경우
						q.offer(new Node_2151(rr, cc, tempDir, mirror+1));
					}
				}
			}
		}
		System.out.println(answer);
}catch (Exception e) {}
	}
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<N) {
			return true;
		}
		return false;
	}
	boolean isEnd(int r, int c) {
		if(r == endR && c == endC) {
			return true;
		}
		return false;
	}
	class Node_2151 implements Comparable<Node_2151>{
		int r, c, dir, mirror;
		public Node_2151(int r, int c, int dir, int mirror) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.mirror = mirror;
		}
		@Override
		public int compareTo(Node_2151 o) {
			return this.mirror - o.mirror;
		}
	}
}