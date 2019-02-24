package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.StringTokenizer;

public class B_16918 {
	int R, C, N;
	char[][] map, t1Map, t2Map;
	boolean[][] isChecked;
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	Queue<Node_16918> q = new LinkedList<>();
	public B_16918() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
//	while(true) {
//		R = new Random().nextInt(5);
//		C = new Random().nextInt(5);
//		while((N = new Random().nextInt(200)) %2 != 1) {
//		}
//		char[] ch = {'O','.'};
		map = new char[R][C];
		t1Map = new char[R][C];
		t2Map = new char[R][C];
		isChecked = new boolean[R][C];
		for(int i = 0;i<R;i++) {
			String str = br.readLine();
			for(int j = 0;j<C;j++) {
				map[i][j] = str.charAt(j);
//				map[i][j] = ch[new Random().nextInt(2)];
				if(map[i][j] == 'O') {
					q.offer(new Node_16918(i, j));
				}
			}
		}
		if(N%2 == 0) {
			showMap(0);
		}else if(N==1) {
			showMap(-1);
		}else {
			copyMap(map, t1Map);
		
		
			while(!q.isEmpty()) {
				Node_16918 temp = q.poll();
				int r = temp.r;
				int c = temp.c;
				t1Map[r][c] = '.';
				isChecked[r][c] = true;
				for(int i = 0;i<4;i++) {
					int rr = r + dirR[i];
					int cc = c + dirC[i];
					if(rr>=0 && rr< R && cc >= 0 && cc < C) {
						isChecked[rr][cc] = true;
						t1Map[rr][cc] = '.';
					}
				}
			}
			for(int i= 0;i<R;i++) {
				for(int j = 0;j<C;j++) {
					if(isChecked[i][j] == false) {
						t1Map[i][j] = 'O';
					}
				}
			}
			copyMap(t1Map, t2Map);
			for(int i= 0;i<R;i++) {
				for(int j = 0;j<C;j++) {
					if(isChecked[i][j] == false) {
						t2Map[i][j] = 'x';
						for(int k = 0;k< 4;k++) {
							int rr = i + dirR[k];
							int cc = j + dirC[k];
							if(rr >= 0 && rr< R && cc>=0 && cc < C) {
								t2Map[rr][cc] = 'x';
							}
						}
					}
				}
			}
			
			for(int i= 0;i<R;i++) {
				for(int j = 0;j<C;j++) {
					if(t2Map[i][j] == 'x') {
						t2Map[i][j] = '.';
					}else {
						t2Map[i][j] = 'O';
					}
				}
			}
//			for(int i= 0;i< R;i++) {
//				for(int j=0;j< C;j++) {
//					if(map[i][j] != t2Map[i][j]) {
//						System.out.println("answer!!!\n"+  R + " "+ C + " "+ N);
//						showMap(-1);
//						System.out.println();
//						showMap(2);
//						break;
//					}
//				}
//			}
			if(N%4 == 3) {
				showMap(1);
			}else {
				showMap(2);
			}
			
//		}
	}
}catch (Exception e) {e.printStackTrace();}
	}
	void copyMap(char[][] original, char[][] copyMap) {
		for(int i = 0;i<R;i++) {
			System.arraycopy(original[i], 0, copyMap[i], 0, C);
		}
	}
	void showMap(int c) {
		if(c == 0) {
			for(int i = 0;i<R;i++) {
				for(int j = 0;j<C;j++) {
					System.out.print('O');
				}
				System.out.println();
			}
		}else if(c == -1){
			for(int i = 0;i<R;i++) {
				for(int j = 0;j<C;j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}else if(c == 1){
			for(int i = 0;i<R;i++) {
				for(int j = 0;j<C;j++) {
					System.out.print(t1Map[i][j]);
				}
				System.out.println();
			}
		}else if(c == 2) {
			for(int i = 0;i<R;i++) {
				for(int j = 0;j<C;j++) {
					System.out.print(t2Map[i][j]);
				}
				System.out.println();
			}
		}
	}
	class Node_16918{
		int r, c;
		public Node_16918(int r, int c) {
			this.r = r;
			this.c = c;
		}		
	}
}
