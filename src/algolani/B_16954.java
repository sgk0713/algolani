package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class B_16954 {//움직이는 미로 탈출
	//1초마다의 벽들을 담은 맵 8개를 만들어서 갈수있는 곳을 찾아 8번째 맵까지 가는 좌표가 있다면 가능하다고 출력한다
	
	int dirR[] = {0,0,1,0,-1,-1,-1,1,1};//제자리, 동, 남, 서, 북, 북서, 동서, 남서, 동남
	int dirC[] = {0,1,0,-1,0,-1,1,-1,1};
	public B_16954() {
try {
		boolean[][][] map = new boolean[8][8][8];
		boolean isChecked[][] = new boolean[8][8];
		int answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 8; i++) {
			String str = br.readLine();
			for(int j = 0; j < 8; j++) {
				if(str.charAt(j) == '#') {
					for(int k = 0;k<8;k++) {
						if(k+i<8) {
							map[k][k+i][j] = true;// 벽은 true로 초기화한다
						}
					}
				}
			}
		}
		Queue<Node_16954> q = new LinkedList<>();
		q.offer(new Node_16954(7,0));//시작값
		int mapNumber = 0;//첫번째 맵부터 시작
		while(!q.isEmpty() && mapNumber<7) {//다음 맵과 현재맵을 비교 할 것이기때문에 7미만일때만 실행 시킨다
			int qSize = q.size();
			for(int k = 0;k < 8; k++) {
				Arrays.fill(isChecked[k], false);
			}
			for(int k = 0;k<qSize;k++) {
				Node_16954 tempNode = q.poll();
				int r = tempNode.r;
				int c = tempNode.c;
				for(int i = 0;i<9;i++) {
					int rr = r+dirR[i];
					int cc = c+dirC[i];
					if(isInBoundery(rr, cc) && map[mapNumber][rr][cc] == false && map[mapNumber+1][rr][cc] == false && isChecked[rr][cc] == false) {
						isChecked[rr][cc] = true;
						if(mapNumber == 6) {//7번째맵이라면 이미 블락을 다 내려왔기때문에 탈출할 수 있기에 answer는 1로 갱신해준다 
							answer = 1;
							k = qSize;
							q.clear();
							break;
						}
						q.offer(new Node_16954(rr, cc));
					}
				}
			}
			mapNumber++;//맵번호 증가
		}
		System.out.println(answer);
}catch (Exception e) {e.printStackTrace();}	
	}
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<8 && c>=0 && c<8) {
			return true;
		}
		return false;
	}
	class Node_16954{
		int r, c;
		public Node_16954(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
