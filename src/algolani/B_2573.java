package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2573 {
	//빙산
	int R, C, totalIceberg, tempCount, answer;
	int[][] map;//지도용
	boolean[][] isChecked;//bfs돌릴때 왔던길 돌아가지 않기용
	boolean[][] wasZero;//빙산을 갱신할때 0이됐을때 이전값이 0이었는지 확인용
	int[] dirR = {0, 1, 0, -1};
	int[] dirC = {1, 0, -1, 0};
	Queue<Pair_2573> q = new LinkedList<>();//특정 위치에서 빙산갯수 세기용(bfs)
	ArrayList<Pair_2573> list = new ArrayList<>();//빙산들 담아두는 리스트
	public B_2573() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		isChecked = new boolean[R][C];
		wasZero = new boolean[R][C];
		map = new int[R][C];
		for(int i= 0; i<R;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j< C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) {//빙산일경우
					list.add(new Pair_2573(i, j));
					totalIceberg++;//빙산의 총 개수를 파악한다
				}
			}
		}
		while(!list.isEmpty()) {//빙산이 없어질때까지 반복
			answer++;//초기값은 0이고 처음 들어오면 1이됨. 1년후...
			tempCount = totalIceberg;//빙산 개수의 변화가 있는지 확인하기 위해쓰임
			for(int i =0;i<wasZero.length;i++) {//이전에 0이었다.(바다물이었다)로 초기화
				Arrays.fill(wasZero[i], true);
			}
			for(int i= 0;i<list.size();i++) {//리스트에 담긴 빙산들의 높이값을 갱신
				int r = list.get(i).r;
				int c = list.get(i).c;
				int num = map[r][c];
				int zeros = howManyZeros(r, c);
				wasZero[r][c] = false;//빙산이므로 false로 바닷물이 아니었다고 표시
				num-=zeros;
				if(num<= 0) {//음수값이 나오면
					num = 0;//0으로 만들어주고
					totalIceberg--;//빙산의 개수 하나없애줌
					list.remove(i);//리스트에서도 삭제
					i--;//리스트의 인덱스가 하나 줄어드니까i도 줄여주고 다음에 같은 i값을 갖게 해줌
				}
				map[r][c] = num;//맵에 갱신
			}
			showMap(map);
			if (totalIceberg == 0) {//총 빙산 개수가 0이면 나눠지지않고 한꺼번에 소멸됐다는뜻
				answer = 0;//0으로 만들고 while문 나감
				break;
			}
			if(tempCount != totalIceberg) {//빙산갯수에 변화가 있을 때
				int r = list.get(0).r;//첫번째 빙산만 봐봄
				int c = list.get(0).c;
				int curIce = howManyIceFrom(r, c);//첫번째 빙산에서 몇개의 빙산이 나오는지 본다
				if(curIce != totalIceberg) {//총 개수랑 안맞으면 어디 동떨어진곳에 있다는것
					break;//2개이상으로 나눠졌다는뜻이므로 while문 나감
				}
			}
		}
		System.out.println(answer);//답출력
}catch (Exception e) {}
	}
	
	int howManyIceFrom(int row, int col) {
		q.clear();
		q.offer(new Pair_2573(row, col));
		for(int i= 0;i<isChecked.length;i++) {//체크값 초기화
			Arrays.fill(isChecked[i], false);
		}
		isChecked[row][col] = true;//첫시작은 들린것으로 시작
		int iceCount = 1;
		while(!q.isEmpty()) {
			Pair_2573 tempP = q.poll();
			int r = tempP.r;
			int c = tempP.c;
			for(int i= 0;i<4;i++) {
				int rr = r+dirR[i];
				int cc = c+dirC[i];
				//테두리는 무조건0이기때문에 범위를 고려안해도 됨
				if(map[rr][cc] > 0//빙산이고
					&& isChecked[rr][cc] == false) {//들린곳아니면
					isChecked[rr][cc] = true;
					q.offer(new Pair_2573(rr, cc));
					iceCount++;
				}
			}
		}
		return iceCount;
	}
	int howManyZeros(int r, int c) {
		int count = 0;
		for(int i = 0;i< 4;i++) {
			int rr = r + dirR[i];
			int cc = c + dirC[i];
			if(wasZero[rr][cc] == true//과거도 0
			&& map[rr][cc] == 0) {//지금도 0이면
				count++;
			}
		}
		return count;
	}
	class Pair_2573{
		int r, c;
		public Pair_2573(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	void showMap(int[][] arr) {
		System.out.println("showMap : ");
		for(int i= 0;i<arr.length;i++) {
			for(int j = 0;j<arr[i].length;j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
