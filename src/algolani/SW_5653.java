package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SW_5653 {
	int answer,R,C,K;
	final int N = 700;
	int[][] map = new int[N][N];
	boolean[][] isChecked = new boolean[N][N];
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	PriorityQueue<Node_5653> pq = new PriorityQueue<>();
	public SW_5653() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			pq.clear();
			for(int i= 0;i<N;i++) {
				Arrays.fill(map[i], 0);
				Arrays.fill(isChecked[i], false);
			}
			int midR = (N/2)-(R/2);
			int midC = (N/2)-(C/2);
			for(int i= midR;i<midR+R;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = midC;j<midC+C;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] != 0) {
						pq.offer(new Node_5653(i, j, map[i][j], map[i][j]));
						isChecked[i][j] = true;
					}
				}
			}
			int time = 0;
			while(time<K) {
				time++;
				while(pq.peek().time <= time) {
					Node_5653 temp = pq.poll();
					int r = temp.r;
					int c = temp.c;
					int life = temp.life;
					if(map[r][c] >= 0) {
						map[r][c] = -1;
						pq.offer(new Node_5653(r, c, life, time+1));
						continue;
					}else {
						if(map[r][c] != -life) {
							map[r][c]--;
							pq.offer(new Node_5653(r, c, life, time+1));
						}
					}
					for(int i= 0;i<4;i++) {
						int rr = r + dirR[i];
						int cc = c + dirC[i];
						if(isChecked[rr][cc]==false) {
							isChecked[rr][cc] = true;
							map[rr][cc] = life;
							pq.offer(new Node_5653(rr, cc, life, life+time));
						}
					}
				}
			}
			answer = pq.size();
			System.out.println("#"+t+ " "+ answer);
		}
}catch (Exception e) {e.printStackTrace();}
	}
	class Node_5653 implements Comparable<Node_5653>{
		int r, c, life, time;
		public Node_5653(int r, int c, int life, int time) {
			this.r = r;
			this.c = c;
			this.life = life;
			this.time = time;
		}
		@Override
		public int compareTo(Node_5653 o) {
			if(this.time == o.time) {
				return o.life-this.life;
			}else {
				return this.time-o.time;
			}
		}
	}
}
