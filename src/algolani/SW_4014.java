package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4014 {//37
	int answer, N, base;
	int[][] map = new int[20][20];
	public SW_4014() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			base = Integer.parseInt(st.nextToken());
			answer = 0;
			for(int i= 0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int rowCurrHeight, colCurrHeight, rowCount, colCount;
			boolean isRowPossible, isColPossible;
			boolean isRowDown, isColDown;
			for(int i= 0;i<N;i++) {
				rowCurrHeight = map[i][0];
				colCurrHeight = map[0][i];
				rowCount = colCount = 1;
				isRowPossible = isColPossible = true;
				isRowDown = isColDown = false;
				for(int j=1;j<N;j++) {
					if(isRowPossible) {
						if(rowCurrHeight == map[i][j]) {
							rowCount++;
						}else if(rowCurrHeight+1 == map[i][j]) {
							if(base<=rowCount) {
								rowCurrHeight = map[i][j];
								rowCount = 1;
							}else {
								isRowPossible = false;
							}
						}else if(rowCurrHeight-1 == map[i][j]) {
							if(!isRowDown) {
								isRowDown = true;
								rowCurrHeight = map[i][j];
								rowCount = 1;
							}else {
								isRowPossible = false;
							}
						}else {
							isRowPossible = false;
						}
						if(isRowDown && rowCount==base) {
							isRowDown = false;
							rowCount = 0;
						}
						if(j == N-1 && isRowPossible && !isRowDown) {
							answer++;
						}
					}
					if(isColPossible) {
						if(colCurrHeight == map[j][i]) {
							colCount++;
						}else if(colCurrHeight+1 == map[j][i]) {
							if(base<=colCount) {
								colCurrHeight = map[j][i];
								colCount = 1;
							}else {
								isColPossible = false;
							}
						}else if(colCurrHeight-1 == map[j][i]) {
							if(!isColDown) {
								isColDown = true;
								colCurrHeight = map[j][i];
								colCount = 1;
							}else {
								isColPossible = false;
							}
						}else {
							isColPossible = false;
						}
						if(isColDown && colCount==base) {
							isColDown = false;
							colCount = 0;
						}
						if(j == N-1 && isColPossible && !isColDown) {
							answer++;
						}
					}
				}
			}
			System.out.println("#" + t + " " + answer);
		}
}catch (Exception e) {}
	}
}
