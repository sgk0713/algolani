package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_3967 {
	//매직스타
	int[] temp = new int[13];
	int[] numberSlot = new int[13];
	char[][] answer = new char[5][9];
	boolean[] isUsed = new boolean[12];
	public B_3967() {try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tempStr = null;
		int k = 0;
		for(int i = 0;i< 5;i++) {
			tempStr = br.readLine();
			for(int j = 0;j< 9;j++) {
				char tempChar = answer[i][j] = tempStr.charAt(j);
				if(tempChar != '.') {
					if(tempChar == 'x') {
						numberSlot[k] = 0;
					}else {
						numberSlot[k] = tempChar-'A'+1;
						isUsed[tempChar-'A'] = true;
					}
					k++;
				}
			}
		}
		dfs(0);
			
		}catch (Exception e) {e.printStackTrace();}
	}
	
	void dfs(int idx) {
		if(idx == 12) {//끝까지 채워지면 정답인지 확인하고 정답이면 출력하고 끝낸다
			if(isAnswer()) {
				showAnswer();
				System.exit(0);
			}
			return;
		}
		
		if(numberSlot[idx] != 0) {//숫자가 채워져있다면 다음 숫자로 넘어간다.
			dfs(idx+1);
		}else {
			for(int i= 0;i<12;i++) {
				if(isUsed[i] == false) {
					numberSlot[idx] = i+1;
					isUsed[i] = true;
					dfs(idx+1);
					numberSlot[idx] = 0;
					isUsed[i] = false;
				}
			}
		}
	}
	void showAnswer() {
		int n = 0;
		for(int i= 0;i<5;i++) {
			for(int j =0;j<9;j++) {
				if(answer[i][j]!='.') {
					if(answer[i][j] == 'x') {
						System.out.print((char)(numberSlot[n] + 'A' -1));
					}else {
						System.out.print(answer[i][j]);
					}
					n++;
				}else {
					System.out.print(answer[i][j]);
				}
			}
			System.out.println();
		}
	}
	boolean isAnswer() {
		//	 0
		//1 2 3 4
		// 5   6
		//7 8 9 10
		//   11
		if(numberSlot[0] + numberSlot[2] + numberSlot[5] + numberSlot[7] != 26) {
			return false;
		}
		if(numberSlot[1] + numberSlot[2] + numberSlot[3] + numberSlot[4] != 26) {
			return false;
		}
		if(numberSlot[0] + numberSlot[3] + numberSlot[6] + numberSlot[10] != 26) {
			return false;
		}
		if(numberSlot[7] + numberSlot[8] + numberSlot[9] + numberSlot[10] != 26) {
			return false;
		}
		if(numberSlot[1] + numberSlot[5] + numberSlot[8] + numberSlot[11] != 26) {
			return false;
		}
		if(numberSlot[4] + numberSlot[6] + numberSlot[9] + numberSlot[11] != 26) {
			return false;
		}
		return true;
	}
}