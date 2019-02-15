package algolani;

import java.util.Scanner;

public class SW_1220 {
	int[][] map = new int[100][100];
	int count, number = 1;
	boolean deadLockFlag;
	public SW_1220() {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			count = 0;
			deadLockFlag = false;
			sc.nextInt();
			for(int i = 0; i<100;i++) {
				for(int j = 0; j< 100; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			for(int j = 0; j< 100; j++) {
				deadLockFlag = false;
				for(int i = 0; i<100;i++) {
					if(deadLockFlag==false && map[i][j] == 1) {
						deadLockFlag = true;
					}else if(deadLockFlag == true && map[i][j] == 2) {
						deadLockFlag = false;
						count++;
					}
				}
			}
			System.out.println("#"+number + " " + count);
			number++;
		}
	}
}
