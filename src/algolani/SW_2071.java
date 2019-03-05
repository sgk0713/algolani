package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_2071 {
	public SW_2071() {
try {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int temp = 0;
		for(int t = 1;t<=T;t++) {
			int answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				answer += Integer.parseInt(st.nextToken());
			}
			System.out.println("#"+t + " "+ (int)Math.round(answer/10.0));
		}
}catch (Exception e) {}
	}
}
