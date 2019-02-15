package algolani;

import java.util.Scanner;

public class B_14891 {
	final int S = 1, N = 0;
	final int A = 0, B = 1, C = 2, D = 3;
	final int TURN_LEFT = -1, TURN_RIGHT = 1;
	int[][] raw = new int[4][8];
	int T, curLM, curRM;
	public B_14891() {
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < 4; i++) {
			String s = sc.nextLine();
			for(int j = 0; j< 8; j++) {
				raw[i][j] = s.charAt(j) - '0';
			}
		}
		
		T = sc.nextInt();
		while(T-->0) {
			int temp = sc.nextInt()-1;
			curLM = getLeftMid(temp);
			curRM = getRightMid(temp);
			if(sc.nextInt() == TURN_LEFT) {
				doLeftSide(temp, TURN_LEFT);
				doRightSide(temp, TURN_LEFT);
				turnLeft(temp);
			}else {
				doLeftSide(temp, TURN_RIGHT);
				doRightSide(temp, TURN_RIGHT);
				turnRight(temp);
			}
		}
		System.out.println(getScore());
	}
	
	void doLeftSide(int rawNum, int turn) {
		if(rawNum == A) {
			return;
		}
		int tempL = getLeftMid(rawNum);
		if(tempL != getRightMid(rawNum-1)) {
			if(turn == TURN_LEFT) {
				doLeftSide(rawNum-1, TURN_RIGHT);
				turnRight(rawNum-1);
			}else {
				doLeftSide(rawNum-1, TURN_LEFT);
				turnLeft(rawNum-1);
			}
		}
	}
	void doRightSide(int rawNum, int turn) {
		if(rawNum == D) {
			return;
		}
		int tempR = getRightMid(rawNum);
		if(tempR != getLeftMid(rawNum+1)) {
			if(turn == TURN_LEFT) {
				doRightSide(rawNum+1, TURN_RIGHT);
				turnRight(rawNum+1);
			}else {
				doRightSide(rawNum+1, TURN_LEFT);
				turnLeft(rawNum+1);
			}
		}
	}
	void turnRight(int r) {
		int temp = raw[r][7];
		for(int i = 7; i > 0; i--) {
			raw[r][i] = raw[r][i-1];
		}
		raw[r][0] = temp;
		
	}
	
	void turnLeft(int r) {
		int temp = raw[r][0];
		for(int i = 0; i < 7; i++) {
			raw[r][i] = raw[r][i+1];
		}
		raw[r][7] = temp;
	}
	
	void printArray(int r) {
		for(int i = 0; i < 8;i++) {
			System.out.print(raw[r][i]);
		}
		System.out.println();
	}
	int getLeftMid(int r) {
		return raw[r][6];
	}
	int getRightMid(int r) {
		return raw[r][2];
	}
	int getTop(int r) {
		return raw[r][0];
	}
	int getScore() {
		int sum = 0;
		for(int i = 0; i<4; i++) {
			if(getTop(i) == S) {
				sum += (int)Math.pow(2, i);
			}
		}
		return sum;
	}
}
