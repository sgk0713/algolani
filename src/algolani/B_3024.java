package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_3024 {
	int N;
	char answer = '0';
	char[][] map;
	int[] dirR = {-1,1,-1,1};
	int[] dirC = {-1,1,1,-1};
	public B_3024() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for(int i= 0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<N;j++) {
				map[i][j] = str.charAt(j);
			}
		}
		for(int i= 0;i<N;i++) {
			char row = map[i][0], col = map[0][i];
			int rowCount=1, colCount = 1;
			for(int j=1;j<N;j++) {
				char diagnal = map[i][j];
				if(row == map[i][j]) {
					rowCount++;
				}else {
					rowCount=1;
					row = map[i][j];
				}
				if(col == map[j][i]) {
					colCount++;
				}else {
					colCount = 1;
					col = map[j][i];
				}
				if(rowCount == 3) {
					if(row == '.') {
						row = map[i][j];
					}else {
						answer = row;
						i = N;
						break;
					}
				}
				if(colCount == 3) {
					if(col == '.') {
						col = map[i][j];
					}else {
						answer = col;
						i = N;
						break;
					}
				}
				int diagnalCount = 1;
				for(int k = 0;k<4;k++) {
					int r = i+dirR[k];
					int c = j+dirC[k];
					if(k == 2) {
						diagnalCount = 1;
					}
					if(isInBounder(r, c) && map[r][c] == diagnal) {
						diagnalCount++;
					}
					if(diagnalCount == 3 && diagnal != '.') {
						answer = diagnal;
						i = j =N;
						break;
					}
				}
			}
		}
		if(answer == '0') {
			System.out.println("ongoing");
		}else {
			System.out.println(answer);
		}
		
}catch (Exception e) {e.printStackTrace();}
	}
	boolean isInBounder(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<N) {
			return true;
		}return false;
	}
}
