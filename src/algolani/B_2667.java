package algolani;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class B_2667 {
	 int N, count;
	 int[][] map;
	 boolean[][] isChecked;
	 int[] dx = {-1, 0, 1, 0};
	 int[] dy = {0, 1, 0, -1};
	 ArrayList<Integer> array;
	 public B_2667() {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		map = new int[N][N];
		isChecked = new boolean[N][N];
		count = 1;
		
		for(int i = 0; i< N; i++) {
			String tmp = scan.next();
			for(int j = 0; j< N; j++) {
				map[i][j] = tmp.charAt(j)-'0';
			}
		}
		
		for(int i = 0; i< N; i++) {
			for(int j = 0; j< N; j++) {
				if(map[i][j] == 1 && isChecked[i][j] == false) {
					dfs(i, j);
					count++;
				}
			}
		}
		
		System.out.println(count-1);
		int[] num = new int[count];//0으로 초기화
		for(int i = 0; i< N; i++) {
			for(int j = 0; j< N; j++) {
				if(map[i][j] != 0) {
					num[map[i][j]]++;
				}
			}
		}
		Arrays.sort(num);
		for(int i = 1; i<count; i++) {
			System.out.println(num[i]);
		}
	}
	
	 void dfs(int x, int y) {
		isChecked[x][y] = true;
		map[x][y] = count;
		for(int i = 0; i< 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx>=0 && ny >=0 && nx < N && ny < N) {
				if(map[nx][ny] == 1 && isChecked[nx][ny] == false) {
					dfs(nx, ny);		
				}
			}
		}
	}
}
