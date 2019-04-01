package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_3085 {
	int N, answer;
	char[][] map1, map2;
	public B_3085() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map1 = new char[N][N];
		map2 = new char[N][N];
		for(int i= 0;i<N;i++) {
			String str = br.readLine();
			for(int j = 0;j<N;j++) {
				map1[i][j] = map2[i][j] = str.charAt(j);
			}
		}
		for(int k= 0;k<N-1;k++) {
			for(int i= 0;i<N;i++) {
				char temp = map1[k][i];
				map1[k][i] = map1[k+1][i];
				map1[k+1][i] = temp;
				temp = map2[i][k];
				map2[i][k] = map2[i][k+1];
				map2[i][k+1] = temp;
				
				char map1Value = map1[k][0];
				char map1Value1 = map1[k+1][0];
				char map1Value2 = map1[0][k];
				char map1Value3 = map1[0][k+1];
				
				char map2Value = map2[k][0];
				char map2Value1 = map2[k+1][0];
				char map2Value2 = map2[0][k];
				char map2Value3 = map2[0][k+1];
				
				int rowCount = 1, rowTempCount = 1, rowCount1 = 1, rowTempCount1 = 1, rowCount2 = 1, rowTempCount2 = 1, rowCount3 = 1, rowTempCount3 = 1;
				int colCount = 1, colTempCount = 1, colCount1 = 1, colTempCount1 = 1, colCount2 = 1, colTempCount2 = 1, colCount3 = 1, colTempCount3 = 1;
				for(int j = 1;j<N;j++) {
					if(map1Value == map1[k][j]) {
						rowTempCount++;
					}else {
						map1Value = map1[k][j];
						rowTempCount = 1;
					}
					if(rowTempCount > rowCount) {
						rowCount = rowTempCount;
					}
					
					if(map1Value1 == map1[k+1][j]) {
						rowTempCount1++;
					}else {
						map1Value1 = map1[k+1][j];
						rowTempCount1 = 1;
					}
					if(rowTempCount1 > rowCount1) {
						rowCount1 = rowTempCount1;
					}
					
					if(map1Value2 == map1[j][k]) {
						rowTempCount2++;
					}else {
						map1Value2 = map1[j][k];
						rowTempCount2 = 1;
					}
					if(rowTempCount2 > rowCount2) {
						rowCount2 = rowTempCount2;
					}
					
					if(map1Value3 == map1[j][k+1]) {
						rowTempCount3++;
					}else {
						map1Value3 = map1[j][k+1];
						rowTempCount3 = 1;
					}
					if(rowTempCount3 > rowCount3) {
						rowCount3 = rowTempCount3;
					}
					//
					
					
					if(map2Value == map2[k][j]) {
						colTempCount++;
					}else {
						map2Value = map2[k][j];
						colTempCount = 1;
					}
					if(colTempCount > colCount) {
						colCount = colTempCount;
					}
					
					if(map2Value1 == map2[k+1][j]) {
						colTempCount1++;
					}else {
						map2Value1 = map2[k+1][j];
						colTempCount1 = 1;
					}
					if(colTempCount1 > colCount1) {
						colCount1 = colTempCount1;
					}
					
					if(map2Value2 == map2[j][k]) {
						colTempCount2++;
					}else {
						map2Value2 = map2[j][k];
						colTempCount2 = 1;
					}
					if(colTempCount2 > colCount2) {
						colCount2 = colTempCount2;
					}
					
					if(map2Value3 == map2[j][k+1]) {
						colTempCount3++;
					}else {
						map2Value3 = map2[j][k+1];
						colTempCount3 = 1;
					}
					if(colTempCount3 > colCount3) {
						colCount3 = colTempCount3;
					}
				}
				if(answer < rowCount) {
					answer = rowCount;
				}
				if(answer < rowCount1) {
					answer = rowCount1;
				}
				if(answer < rowCount2) {
					answer = rowCount2;
				}
				if(answer < rowCount3) {
					answer = rowCount3;
				}
				if(answer < colCount) {
					answer = colCount;
				}
				if(answer < colCount1) {
					answer = colCount1;
				}
				if(answer < colCount2) {
					answer = colCount2;
				}
				if(answer < colCount3) {
					answer = colCount3;
				}
				temp = map1[k][i];
				map1[k][i] = map1[k+1][i];
				map1[k+1][i] = temp;
				temp = map2[i][k];
				map2[i][k] = map2[i][k+1];
				map2[i][k+1] = temp;
			}
		}
		System.out.println(answer);
}catch (Exception e) {}		
	}
}