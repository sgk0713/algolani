package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16397 {
	public B_16397() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int curNum = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());
		String answer = "ANG";
		
		if(curNum == goal) {//주어진값과 현재 값이 같으면 0 출력
			System.out.println(0);
		}
		
		boolean isChecked[] = new boolean[100_000];
		Queue<Integer> q = new LinkedList<>();
		q.offer(curNum);
		isChecked[curNum] = true;
		int count = 1;
		while(!q.isEmpty() && count<=T) {
			int qSize = q.size();
			for(int k = 0;k<qSize;k++) {
				int tempNum = q.poll();
				int A = pressA(tempNum);
				int B = pressB(tempNum);
				if(A != -1 && isChecked[A] == false) {
					if(A == goal) {
						answer = count+"";
						q.clear();
						break;
					}
					q.offer(A);
				}
				if(B != -1 && isChecked[B] == false) {
					if(B == goal) {
						answer = count+"";
						q.clear();
						break;
					}
					q.offer(B);
				}
			}
			count++;
		}
		System.out.println(answer);
}catch (Exception e) {}
	}
	int pressA(int num) {
		num++;
		if(num<100000) {
			return num;
		}else {
			return -1;
		}
		
	}
	int pressB(int num) {
		if(num == 0) {
			return num;
		}
		num *= 2;
		if(num/10000 >= 10) {
			return -1;
		}else {
			for(int i = 10000;i>0;i/=10) {
				if(num/i != 0) {
					num -= i;
					return num;
				}
			}
		}
		return -1;
		
	}
}
