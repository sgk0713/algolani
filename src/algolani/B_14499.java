package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14499 {//50
	int R, C, K;
	int map[][];
	final int EAST = 0;
	final int WEST = 1;
	final int NORTH = 2;
	final int SOUTH = 3;
	int dirR[] = {0, 0, -1, 1};//동서북남
	int dirC[] = {1, -1, 0, 0};
	int dice[] = new int[6];//아래 주석처럼 인덱스 설정
	/*
	 *   0
	 * 4 1 5
	 *   2
	 *   3
	 */
	int top = 1;
	int right = 5;
	int left = 4;
	int up = 2;
	int down = 0;
	int bottom = 3;
	
	public B_14499() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for(int i= 0;i< R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int dir = 0;
		int rr, cc;
		for(int i = 0;i<K;i++) {
			dir = Integer.parseInt(st.nextToken())-1;//-1을 해주어 0부터 시작하게 해서해당하는 값으로 방향 맵핑을 가능하게 함
			rr = r + dirR[dir];
			cc = c + dirC[dir];
			if(isInBoundery(rr, cc)) {//갈수있는 곳이라면 이동하고 답까지 출력한다
				pushDice(dir);//입력받은 방향으로 다이스를 민다
				if(map[rr][cc] == 0) {//맵값이 0이면 다이스 바닥면이 복사
					map[rr][cc] = dice[bottom];
				}else {
					dice[bottom] = map[rr][cc];//0이 아닌값이면 서로의 값을 바꾼다
					map[rr][cc] = 0;
				}
				r = rr;//좌표갱신
				c = cc;
				System.out.println(dice[top]);//다이스 윗면 출력
			}
		}
}catch (Exception e) {}
	}
	void pushDice(int dir) {//방향에따라 좌표값 갱신
		int temp;
		switch (dir) {
		case EAST:
			temp = bottom;
			bottom = right;
			right  = top;
			top = left;
			left = temp;
			break;
		case WEST:
			temp = bottom;
			bottom = left;
			left = top;
			top = right;
			right = temp;
			break;
		case SOUTH:
			temp = bottom;
			bottom = up;
			up = top;
			top = down;
			down = temp;
			break;
		case NORTH:
			temp = bottom;
			bottom = down;
			down = top;
			top = up;
			up = temp;
			break;
		}
	}
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<R && c>=0 && c<C ) {
			return true;
		}
		return false;
	}
}
