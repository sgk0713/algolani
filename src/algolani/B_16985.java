package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16985 {
	boolean[][][] map = new boolean[5][5][5];
	boolean[][][] isChecked = new boolean[5][5][5];
	int dirR[] = {0,1,0,-1,0,0};
	int dirC[] = {1,0,-1,0,0,0};
	int dirZ[] = {0,0,0,0,-1,1};
	Queue<Node_16985> q = new LinkedList<>();
	int answer = 5*5*5+1, minAnswer = 12;
	public B_16985() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for(int i= 0;i<5;i++) {
			for(int j =0;j<5;j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0;k<5;k++) {
					int temp = Integer.parseInt(st.nextToken());
					if(temp == 1) {
						map[i][j][k] = true;
					}
				}
			}
		}
		setMatrix(0);
		if(answer==5*5*5+1) {//답갱신이 없었다면
			System.out.println(-1);//-1출력
		}else {
			System.out.println(answer);//답출력
		}
}catch (Exception e) {}
	}
	void findMap(int z) {//층마다의 회전을 하여 회전할수있는 경우의 수를 구하는 메소드
		if(answer == minAnswer) {//답이 혹시나 나올 수 있는 최소값이면 볼필요없으니 리턴
			return;
		}
		if(z == 5) {//마지막층이 도달했을 때
			findWay();//길을 찾는다
			return;
		}
		for(int i = 0;i< 4;i++) {//해당 층을 오른쪽으로 총 4번 돌리면서, 다음층으로 이동한다 
			if(!rotateMatrix(z)) {
				continue;
			}
			findMap(z+1);
		}
	}
	void setMatrix(int pivot) {
		if(pivot==5) {//모든 층의 순열을 구했을 경우
			findMap(0);//맵을 돌리기며 맵을 구해본다
		}
		for(int i = pivot;i<5;i++) {
			swap(i, pivot);
			setMatrix(pivot+1);
			swap(i, pivot);
		}
	}
	void swap(int a, int b) {//순열을 위한 층간의 교환 메소드
		boolean tempArr[][] = new boolean[5][5];
		for(int i= 0;i<5;i++) {
			tempArr[i] = Arrays.copyOf(map[a][i], 5);
		}
		for(int i= 0;i<5;i++) {
			map[a][i] = Arrays.copyOf(map[b][i], 5);
		}
		for(int i= 0;i<5;i++) {
			map[b][i] = Arrays.copyOf(tempArr[i], 5);
		}
	}
	void findWay() {//길을 찾는다
		//초기화
		for(int i = 0;i<5;i++) {
			for(int j = 0;j<5;j++) {
				Arrays.fill(isChecked[i][j], false);
			}
		}
		q.clear();
		
		q.offer(new Node_16985(0, 0, 0));//입구 넣는다
		isChecked[0][0][0] = true;//입구체크
		int move = 0;
		while(!q.isEmpty()) {
			int qSize = q.size();
			move++;
			for(int i= 0;i<qSize;i++) {
				Node_16985 temp = q.poll();
				int z = temp.z;
				int r = temp.r;
				int c = temp.c;
				for(int k = 0;k<6;k++) {//위층, 아래층, 상하좌우 총 6개를 탐색
					int zz = z + dirZ[k];
					int rr = r + dirR[k];
					int cc = c + dirC[k];
					if(move>=answer) {//이동하고있는횟수가 답보다 크면 더이상 보지않는다
						i = qSize;
						q.clear();
						break;
					}
					if(isEnd(zz, rr, cc)) {//출구일 때 
						if(move<answer) {//답보다 작으면 갱신하고 스탑
							answer = move;
						}
						i = qSize;
						q.clear();
						break;
					}
					//범위안이고, 갈수있는길이고, 가보지않은 길이면, 큐에 담는다 
					if(isInBoundery(zz, rr, cc) && map[zz][rr][cc] == true && isChecked[zz][rr][cc] == false) {
						isChecked[zz][rr][cc] = true;
						q.offer(new Node_16985(zz, rr, cc));
					}
				}
			}
		}
	}
	boolean isEnd(int z, int r, int c) {//출구인지 체크
		if(z == 4 && r == 4 && c == 4) {
			return true;
		}
		return false;
	}
	boolean isInBoundery(int z, int r, int c) {//범위안에 있는지 체크
		if(z>=0 && z<5 && r>=0 && r<5 && c>=0 && c<5) {
			return true;
		}
		return false;
	}
	boolean rotateMatrix(int z) {//해당층을 오른쪽으로 돌린다
		boolean[][] tempMap = new boolean[5][5];
		for(int i = 0;i<5;i++) {
			for(int j =0;j<5;j++) {
				tempMap[i][j] = map[z][i][j];
			}
		}
		for(int i =0;i<5;i++) {
			for(int j =0;j<5;j++) {
				map[z][i][j] = tempMap[4-j][i]; 
			}
		}
		if(z == 0 && tempMap[4][0] == false) {
			return false;
		}
		if(z == 4 && tempMap[0][4] == false) {
			return false;
		}
		return true;
	}
	class Node_16985{
		int z, r, c;
		public Node_16985(int z, int r, int c) {
			this.z = z;
			this.r = r;
			this.c = c;
		}
		
	}
}
