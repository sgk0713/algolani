package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SW_1249 {
	//보급로
	
	int[][] map = new int[102][102];
	boolean[][] isChecked = new boolean[102][102];
	int N;
	PriorityQueue<Pair_1249> qq = new PriorityQueue<>();
	Deque<Pair_1249> q = new LinkedList<>();
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	public SW_1249() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1;t<=T;t++) {
			int answer = Integer.MAX_VALUE;
			q.clear();
			N = Integer.parseInt(br.readLine());
			for(int i= 0;i<=N+1;i++) {
				Arrays.fill(map[i], Integer.MIN_VALUE);
				Arrays.fill(isChecked[i], false);
			}
			for(int i= 1;i<=N;i++) {
				String str = br.readLine();
				for(int j = 1;j<=N;j++) {
					map[i][j] = str.charAt(j-1)-'0';
				}
			}
			
			isChecked[1][1] = true;
			q.offer(new Pair_1249(1,1,0,0));
			while(!q.isEmpty()) {
				Pair_1249 pair = q.poll();
				int r = pair.r;
				int c = pair.c;
				int num = pair.num;
				int count = pair.count;
				for(int i = 0 ; i < 4 ; i++) {
					int rr = r+dirR[i];
					int cc = c+dirC[i];
					if(rr == N && cc == N) {
						q.clear();
						answer = num;
						break;
					}
					if(isChecked[rr][cc]==false
						&& map[rr][cc] >= 0) {
						if(map[rr][cc] == count) {
							isChecked[rr][cc] = true;
							qq.add(new Pair_1249(rr, cc, num+map[rr][cc], 0));
						}else {
							q.offer(new Pair_1249(r, c, num, count+1));
						}
					}
				}
//				showMap();
			}
			System.out.println("#"+t+ " " + answer);
		}
}catch (Exception e) {e.printStackTrace();}
	}
	class Pair_1249{
		int r, c, num, count;
		public Pair_1249(int r, int c, int num, int count) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.count = count;
		}
	}
//	void showMap() {
//		for(int i = 1;i<=N;i++) {
//			for(int j =1;j<=N;j++) {
//				if(isChecked[i][j] == true) {
//					System.out.print("o");
//				}else {
//					System.out.print(".");
//				}
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}
