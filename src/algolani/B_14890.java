package algolani;

import java.util.Scanner;

public class B_14890 {
	int N, L, count=1, answer = 0;
	int[][] map = new int[101][101];
	boolean countFlag = false;
	
	public B_14890() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		for(int i = 0; i< N;i++) {
			for(int j = 0; j< N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for(int i = 0; i<N; i++) {
			iter(i);
		}
		System.out.println(answer);
	}
	
	void iter(int i) {
		count = 1;
		countFlag = false;
		for(int j = 0; j< N;j++) {
			if(countFlag && count >= L) {
				countFlag = false;
				count = 0;
			}
			if(j == N-1 && !countFlag) {
				answer++;
				break;
			}
			if(Math.abs(map[i][j]-map[i][j+1]) > 1) {//다음칸과 2이상 차이가 날때
				break;
			}
			if(map[i][j]+1 == map[i][j+1]) {//다음 칸이 높을 때
				if(count < L) {//지나온 길이 L만큼 없을때 (경사로를 만들지 못할때)
					break;
				}else{//지나온 길이 L만큼 있었을 때
					count = 1;
				}
			}
			if(map[i][j]-1 == map[i][j+1]) {//다음 칸이 낮을 때
				if(countFlag && count < L) {
					break;
				}else {
					countFlag = true;
					count = 1;
				}
			}
			if(map[i][j] == map[i][j+1]) {//다음 칸이 현재칸과 같을때
				count++;
			}
		}
		count = 1;
		countFlag = false;
		for(int j = 0; j< N;j++) {
			if(countFlag && count >= L) {
				countFlag = false;
				count = 0;
			}
			if(j == N-1 && !countFlag) {
				answer++;
				break;
			}
			if(Math.abs(map[j][i]-map[j+1][i]) > 1) {//다음칸과 2이상 차이가 날때
				break;
			}
			
			if(map[j][i]+1 == map[j+1][i]) {//다음 칸이 높을 때
				if(count < L) {//지나온 길이 L만큼 없을때 (경사로를 만들지 못할때)
					break;
				}else{//지나온 길이 L만큼 있었을 때
					count = 1;
				}
			}
			if(map[j][i]-1 == map[j+1][i]) {//다음 칸이 낮을 때
				if(countFlag && count < L) {
					break;
				}else {
					countFlag = true;
					count = 1;
				}
			}
			if(map[j][i] == map[j+1][i]) {//다음 칸이 현재칸과 같을때
				count++;
			}
		}
	}
	
}