package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_13903 {//출근 34:56
	int R, C, N, answer = -1;
	boolean[][] map;
	boolean[][] isChecked;
	int[] dirR;
	int[] dirC;
	PriorityQueue<Node_13903> pq = new PriorityQueue<>();
	public B_13903(){
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];
		isChecked = new boolean[R][C];
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				if(st.nextToken().equals("1")) {//세로 블럭
					map[i][j] = true;
					if(i == 0) {
						pq.offer(new Node_13903(i, j, 0));
						isChecked[i][j] = true;
					}
				}
			}
		}
		N = Integer.parseInt(br.readLine());
		dirR = new int[N];
		dirC = new int[N];
		for(int i= 0;i<N;i++) {//방향 입력 받음
			st = new StringTokenizer(br.readLine());
			dirR[i] = Integer.parseInt(st.nextToken());
			dirC[i] = Integer.parseInt(st.nextToken());
		}
		while(!pq.isEmpty()) {//우선순위큐가 빌때까지 진행, 우선순위큐는 이동횟수 오름차순, 같다면 row 내림차순
			Node_13903 temp = pq.poll();
			int r = temp.r;
			int c = temp.c;
			int count = temp.count;
			for(int i= 0;i<N;i++) {//방향 체크
				int rr = r + dirR[i];
				int cc = c + dirC[i];
				//갈수있고, 가지않았던 곳이라면
				if(isInBoundery(rr, cc) && map[rr][cc] == true && isChecked[rr][cc] == false) {
					if(rr == R-1) {//마지막 row에 도착했다면
						answer = count+1;//답갱신
						pq.clear();
						break;
					}
					isChecked[rr][cc] = true;//방문체크
					pq.offer(new Node_13903(rr, cc, count+1));//새좌표와 이동횟수 증가하여 삽입
				}
			}
		}
		if(R == 1) {//R이 1인경우 이동안해도 도착하므로 0으로 답갱신, 예외처리
			answer = 0;
		}
		System.out.println(answer);//답출력
}catch (Exception e) {}
	}
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<R && c>=0 && c<C) {
			return true;
		}return false;
	}
	class Node_13903 implements Comparable<Node_13903>{
		int r, c, count;
		public Node_13903(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}
		@Override
		public int compareTo(Node_13903 o) {//이동횟수로 오름차순, row크기로 내림차순
			if(this.count>o.count) return 1;
			if(this.count<o.count) return -1;
			return o.r-this.r;
		}
		
	}
}
