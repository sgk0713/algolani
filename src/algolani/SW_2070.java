package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_2070 {
	public SW_2070() {
try {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int num1, num2;
		StringBuilder sb = new StringBuilder("#");
		String big = " >", small = " <", equal = " =";
		for(int t = 1;t<=T;t++) {
			sb.delete(1, 5);
			sb.append(t);
			StringTokenizer st = new StringTokenizer(br.readLine());			
			num1 = Integer.parseInt(st.nextToken());
			num2 = Integer.parseInt(st.nextToken());
			if(num1>num2) {
				sb.append(big);
			}else if(num1<num2) {
				sb.append(small);
			}else {
				sb.append(equal);
			}
			System.out.println(sb);
		}
}catch (Exception e) {}
	}
}
