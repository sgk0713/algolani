package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_16113 {
	int nextLineIdx = 0;
	final int CASE_ONE = 1;// 8 0 6 1
	final int CASE_TWO = 2;// 2 3 7, 아래 한칸이 비워져있는 경우
	final int CASE_THREE = 3;// 4 5 9, 아래 한칸은 채워져있고, 칸이 비워져있는 경우
	StringBuilder sb;
	public B_16113() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		nextLineIdx = N/5;
		sb = new StringBuilder(br.readLine());
		sb.trimToSize();
		int curIdx = 0;
		StringBuilder answer = new StringBuilder();
		while(sb.charAt(curIdx) != '#') {
			curIdx++;
			if(curIdx >= nextLineIdx) {
				break;
			}
		}
		//현재 값아래로 전부 채워져있는 경우 8 0 6 1
		//현재 값 한칸 아래가 비워져있는 경우 2 3 7
		//현재 값 세칸 아래만 비어져 있는 경우 4 5 9
		if(nextLineIdx == 1) {
			answer.append(1);
			curIdx = nextLineIdx;
		}
		while(curIdx < nextLineIdx) {
			switch (whichCase(curIdx)) {
			case CASE_ONE://8 0 6 1
				if(curIdx+2 < nextLineIdx) {
					if(sb.charAt(curIdx+1) != '#') {//1
						answer.append(1);
						curIdx += 1;
						break;
					}else if(sb.charAt(curIdx+1+(nextLineIdx*2)) != '#'){//0
						answer.append(0);
					}else if(sb.charAt(curIdx+2 + (nextLineIdx)) != '#'){//6
						answer.append(6);
					}else {//8
						answer.append(8);
					}
					curIdx+= 3;
				}else {
					answer.append(1);
					curIdx += 1;
				}
				break;
			case CASE_TWO://2 3 7
				if(sb.charAt(curIdx+nextLineIdx*3) == '#') {//2
					answer.append(2);
				}else if(sb.charAt(curIdx+nextLineIdx*4) == '#') {//3
					answer.append(3);
				}else {//7
					answer.append(7);
				}
				curIdx+= 3;
				break;
			case CASE_THREE://4 5 9
				if(sb.charAt(curIdx+nextLineIdx*4) != '#') {//4
					answer.append(4);
				}else if(sb.charAt(curIdx + 2 + nextLineIdx) == '#') {//9
					answer.append(9);
				}else {//5
					answer.append(5);
				}
				curIdx+= 3;
				break;
			}
			
			while(sb.charAt(curIdx) != '#') {
				curIdx++;
				if(curIdx >= nextLineIdx) {
					break;
				}
			}
		}
		System.out.println(answer.toString());
}catch (Exception e) {e.printStackTrace();}
	}
	int whichCase(int idx) {
		//아래 한칸
		if(sb.charAt(idx+nextLineIdx) != '#') {
			return CASE_TWO;// 2 3 7, 아래 한칸이 비워져있는 경우
		}
		//아래 세칸
		if(sb.charAt(idx+nextLineIdx*3) != '#') {
			return CASE_THREE;// 4 5 9, 아래 한칸은 채워져있고, 칸이 비워져있는 경우
		}
		
		return CASE_ONE;// 8 0 6 1, 다 채워진 경우
	}	
}