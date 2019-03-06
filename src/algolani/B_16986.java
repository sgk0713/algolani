package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16986 {
	final int Jiwoo = 0;
	final int Kyung = 1;
	final int Min   = 2;
	int N, K, answer;
	int JiC, KyC, MiC;
	int rule[][];
	int[][] JisOrder;
	int[][] order = new int[3][20];
	Queue<Person_16986> q = new LinkedList<>();
	public B_16986() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken())+1;
		K = Integer.parseInt(st.nextToken());
		rule = new int[N][N];
		
		JisOrder = new int[N][20];
		for(int i= 1;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1;j<N;j++) {
				rule[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1;i<3;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<20;j++) {
				order[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			if(JiC==K || KyC == K || MiC == K) {
				if(JiC==K) {
					answer = 1;
					break;
				}else {
					initQue();
				}
			}
		}
		System.out.println(answer);
}catch (Exception e) {}
	}
	void dfs(int key) {
		
	}
	class Person_16986{
		int name, turn;
		public Person_16986(int name, int turn) {
			this.name = name;
			this.turn = turn;
		}
	}
	void playGame() {
		Person_16986 play1 = q.poll();
		Person_16986 play2 = q.poll();
		
	}
	void initQue(int idx) {
		q.clear();
		JiC = KyC = MiC = 0;
		q.offer(new Person_16986(Jiwoo, 0));
		q.offer(new Person_16986(Kyung, 0));
		q.offer(new Person_16986(Min, 0));
	}
}
