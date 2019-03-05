package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1799 {//비숍
	//체스판은 검은색 타일과 하얀색 타일이 존재한다.
	//하얀색에 있는 비숍은 검색은 타일에 절대 못 가고, 검은색 타일 또한 하얀색 타일로 절대 못 간다.
	//이 특성을 이용해서 각 타일 색에 대해 최대를 넣고, 다른 색에 대해 최대를 넣어서 합치면 시간안에 답을 찾을 수 있다.
	int N, totalEmptySlot, maxBishop;
	int[] emptyR;
	int[] emptyC;
	Bishop[] bishop;
	final int BLACK = 0;
	final int WHITE = 1;
	public B_1799() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maxBishop = N*2 - 2;//최대 비숍개수
		emptyR = new int[N*N];
		emptyC = new int[N*N];
		bishop = new Bishop[maxBishop];
		for(int i = 0; i < N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 1) {
					emptyR[totalEmptySlot] = i;//1이면 비숍을 넣을 수 있는 자리이므로 좌표값을 저장해둔다
					emptyC[totalEmptySlot] = j;
					totalEmptySlot++;
				}
			}
		}
		System.out.println(dfs(dfs(0, 0, BLACK), 0, WHITE));//black에 채우고 반환값으로 채운 비숍갯수이므로, 바로 이어서white를 채우며 개수를 센다
}catch (Exception e) {e.printStackTrace();}
	}
	int dfs(int count, int curEmptySlot, int color) {
		int tempAnswer = count;
		for(int i = curEmptySlot;i<totalEmptySlot;i++) {//빈곳들을 다 탐색
			if(tempAnswer < maxBishop) {//임시 결과값이 최대값이면 이미 볼필요가없다. 답은 최대값이니..
				int r = emptyR[i];
				int c = emptyC[i];
				if((r+c)%2 == color) {//좌표값을 더한것을 2모듈연산을 하면 0이나1이 나오며 각 타일색을 유추할 수 있다.
					if(isPossible(r, c, count)) {//대각선상에 놓은 비숍이 있는지 확인하고 놓을 수 있는 위치라면
						bishop[count] = new Bishop(r, c);//비숍을 놓는다
						tempAnswer = Math.max(tempAnswer, dfs(count+1, i+1, color));//증가하면서 다음 칸을 본다
					}
				}
			}
		}
		return tempAnswer;
	}
	boolean isPossible(int r, int c, int count) {
		for(int j = 0;j<count;j++) {
			if(Math.abs(r-bishop[j].r) == Math.abs(c-bishop[j].c)) {//bishop배열은 배치한 비숍들의 좌표값을 가지고있다.현재좌표들의 각 값을 빼서 절대값을 씌운값은 대각선상에 있다는 뜻이다
				return false;
			}
		}
		return true;
	}
	class Bishop{
		int r , c;
		public Bishop(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
