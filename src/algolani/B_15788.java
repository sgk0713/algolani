package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_15788 {
	int N;
	long[][] map;
	int r = -1, c = -1;
	long value, zeroValue, tempValue, answer;
	public B_15788() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new long[N][N];
		for(int i= 0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			tempValue = 0;
			for(int j=0;j<N;j++) {
				tempValue += map[i][j] = Long.parseLong(st.nextToken());
				if(map[i][j] == 0) {
					r = i; c = j;
				}
			}
			if(r!=i && value == 0) {
				value = tempValue;
			}else if(r == i && zeroValue == 0) {
				zeroValue = tempValue;
			}
		}
		answer = map[r][c] = value - zeroValue;
		long temp1 = 0, temp2 = 0, temp3 = 0, temp4 = 0;
		for(int i= 0;i<N;i++) {
			temp3 += map[i][i];
			temp4 += map[i][N-1-i]; 
			temp1 = temp2 = 0;
			for(int j=0;j<N;j++) {
				temp1 += map[i][j];
				temp2 += map[j][i];
			}
			if(temp1 != value || temp2 != value) {
				answer = -1;
				break;
			}
		}
		if(temp3 != value || temp4 != value) {
			answer = -1;
		}
		System.out.println(answer);
}catch (Exception e) {e.printStackTrace();}
	}
}