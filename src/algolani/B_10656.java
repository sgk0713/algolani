package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_10656 {
	char[][] map;
	final int E = 0;
	final int S = 1;
	final int W = 2;
	final int N = 3;
	int[] dirR = {0, 1, 0, -1};
	int[] dirC = {1, 0, -1, 0};
	int r, c;
	public B_10656() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for(int i= 0;i<r;i++) {
			String str = br.readLine();
			for(int j = 0;j<c;j++) {
				map[i][j] = str.charAt(j);
			}
		}
		//입력 완료
		int clues = 0;
		StringBuilder sb = new StringBuilder();
		for(int i= 0;i<r;i++) {
			for(int j = 0;j<c;j++) {
				//벽이 아니고, 가로나 세로로 가능하면
				if(map[i][j] != '#' && (isEastPossible(i, j) || isSouthPossible(i, j))) {
					//좌표 문자열 추가
					sb.append((i+1) + " " + (j+1) + "\n");
					clues++;
				}
			}
		}
		//맨앞에 단서의 개수를 넣어준다
		sb.insert(0, clues + "\n");
		//딥출력
		System.out.println(sb.toString());
		
}catch (Exception e) {}
	}
	boolean isEastPossible(int row, int col) {
		//왼쪽이 막혔고, 오른쪽 한칸이 안 막혔고, 오른쪽 두칸이 안 막혔으면 true
		if(isBlocked(row+dirR[W], col+dirC[W]) && !isBlocked(row+dirR[E], col+dirC[E]) && !isBlocked(row+(dirR[E]*2), col+(dirC[E]*2))) {
			return true;
		}
		//아님 false
		return false;
	}
	boolean isSouthPossible(int row, int col) {
		//위쪽이 막혔고, 아래쪽 한칸이 안 막혔고, 아래쪽 두칸이 안 막혔으면 true
		if(isBlocked(row+dirR[N], col+dirC[N]) && !isBlocked(row+dirR[S], col+dirC[S]) && !isBlocked(row+(dirR[S]*2), col+(dirC[S]*2))) {
			return true;
		}
		return false;
	}
	boolean isBlocked(int row, int col) {
		//바운더리 밖이라면 막혔으니, true반환
		if(row < 0 || row >= r || col < 0 || col >= c) {
			return true;
		//벽이면 막혔으니 true 반환, 위조건에서 범위 안이라는 것은 보장 됨
		}else if(map[row][col] == '#') {
			return true;
		//그외엔 안막혔으니 false반환
		}else {
			return false;
		}
	}
}