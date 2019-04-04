package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_4991 {
	int R,C,sR, sC, dirty, answer;
	int[][] copyMap = new int[20][20];
	int[][] map = new int[20][20];
	boolean[][] isChecked = new boolean[20][20];
	Queue<Node_4991> q = new LinkedList<>();
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	int[] targetR = new int[11];
	int[] targetC = new int[11];
	int[][] distance = new int[11][11];
	int[] list = new int[11];
	public B_4991() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			if(R ==0) {
				break;
			}
			dirty = 1;
			answer = 0;
			
			for(int i =0;i<11;i++) {
				list[i] = i;
				for(int j= 0;j<11;j++) {
					distance[i][j] = 1000000;
				}
			}
			for(int i=0;i<R;i++) {
				String str = br.readLine();
				for(int j=0;j<C;j++) {
					map[i][j] = 0;
					switch (str.charAt(j)) {
						case 'x':
							map[i][j] = -1;
							break;
						case 'o':
							sR = targetR[0] = i;
							sC = targetC[0] = j;
							break;
						case '*':
							targetR[dirty] = i;
							targetC[dirty++] = j;
							break;
					}
				}
			}
			for(int i= 0;i<dirty-1;i++) {
				if(answer != -1) {
					BFS(targetR[i], targetC[i], i);
				}
			}
			
			if(answer==-1) {
				sb.append("-1\n");
			}else {
				answer = Integer.MAX_VALUE;
				permutation(1);
				sb.append(answer).append("\n");
			}
		}
		System.out.println(sb.toString());
}catch (Exception e) {e.printStackTrace();}
	}
	void permutation(int idx) {
		if(idx == dirty) {
			int temp = 0;
			for(int i= 0;i<dirty-1;i++) {
				temp += distance[list[i]][list[i+1]];
			}
			answer = Math.min(answer, temp);
			return;
		}else {
			for(int i = idx;i<dirty;i++) {
				swap(i,idx);
				permutation(idx+1);
				swap(i, idx);
			}
		}
	}
	void swap(int a, int b) {
		int temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}
	void initArray() {
		for(int i=0;i<20;i++) {
			Arrays.fill(isChecked[i], false);
			copyMap[i] = Arrays.copyOf(map[i], 20);
		}
		
	}
	void BFS(int sr, int sc, int idx) {
		int dist = 0;
		q.clear();
		initArray();
		q.offer(new Node_4991(sr, sc));
		copyMap[sr][sc] = dist;
		isChecked[sr][sc] = true;
		while(!q.isEmpty()) {
			int qSize = q.size();
			dist++;
			for(int i = 0;i<qSize;i++) {
				Node_4991 temp = q.poll();
				int r = temp.r;
				int c = temp.c;
				for(int k= 0;k<4;k++) {
					int rr = r + dirR[k];
					int cc = c + dirC[k];
					if(isInBoundery(rr, cc) && isChecked[rr][cc] == false && copyMap[rr][cc] != -1) {
						isChecked[rr][cc] = true;
						copyMap[rr][cc] = dist;
						q.offer(new Node_4991(rr, cc));
					}
				}
			}
		}
		for(int i= idx+1;i<dirty;i++) {
			if(copyMap[targetR[i]][targetC[i]] == 0) {
				answer = -1;
				return;
			}
			distance[idx][i] = distance[i][idx] = Math.min(distance[idx][i], copyMap[targetR[i]][targetC[i]]);
		}
	}
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<R && c>=0 && c<C) {
			return true;
		}return false;
	}
	class Node_4991{
		int r, c;
		public Node_4991(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
