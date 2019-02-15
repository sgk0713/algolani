package algolani;

import java.util.ArrayList;
import java.util.Scanner;

public class B_3190 {
	int N, time, rx, ry;
	boolean on = true, eat = false;
	int[][] map = new int[100][100];
	char[] turn = new char[10000];
	final int EAST = 0, SOUTH = 1, WEST = 2, NORTH = 3;
	int dir;
	int[] dx = {0, 1, 0, -1};
	int[] dy = {1, 0, -1, 0};
	ArrayList<Pair> list = new ArrayList<>();
	public B_3190() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dir = EAST;
		int k = sc.nextInt();
		for(int i = 0; i< k; i++) {
			map[sc.nextInt()-1][sc.nextInt()-1] = 1;
		}
		k = sc.nextInt();
		for(int i = 0;i<k;i++) {
			turn[sc.nextInt()] = sc.next().charAt(0);
		}
		rx = ry = 0;
		list.add(new Pair(rx, ry));
		while(on) {
			goForward();
			time++;
		}
		System.out.println(time);
		
	}
	
	
	void goForward() {
		if(turn[time] == 'D') {
			rotateClockwise();
		}else if(turn[time] == 'L') {
			rotateAntiClockwise();
		}
		
		rx = rx+dx[dir];
		ry = ry+dy[dir];
		if(rx < 0 || rx >= N || ry < 0 || ry >= N) {
			on = false;
			return;
		}
		int tempX = list.get(list.size()-1).x;
		int tempY = list.get(list.size()-1).y;
		
		for(int i = list.size()-1; i>0;i--) {
			list.get(i).x = list.get(i-1).x;
			list.get(i).y = list.get(i-1).y;
			if(list.get(i).x == rx && list.get(i).y == ry) {//이동할 좌표값이 중복일 때
				on = false;//종료
				return;
			}
		}
		if(tempX == rx && tempY == ry) {
			on = false;
			return;
		}
		list.get(0).x = rx;
		list.get(0).y = ry;
		
		if(map[rx][ry] == 1) {//사과를 먹었을 때
			map[rx][ry] = 0;
			list.add(new Pair(tempX, tempY));
		}
	}
	void rotateClockwise() {
		if(dir==3) {
			dir=0;
		}else{
			dir++;
		}
	}
	void rotateAntiClockwise() {
		if(dir==0) {
			dir=3;
		}else {
			dir--;
		}
	}
}

class Pair<x, y>{
	public int x, y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}
