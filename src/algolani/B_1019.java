package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_1019 {
	int zero, one, two, three, four, five, six, seven, eight, nine;
	public B_1019() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		
		
		answer.append(zero + " " + one + " " +  two + " " +  three + " " +  four + " " +  five + " " +  six + " " +  seven + " " +  eight + " " +  nine);
		System.out.println(answer.toString());
}catch (Exception e) {}
	}
}
