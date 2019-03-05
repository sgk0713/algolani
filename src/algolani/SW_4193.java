package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_4193 {//56분
	//4193. 수영대회 결승전 ( 완전 탐색 + 구현 ) D4
	int maxPeriod;
	int[][] map;
	boolean[][] isChecked;
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	int startR, startC, endR, endC, N;
	Queue<Node_4193> q = new LinkedList<>();
	public SW_4193() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			int answer = -1;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			isChecked = new boolean[N][N];
			q.clear();
			StringTokenizer st;
			for(int i= 0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxPeriod = Math.max(maxPeriod, map[i][j]); 
				}
			}
			st = new StringTokenizer(br.readLine());
			startR = Integer.parseInt(st.nextToken());
			startC = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			endR = Integer.parseInt(st.nextToken());
			endC = Integer.parseInt(st.nextToken());
			if(startR == endR && startC == endC) {
				answer = 0;
			}else {
				isChecked[startC][startC] = true;
				q.offer(new Node_4193(startR, startC, 0));
			}
			int time = 0;
			while(!q.isEmpty()) {
				int qSize = q.size();
				time++;
				for(int k = 0;k<qSize;k++) {
					Node_4193 tempNode = q.poll();
					int r = tempNode.r;
					int c = tempNode.c;
					int count = tempNode.count;
					if(count>(maxPeriod+1)*2) {
						continue;
					}
					for(int i= 0;i<4;i++) {
						int rr = r + dirR[i];
						int cc = c + dirC[i];
						if(isInBoundery(rr, cc) && isChecked[rr][cc] == false && map[rr][cc] != 1) {
							if(map[rr][cc] >= 2 && time%(map[rr][cc]+1)!=0) {
									tempNode.count+=1;
									q.offer(tempNode);
									continue;
							}
							if(rr == endR && cc == endC) {
								answer = time;
								k = qSize;
								q.clear();
								break;
							}
							isChecked[rr][cc]=true;
							q.offer(new Node_4193(rr, cc, 0));
						}
					}
				}
			}
			System.out.println("#" + t + " " + answer);
		}
}catch (Exception e) {System.out.println(e.getMessage());}
	}
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<N) {
			return true;
		}
		return false;
	}
	class Node_4193{
		int r, c, count;
		public Node_4193(int r, int c, int count) {
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}
}
