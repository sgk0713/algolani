package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_2477 {
	int answer, N, M, K, A, B, curTime;
	Queue<Customer_2477> q = new LinkedList<>();
	PriorityQueue<Customer_2477> pq = new PriorityQueue<>();
	public SW_2477() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			answer = N = M = K = A = B = 0;
			curTime = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			Customer_2477[] Aline = new Customer_2477[N+1];
			Customer_2477[] Bline = new Customer_2477[M+1];
			int[] timeA = new int[N+1];
			int[] timeB = new int[M+1];
			st = new StringTokenizer(br.readLine());
			for(int i= 1;i<N+1;i++) {
				timeA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i= 1;i<M+1;i++) {
				timeB[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int k =1;k<=K;k++) {
				q.offer(new Customer_2477(k, -1, Integer.parseInt(st.nextToken())));
			}
			while(true) {
				for(int i = 1;i<=N;i++) {
					if(Aline[i] != null && Aline[i].time == curTime) {
						pq.add(Aline[i]);
						Aline[i] = null;
					}
				}
				int ASlot = getEmptySlot(Aline);
				while(ASlot != -1) {
					Customer_2477 cus = q.peek();
					if(cus != null && cus.time <= curTime) {
						Aline[ASlot] = q.poll();
						Aline[ASlot].used = ASlot;
						Aline[ASlot].time = curTime+timeA[ASlot];
					}else {
						break;
					}
					ASlot = getEmptySlot(Aline);
				}
				for(int i = 1;i<=M;i++) {
					if(Bline[i] != null && Bline[i].time == curTime) {
						if(Bline[i].used == A && i == B) {
							answer += Bline[i].number;
						}
						Bline[i] = null;
					}
				}
				int BSlot = getEmptySlot(Bline);
				while(BSlot != -1) {
					Customer_2477 cus = pq.peek();
					if(cus != null) {
						Bline[BSlot] = pq.poll();
						Bline[BSlot].time = curTime+timeB[BSlot];
						BSlot = getEmptySlot(Bline);
					}else {
						break;
					}
				}
				if(q.isEmpty() && pq.isEmpty() && isEmpty(Aline) && isEmpty(Bline)) {
					break;
				}
				curTime++;
			}
			if(answer == 0) {
				answer = -1;
			}
			System.out.println("#"+ t+" " + answer);
		}
}catch (Exception e) {e.printStackTrace();}
	}
	class Customer_2477 implements Comparable<Customer_2477>{
		int number, used, time;
		public Customer_2477(int customerNumber, int used, int time) {
			this.number = customerNumber;
			this.used = used;
			this.time = time;
		}
		@Override
		public int compareTo(Customer_2477 o) {
			if(this.time>o.time) return 1;
			if(this.time==o.time) {
				return this.used-o.used;
			}
			return -1;
		}
	}
	boolean isEmpty(Customer_2477[] arr) {
		for(int i= 1;i<arr.length;i++) {
			if(arr[i] != null) {
				return false;
			}
		}return true;
	}
	int getEmptySlot(Customer_2477[] arr) {
		for(int i= 1;i<arr.length;i++) {
			if(arr[i] == null) {
				return i;
			}
		}return -1;
	}
}
