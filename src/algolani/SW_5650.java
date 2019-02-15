package algolani;

import java.util.Arrays;
import java.util.Scanner;

public class SW_5650 {
//핀볼 게임
int N=100;
int[][] map = new int[102][102];//가장자리는 5로 채우기용
int[] dirR = {0, 1, 0, -1};//0번: 동쪽으로 이동, 1번: 남쪽으로, 2번: 서쪽으로, 3번:북쪽으로
int[] dirC = {1, 0, -1, 0};
final int EAST = 0, SOUTH = 1, WEST = 2, NORTH = 3;
int[] holeR = new int[10];//웜홀은 최대 5쌍.1차원배열로 표현
int[] holeC = new int[10];
int answer = 0;	//벽이나 블록에 부딪힌 최대 횟수
public SW_5650() {try {
	Scanner sc = new Scanner(System.in);
	int T = sc.nextInt();
	for(int t = 1; t<= T ;t++){
		Arrays.fill(holeR, -1);//-1로 웜홀배열 초기화
		Arrays.fill(holeC, -1);
		N = sc.nextInt();
		for(int i= 0; i<=N+1;i++) {
			for(int j = 0;j<=N+1;j++) {
				if(i == 0 || i == (N+1) || j == 0 || j ==(N+1)) {
					map[i][j] = 5;//가장자리는 5번블록으로 채움
				}else {
					int tempValue = map[i][j] = sc.nextInt();
					if(tempValue >= 6) {//6번이상인 블록(웜홀)일 경우
						tempValue = (tempValue-6) * 2;//0, 2, 4, 6, 8
						if(holeR[tempValue] == -1) {//웜홀 번호의 첫번째 웜홀
							holeR[tempValue] = i;
							holeC[tempValue] = j;
						}else {
							holeR[tempValue + 1] = i;//1, 3, 5, 7, 9
							holeC[tempValue + 1] = j;//두번째 웜홀
						}
					}
				}
			}
		}
		//입력 완료
		
		answer = 0;//결과값 최기화
		for(int i= 1; i<=N;i++) {//가장자리는 5번블록이므로 1부터 탐색
			for(int j = 1;j<=N;j++) {
				if(map[i][j] == 0) {//공이 시작할 수 있는 위치일 때
					for(int k =0;k<4;k++) {//갈 수 있는 방향 탐색
						int curR = i+dirR[k];//위치 이동
						int curC = j+dirC[k];
						int to = k;//k와 상수 방향변수와 동일, 어디로 향할지 지정해줌
						int bounce = 0;//최대횟수와 비교할 값
						
						while(true) {
							//현 위치가 원위치거나 블랙홀일때 while문 탈출
							if(map[curR][curC] == -1 || (curR==i && curC==j)) {
								break;
							}
							int item = map[curR][curC];//현위치가 몇번 블록인지 item변수에 할당
							if(item >= 6) {//웜홀일 경우 6~10
								int tempValue = (item-6) * 2;//웜홀 배열에 접근하기 위한 인덱스 계산
								if(holeR[tempValue] == curR && holeC[tempValue] == curC) {//다른 쌍의 웜홀에서 향하는 방향으로 한칸 이동
									curR = holeR[tempValue+1] + dirR[to];
									curC = holeC[tempValue+1] + dirC[to];
								}else {
									curR = holeR[tempValue] + dirR[to];
									curC = holeC[tempValue] + dirC[to];
								}
								continue;
								
							}else if(item >= 1) {//1~5일 경우
								if(to == EAST) {//동쪽 방향으로 향할때 블록에 따른 방향 설정. 이하동일
									if(item == 1 || item == 2 || item == 5) {
										to = WEST;
									}else if(item == 3) {
										to = SOUTH;
									}else if(item == 4) {
										to = NORTH;
									}
								}else if(to == WEST) {
									if(item == 3 || item == 4 || item == 5) {
										to = EAST;
									}else if(item == 2) {
										to = SOUTH;
									}else if(item == 1) {
										to = NORTH;
									}
								}else if(to == SOUTH) {
									if(item == 2 || item == 3 || item == 5) {
										to = NORTH;
									}else if(item == 1) {
										to = EAST;
									}else if(item == 4) {
										to = WEST;
									}
								}else if(to == NORTH) {
									if(item == 1 || item == 4 || item == 5) {
										to = SOUTH;
									}else if(item == 2) {
										to = EAST;
									}else if(item == 3) {
										to = WEST;
									}
								}
								//방향설정을 마친 후 그 방향으로 이동 및 튕긴횟수 증가
								curR += dirR[to];
								curC += dirC[to];
								bounce++;
							}else if(item == 0) {//블록이 없는 경우, 향하는 방향으로 이동
								curR += dirR[to];
								curC += dirC[to];
							}
						}
						answer = Math.max(answer, bounce);//최대값 비교
					}
				}
			}
		}
		System.out.println("#" + t + " " + answer);
	}
}catch (Exception e) {e.printStackTrace();}
	}
}