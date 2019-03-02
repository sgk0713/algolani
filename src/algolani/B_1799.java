package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1799 {//비숍
	int N, totalEmptySlot, maxBishop;
	int[] emptyR;
	int[] emptyC;
	Bishop[] bishop;
	public B_1799() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maxBishop = N*2 - 2;
		emptyR = new int[N*N];
		emptyC = new int[N*N];
		bishop = new Bishop[maxBishop];
		for(int i = 0; i < N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 1) {
					emptyR[totalEmptySlot] = i;
					emptyC[totalEmptySlot] = j;
					totalEmptySlot++;
				}
			}
		}
		System.out.println(dfs(0, 0));
}catch (Exception e) {e.printStackTrace();}
	}
	int dfs(int count, int curEmptySlot) {
		int tempAnswer = count;
		for(int i = curEmptySlot;i<totalEmptySlot;i++) {
			if(tempAnswer < maxBishop) {
				if(isPossible(emptyR[i], emptyC[i], count)) {
					bishop[count] = new Bishop(emptyR[i], emptyC[i]);
					tempAnswer = Math.max(tempAnswer, dfs(count+1, i+1));
				}
			}
		}
		return tempAnswer;
	}
	boolean isPossible(int r, int c, int count) {
		for(int j = 0;j<count;j++) {
			if(Math.abs(r-bishop[j].r) == Math.abs(c-bishop[j].c)) {
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
