package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16569 {
	int map[][];
	boolean isChecked[][];
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	int R, C;
	int answerHeight, answerTime;
	PriorityQueue<Node_16569> PQ_volcano = new PriorityQueue<>();//시간으로 오름차순
	Queue<Node_16569> Q_JAESANGsMove = new LinkedList<>();
	public B_16569() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int curR = Integer.parseInt(st.nextToken())-1;
		int curC = Integer.parseInt(st.nextToken())-1;
		
		map = new int[R][C];
		isChecked = new boolean[R][C];
		for(int i= 0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i= 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int volR = Integer.parseInt(st.nextToken())-1;//좌표값 조정을 위해 -1 해줌
			int volC = Integer.parseInt(st.nextToken())-1;
			int volT = Integer.parseInt(st.nextToken());
			map[volR][volC] = -1;//화산은 미리 -1로 두어서 화산지대인것을 표시
			PQ_volcano.offer(new Node_16569(volR, volC, volT));//우선순위큐에 화산의 좌표와 시간을 넣어줌
		}
		//입력 완료!!
		
		answerHeight = map[curR][curC];//현재 위치의 고도로 답 초기화
		isChecked[curR][curC] = true;//현재위치는 방문 한것
		answerTime = 0;//첫시작의 시간이므로 0으로 초기화
		Q_JAESANGsMove.offer(new Node_16569(curR, curC));//재상이큐에 담아줌
		int time = 0;//초기화
		while(!Q_JAESANGsMove.isEmpty()) {//재상이가 갈 수 있는 곳을 다 갈 때까지 진행
			time++;//시간 증가
			//화산 폭발 !!
			//화산의 맨앞의 노드는 제일 낮은 시간이 들어있음
			//미리 화산들을 폭발시켜 갈수없는길을 만들어준다.
			//첫 시작엔 0초와 1초에 터지는 화산들이 다 터지고 퍼지게 맵에 설정해두고 시작.
			while(PQ_volcano.peek().t <= time) {
				Node_16569 vol = PQ_volcano.poll();
				int volR = vol.r;
				int volC = vol.c;
				int volT = vol.t;
				
				if(map[volR][volC] != -2) {//현재 지대가 화산쇄설류가 덮지않은 지역이라면 
					map[volR][volC] = -2;//화산쇄설류가 덮는다. 방문 체크				
					for(int i = 0;i<4;i++) {//4방향 체크
						int volRR = volR + dirR[i];
						int volCC = volC + dirC[i];
						//맵안에 있고, -1이상이면(-1은 화산의 위치, -2는 쇄설류가 덮은 지역(방문한 지역))
						if(isInBoundery(volRR, volCC) && map[volRR][volCC] >= -1) {
							//위치좌표와 시간값을 1 증가하여 넣어준다.
							//0초엿다면 1초로들어가서 우선순위 큐에 의해 앞쪽으로 이동함
							PQ_volcano.offer(new Node_16569(volRR, volCC, volT+1));
						}
					}
				}
			}
			
//			showMap();//디버깅~
			//재상이가 시간마다 움직일 수 있는 거리들을 모두 체크 하기 위해 사이즈를 받아둠
			int qSize = Q_JAESANGsMove.size();
			for(int k = 0;k< qSize ;k++) {
				Node_16569 tempNode = Q_JAESANGsMove.poll();
				int r = tempNode.r;
				int c = tempNode.c;
				for(int i = 0 ;i< 4;i++) {//4방향 체크
					int rr = r + dirR[i];
					int cc = c + dirC[i];
					//맵안에 있고,고도가 0이상이고(화산이 있는 곳은 -1, 쇄설류는 -2이다), 재상이가 들린곳이 아니라면
					if(isInBoundery(rr, cc) && map[rr][cc] >= 0 && isChecked[rr][cc] == false) {
						//재상이가 들렸다고 체크
						isChecked[rr][cc] = true;
						//간곳이 고도가 높으면 답안 갱신
						if(map[rr][cc] > answerHeight) {
							answerHeight = map[rr][cc];
							answerTime = time;
						}
						Q_JAESANGsMove.offer(new Node_16569(rr, cc));//재상이 위치를 재상이큐에 담아준다
					}
				}
			}
//			showMap();//디버깅~
		}
		System.out.println(answerHeight + " " + answerTime);
}catch (Exception e) {e.printStackTrace();}
	}
	boolean isInBoundery(int r, int c) {//맵안에 있는지 체크 
		if(r>=0 && r<R && c>=0 && c<C) {
			return true;
		}
		return false;
	}
	void showMap(){//디버깅할때 주석 풀기
		for(int i= 0;i<R;i++) {
			for(int j = 0;j<C;j++) {
				if(isChecked[i][j] == true) {
					System.out.print("SS ");
				}
				
				else System.out.printf("%2d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	class Node_16569 implements Comparable<Node_16569>{
		int r, c, t;
		public Node_16569(int r, int c) {//생성자 오버로딩
			this.r = r;
			this.c = c;
		}
		public Node_16569(int r, int c, int t) {//생성자 오버로딩
			this.r = r;
			this.c = c;
			this.t = t;
		}
		@Override
		public int compareTo(Node_16569 o) {//우선순위큐 오버라이딩
			return this.t - o.t;
		}
		
	}
}
