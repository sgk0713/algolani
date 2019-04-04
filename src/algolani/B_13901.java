package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_13901 {
	int R, C, K;
	boolean[][] map, isChecked;
	int[] dirR = {-1,1,0,0};
	int[] dirC = {0,0,-1,1};
	Queue<Node_13901> q = new LinkedList<>();
	public B_13901() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		isChecked = new boolean[R][C];
		K = Integer.parseInt(br.readLine()); 
		for(int k= 0;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			isChecked[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}
		st = new StringTokenizer(br.readLine());
		int tr = Integer.parseInt(st.nextToken());
		int tc = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] dir = new int[4];
		for(int k =0;k<4;k++) {
			dir[k] = Integer.parseInt(st.nextToken()) - 1;
		}
		isChecked[tr][tc] = true;
		q.offer(new Node_13901(tr,tc));
		int r=0, c=0, k = 0;
		while(!q.isEmpty()) {
			Node_13901 temp = q.poll();
			r = temp.r;
			c = temp.c;
			for(int i =0;i<4;i++,k=(k+1)%4) {
				int rr = r + dirR[dir[k]];
				int cc = c + dirC[dir[k]];
				if(isInBoundery(rr, cc) && isChecked[rr][cc] == false) {
					isChecked[rr][cc] = true;
					q.offer(new Node_13901(rr, cc));
					break;
				}
			}
		}
		System.out.println(r+" "+c);
}catch (Exception e) {}
	}
	boolean isInBoundery(int r, int c) {
		if(r>=0&&r<R && c>=0&&c<C) {
			return true;
		}return false;
	}
	class Node_13901{
		int r, c;
		public Node_13901(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
