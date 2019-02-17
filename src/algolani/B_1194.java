package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_1194 {
	char[][] map;
	boolean[][][] isChecked;
	int[] dirR = {0,1,0,-1};
	int[] dirC = {1,0,-1,0};
	int answer = -1;//출구 못찾으면 -1출력되도록 초기화
	Queue<Node_1994> q = new LinkedList<>();
	public B_1194() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		isChecked = new boolean[R][C][1<<6];//fedcba 순으로 비트마스킹
		int sR=0, sC=0;
		
		for(int i= 0;i<R;i++) {
			String str = br.readLine();
			for(int j = 0; j < C ;j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '0') {//시작위치라면
					map[i][j] = '.';//빈곳으로 설정하고
					sR = i;//좌표 저장
					sC = j;
				}
			}
		}
		q.offer(new Node_1994(sR, sC, 0));//시작위치넣어줌
		isChecked[sR][sC][0] = true;//시작위치 방문 체크
		int count = 0;
		while(!q.isEmpty()) {
			int qSize = q.size();
			count++;
			for(int i= 0;i<qSize;i++) {
				Node_1994 node = q.poll();
				int r = node.r;
				int c = node.c;
				int key = node.key;
				for(int k = 0;k<4;k++) {
					int rr = r + dirR[k];
					int cc = c + dirC[k];
					
					//범위 안이고, 방문하지않았고, 벽이 아니라면
					if(rr>=0 && rr< R && cc >= 0 && cc < C
							&& isChecked[rr][cc][key] == false
							&& map[rr][cc] != '#') {
						
						if(map[rr][cc] == '1') {//출구일때
							
							answer = count;//답안 갱신, 맨처음 만난것이가장 빨리 걸린것
                            i = qSize;//k-for문 나가서 i-for문도 나가기 위해
							q.clear();
							break;
						
						}else if(map[rr][cc]>='a' && map[rr][cc] <= 'f') {//열쇠일때
						
							int newKey = key|1<<(map[rr][cc]-'a');//0,1,2,3,4,5 중에 하나를 비트마스킹
							isChecked[rr][cc][newKey] = true;//새로운 키로 방문체크
							q.offer(new Node_1994(rr, cc, newKey));//새로운 키와 좌표를 넣어줌
			
						}else if(map[rr][cc]>='A' && map[rr][cc] <='F'//문이고
								&& ((key & (1<<(map[rr][cc]-'A'))) != 0)) {//열쇠를 가지고있다면
							
							isChecked[rr][cc][key] = true;//방문 체크
							q.offer(new Node_1994(rr, cc, key));//큐에 넣어줌
							
						}else if(map[rr][cc] == '.') {//빈 곳일때
							
							isChecked[rr][cc][key] = true;//방문 체크
							q.offer(new Node_1994(rr, cc, key));//큐에 넣어줌
						}
						
					}
				}
			}
		}
		System.out.println(answer);//답출력
}catch (Exception e) {e.printStackTrace();}
	}
	class Node_1994{
		int r, c, key;
		public Node_1994(int r, int c, int key) {
			this.r = r;
			this.c = c;
			this.key = key;
		}
	}
}