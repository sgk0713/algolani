package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16397 {//탈출
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
		}else {
			boolean isChecked[] = new boolean[100_000];
			Queue<Integer> q = new LinkedList<>();
			q.offer(curNum);//초기값 넣기
			isChecked[curNum] = true;//초기화
			int count = 0;//초기값
			while(!q.isEmpty() && count<T) {//큐가 비거나, 주어진 횟수를 넣어가면 while문은 끝난다
				int qSize = q.size();//큐사이즈
				count++;//증가
				for(int k = 0;k<qSize;k++) {
					int tempNum = q.poll();
					int A = pressA(tempNum);//A버튼을 눌렀을 때의 값
					int B = pressB(tempNum);//B버튼을 눌렀을 때의 값
					if(A != -1 && isChecked[A] == false) {//정상값이고, 들리지 않은 곳이면
						if(A == goal) {//목표값이면
							answer = count+"";//답 갱신
							q.clear();
							break;
						}
						isChecked[A] = true;
						q.offer(A);
					}
					if(B != -1 && isChecked[B] == false) {//정상값이고, 들리지 않은 곳이면
						if(B == goal) {//목표값이면
							answer = count+"";//답 갱신
							q.clear();
							break;
						}
						isChecked[B] = true;
						q.offer(B);
					}
				}
				
			}
			System.out.println(answer);//답 출력
		}
}catch (Exception e) {}
	}
	int pressA(int num) {//1증가하는 버튼
		num++;
		if(num<100000) {//범위안이면 증가한 값 리턴
			return num;
		}else {
			return -1;//아니면 -1 리턴
		}
		
	}
	int pressB(int num) {//2배 증가시키고, 최대자리수 1빼는 버튼
		if(num == 0) {//0이면 그냥 0리턴
			return 0;
		}
		num <<= 1;//2배를 곱한다
		if(num/100000 >= 1) {//100_000으로 나눈값이 1이상이 나온다면 -1 리턴 
			return -1;
		}else {//범위안에 있는 경우
			for(int i = 10000;i>0;i/=10) {//최대자리수부터 0이 아닌값이 있는지 체크
				if(num/i >= 1) {//0이아니라면
					num -= i;//그 자리수만큼 뺀 값을 리턴
					return num;
				}
			}
		}
		return -1;//default
	}
}
