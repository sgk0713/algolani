package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1152 {
	public B_1152() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int answer = 0;
		while(st.hasMoreTokens()) {
			answer++;
			st.nextToken();
		}
		System.out.println(answer);
}catch (Exception e) {}
	}
}
