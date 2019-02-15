package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B_1449 {
	//수리공 항승
	int N,L, answer;
	HashSet<Integer> set = new HashSet<>(); 
	int[] arr;
	public B_1449() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0;i< N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//입력받고
		Arrays.sort(arr);//오름차순으로 정렬
		
		for(int i = 0;i<N;i++) {
			int value = arr[i];
			if(!set.contains(value)) {//set에 없으면
				for(int j = 0;j<L && value<=1000;j++) {
					set.add(value++);//하나씩 늘리며 추가
				}
				answer++;//테이프 하나 사용 증가
			}
		}
		System.out.println(answer);
}catch (Exception e) {}
	}
}
