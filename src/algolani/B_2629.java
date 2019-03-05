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
	boolean[] isPossibleWeight = new boolean[15002];
	HashSet<Integer> set = new HashSet<>();
	Queue<Integer> q = new LinkedList<>();
	public B_2629() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		weights = new int[30];//추는 최대 30개
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tempVal = 0, tempWeight = 0;
		for(int i = 0;i< N;i++) {
			tempWeight = weights[i] = Integer.parseInt(st.nextToken());//추 입력 받기
			q.offer(tempWeight);
			isPossibleWeight[tempWeight] = true;
			for(Integer nextInt : set) {
				tempVal = nextInt + tempWeight;
				if(isPossibleWeight[tempVal] == false) {
					isPossibleWeight[tempVal] = true;
					q.offer(tempVal);
				}
			}
			while(!q.isEmpty()) {
				tempVal = q.poll();
				if(!set.contains(tempVal)) {
					set.add(tempVal);
				}
			}
		}
		for(Integer nextInt: set) {
			tempVal = nextInt;
			for(Integer i : set) {
				int temp = Math.abs(tempVal-i);
				if(isPossibleWeight[temp] == false) {
					isPossibleWeight[temp] = true;
					q.offer(temp);
				}
			}
		}
		while(!q.isEmpty()) {
			tempVal = q.poll();
			if(!set.contains(tempVal)) {
				set.add(tempVal);
			}
		}
		
		N = Integer.parseInt(br.readLine());//변수 재활용
		st = new StringTokenizer(br.readLine());
		for(int i = 0;i< N;i++) {//추로 구할 수 있는 무게인지 확인해서 답안 출력
			tempWeight = Integer.parseInt(st.nextToken());
			if(tempWeight <= 500*30 && set.contains(tempWeight)) {
				System.out.print("Y ");
			}else{
				System.out.print("N ");
			}
		}
}catch (Exception e) {e.printStackTrace();}
	}
}