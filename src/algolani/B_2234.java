package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2234 {//34
	int R, C;
	int numRoom = 0;//답1
	int biggestRoomSize = 0;//답2
	int possibleBigSize = 0;//답3
	int map[][];//입력 맵
	int markedMap[][];//단지번호붙이기 느낌으로다가
	boolean isChecked[][];//체크배열
	int dirR[] = {0,-1,0,1};//서북동남, 각 인덱스만큼 비트마스킹해주면 벽있는지 확인 가능
	int dirC[] = {-1,0,1,0};
	ArrayList<Integer> arrList = new ArrayList<>();//단지번호에 해당하는 방개수가 담기는 리스트
	Queue<Node_2234> q = new LinkedList<>();//bfs전용 큐
	public B_2234() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		markedMap = new int[R][C];
		isChecked = new boolean[R][C];
		for(int i = 0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//입력완료
		for(int i=0;i<R;i++) {
			for(int j = 0;j<C;j++) {
				if(isChecked[i][j] == false) {
					BFS(i,j);
					numRoom++;//bfs를 돌때마다 방들의 개수증가
				}
			}
		}
		int tempValue = 0;
		for(int i= 0;i<R;i++) {
			for(int j = 0;j<C;j++) {
				if(isInBoundery(i, j+1)) {//우측을 본다 
					if(markedMap[i][j] != markedMap[i][j+1]) {//다른 단지번호라면
						tempValue = arrList.get(markedMap[i][j])+arrList.get(markedMap[i][j+1]);
						if(possibleBigSize < tempValue) {//대소비교
							possibleBigSize = tempValue;//답 갱신
						}
					}
				}
				if(isInBoundery(i+1, j)) {//하측을 본다, 위와동일
					if(markedMap[i][j] != markedMap[i+1][j]) {
						possibleBigSize = Math.max(possibleBigSize, arrList.get(markedMap[i][j])+arrList.get(markedMap[i+1][j]));
						if(possibleBigSize < tempValue) {
							possibleBigSize = tempValue;
						}
					}
				}
				
			}
		}
		//답출력
		System.out.println(numRoom+ "\n" + biggestRoomSize + "\n" + possibleBigSize);
}catch (Exception e) {}
	}
	void BFS(int i, int j) {
		q.clear();
		q.offer(new Node_2234(i,j));//현재값 넣고
		isChecked[i][j] = true;//체크 해주고
		int number = arrList.size();//현재 사이즈가 단지번호
		markedMap[i][j] = number;//단지번호맵에 갱신
		int roomSize = 1;//방개수
		while(!q.isEmpty()) {
			Node_2234 temp = q.poll();
			int r = temp.r;
			int c = temp.c;
			for(int k= 0;k<4;k++) {
				if((map[r][c]&(1<<k)) == 0) {//서북동남이므로 , 비트마스킹으로 벽이 존재하는지 확인, 0이 나오면 벽이 없다는 뜻이므로 이동가능
					int rr = r + dirR[k];//좌표갱신
					int cc = c + dirC[k]; 
					if(isInBoundery(rr, cc) && isChecked[rr][cc] == false) {//갈수있는 곳이라면
						markedMap[rr][cc] = number;//단지번호 붙여주고
						isChecked[rr][cc] = true;//갔다고 표시해주고
						roomSize++;//방개수 증가시켜주고
						q.offer(new Node_2234(rr, cc));//좌표 넣어줌
					}
				}
			}
		}
		biggestRoomSize = Math.max(biggestRoomSize, roomSize);//방의 개수가 많은것으로 답2 갱신
		arrList.add(roomSize);//단지번호에 해당하는 방개수를 추가
	}
	boolean isInBoundery(int r, int c) {
		if(r>=0 && r<R && c>=0 && c<C) {
			return true;
		}
		return false;
	}
	class Node_2234{
		int r, c;
		public Node_2234(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
