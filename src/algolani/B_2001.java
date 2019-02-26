package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2001 {//보석 줍기
	int N, M, K, curJam, answer;
	int[] islands;
	int[][] weights;
	boolean[][][] isChecked; 
	PriorityQueue<Node_2001> q = new PriorityQueue();
	ArrayList<Integer>[] bridges;
	int[] idxIsland = new int[101];
	public B_2001() {
		
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
//		N = new Random().nextInt(100)+1;
//		M = new Random().nextInt(1000)+1;
//		K = new Random().nextInt(14)+1;
//		System.out.println(N+" "+M+" "+K);
		bridges = new ArrayList[N+1];
		for(int i= 0;i< N+1;i++) {
			bridges[i] = new ArrayList<>();
		}
		islands = new int[N+1];
		isChecked = new boolean[1<<(K)][N+1][N+1];
		weights = new int[N+1][N+1];
//		System.out.print("temp : ");
		int temp = 0;
		for(int i= 0;i<K;i++) {
			//디버
//			int temp= new Random().nextInt(N)+1;
//			while(islands[temp] != 0) {
//				System.out.print(temp + " ");
//				temp= new Random().nextInt(N)+1;
//			}
//			System.out.println("\n"+temp);
			//디버깅끝
			temp = Integer.parseInt(br.readLine());
			islands[temp] = 1;
			idxIsland[temp] = i+1;
		}
		for(int i= 0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
//			int from = new Random().nextInt(N)+1;
//			int to = new Random().nextInt(N)+1;
//			int weight = new Random().nextInt(100)+1;
//			while(weights[from][to] != 0) {
//				from = new Random().nextInt(N)+1;
//				to = new Random().nextInt(N)+1;
//				weight = new Random().nextInt(100)+1;
//			}
//			System.out.println("i:"+(i+1)+" "+from+" "+to+" "+weight);
			bridges[from].add(to);
			bridges[to].add(from);
			weights[from][to] = weights[to][from] = weight;
		}
		
		for(int i= 0;i<bridges[1].size();i++) {
			int island = bridges[1].get(i);
			q.add(new Node_2001(island, 0));
//			q.add(new Node_2001(temp, 0));
		}
		while(!q.isEmpty()) {
			Node_2001 tempNode = q.poll();
			int curJam = tempNode.jam;
			int curNum = tempNode.num;
			if(curNum == 1) {
				if(islands[1]!=0) {
					curJam |= 1<<(idxIsland[curNum]-1);
				}
				answer = Math.max(answer, getNumofJams(curJam));
//				if(!q.isEmpty()) {
//					continue;
//				}
			}
			ArrayList<Integer> tempList = bridges[curNum];
			int listSize = tempList.size();
			for(int i= 0;i<listSize;i++) {
				int nextNum = tempList.get(i);
				if(islands[curNum] != 0) {//보석을 가지고 이동할 경우
					int grapJam = curJam|(1<<(idxIsland[curNum]-1));
					if(isChecked[grapJam][curNum][nextNum] == false
							&& weights[curNum][nextNum] >= getNumofJams(grapJam)) {
						isChecked[grapJam][curNum][nextNum] = true;
						isChecked[grapJam][nextNum][curNum] = true;
						q.offer(new Node_2001(nextNum, grapJam));
					}
				}
				//보석을 안가지고 그냥 이동할 경우
				else if(isChecked[curJam][curNum][nextNum] == false
				&& weights[curNum][nextNum] >= getNumofJams(curJam)) {
					isChecked[curJam][curNum][nextNum] = true;
					isChecked[curJam][nextNum][curNum] = true;
					q.offer(new Node_2001(nextNum, curJam));
				}
			}
		}
		System.out.println(answer);
}catch (Exception e) {e.printStackTrace();}
	}
	class Node_2001 implements Comparable<Node_2001>{
		int num, jam;
		public Node_2001(int num, int jam) {
			this.num = num;
			this.jam = jam;
		}
		@Override
		public int compareTo(Node_2001 o) {
			return getNumofJams(o.jam)-getNumofJams(this.jam);
		}
		
	}
	int getNumofJams(int binary) {
		int i = 0;
		for(i = 0;binary != 0;i++) {
			binary &= (binary-1);
		}
		return i;
	}
}