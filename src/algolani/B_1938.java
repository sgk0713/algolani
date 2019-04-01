package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B_1938 {
	
	int N, answer;
	char map[][];
	boolean isChecked[][][];
	final int HORIZONTAL = 0;
	final int VERTICAL = 1;
	int[] dirR = {0,1,0,-1,0};
	int[] dirC = {1,0,-1,0,0};
	int[] dirS = {0,0,0,0,1};
	int sR,sC, sS ,eR,eC, eS;
	Queue<Timber_1938> q = new LinkedList<>();
	public B_1938() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		boolean sFlag = false, eFlag = false;
		map = new char[N][N];
		isChecked = new boolean[2][N][N];
		for(int i =0;i<N;i++) {
			String str = br.readLine();
			for(int j =0;j<N;j++) {
				map[i][j] = str.charAt(j);
				if(!sFlag && str.charAt(j) == 'B') {
					sFlag = true;
					sR = i;
					sC = j;
				}
				if(!eFlag && str.charAt(j) == 'E') {
					eFlag = true;
					eR = i;
					eC = j;
				}
			}
		}
		if(isInBoundery(sR, sC+1) && map[sR][sC+1] == 'B') {//다음 열이 B라면 가로로 시작
			sC++;
			sS = HORIZONTAL;
		}else {
			sR++;
			sS = VERTICAL;
		}
		if(isInBoundery(eR, eC+1) && map[eR][eC+1] == 'E') {//다음 열이 E라면 가로로 끝
			eC++;
			eS = HORIZONTAL;
		}else {
			eR++;
			eS = VERTICAL;
		}
		q.add(new Timber_1938(sR, sC, sS));
		isChecked[sS][sR][sC] = true;
		int count = -1;
		while(!q.isEmpty()) {
			int qSize = q.size();
			count++;
			for(int k = 0;k< qSize;k++) {
				Timber_1938 temp = q.poll();
				int r = temp.r;
				int c = temp.c;
				int s = temp.state;
				if(r == eR && c == eC && s == eS) {
					answer = count;
					q.clear();
					break;
				}
				for(int i = 0;i<5;i++) {
					int rr = r+dirR[i];
					int cc = c+dirC[i];
					int ss = (s+dirS[i])%2;
					if(canGo(r, c, s, i)) {
						isChecked[ss][rr][cc] = true;
						q.offer(new Timber_1938(rr, cc, ss));
					}
				}
			}
		}
		System.out.println(answer);
}catch (Exception e) {e.printStackTrace();}
	}
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<N) {
			return true;
		}return false;
	}
	boolean canGo(int r, int c, int state, int flag) {
		switch (flag) {
		case 0:
			if(canGoRight(r, c, state)) {
				return true;
			}
			break;
		case 1:
			if(canGoDown(r, c, state)) {
				return true;
			}		
			break;
		case 2:
			if(canGoLeft(r, c, state)) {
				return true;
			}
			break;
		case 3:
			if(canGoUp(r, c, state)) {
				return true;
			}
			break;
		case 4:
			if(canTurn(r, c, state)) {
				return true;
			}
			break;
		}
		return false;
	}
	boolean canGoUp(int r, int c, int state) {
		if(state == VERTICAL) {
			if(isInBoundery(r-2, c) && map[r-2][c] != '1' && isChecked[state][r-1][c] == false) {
				return true;
			}
		}else if(state == HORIZONTAL) {
			if(isInBoundery(r-1, c) && map[r-1][c-1] != '1' && map[r-1][c+1] != '1' && map[r-1][c] != '1' && isChecked[state][r-1][c] == false) {
				return true;
			}
		}
		return false;
	}
	boolean canGoDown(int r, int c, int state) {
		if(state == VERTICAL) {
			if(isInBoundery(r+2, c) && map[r+2][c] != '1' && isChecked[state][r+1][c] == false) {
				return true;
			}
		}else if(state == HORIZONTAL) {
			if(isInBoundery(r+1, c) && map[r+1][c-1] != '1' && map[r+1][c+1] != '1' && map[r+1][c] != '1' && isChecked[state][r+1][c] == false) {
				return true;
			}
		}
		return false;
	}
	boolean canGoLeft(int r, int c, int state) {
		if(state == VERTICAL) {
			if(isInBoundery(r, c-1) && map[r-1][c-1] != '1' && map[r][c-1] != '1' && map[r+1][c-1] != '1' && isChecked[state][r][c-1] == false) {
				return true;
			}
		}else if(state == HORIZONTAL) {
			if(isInBoundery(r, c-2) && map[r][c-2] != '1' && isChecked[state][r][c-1] == false) {
				return true;
			}
		}
		return false;
	}
	boolean canGoRight(int r, int c, int state) {
		if(state == VERTICAL) {
			if(isInBoundery(r, c+1) && map[r-1][c+1] != '1' && map[r+1][c+1] != '1' && map[r][c+1] != '1' && isChecked[state][r][c+1] == false) {
				return true;
			}
		}else if(state == HORIZONTAL) {
			if(isInBoundery(r, c+2) && map[r][c+2] != '1' && isChecked[state][r][c+1] == false) {
				return true;
			}
		}
		return false;
	}
	boolean canTurn(int r, int c, int state) {
		if(isChecked[(state+1)%2][r][c] == false) {
			if(state == VERTICAL) {
				if(!(isInBoundery(r, c-1) 
						&& isInBoundery(r, c+1))) {
					return false;
				}
			}else if(state == HORIZONTAL) {
				if(!(isInBoundery(r-1, c) && isInBoundery(r+1, c))) {
					return false;
				}
			}
			for(int i= r-1;i<r+2;i++) {
				
				for(int j = c-1;j<c+2;j++) {
					
					if(map[i][j] == '1') {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	class Timber_1938{
		int r, c, state;
		public Timber_1938(int r, int c, int state) {
			this.r = r;
			this.c = c;
			this.state = state;
		}
	}
}
