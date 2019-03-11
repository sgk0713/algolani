package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_16958 {//54
	int N, T, M;
	int distance[][];
	final int NOT_SPECIAL = 0;
	final int SPECIAL = 1;
	int cityS[];
	int cityR[];
	int cityC[];
	public B_16958() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		distance = new int[N][N];
		cityS = new int[N];
		cityR = new int[N];
		cityC = new int[N];
		
		for(int i= 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int sp = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			cityS[i] = sp;
			cityR[i] = r;
			cityC[i] = c;
		}
		for(int i = 0;i<N-1;i++) {
			for(int j = i+1;j<N;j++) {
				if(cityS[i] == SPECIAL && cityS[j] == SPECIAL) {
					distance[i][j] = Math.min(getDistance(cityR[i], cityC[i], cityR[j], cityC[j]), T);						
				}else {
					distance[i][j] = getDistance(cityR[i], cityC[i], cityR[j], cityC[j]);
				}
				distance[j][i] = distance[i][j];
			}
		}
		M = Integer.parseInt(br.readLine());
		for(int k = 0;k<N;k++) {
			for(int i= 0;i<N-1;i++) {
				for(int j = i+1;j<N;j++) {
					if(k!= i && k != j) {
						if(distance[i][k]+distance[k][j] < distance[i][j]) {
							distance[i][j] = distance[j][i] = distance[i][k]+distance[k][j];
						}
					}
				}
			}
		}
		for(int i= 0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			System.out.println(distance[start-1][end-1]);
		}
		
}catch (Exception e) {e.printStackTrace();}
	}
	int getDistance(int r, int c, int rr, int cc) {
		return Math.abs(r-rr) + Math.abs(c-cc);
	}
}
