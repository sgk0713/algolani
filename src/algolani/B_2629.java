package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2629 {//양팔저울
	int N;
	int[] weights;
	HashSet<Integer> set;
	Queue<Integer> q = new LinkedList<>();
	public B_2629() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		set = new HashSet<>();
		weights = new int[30];//추는 최대 30개
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0;i< N;i++) {
			int tempWeight = weights[i] = Integer.parseInt(st.nextToken());//추 입력 받기
			q.add(1<<i);
			if(!set.contains(tempWeight)) {
				set.add(tempWeight);
			}
		}
		while(!q.isEmpty()) {
			int key = q.poll();
			int weight = getNumberWithKey(key);
			for(int i = 0;i<N;i++) {
				if((key&(1<<i)) == 0) {
					if(!set.contains(Math.abs(weight-weights[i]))) {
						set.add(Math.abs(weight-weights[i]));
					}
					int sum = weight+weights[i];
					if(!set.contains(sum)) {
						set.add(sum);
					}
					q.offer(key|1<<i);
				}
			}
		}
		N = Integer.parseInt(br.readLine());//변수 재활용
		st = new StringTokenizer(br.readLine());
		for(int i = 0;i< N;i++) {//추로 구할 수 있는 무게인지 확인해서 답안 출력
			if(set.contains(Integer.parseInt(st.nextToken()))) {
				System.out.print("Y ");
			}else {
				System.out.print("N ");
			}
		}
}catch (Exception e) {e.printStackTrace();}
	}
	int getNumberWithKey(int key) {
		int returnValue = 0;
		for(int i = 0;i<N;i++) {
			if((key&(1<<i))==1) {
				returnValue+=weights[i];
			}
		}
		return returnValue;
	}
}
