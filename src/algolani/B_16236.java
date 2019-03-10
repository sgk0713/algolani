package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16236 {
	int N, answer, totalFish, sharkSize=2, fed;
	int fishR, fishC, curR, curC;
	int[][] map;
	boolean[][] isChecked;
	int dirR[] = {0, 1, 0, -1};
	int dirC[] = {1, 0, -1, 0};
	Queue<Node_16236> q = new LinkedList<>();
	LinkedList<Fish_16236> list = new LinkedList<>();
	
	public B_16236() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		isChecked = new boolean[N][N];
		for(int i= 0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>=1 && map[i][j] <=6) {
					totalFish++;
				}
				if(map[i][j] == 9) {
					map[i][j] = 0;
					q.offer(new Node_16236(i, j));
					curR = i;
					curC = j;
				}
			}
		}
		int time = 0;
		initChecked();
		isChecked[curR][curC] = true;
		while(!q.isEmpty()) {
			time++;
			int qSize = q.size();
			if(fishR == -1) {
				break;
			}
			for(int i= 0;i<qSize;i++) {
				Node_16236 temp = q.poll();
				int r = temp.r;
				int c = temp.c;
				for(int k = 0;k<4;k++) {
					int rr = r + dirR[k];
					int cc = c + dirC[k];
					if(isInBoundery(rr, cc) 
							&& isChecked[rr][cc] == false
							&& map[rr][cc] <= sharkSize) {
						isChecked[rr][cc] = true;
						if(map[rr][cc] == 0 || map[rr][cc] == sharkSize) {
							q.offer(new Node_16236(rr, cc));
						}else if(rr == fishR && cc == fishC){
							curR = rr;
							curC = cc;
							map[rr][cc] = 0;
							totalFish--;
							answer = time;
							q.clear();
							if(fed+1 == sharkSize) {
								sharkSize++;
								fed = 0;
							}else {
								fed++;
							}
							initChecked();
							isChecked[rr][cc] = true;
							q.offer(new Node_16236(rr, cc));
							i = qSize;
							break;
						}
					}
				}
			}
		}
		System.out.println(answer);
}catch (Exception e) {e.printStackTrace();}
	}
	void initChecked() {
		for(int i = 0;i<N;i++) {
			Arrays.fill(isChecked[i], false);
		}
		Queue<Node_16236> tempQ = new LinkedList<>();
		tempQ.offer(new Node_16236(curR, curC));
		list.clear();
		isChecked[curR][curC] = true;
		int distance = 0;
		while(!tempQ.isEmpty() && list.size()== 0) {
			distance++;
			int qSize = tempQ.size();
			for(int i= 0;i< qSize;i++) {
				Node_16236 temp = tempQ.poll();
				int r = temp.r;
				int c = temp.c;
				for(int k =0;k<4;k++) {
					int rr = r + dirR[k];
					int cc = c + dirC[k];
					if(isInBoundery(rr, cc) && map[rr][cc] <= sharkSize && isChecked[rr][cc] == false) {
						isChecked[rr][cc] = true;
						if(map[rr][cc] < sharkSize && map[rr][cc] != 0) {
							list.add(new Fish_16236(rr, cc, map[rr][cc], distance));
						}
						tempQ.offer(new Node_16236(rr, cc));
					}
				}
			}
		}
		tempQ.clear();
		for(int i = 0;i<N;i++) {
			Arrays.fill(isChecked[i], false);
		}
		int listSize = list.size();
		if(listSize!= 0) {
			Collections.sort(list);
			for(int i = 0;i<listSize;i++) {
				if(list.get(i).size < sharkSize) {
					Fish_16236 tempFish = list.remove(i);
					fishR = tempFish.r;
					fishC = tempFish.c;
					break;
				}
			}
		}else {
			fishC = fishR = -1;
		}
	}
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<N) {
			return true;
		}
		return false;
	}
	class Fish_16236 implements Comparable<Fish_16236>{
		int r, c, size, distance;

		public Fish_16236(int r, int c, int size, int distance) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.distance = distance;
		}

		@Override
		public int compareTo(Fish_16236 o) {
			if(this.distance > o.distance) {
				return 1;
			}else if(this.distance == o.distance) {
				if(this.r > o.r) {
					return 1;
				}else if(this.r == o.r) {
					if(this.c > o.c) {
						return 1;
					}
					return -1;
				}
				return -1;
			}
			return -1;
		}
	}
	class Node_16236{
		int r, c;
		public Node_16236(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
