package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_7576 {
	//토마토
	int R, C, D, tmpR, tmpC, tmpD, unripeCount;
	int[][] map = new int[1000][1000];
	final int RIPE = 1, UNRIPE = 0, EMPTY = -1;
	Queue<Pair_7576> q = new LinkedList<>();
	int[] dirR = {1, -1, 0, 0};//SOUTH, NORTH, EAST, WEST
	int[] dirC = {0, 0, 1, -1};//남, 북, 동 서 방향은 상관없는 문제
	B_7576(){try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tempStr = br.readLine();
		StringTokenizer st = new StringTokenizer(tempStr, " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		for(int i = 0; i < R; i++) {
			tempStr = br.readLine();
			st = new StringTokenizer(tempStr, " ");
			for(int j = 0; j< C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == RIPE) {
					q.offer(new Pair_7576(i, j, 0));//익은 과일을 미리 큐에 담는다
				}else if(map[i][j] == UNRIPE) {
					unripeCount++;//덜익은 과일갯수를 센다
				}
			}
		}
		while(!q.isEmpty()) {
			Pair_7576 tmpPair = q.poll();
			D = Math.max(D, tmpPair.D);//최대 일수를 계산
			for(int i= 0; i<4;i++) {
				 tmpR = tmpPair.R + dirR[i];// 방향 설정
				 tmpC = tmpPair.C + dirC[i];
				 if(tmpR >= 0 && tmpR < R 
					 && tmpC >= 0 && tmpC < C 
					 && map[tmpR][tmpC] == UNRIPE) {//범위 안에 있고 덜익은 토마토가 있다면
					 unripeCount--;//덜익은 전체 토마토 수량 감소
					 map[tmpR][tmpC] = RIPE;//익은것으로 값 갱신
					 q.offer(new Pair_7576(tmpR, tmpC, tmpPair.D + 1));//큐에 삽입
				 }
			}
		}
		if(unripeCount==0) {// 덜 익은 토마토가 없다면
			System.out.println(D);
		}else {// 덜 익은 토마토가 남아있다면
			System.out.println(-1);
		}										}catch (Exception e) {}
	}
}

class Pair_7576{
	int R, C, D;
	public Pair_7576(int R, int C, int D) {
		this.R = R;
		this.C = C;
		this.D = D;
	}
}
