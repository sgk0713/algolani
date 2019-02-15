package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16469 {
	//소년점프
	int R, C;
	final int WALL = -1;
	int answer1 = 1002;//최소값
	int answer2 = -1;//최소값의 갯수
	int[][] map = new int[102][102];
	int[] dirR = {0, 1, 0, -1};//방향
	int[] dirC = {1, 0, -1, 0};
	boolean[][] isChecked;
	int[] badR = new int[3];//악당 위치
	int[] badC = new int[3];
	Queue<Pair_16469> q = new LinkedList<>();
	public B_16469() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tempStr = br.readLine();
		StringTokenizer st = new StringTokenizer(tempStr, " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		for(int i = 1;i<=R;i++) {
			tempStr = br.readLine();
			for(int j = 1;j<=C;j++) {
				map[i][j] = -(tempStr.charAt(j-1)-'0');//0 과 1을 입력받아 -를 붙여 어싸인한다
			}
		}
		for(int i= 0; i< 3;i++) {//악당3명의 r,c값을 배열에 넣는다
			tempStr = br.readLine();
			st = new StringTokenizer(tempStr, " ");
			badR[i] = Integer.parseInt(st.nextToken());
			badC[i] = Integer.parseInt(st.nextToken());
		}
		//입력완료
		boolean possible2 = false, possible3 = false;//1번 악당이 2번,3번을 만날수있는지확인하기 위함 
		
		for(int i= 0;i<3;i++) {//악당 3명에 대해 bfs돌림
			isChecked = new boolean[R+2][C+2];//방문했는지 체크 배열 생성
			q.offer(new Pair_16469(badR[i], badC[i]));//1번악당 큐삽입
			isChecked[badR[i]][badC[i]] = true;//1번악당 방문한것으로 체크
			int count =0;//거리 계산용 변수
			
			while(!q.isEmpty()) {//bfs 시작
				int qSize = q.size();// 큐싸이즈
				for(int k = 0; k<qSize;k++) {//큐사이즈만큼 돌림
					Pair_16469 tPair = q.poll();
					
					for(int j = 0;j<4;j++) {//방향마다 갈수있는지 체크
						int rr = tPair.r + dirR[j];
						int cc = tPair.c + dirC[j];
						if(rr > 0 && rr <= R && cc > 0 && cc <= C//가려는 꼿이 범위안이
						&& map[rr][cc] != WALL//벽(-1)이 아니고
						&& isChecked[rr][cc] == false) {//방문한곳이 아니면
							
							if(i == 0) {//1번악당일 경우 방문하는곳에 2번,3번악당의 위치에 도달했는지 확인
								if(rr == badR[1] && cc == badC[1]) {
									possible2 = true;//도달했으면 true
								}
								if(rr == badR[2] && cc == badC[2]) {
									possible3 = true;
								}
							}
							isChecked[rr][cc] = true;//방문한곳 체크
							map[rr][cc] = Math.max(map[rr][cc], count+1);//가려는 위치의 기존값과 거리중 큰값을 넣어둠
//							showMap();//디버깅용
							q.offer(new Pair_16469(rr, cc));//큐삽입
						}
					}
				}//큐사이즈만큼 돌리는 for문 끝
				count++;//거리 1증가
			}
			
//			showMap();//디버깅용
			if(i == 0 && (!possible2 || !possible3)) {//1번이 2번,3번악당의 위치에 도달하지 못했을 경우
				System.out.println(-1);//출력하고
				break;//악당for문 빠져나감
			}
		}

		if(possible2 && possible3) {//2번3번둘다 만날수있는 경우에 답안 출력
			for(int i = 1;i<=R;i++) {
				for(int j = 1;j<=C;j++) {
					if(map[i][j] > 0 && map[i][j] < answer1) {//1이상 기존값보다 작은 거리면
						answer1 = map[i][j];//갱신
						answer2 = 1;//가능지점 1로초기화
					}else if(map[i][j] == answer1) {//기존 거리값과 거리값이 같으면
						answer2++;//가능지점 증가
					}
				}
			}
			System.out.println(answer1);//답출력
			System.out.println(answer2);
		}
}catch (Exception e) {}
	}
	void showMap() {//디버깅용
		for(int i = 1;i<=R;i++) {
			for(int j = 1;j<=C;j++) {
				if(map[i][j] == -1) {
					System.out.print("- ");
				}else
					System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	class Pair_16469{
		int r, c;
		public Pair_16469(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
