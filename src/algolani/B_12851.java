package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_12851 {
	int N, K;
	int tempX, tempTime;
	final int MIN = 0, MAX = 100000;
	int[] arr = new int[3];
	boolean[] isVisited = new boolean[MAX+1]; 
	Queue<Pair_12851> q = new LinkedList<>();
	int answer1 = 100001, answer2 = 0;//수빈이가 동생을 찾는 가장빠른 시간, 그 시간으로 동생을 찾는 방법의 수
	B_12851(){
							try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tempStr = br.readLine();
		StringTokenizer st = new StringTokenizer(tempStr, " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N >= K) {
			System.out.println(Math.abs(N-K));
			System.out.println(1);
			return;
		}
		q.add(new Pair_12851(N, 0));
		while(!q.isEmpty()) {
			Pair_12851 temp = q.poll();
			tempTime = temp.time;
			tempX = temp.curX;
			arr[0] = tempX - 1;
			arr[1] = tempX + 1;
			arr[2] = tempX * 2;
			if(tempTime > answer1) {
				continue;
			}
			if(tempX == K) {
				findAnswer(tempTime);
				continue;
			}
			isVisited[tempX] = true;
			for(int i = 0; i< 3; i++) {
				if(arr[i] >= MIN && arr[i] <= MAX && !isVisited[arr[i]]) {
					q.add(new Pair_12851(arr[i], tempTime+1));
				}
			}
		}
		
		System.out.println(answer1);
		System.out.println(answer2);
		
							}catch (Exception e) {}
	}
	void findAnswer(int time) {
		if(time < answer1) {
			answer1 = time;
			answer2 = 1;
			return;
		}
		if(time == answer1) {
			answer2++;
			return;
		}
	}
}

class Pair_12851 {
	int curX;
	int time;
	Pair_12851(int curX, int time){
		this.curX = curX;
		this.time = time;
	}
}