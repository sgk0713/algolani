package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_10711 {//모래성
	int R, C;
	int[][] map;
	Queue<Integer> q = new LinkedList<>();
	int dirR[] = {0,1,0,-1,1,1,-1,-1};
	int dirC[] = {1,0,-1,0,-1,1,-1,1};
	public B_10711() {
try {
	//땅인 지형 8방향 주변의 모래성을 -1해주면서 0이되는 순간 큐에 넣어준다
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for(int i = 0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = str.charAt(j)-'0';
				if(map[i][j]==('.'-'0')) {
					q.offer(i*C+j);//1차원 좌표값으로 전환해서 삽입
				}
			}
		}
		int answer = -1;
		while(!q.isEmpty()) {
			int size = q.size();//현재 담겨져있는 땅만큼 모래성을 깎으면서 0이 무너지는 모래성은 큐에 담아준다
			answer++;//파도횟수 증가
			for(int i = 0;i<size;i++) {
				int idx = q.poll();
				reduceAround(idx/C, idx-((idx/C)*C));//8방향의 모래성을 1만큼 깎는다.깎았는데 0이 되면 큐에 담아준다
			}
		}
		System.out.println(answer);
		
}catch (Exception e) {}
	}
	void reduceAround(int r, int c) {
		for(int i =0;i<8;i++) {
			int rr = r + dirR[i];
			int cc = c + dirC[i];
			if(rr>=0 && rr<R && cc>=0 && cc<C && map[rr][cc]>0) {
				map[rr][cc] -= 1;
				if(map[rr][cc] == 0) {
					q.offer(rr*C+cc);
				}
			}
		}
	}
}
