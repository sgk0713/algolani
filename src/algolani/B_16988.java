package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16988 {//Baaaaaaaaaduk2 (Easy)
	int[][] map;
	boolean[][] isChecked;
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	int[][] stones;
	int R, C, answer, add;
	ArrayList<Node_16988> oneList = new ArrayList<>();
	Queue<Node_16988> q = new LinkedList<>();
	public B_16988() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R+2][C+2];
		isChecked = new boolean[R+2][C+2];
		stones = new int[(R+2)*(C+2)][(R+2)*(C+2)];
		for(int i = 0;i<R+2;i++) {
			if(i != 0 && i!=R+1) {
				st = new StringTokenizer(br.readLine());
			}
			for(int j = 0;j<C+2;j++) {
				if(j==0 || j==C+1 || i == 0 || i == R+1) {
					map[i][j] = 1;
				}else {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		for(int i = 1;i<R+2;i++) {
			for(int j = 1;j<C+2;j++) {
				if(isChecked[i][j] == false && map[i][j] == 2) {
					int count = 1;
					isChecked[i][j] = true;
					q.offer(new Node_16988(i, j));
					int first = 0;
					int second = 0;
					boolean isOverLimit = false;
					while(!q.isEmpty()) {
						Node_16988 temp = q.poll();
						int r = temp.r;
						int c = temp.c;
						for(int k = 0;k<4;k++) {
							int rr = r + dirR[k];
							int cc = c + dirC[k];
							if(map[rr][cc]==2 && isChecked[rr][cc] == false) {
								count++;
								isChecked[rr][cc] = true;
								q.offer(new Node_16988(rr, cc));
							}else if(map[rr][cc] == 0 && isChecked[rr][cc] == false) {
								if(first == 0) {
									first = rr*(C+2)+cc;
									isChecked[rr][cc] = true;
								}else if(second == 0) {
									second = rr*(C+2)+cc;
									isChecked[rr][cc] = true;
								}else {
									isOverLimit = true;
								}
							}
						}
					}
					if(first != 0) {
						isChecked[first/(C+2)][first-((first/(C+2))*(C+2))] = false;
					}
					if(second != 0) {
						isChecked[second/(C+2)][second-((second/(C+2))*(C+2))] = false;
					}
                    if(!isOverLimit && first != 0) {
						if(second == 0) {
							boolean isThere = false;
							for(Node_16988 node: oneList) {
								if(node.r == first) {
									node.count += count;
									isThere = true;
									break;
								}
							}
							if(!isThere) {
								oneList.add(new Node_16988(first, 0, count));
							}
						}else {
							answer = Math.max(answer, stones[first][second] += count);
							answer = Math.max(answer, stones[second][first] += count);
						}
					}
				}
			}
		}
		int len = oneList.size();
		for(int i = 0;i<stones.length;i++) {
			int maxNum = 0;
			for(int j = 0;j<len;j++) {
				int first = oneList.get(j).r;
				int firstCount = oneList.get(j).count;
				int tempSum = stones[first][i];
				maxNum = Math.max(maxNum, tempSum + firstCount);
			}
			answer = Math.max(maxNum, answer);
		}
		for(int i = 0;i<len-1;i++) {
			int first = oneList.get(i).r;
			int firstCount = oneList.get(i).count;
			for(int j = i+1;j<len;j++) {
				int second = oneList.get(j).r;
				int secondCount = oneList.get(j).count;
				answer = Math.max(answer, stones[first][second] += (firstCount+secondCount));
				answer = Math.max(answer, stones[second][first] += (firstCount+secondCount));
			}
		}
		System.out.println(answer);
}catch (Exception e) {e.printStackTrace();}
	}
	class Node_16988{
		int r, c, count;
		public Node_16988(int r, int c) {
			this.r = r;
			this.c = c;
		}
		public Node_16988(int first, int second, int count) {
			this.r = first;
			this.c = second;
			this.count = count;
		}
		public boolean equals(Node_16988 obj) {
			if((obj.r == r&& obj.c == c)||(obj.c == r && obj.r == c)) {
				return true;
			}
			return false;
		}
	}
}