package algolani;

import java.util.Arrays;
import java.util.Scanner;

public class SW_1219 {
	int[] map1 = new int[100];
	int[] map2 = new int[100];
	int t;
	int len;
	int tmpIdx, tmpVal;
	boolean isPossible = false;
	public SW_1219() {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			Arrays.fill(map1, -1);
			Arrays.fill(map2, -1);
			isPossible = false;
			t = sc.nextInt();
			len = sc.nextInt();
			for(int i = 0; i< len;i++) {
				tmpIdx = sc.nextInt();
				tmpVal = sc.nextInt();
				if(map1[tmpIdx] == -1) {
					map1[tmpIdx] = tmpVal;
				}else {
					map2[tmpIdx] = tmpVal;
				}
			}
			dfs(0);
			if(isPossible == true) {
				System.out.println("#"+t+" "+1);
			}else {
				System.out.println("#"+t+" "+0);
			}
		}
		sc.close();
	}
	
	void dfs(int idx) {
		if(idx==99) {
			isPossible = true;
		}
		if(map1[idx] != -1) {
			dfs(map1[idx]);
		}
		if(map2[idx] != -1) {
			dfs(map2[idx]);
		}
	}
}
