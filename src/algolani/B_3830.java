package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B_3830 {
	int N, M=1;
	int mark, a, b, w;
	int[][] sample;
	int[] root = new int[1000000];
	HashSet<Integer> history;
	final int Q_MARK = '?';
	final int E_MARK = '!';
	ArrayList<Node_3830> list = new ArrayList<>();
	//BST로 풀어보자
	public B_3830() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while(M!=0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			sample = new int[N+1][N+1];
			history = new HashSet<>();
			for(int i = 0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				mark = st.nextToken().charAt(0);
				if(mark == E_MARK) {
					a = Integer.parseInt(st.nextToken());
					b = Integer.parseInt(st.nextToken());
					w = Integer.parseInt(st.nextToken());
					Node_3830 head = null;
					if(!history.contains(b)) {
						int len = list.size();
						for(int j = 0;j<len;j++) {
							if(list.get(j).set.contains(b)) {
								head = list.get(j);
								while(head!=null && head.value!=b) {
									head = head.next;
								}
								break;
							}
						}
					}
					if(head == null) {
					}
				}else if(mark == Q_MARK){
					a = Integer.parseInt(st.nextToken());
					b = Integer.parseInt(st.nextToken());
					int answer = 0;
					int len = list.size();
					for(int j = 0;j<len;j++) {
						if(list.get(j).set.contains(b) && list.get(j).set.contains(a)) {
							Node_3830 head = list.get(j);
							while(head.value!=b) {
								head = head.next;
							}
							while(head.value!=a) {
								answer += head.next.diff;
								head = head.next;
							}
						}
					}
					
					if(answer!=0) {
						System.out.println(answer);
					}else {
						System.out.println("UNKNOWN");
					}
				}
			}
		}
		
}catch (Exception e) {e.printStackTrace();}
	}
	void Union(int a, int b) {
		
	}
	int find(int a) {
		if(root[a] < 0) return a;
		return root[a] = find(root[a]);
	}
	class Node_3830{
		int value, diff;
		Node_3830 prev, next;
		HashSet<Integer> set;
		public Node_3830(int value, int diff, Node_3830 prev, Node_3830 next) {
			this.value = value;
			this.diff = diff;
			this.prev = prev;
			this.next = next;
		}
	}
}