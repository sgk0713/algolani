package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_1767 {
	//프로세서 연결하기
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	boolean isChecked[][] = new boolean[14][14];
	int[][] arr = new int[14][14];
	final int EAST = 0;
	final int SOUTH = 1;
	final int WEST = 2;
	final int NORTH = 3;
	int totalCore = 0, answer, N, maxCore = 0;
	ArrayList<Pair_1767> list = new ArrayList<>();
	public SW_1767() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			answer = 500;
			maxCore = totalCore = 0;
			list.clear();//초기화
			
			N = Integer.parseInt(br.readLine());
			for(int i = 0;i< N+2;i++) {//초기화
				Arrays.fill(arr[i], -1);
				Arrays.fill(isChecked[i], false);
			}
			for(int i = 1;i<=N;i++) {//입력
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 1; j<=N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					
					if(arr[i][j]==1) {
						isChecked[i][j] = true;
						if(i !=1 && i!=N && j != 1 && j!=N) {//테두리에 붙은 코어는 이미 연결돼있기때문에 새지않는다
							list.add(new Pair_1767(i, j));
							totalCore++;
						}
					}
				}
			}
			if(totalCore == 0) {//코어없으면 0출력
				System.out.println("#"+t+" " + 0);
			}else {//코어가 하나라도 있으면
				dfs(list.get(0).r, list.get(0).c, 0, 0, 0);
				System.out.println("#"+t+" " + answer);
			}
		}
}catch (Exception e) {e.printStackTrace();}
	}
	void dfs(int r, int c, int curCore, int wire, int idx) {
		boolean flag = false;
		for(int i=0;i<4;i++) {//모든 방향 체크
//				showMap();
			int tempWire = canConnect(r, c, i); // 한방향으로 거리수 입력받음
//				showMap();
			if(tempWire > 0) {//양수면 갈 수 있다는 뜻.
				flag = true;
				if(idx+1 < list.size()) {
					dfs(list.get(idx+1).r, list.get(idx+1).c, curCore+1, wire+tempWire, idx+1);
				}
			}
			
			int rr = r;
			int cc = c;
//			showMap();
			if(idx+1 == list.size()) {
				if(maxCore <= curCore) {
					if(maxCore < curCore) {
						maxCore = curCore;
						if(tempWire>0) {
							answer = wire+tempWire;
						}else if(i == 3 && flag == false){
							answer = wire;
						}
					}else {
						if(tempWire>0) {
							answer = Math.min(answer, wire+tempWire);
						}else if(i == 3 && flag == false){
							answer = Math.min(answer, wire);
						}
					}
				}
			}
			for(int j = 0;j<Math.abs(tempWire);j++) {//갔던 거리 되돌려주기
				isChecked[rr+=dirR[i]][cc+=dirC[i]] = false;
			}
//			showMap();
			if(i == 3 && flag == false && idx+1 < list.size()) {
				dfs(list.get(idx+1).r, list.get(idx+1).c, curCore, wire, idx+1);
			}
			
		}
	}
	class Pair_1767{
		int r, c;
		public Pair_1767(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
		void showMap() {
			for(int i = 1;i<=N;i++) {
				for(int j = 1;j<=N;j++) {
					if(arr[i][j] == 1) {
						System.out.print("x");
					}else if(isChecked[i][j] == false) {
						System.out.print(".");
					}else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			System.out.println();
		}
	int canConnect(int r, int c, int dir) {//간 거리를 반환, 못가는 경우 음수로 반환
		int rr = r;
		int cc = c;
		int count = 0;
		while(true) {
			rr += dirR[dir];
			cc += dirC[dir];
			if(isChecked[rr][cc] == true) {
				break;
			}
			if(arr[rr][cc] == -1) {
				return count;
			}
			count++;
			isChecked[rr][cc] = true;
		}
		return -count;
	}
}
