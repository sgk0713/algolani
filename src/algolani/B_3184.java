package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_3184 {
	int R, C;
	int sheep;
	int wolf;
	char[][] map;
	boolean[][] isChecked;
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	Queue<Node_3184> q = new LinkedList<>();
	public B_3184() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		isChecked = new boolean[R][C];
		for(int i= 0;i<R;i++) {
			String str = br.readLine();
			for(int j= 0;j<C;j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for(int i= 0;i<R;i++) {
			for(int j= 0;j<C;j++) {
				if(map[i][j] != '#' && !isChecked[i][j]) {
					int w = 0, s=0;
					q.offer(new Node_3184(i, j));
					isChecked[i][j] = true;
					if(map[i][j] == 'o') {
						s++;
					}else if(map[i][j] == 'v'){
						w++;
					}
					while(!q.isEmpty()) {
						Node_3184 temp = q.poll();
						int r = temp.r;
						int c = temp.c;
						for(int k= 0;k<4;k++) {
							int rr = r + dirR[k];
							int cc = c + dirC[k];
							if(isInBoundery(rr, cc)&& map[rr][cc] != '#' && !isChecked[rr][cc]) {
								isChecked[rr][cc] = true;
								if(map[rr][cc] == 'o') {
									s++;
								}else if(map[rr][cc] == 'v'){
									w++;
								}
								q.offer(new Node_3184(rr, cc));
							}
						}
					}
					if(s>w) {
						sheep+=s;
					}else {
						wolf+=w;
					}
				}
			}
		}
		System.out.println(sheep+" " + wolf);
		
		
}catch (Exception e) {}
	}
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<R && c>=0 && c< C) {
			return true;
		}return false;
	}
	class Node_3184{
		int r, c;
		public Node_3184(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
