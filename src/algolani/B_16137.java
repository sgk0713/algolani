package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16137 {
	//견우와 직녀
	int N, M, answer = Integer.MAX_VALUE;
	Queue<Pair_16137> q;
	int[][] map;
	boolean isChecked[][];
	int[] dirR = {0, 1, 0, -1};
	int[] dirC = {1, 0, -1, 0};
	int[] clifR;
	int[] clifC;
	public B_16137() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+2][N+2];//범위지정 조건문을 안쓰기위해 모서리부분 행과열만큼 만든다
		isChecked = new boolean[N+2][N+2];
		clifR = new int[N*N];
		clifC = new int[N*N];
		//생성후 -1로 초기화
		for(int i= 0;i<N+2;i++) {
			Arrays.fill(map[i], -1);
		}
		int count = 0;
		//맵입력
		for(int i= 1;i<=N;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					clifR[count] = i;
					clifC[count] = j;
					count++;
				}
			}
		}

		//큐생성
		q = new LinkedList<>();
		
		//오작교 건설이 가능한 절벽에 M값을 넣고 최단거리를 찾는다
		if(count == 0) {
			answer = Math.min(answer, bfs());
		}else {
			int R, C;
			for(int i= 0;i < count;i++) {
				R = clifR[i];
				C = clifC[i];
				if(map[R][C] == 0 && canConstruct(R,C)) {
					map[R][C] = M;
					answer = Math.min(answer, bfs());
					map[R][C] = 0;
				}
			}
		}
		System.out.println(answer);//답출력
}catch (Exception e) {e.printStackTrace();}	
	}
	int bfs() {
		init();//초기화 메소드
		int time = 0;//시간의 흐름~~
		
		while(!q.isEmpty()) {//bfs
			int qSize = q.size(); 
			
			for(int i= 0;i<qSize;i++) {
				Pair_16137 temp = q.poll();
				int r = temp.r;
				int c = temp.c;
				for(int k = 0;k<4;k++) {
					int rr = r + dirR[k];
					int cc = c + dirC[k];
					if(rr == N && cc == N) {//가려는곳이 직녀의 위치라면 바로 리턴
						q.clear();
						return time+1;
					}
					if(map[rr][cc] > 0//길이 있는 곳이고
					&& isChecked[rr][cc] == false//가보지않았던 곳이고
					&& !(map[r][c] >= 2 && map[rr][cc] >= 2)) {//연속으로 오작교가 아닐때
						if((time+1)%map[rr][cc] == 0) {//현재시간으로 건널수있는 길이라면
							isChecked[rr][cc] = true;
							q.offer(new Pair_16137(rr, cc));//가즈아
						}else {//현재시간으로 건널수 없는 길이라면
							q.offer(new Pair_16137(r,c));//기존값넣고 다음을 기약...
						}
					}
				}
			}
			time++;//큐사이즈만큼 돌렸으면 시간이 1 흐름
		}
		return Integer.MAX_VALUE;//오류때메 적음..여기까지 올일없음
	}
	class Pair_16137{
		int r;
		int c;
		public Pair_16137(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	void init() {
		for(int i =1;i<=N;i++) {
			Arrays.fill(isChecked[i], false);
		}
		isChecked[1][1] = true;
		q.clear();
		q.offer(new Pair_16137(1, 1));
	}
	boolean canConstruct(int i, int j) {
		for(int k =0;k<4;k++) {
			if(map[i+dirR[k]][j+dirC[k]] == 0 && map[i+dirR[(k+1)%4]][j+dirC[(k+1)%4]] == 0) {
				return false;
			}
		}
		return true;
	}
}
