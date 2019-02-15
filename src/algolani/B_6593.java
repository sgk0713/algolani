package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_6593 {
	//상범 빌딩
	int L, R, C, answer;
	char[][][] map;
	boolean[][][] isChecked;
	int[] dirL = { 0, 0, 0, 0, 1, -1};//제자리 동남서북, 한층위로, 한층아래
	int[] dirR = { 0, 1, 0,-1, 0,  0};
	int[] dirC = { 1, 0,-1, 0, 0,  0};
	Queue<Pair_6593> q = new LinkedList<>();
	public B_6593() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(L == 0) {//L이 0이면 층이 없다는 거니까 프로그램 종료
				break;
			}
			q.clear();
			answer = 0;
			map = new char[L][R][C];
			isChecked = new boolean[L][R][C];
			
			for(int i =0;i<L;i++) {
				for(int j = 0;j<R;j++) {
					String str = br.readLine();
					for(int k=0;k<C;k++) {
						map[i][j][k] = str.charAt(k);
						if(map[i][j][k] == 'S') {
							q.offer(new Pair_6593(i, j, k));
							isChecked[i][j][k] = true;
						}
					}
				}
				br.readLine();
			}
			//입력 완료
			
			char tempValue;//값 비교를 위한 변수
			while(!q.isEmpty()) {//BFS 시작
				int qSize = q.size();//각 위치에서의 거리 계산을 위해 큐사이즈만큼 돌림
				answer++;//거리 증가
				for(int k = 0; k< qSize;k++) {
					Pair_6593 pair = q.poll();
					int l = pair.l;
					int r = pair.r;
					int c = pair.c;
					for(int i= 0; i<6;i++) {//방향 계산
						int ll = l+dirL[i];
						int rr = r+dirR[i];
						int cc = c+dirC[i];
						if(isInBoundery(ll, rr, cc)//범위안이고
						&& isChecked[ll][rr][cc] == false//가지않은곳이면
						&& (tempValue = map[ll][rr][cc]) != '#') {//벽이 아니면 감
							if(tempValue == 'E') {//다음지역이 도착지역이면
								answer++;//답하나 올려주고
								q.clear();//큐비우고
								k = qSize;//k-for문 못돌게 변수값바꿔주고
								break;//i-for문 스탑
							}
							isChecked[ll][rr][cc] = true;//들렸다 체크해주고
							q.offer(new Pair_6593(ll, rr, cc));//큐에 넣어줌
						}
					}
				}
				
			}
			showAnswer(answer-1);//값에 따라 답출력
		}
}catch (Exception e) {e.printStackTrace();}
	}
	
	class Pair_6593{int l, r, c;public Pair_6593(int l, int r, int c) {this.l = l;this.r = r;this.c = c;}}
	void showAnswer(int x) {
		if(x !=0) {
			System.out.println("Escaped in "+ x +" minute(s).");
		}else {
			System.out.println("Trapped!");
		}
	}
	boolean isInBoundery(int l, int r, int c) {if(l>=0 && l<L && r>=0 && r < R && c>=0 && c< C) {return true;}return false;}
//	void showMap() {for(int i =0;i<L;i++) {for(int j = 0;j<R;j++) {for(int k=0;k<C;k++) {System.out.print(map[i][j][k]);}System.out.println();}System.out.println();}System.out.println();}
}
