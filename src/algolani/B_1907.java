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
		for(int i = 1;i<=10;i++) {
			for(int j = 1;j<=10;j++) {
				for(int k = 1;k<=10;k++) {
					if(compareType(C, i,j,k) == 0
						&& compareType(H, i,j,k) == 0
						&& compareType(O, i,j,k) == 0) {
						System.out.println(i+ " " + j + " " + k);
						System.exit(0);
					}
				}
			}
		}
}catch (Exception e) {}
	}
	int compareType(int[] type, int m1, int m2, int m3) {
		return (type[0] * m1 + type[1] *m2) - (type[2] *m3);
	}
}