package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_11567 {//선진이의 겨울 왕국
	int R, C, sR, sC, eR,eC;
	boolean[][] map;
	boolean[][] isChecked;
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	boolean isPossible = false, isBroken = false;
	Queue<Node_11567> q = new LinkedList<>();
	public B_11567() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];
		isChecked = new boolean[R][C];
		for(int i= 0;i<R;i++) {
			String str = br.readLine();
			for(int j= 0;j<C;j++) {
				if(str.charAt(j)=='.') {
					map[i][j] = true;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		sR = Integer.parseInt(st.nextToken())-1;//zero-base 좌표를 위해 1감소
		sC = Integer.parseInt(st.nextToken())-1;
		st = new StringTokenizer(br.readLine());
		eR = Integer.parseInt(st.nextToken())-1;
		eC = Integer.parseInt(st.nextToken())-1;
		if(map[eR][eC] == false) {//도착지점이 손상된 상태인지 확인, 손상된 상태라면 도착과 동시에 탈출
			isBroken = true;
		}else {
			isBroken = false;
		}
		map[eR][eC] = map[sR][sC] = true;//시작과 도착지점을 갈수있게 만들어준다.
		findRoot();//답찾기
		if(isPossible) {//답출력
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
}catch (Exception e) {}
	}
	class Node_11567{
		int r, c;
		public Node_11567(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	void findRoot() {
		q.offer(new Node_11567(sR, sC));//출발지 넣고 출발
		isChecked[sR][sC] = true;//출발지 방문 체크
		while(!q.isEmpty()) {
			Node_11567 temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			for(int i = 0; i < 4; i++) {//4방향 이동
				int rr = r + dirR[i];
				int cc = c + dirC[i];
				if(isInBoundery(rr, cc) && map[rr][cc] == true) {//갈수있는곳이면
					if(rr == eR && cc == eC) {//갈수있는곳이 도착지점이라면
						isPossible = true;//갈수있다고 flag 표시
						if(!isBroken) {//도착지점이 손상된 상태가 아니라면, 인접한곳을 갔다가 와야됨
							isPossible = false;//초기화
							if(sR==eR && sC==eC) {//출발지와 도착지가 같은 지점이었다면
								isPossible = true;
							}else {//그외
								for(int k= 0;k<4;k++) {//도착지의 4방향 체크
									int tr = eR + dirR[k];
									int tc = eC + dirC[k];
									if(isInBoundery(tr, tc) && map[tr][tc] == true //갈수있는 곳이고
											&& !(tr== r && tc==c)) {//도착지로 온 길이 아니라면
										isPossible = true;//갱신후끝냄
										break;
									}
								}
							}
						}
						q.clear();//큐비우고 for문나감
						break;
					}
					if(isChecked[rr][cc] == false) {//방문한 곳이 아니라면, 방문체크 후 큐에 삽입
						isChecked[rr][cc] = true;
						q.offer(new Node_11567(rr, cc));
					}
				}
			}
		}
	}
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<R && c>=0 && c<C) {
			return true;
		}return false;
	}
}
