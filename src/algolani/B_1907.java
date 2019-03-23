package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_1907 {
	int[] C = new int[3];
	int[] H = new int[3];
	int[] O = new int[3];
	public B_1907() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		char prev = str.charAt(0);
		int idx = 0;
		if(prev=='C') {
			C[idx]++;
		}else if(prev=='H') {
			H[idx]++;
		}else if(prev=='O') {
			O[idx]++;
		}
		for(int i= 1;i<str.length();i++) {
			char cur = str.charAt(i);
			if(cur>='2' && cur <= '9') {
				if(prev=='C') {
					C[idx]+=cur-'0'-1;
				}else if(prev == 'H') {
					H[idx]+=cur-'0'-1;
				}else if(prev == 'O') {
					O[idx]+=cur-'0'-1;
				}
			}else {
				if(cur=='C') {
					C[idx]++;
				}else if(cur=='H') {
					H[idx]++;
				}else if(cur=='O') {
					O[idx]++;
				}else if(cur == '+' || cur=='=') {
					idx++;
				}
			}
			prev = cur;
		}
		int answer = 111;
		while(!(compareType(C, answer) == 0
			&& compareType(H, answer) == 0
			&& compareType(O, answer) == 0)) {
			System.out.println(answer);
			answer++;
			if((answer/10)%10 == 0){
				answer+=10;
			}
			if((answer/1)%10 == 0){
				answer+=1;
			}
		}
		System.out.println((answer/100)%10 + " " + (answer/10)%10 + " " + (answer/1)%10);
}catch (Exception e) {}
	}
	int compareType(int[] type, int answer) {
		int m1 = (answer/100)%10;
		int m2 = (answer/10)%10;
		int m3 = (answer/1)%10;
		return (type[0] * m1 + type[1] *m2) - (type[2] *m3);
	}
}