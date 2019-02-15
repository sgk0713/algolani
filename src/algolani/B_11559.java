package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class B_11559 {
	int answer;
	char[][] map = new char[12][6];
	boolean[][] isChecked = new boolean[12][6];
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	char[] lists = {'Y','G','R'};
	Queue<Node_11559> q = new LinkedList<>();
	public B_11559() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		for(int i= 0;i<12;i++) {//테케 생성
//			for(int j = 0;j<6;j++) {
//				System.out.print(lists[new Random().nextInt(3)]);
//			}
//			System.out.println();
//		}
		answer = 0;
		for(int i= 0;i<12;i++) {
			String s = br.readLine();
			for(int j = 0;j<6;j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		
//		showMap();
		boolean flag = true;
		while(flag) {// 전체를 보는것을 사라질뿌요가 없을때까지 반복함
			flag = false;//초기화
			for(int k= 0;k<12;k++) {//방문 배열 초기화
				Arrays.fill(isChecked[k], false);
			}
			for(int i=0;i<12;i++) {
				for(int j= 0;j<6;j++) {
					if(isChecked[i][j] == false && map[i][j] != '.') {//.이 아니고 방문한 곳이 아니면
						if(bfs(i, j, map[i][j])) {//bfs 돈다.연속된게 4개이상이면 true반환 아니면 false반환
							fillWithDot(i, j, map[i][j]);//4개이상이면 .으로 채운다
							flag = true;//한번이라도 지우면 맵이 리셋될테니 다시 보기위해 flag를 true로 값설정
						}
					}
				}
			}
			if(flag) {//한번이라도 지웠다면
				
				resetMap();//맵의 빈공간을 중력의 힘으로 내려주고
//				Thread.sleep(1000);//1초쉬고
//				showMap();//출력 디버깅
				
				answer++;//연쇄 횟수1증가
			}
			
		}
		System.out.println(answer);//답출력
}catch (Exception e) {e.printStackTrace();}
	}
	
	boolean bfs(int row, int col, char color) {
		q.offer(new Node_11559(row, col));//넣고
		isChecked[row][col] = true;//현재를 방문처리
		int count = 1;//현재 뿌요 1개
		
		while(!q.isEmpty()) {
			Node_11559 tempNode = q.poll();
			int nR = tempNode.r;
			int nC = tempNode.c;
			for(int i = 0;i<4;i++) {//4방향 둘러보고
				int rr = nR+dirR[i];
				int cc = nC+dirC[i];
				if(isInBoundery(rr, cc) // 범위안이고
						&& isChecked[rr][cc] == false//방문하지않았고 
						&& color == map[rr][cc]) {//컬러가 같으면
					isChecked[rr][cc] = true;//방문햇다 표시
					q.offer(new Node_11559(rr, cc));//큐에 넣음
					count++;//연속된 수 1증가
				}
			}
		}
		if(count >= 4) {//연속된게 4이상이면
			return true;//참 반환
		}else {
			return false;//아니면 거짓 반환
		}
	}
	
	void showMap() {//디버깅용
		System.out.println("\n==showMap==");
		for(int i= 0;i<12;i++) {
			for(int j = 0;j<6;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	void resetMap() {//중력의 힘으로 뿌요들을 끌어내림
		for(int col = 0;col<6;col++) {//한 컬럼에 대해서 위에서부터 본다
			for(int row = 0;row<12;row++) {
				if(map[row][col] == '.') {//중간에 빈공간이 있다면
					int tempRow = row;
					while(isInBoundery(tempRow-1, col)) {//범위안에 있으면 무한반복
						map[tempRow][col] = map[tempRow-1][col];//한칸씩 내림
						tempRow--;
					}
					map[0][col] = '.';// 맨위는 .으로 채운다
				}
			}
		}
	}
	
	boolean isInBoundery(int r, int c) {//범위 안이면 true 아니면 false
		if(r>=0 && r<12 && c >= 0 && c < 6) {
			return true;
		}
		return false;
	}
	
	void fillWithDot(int row, int col, char color) {//4개이상일때 실행될메소
		q.offer(new Node_11559(row, col));
		map[row][col] = '.';//현재 뿌요 비움
		while(!q.isEmpty()) {
			Node_11559 tempNode = q.poll();
			int nR = tempNode.r;
			int nC = tempNode.c;
			for(int i = 0;i<4;i++) {
				int rr = nR+dirR[i];
				int cc = nC+dirC[i];
				if(isInBoundery(rr, cc) //범위안이고
						&& isChecked[rr][cc] == true//들렀던 곳이고 
						&& color == map[rr][cc]) {//같은 컬러면
					map[rr][cc] = '.';//.으로 채우고
					q.offer(new Node_11559(rr, cc));//큐에 담아줌
				}
			}
		}
	}
	class Node_11559{
		int r, c;
		public Node_11559(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
