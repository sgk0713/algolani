package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16928 {//뱀과 사다리 게임
	int answer;
	int teleport[];
	boolean isChecked[] = new boolean[101];
	public B_16928() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Queue<Node_16928> q = new LinkedList<>();
		teleport = new int[101];
		for(int i= 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());			
			teleport[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			teleport[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		q.offer(new Node_16928(1, 0));
		while(!q.isEmpty()) {
			Node_16928 temp = q.poll();
			int idx = temp.idx;
			int count = temp.count;
			if(idx == 100) {
				answer = count;
				break;
			}
			for(int i= 6;i>0;i--) {
				if(idx+i<=100 && isChecked[idx+i] == false) {
					if(idx+i == 100) {
						answer = count +1;
						q.clear();
						break;
					}
					isChecked[idx+i] = true;
					if(teleport[idx+i] != 0) {
						q.offer(new Node_16928(teleport[idx+i], count+1));
					}else {
						q.offer(new Node_16928(idx+i, count+1));
					}
				}
			}
		}
		System.out.println(answer);
}catch (Exception e) {}	
	}
	class Node_16928{
		int idx, count;
		public Node_16928(int idx, int count) {
			this.idx = idx;
			this.count = count;
		}
	}
}
