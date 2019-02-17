package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_3197 {
	int R, C, xR=-1, xC=-1, yR=-1,yC=-1, answer = 0;
	int[][] map;
	boolean[][] isChecked;
	Queue<Node_3197> q = new LinkedList<>();
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	
	public B_3197() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		isChecked = new boolean[R][C];
		for(int i = 0;i<R;i++) {
			String str = br.readLine();
			for(int j = 0;j<C;j++) {
				int curChar = str.charAt(j);
				if(curChar == 'L') {//백조라면
					curChar = 0;//(그 위치는 물이므로)0으로 설정
					if(xR == -1) {//첫번째 백조의 좌표가 등록되지않았다면
						xR = i;xC = j;//좌표설정
					}else {
						yR = i;yC = j;//두번째 백조 좌표 설정
					}
				}else if(curChar == '.') {//물이라면 0으로 설정
					curChar = 0;
				}else {
					curChar = -1;//그외는 빙판이므로 -1로 설정한다
				}
				map[i][j] = curChar;//해당되는 값을 맵에 넣어주고
				//물(0)인데 전 값이 빙판(-1)인 경우
				if(j!=0 && isChecked[i][j] == false && curChar == 0 && map[i][j-1] == -1) {
					isChecked[i][j] = true;
					q.offer(new Node_3197(i, j));
				}
				//빙판(-1)인데 전값이 물(0)인경우
				if(j!= 0 && isChecked[i][j-1] == false && curChar == -1 && map[i][j-1] == 0) {
					isChecked[i][j-1] = true;
					q.offer(new Node_3197(i, j-1));
				}
			}
		}
		int day = 0;
		//며칠에 녹는 얼음인지 맵에 채우기
		while(!q.isEmpty()) {
			int qSize = q.size();
			day++;
			//큐싸이즈 만큼 돌려서 1일차, 2일차...때의 빙판을 녹임
			for(int i= 0;i<qSize;i++) {
				Node_3197 node = q.poll();
				int r = node.r;
				int c = node.c;
				for(int k = 0;k< 4;k++) {
					int rr = r + dirR[k];
					int cc = c + dirC[k];
					//범위 안이고, 방문하지않은 빙판이면
					if(rr>=0&&rr<R&&cc>=0&&cc<C && map[rr][cc] == -1) {
						map[rr][cc] = day;//녹을 날짜 입력
						q.offer(new Node_3197(rr, cc));
					}
				}
			}
		}
		int min = 0;
		int max = day;//빙판이 다 녹을 날짜를 최대로 잡아준다
		int mid = 0;
		boolean found = false;//백조끼리 만났는지 판단하는 flag
		while(min<=max) {
			mid = (min+max)/2;//이분탐색을 위해 중간값을 구함.
			q.offer(new Node_3197(xR, xC));//첫번째 백조 좌표를 넣어 초기화
			for(int i= 0;i<R;i++) {
				Arrays.fill(isChecked[i], false);//방문 배열 초기화
			}
			found = false;//flag 초기화
			//백조가 만나거나 모든 경우의 수를 bfs로 돌릴때까지 반복
			while(!q.isEmpty()) {
				Node_3197 node = q.poll();
				int r = node.r;
				int c = node.c;
				for(int i = 0;i<4;i++) {//4방향으로 탐색
					int rr = r + dirR[i];
					int cc = c + dirC[i];
					if(rr == yR && cc == yC) {//두번째 백조를 만났다면
						q.clear();//큐를 비우고
						found = true;//플래그 표시
						answer = mid;//답안 갱신
						break;//for문 나감
					}
					//범위안에 있고, 방문하지않았고, 현재 날짜이하에 녹을 빙판길이라면
					if(rr>=0&&rr<R&&cc>=0&&cc<C && isChecked[rr][cc] == false && map[rr][cc] <= mid) {
						isChecked[rr][cc] = true;//방문표시
						q.offer(new Node_3197(rr, cc));//큐에 넣어줌
					}
				}
			}
			//bfs가 끝난 후 , 백조끼리 만났는지 체크
			if(found) {//만났다면, 더 적게만날수있는지 체크를 위해
				max = mid-1;//max값 갱신
			}else {//못만났다면, 날짜값을 올리기위해
				min = mid+1;//min값 갱신
			}
		}
		System.out.println(answer);
}catch (Exception e) {}
	}
	class Node_3197{
		int r, c;
		public Node_3197(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}