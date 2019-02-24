package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_16920 {
	int R, C, P;
	char[][] map;
	boolean[][] isChecked;
	int[] turns;//각 성이 한턴당 확장할 수 있는 횟수
	int[] answers;//답을 저장하는 배열
	int[] dirR = {0,1,0,-1};// 방향 배열
	int[] dirC = {1,0,-1,0};
	PriorityQueue<Node_16920> pq = new PriorityQueue<>();
	public B_16920() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		isChecked = new boolean[R][C];
		turns = new int[P+1];
		answers = new int[P+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1;i<=P;i++) {
			turns[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i= 0;i<R;i++) {
			String str = br.readLine();
			for(int j = 0;j< C;j++) {
				map[i][j] = str.charAt(j);//맵에 기록
				if('1' <= map[i][j] && map[i][j] <= '9') {//숫자 문자라면
					isChecked[i][j] = true;//그 위치는 방문한걸로 체크
					answers[map[i][j]-'0']++;//답안의 초기값을 잡아줌
					//우선순위큐에 넣어 성의 숫자기준 오름차순으로 정렬, 턴의내림차순으로 정렬
					pq.add(new Node_16920(i, j, map[i][j]-'0', turns[map[i][j]-'0']));
				}
				if(map[i][j] == '#') {//벽이라면 방문했다고표시하여 확장을 못하게 방지한다
					isChecked[i][j] = true;
				}
			} 
		}
		while(!pq.isEmpty()) {//빌때까지 반복
			Node_16920 temp = pq.poll();
			int r = temp.r;
			int c = temp.c;
			int num = temp.num;
			int turn = temp.turn;
			
			for(int k = 0;k<4;k++) {//4방향 탐색
				int rr = r + dirR[k];
				int cc = c + dirC[k];
				//범위안이고, 방문하지않은곳이거나 같은 숫자인경우 들린다. 벽은 방문처리해놔서 딱히 신경안써도됨. 
				if(rr >= 0 && rr < R && cc >= 0 && cc < C && isChecked[rr][cc] == false) {
					isChecked[rr][cc] = true;//방문햇다 표시하고
					answers[num%(P+1)]++;//해당하는 성의개수를 답안배열에 증가해준다
					
					if(turn > 1) {//1초과, 즉,2~s까지는 계속 확장해야되는 턴이 남았으므로, 횟수를 줄이고 앞쪽에 넣어준다
						pq.offer(new Node_16920(rr, cc, num, turn-1));
					}else {//1회남은 차례는 옮기면 끝이므로 큐의 맨뒤에 턴을 해당하는 값으로 초기화하여 넣어준다
						//다음 숫자차례를 위해P+1을 더해준다
						pq.offer(new Node_16920(rr, cc, num+P+1, turns[num%(P+1)]));
					}
				}
			}
		}
		for(int i = 1;i<=P;i++) {
			System.out.print(answers[i]+ " ");//저장된 답안을 출력한다
		}
}catch (Exception e) {e.printStackTrace();}
	}
	class Node_16920 implements Comparable<Node_16920>{
		int r, c, num, turn;
		public Node_16920(int r, int c, int num, int turn) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.turn = turn;
		}
		@Override
		public int compareTo(Node_16920 o) {
			if(this.num > o.num) return 1;
			else if(this.num < o.num) return -1;
			else if(this.turn < o.turn) return 1;
			else return -1;
		}
	}
}
