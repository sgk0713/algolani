package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B_9207 {
	//페그 솔리테어
	char[][] map = new char[5][9];
	int[] aroundR = {0, 0, 1, -1}; //남 북 동 서
	int[] aroundC = {1, -1, 0, 0};
	int numPin;
	int min;
	ArrayList<Pair_9207> list = new ArrayList<>();
	public B_9207() {try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(br.readLine());
//		int T = sc.nextInt();
		String tempStr = null;
		for(;T>0;T--) {
			list.clear();
			
			numPin = 0;
			for(int i=0;i<5;i++) {
				tempStr = br.readLine();
//				tempStr = sc.next();
				for(int j = 0; j<9;j++) {
					if((map[i][j] = tempStr.charAt(j)) == 'o') {
						list.add(new Pair_9207(i, j));
						numPin++;
					}
				}
			}
			min = numPin;
//			if(T != 1) {
//			}
//			if(T != 1) {
//				sc.nextLine();
//			}
			for(int i= 0;i<list.size();i++) {
				dfs(list.get(i).r, list.get(i).c, numPin);
			}
			System.out.println(min + " " + (numPin-min));
			br.readLine();

		}
	}catch (Exception e) {}
	}
	
	void dfs(int r, int c, int restPin) {
//		for(int j = 0;j<5;j++) {
//			for(int k = 0;k<9;k++) {
//				System.out.print(map[j][k]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		for(int i = 0; i< 4;i++) {
			int rr = r + aroundR[i];
			int cc = c + aroundC[i];
			int overRR = rr + aroundR[i];
			int overCC = cc + aroundC[i];
			if(overRR>=0 && overRR < 5 && overCC >= 0 && overCC < 9
				&& map[rr][cc] == 'o'
				&& map[overRR][overCC] == '.' ) {
				map[r][c] = '.';
				map[rr][cc] = '.';
				map[overRR][overCC] = 'o';
				
				for(int j = 0;j<5;j++) {
					for(int k = 0;k<9;k++) {
						if(map[j][k] == 'o') {
							for(int l = 0; l<4;l++) {
								if(j+(aroundR[l]*2) >= 0 && j+(aroundR[l]*2) < 5 && k+(aroundC[l]*2) >=0 && k+(aroundC[l]*2) < 9
								&& map[j+aroundR[l]][k+aroundC[l]] == 'o'
								&& map[j+(aroundR[l]*2)][k+(aroundC[l]*2)] == '.') {
									dfs(j,k, restPin-1);
								}
							}
						}
					}
				}
				min = Math.min(restPin-1, min);
				map[r][c] = 'o';
				map[rr][cc] = 'o';
				map[overRR][overCC] = '.';
			}
		}

		
	}
	class Pair_9207{
		int r, c;
		public Pair_9207(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
