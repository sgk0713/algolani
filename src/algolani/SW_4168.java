package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.StringTokenizer;

public class SW_4168 {//4168. 삼성이의 쇼핑 ( 비트마스크 + 조합 연습 )
	int money, clothNum, maxSatis, answerKey, answerSatis;
	int[] prices;
	int[] satisfactions;
	StringBuilder answer = new StringBuilder();
	PriorityQueue<Node_4168> q = new PriorityQueue<>();
	public SW_4168() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			answer.delete(0, answer.length());
			StringTokenizer st = new StringTokenizer(br.readLine());
			money = Integer.parseInt(st.nextToken());
			clothNum = Integer.parseInt(st.nextToken());
			prices = new int[clothNum];
			satisfactions = new int[clothNum];
			answerSatis = 0;
			answerKey = 0;
			
			for(int i = 0 ;i< clothNum;i++) {
				st = new StringTokenizer(br.readLine());
				prices[i] = Integer.parseInt(st.nextToken());
				maxSatis += satisfactions[i] = Integer.parseInt(st.nextToken());
			}
			q.offer(new Node_4168(0, 0, 0, 0, maxSatis));
			while(!q.isEmpty()) {
				Node_4168 temp = q.poll();
				int key = temp.key;
				int inputIdx = temp.idx;
				int satisfaction = temp.satisfaction;
				int price = temp.price;
				int estimate = temp.estimate;
				
				if(satisfaction > answerSatis) {
					answerSatis = satisfaction;
					answerKey = key;
				}
				if(estimate < answerSatis || inputIdx >= clothNum) {
					continue;
				}
				//넣는경우
				if(price + prices[inputIdx] <= money) {
					q.offer(new Node_4168(key|(1<<inputIdx), inputIdx+1, satisfaction+satisfactions[inputIdx], price + prices[inputIdx], estimate));
				}
				//안넣는경우
				q.offer(new Node_4168(key, inputIdx+1, satisfaction, price, estimate-satisfactions[inputIdx]));
			}
			for(int i= 0;i<clothNum;i++) {
				if((answerKey&(1<<i)) != 0) {
					answer.append(i+" ");
				}
			}
			System.out.println("#"+ t + " "+ answer + answerSatis);
		}
}catch (Exception e) {e.printStackTrace();}
	}
	class Node_4168 implements Comparable<Node_4168>{
		int key, idx, satisfaction, price, estimate;
		public Node_4168(int key, int idx, int satisfaction, int price, int estimate) {
			this.key = key;
			this.idx = idx;
			this.satisfaction = satisfaction;
			this.price = price;
			this.estimate = estimate;
		}

		@Override
		public int compareTo(Node_4168 o) {
			return o.estimate - this.estimate;
		}
	}
}
