package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_2072 {
	public SW_2072() {
try {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int temp = 0;
		for(int t = 1;t<=T;t++) {
			int answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				temp = Integer.parseInt(st.nextToken());
				if(temp%2==1) {
					answer+=temp;
				}
			}
			System.out.println("#"+t + " "+ answer);
		}
}catch (Exception e) {}
	}
}
