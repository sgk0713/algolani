package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_15898 {//81:11
	int N;
	final int W = 0;
	final int R = 1;
	final int B = 2;
	final int G = 3;
	final int Y = 4;
	int[][][] map = new int [4][5][5];
	int[][][] colorMap = new int[4][5][5];
	int[][][] addMap;
	int[][][] addColorMap;
	boolean[] isUsed;
	int answer;
	public B_15898() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		addMap = new int[N][4][4];//재료의 효능
		addColorMap = new int[N][4][4];//재료의 원소
		isUsed = new boolean[N];//어떤 재료를 썼는지 체크 하기 위한 배열
		for(int k = 0;k<N;k++) {
			for(int i = 0;i<4;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0;j<4;j++) {
					addMap[k][i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i = 0;i<4;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0;j<4;j++) {
					int temp = 0;
					switch (st.nextToken()) {
					case "R":
						temp = R;
						break;
					case "B":
						temp = B;
						break;
					case "G":
						temp = G;
						break;
					case "Y":
						temp = Y;
						break;
					case "W":
						temp = W;
						break;
					}
					addColorMap[k][i][j] = temp;
				}
			}
		}//원소 입력 완료
		dfs(0);//답찾기
		System.out.println(answer);//답출력
}catch (Exception e) {e.printStackTrace();}
	}
	void dfs(int count) {
		if(count == 3) {//3개의 재료를 다 넣었을 때 답갱신
			int tempAnswer = getTotal();
			if(tempAnswer>answer) {
				answer = tempAnswer;
			}
			return;
		}else {
			for(int i= 0;i<N;i++) {//N개의 재료의 순서를 재귀로 정한다
				if(isUsed[i] == false) {//사용하지않은 재료라면
					isUsed[i] = true;//사용했다고 표시
					for(int j = 0;j<4;j++) {//시계방향으로 4번 돌리면서 체크한다
						rotateMap(i);//i번째 재료를 회전
						for(int k = 0;k<4;k++) {//재료를 넣을 공간은 4가지 경우 이므로 각 경우에 따라 넣어주고 확인한다
							fillMap(k, count, i);//재료넣기
							dfs(count+1);//다음 재료 진행
						}
					}
					isUsed[i] = false;//시계방향으로 돌린 재료를 가마의 각각의 위치에 다 넣어봤다면, 사용안함으로 복귀. 다음 재귀때 사용하기 위해서
				}
			}
		}
	}
	void fillMap(int num, int count, int addMapNum) {
		int starti = 0, startj = 0;//num인자값에 따라 재료를 넣는 위치를 구분한다
		if(num == 0) {
			starti = 0;startj=0;//0,0부터 넣을 경우
		}else if(num == 1) {
			starti = 0;startj=1;//0,1부터 넣을 경우
		}else if(num == 2) {
			starti = 1;startj=0;//1,0부터 넣을 경우
		}else if(num == 3) {
			starti = 1;startj=1;//1,1부터 넣을 경우
		}
		for(int i= 0;i<5;i++) {
			for(int j=0;j<5;j++) {
				map[count+1][i][j] = map[count][i][j];//이전의 값을 다음 맵에 넣어준다
				colorMap[count+1][i][j] = colorMap[count][i][j];//원소도 마찬가지로 넣어준다
				if(i>=starti && i < starti+4 && j>=startj && j < startj+4) {//재료를 넣을 위치라면
					map[count+1][i][j] += addMap[addMapNum][i-starti][j-startj];//효능을 추가해주고
					if(map[count+1][i][j]<0) {//조건에 맞게 값 설정
						map[count+1][i][j] = 0;
					}else if(map[count+1][i][j]>9) {
						map[count+1][i][j] = 9;
					}
					if(addColorMap[addMapNum][i-starti][j-startj] != W) {//흰색이 아닌경우엔 가지고있는 원소 적용
						colorMap[count+1][i][j] = addColorMap[addMapNum][i-starti][j-startj];
					}
				}
			}
		}
	}
	void rotateMap(int idx) {//시계방향으로 돌린다
		int[][] tempMap = new int[4][4];
		int[][] tempColorMap = new int[4][4];
		for(int i= 0;i<4;i++) {
			for(int j = 0;j<4;j++) {
				tempMap[i][j] = addMap[idx][i][j];
				tempColorMap[i][j] = addColorMap[idx][i][j];
			}
		}
		for(int i= 0;i<4;i++) {
			for(int j = 0;j<4;j++) {
				addMap[idx][i][j] = tempMap[3-j][i];
				addColorMap[idx][i][j] = tempColorMap[3-j][i];
			}
		}
		
	}
//	void showMap(int idx) {//디버깅용
//		for(int i= 0;i<5;i++) {
//			for(int j = 0;j<5;j++) {
//				System.out.print(map[idx][i][j] + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
	int getTotal() {//마지막 맵을 보며 값을 구한다
		int red=0, blue=0, green=0, yellow=0;
		for(int i= 0;i<5;i++) {
			for(int j=0;j<5;j++) {
				switch (colorMap[3][i][j]) {
				case R:
					red+=map[3][i][j];
					break;
				case B:
					blue+=map[3][i][j];
					break;
				case G:
					green+=map[3][i][j];
					break;
				case Y:
					yellow+=map[3][i][j];
					break;
				}
			}
		}
		return (red*7) + (blue*5) + (green*3) + (yellow*2);
	}
}
