package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B_16771 {
	HashSet<Integer> set = new HashSet<>();
	int[] A = new int[20];
	public B_16771() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i= 0;i<20;i++) {
			if(i==10) {
				st = new StringTokenizer(br.readLine());
			}
			A[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		System.out.println(set.size());
}catch (Exception e) {}
	}
	void dfs(int count, int num) {
		if(count==4) {
			set.add(num);
			return;
		}else if(count%2==0) {
			for(int i= 0;i<10;i++) {
				int temp = A[9];
				A[9] = A[i];
				A[i] = temp;
				dfs(count+1, num-A[9]);
				temp = A[9];
				A[9] = A[i];
				A[i] = temp;
			}
		}else {
			for(int i= 9;i<20;i++) {
				int temp = A[9];
				A[9] = A[i];
				A[i] = temp;
				dfs(count+1, num+A[9]);
				temp = A[9];
				A[9] = A[i];
				A[i] = temp;
			}
		}
	}
}
