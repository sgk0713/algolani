package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16988 {//Baaaaaaaaaduk2 (Easy)
	int[][] map;
	boolean[][] isChecked;
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	int R, C, answer;
	LinkedList<Pair_16988> groupList = new LinkedList<>();
	LinkedList<Node_16988> nodeList = new LinkedList<>();
	Queue<Node_16988> q = new LinkedList<>();
	Node_16988 tempFirst = null;
	Node_16988 tempSecond = null;
	
	public B_16988() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R+2][C+2];
		isChecked = new boolean[R+2][C+2];
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
		int groupNum = 0;
		for(int i = 1;i<R+1;i++) {
			for(int j = 1;j<C+1;j++) {
				if(isChecked[i][j] == false && map[i][j] == 2) {
					q.offer(new Node_16988(i, j));
					isChecked[i][j] = true;
					int count = 1;
					tempFirst = null;
					tempSecond = null;
					boolean isPossible = true;
					while(!q.isEmpty()) {
						Node_16988 tempNode = q.poll();
						int r = tempNode.r;
						int c = tempNode.c;
						for(int k = 0;k<4;k++) {
							int rr = r+dirR[k];
							int cc = c+dirC[k];
							if(isChecked[rr][cc] == false) {
								isChecked[rr][cc] = true;
								
								if(map[rr][cc] == 1) {
									continue;
								}else if(map[rr][cc] == 0 && isPossible) {
									if(groupList.size()==groupNum) {
										tempFirst = new Node_16988(rr, cc);
										groupList.add(new Pair_16988(tempFirst, null, count));
									}else {
										if(groupList.get(groupNum).second == null) {
											tempSecond = new Node_16988(rr, cc);
											groupList.get(groupNum).second = tempSecond;
										}else {
											groupList.removeLast();
											isPossible = false;
										}
									}
								}else if(map[rr][cc] == 2) {
									q.offer(new Node_16988(rr, cc));
									count++;
								}
							}
						}
					}
					if(isPossible) {
						boolean isFisrtPossible = true, isSecondPossible = true;
						
						for(Node_16988 temp : nodeList) {
							if(temp.equals(tempFirst)) {
								isFisrtPossible = false;
							}
							if(temp.equals(tempSecond)) {
								isSecondPossible = false;
							}
						}
						if(tempFirst != null && isFisrtPossible == true) {
							nodeList.add(tempFirst);
						}
						if(tempSecond != null && isSecondPossible == true) {
							nodeList.add(tempSecond);
						}
						groupList.getLast().count = count;
						groupNum++;
					}
					if(tempFirst != null) {
						isChecked[tempFirst.r][tempFirst.c] = false;
					}
					if(tempSecond != null) {
						isChecked[tempSecond.r][tempSecond.c] = false;
					}
				}
			}
		}
		int groupSize = groupList.size();
		int nodeSize = nodeList.size();
		int tempAnswer = 0;
		for(int i = 0;i<nodeSize;i++) {
			tempFirst = nodeList.get(i);
			for(int j = i+1;j<nodeSize;j++) {
				tempSecond = nodeList.get(j);
				
				tempAnswer = 0;
				
				for(int k = 0;k<groupSize;k++) {
					Pair_16988 tempPair = groupList.get(k);
					Node_16988 pairFirst = tempPair.first;
					Node_16988 pairSecond = tempPair.second;
					if(pairSecond == null) {
						if(tempFirst.equals(pairFirst) || tempSecond.equals(pairFirst)) {
							tempAnswer+=tempPair.count;
						}
					}else {
						if(tempFirst.equals(pairFirst)&& tempSecond.equals(pairSecond) || tempFirst.equals(pairSecond)&& tempSecond.equals(pairFirst)) {
							tempAnswer+=tempPair.count;
						}
					}
				}
				answer = Math.max(answer, tempAnswer);
				Pair_16988 temp = groupList.getFirst();
				groupList.removeFirst();
				groupList.addLast(temp);
			}
			
		}
			
		System.out.println(answer);
		
}catch (Exception e) {e.printStackTrace();}
	}
	class Node_16988{
		int r, c;
		public Node_16988(int r, int c) {
			this.r = r;
			this.c = c;
		}
		public boolean equals(Node_16988 obj) {
			if(obj != null && (r == obj.r && c == obj.c)) {
				return true;
			}
			return false;
		}
	}
	class Pair_16988{
		Node_16988 first, second;
		int count;
		public Pair_16988(Node_16988 first, Node_16988 second, int count) {
			this.first = first;
			this.second = second;
			this.count = count;
		}
	}
	void showMap() {
		for(int i = 0;i<R+2;i++) {
			for(int j = 0;j<C+2;j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}