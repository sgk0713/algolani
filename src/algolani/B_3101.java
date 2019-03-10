package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B_3101 {
	int N, K;
	long answer=1;
	HashMap<Long, Integer> hashMap = new HashMap<>();
	public B_3101() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder(br.readLine());

		int idx = -1;
		int r, c;
		r = c = 0;
		while((idx++)<K-1) {
			switch (sb.charAt(idx)) {
			case 'U':
				r--;
				break;
			case 'D':
				r++;
				break;
			case 'R':
				c++;
				break;
			case 'L':
				c--;
				break;
			}
			if(!hashMap.containsKey((long) (r*N+c))) {
				hashMap.put((long) (r*N+c), 1);
			}else {
				hashMap.put((long) (r*N+c), hashMap.get((long) (r*N+c))+1);
			}
		}
		fillMap();
		System.out.println(answer);
}catch (Exception e) {e.printStackTrace();}
	}
	void fillMap() {
		int r = 0;
		int c = 0;
		long number = 1;
		boolean isGoingUP = true;
		while(!hashMap.isEmpty()) {
			if(hashMap.containsKey((long)r*N+c)) {
				answer += hashMap.get((long)r*N+c)*number;
				hashMap.remove((long)r*N+c);
			}
			number++;
			int tempR = 0;
			int tempC = 0;
			if(isGoingUP) {
				tempR = r-1;
				tempC = c+1;
				if(!isInBoundery(tempR, tempC)) {
					isGoingUP = false;
					tempR = r;
					tempC = c+1;
					if(!isInBoundery(tempR, tempC)) {
						tempR = r + 1;
						tempC = c;
					}
				}
				r = tempR;
				c = tempC;
			}else {
				tempR = r + 1;
				tempC = c - 1;
				if(!isInBoundery(tempR, tempC)) {
					isGoingUP = true;
					tempR = r + 1;
					tempC = c;
					if(!isInBoundery(tempR, tempC)) {
						tempR = r;
						tempC = c + 1;
					}
				}
				r = tempR;
				c = tempC;
			}
		}
	}
	
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<N) {
			return true;
		}
		return false;
	}
}
