package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16235 {
	int N, M, K;
	int[][] energyMap, map;
	int[] dirR = {0, 1, 0, -1, 1, 1, -1, -1};
	int[] dirC = {1, 0, -1, 0, 1, -1, 1, -1};
	PriorityQueue<Tree_16235> q = new PriorityQueue<>();
	Queue<Tree_16235> deadTree = new LinkedList<>();
	Queue<Tree_16235> makeOcta = new LinkedList<>();
	Queue<Tree_16235> nextYearTree = new LinkedList<>();
	public B_16235() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken())+1;
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		energyMap = new int[N][N];
		map = new int[N][N];
		for(int i= 1;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1;j<N;j++) {
				map[i][j] = 5;
				energyMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			nextYearTree.add(new Tree_16235(r, c, age));
		}
		int year = 0;
		while(!nextYearTree.isEmpty() && year<K) {
			year++;
			while(!nextYearTree.isEmpty()) {
				q.offer(nextYearTree.poll());
			}
			int qSize = q.size();
			//spring
			for(int t = 0;t<qSize;t++) {
				Tree_16235 tree = q.poll();
				int r = tree.r;
				int c = tree.c;
				int age = tree.age;
				if(map[r][c]>=age) {
					map[r][c] -= age;
					tree.age++;
					if(tree.age%5==0) {
						makeOcta.offer(tree);
					}
					nextYearTree.offer(tree);
				}else {
					tree.age /= 2;
					deadTree.offer(tree);
				}
			}
			//summer
			while(!deadTree.isEmpty()) {
				Tree_16235 tree = deadTree.poll();
				map[tree.r][tree.c] += tree.age;
			}
			//autumn
			while(!makeOcta.isEmpty()) {
				Tree_16235 tree = makeOcta.poll();
				int r = tree.r;
				int c = tree.c;
				for(int i= 0;i<8;i++) {
					int rr = r + dirR[i];
					int cc = c + dirC[i];
					if(isInBoundery(rr, cc)) {
						nextYearTree.offer(new Tree_16235(rr, cc, 1));
					}
				}
			}
			//winter
			if(year<K) {
				addEnergy();
			}else {
				break;
			}
		}
		System.out.println(nextYearTree.size());
}catch (Exception e) {}
	}
	void addEnergy() {
		for(int i = 1;i<N;i++) {
			for(int j =1 ;j<N;j++) {
				map[i][j]+=energyMap[i][j];
			}
		}
	}
	boolean isInBoundery(int r, int c){
		if(r>0 && r<N && c>0 && c<N) {
			return true;
		}
		return false;
		
	}
	class Tree_16235 implements Comparable<Tree_16235>{
		int r, c, age;
		public Tree_16235(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
		@Override
		public int compareTo(Tree_16235 o) {
			return this.age - o.age;
		}
	}
}
