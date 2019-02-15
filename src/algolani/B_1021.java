package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_1021 {
	//회전하는 큐
	int N, M, answer=0;
	int[] list;
	ArrayList<Integer> aList = new ArrayList<>();
	public B_1021() {
try {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i =0;i<N;i++) {//숫자들을 담는다
			aList.add(i);
		}
		list = new int[M];//찾아야할 목록
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0;i< M;i++) {
			list[i] = Integer.parseInt(st.nextToken())-1;//0부터 카운터 하기 위해 -1을 해주어 넣어준다
		}
		dfs(0, 0, 0);//계산
		System.out.println(answer);//답 출력
}catch (Exception e) {e.printStackTrace();}	
	}
	//arraylist의 인덱스, 찾아야할값의 인덱스, 회전한 횟수
	void dfs(int nIdx, int mIdx, int count) {
		if(mIdx == M) {//찾으려는 목록들을 다 찾으면 답갱신 후 리턴
			answer = count;
			return;
		}
		int temp = nIdx;//계산하기 위해 임시 변수에 담음
		int rightCount = 0, leftCount = 0;//우회전횟수 좌회전 횟수
		while(true) {
			if(aList.get(temp) == list[mIdx]) {//찾으려는 값을 찾으면 스탑
				break;
			}else {//숫자들이 담긴 인덱스를 우측으로 이동시킴
				temp = (temp+1)%aList.size();
				rightCount++;//이동횟수 증가
			}
		}
		temp = nIdx;//인덱스 계산을 위해 초기화
		while(true) {
			if(aList.get(temp) == list[mIdx]) {//찾으려는 값을 찾으면 스탑
				break;
			}else {//숫자들이 담긴 인덱스를 좌측으로 이동시킴
				temp--;
				if(temp<0) {//음수면 맨뒤로 수정
					temp = aList.size()-1;
				}
				leftCount++;//이동 횟수 증가
				if(leftCount>rightCount) {// 증가했는데오른쪽으로 간거보다 많으면 더이상 돌려보지않음
					break;
				}
			}
		}
		//탐색해본 결과를 토대로 최적의 이동횟수를 계산
		int nextIdx = nIdx;
		int nextCount = count;
		if(rightCount == 0) {//돌리지않았을때(현재값이 찾으려는 값일때)
			
		}else if(rightCount<=leftCount) {//우측으로 돌릴때가 적게 이동하는 횟수일때
			nextIdx = (nIdx+rightCount)%aList.size();//찾은 인덱스로 갱신
			nextCount = count+rightCount;//이동횟수 갱신
		}else {//좌측으로 돌릴때가 적게 이동했을 경우
			nextIdx = temp;//temp가 최근 계산된 인덱스이므로 temp의 값으로 갱신 
			nextCount = count + leftCount;//이동횟수 갱신
		}
		aList.remove(nextIdx);//리스트에서 지운다
		if(aList.isEmpty()) {//지웠는데 숫자가 아무것도 없을때
			answer = nextCount;//망설이지말고 답갱신후 리턴
			return;
		}
		
		//리스트의 크기가 줄었으니
		//가려는 인덱스가 크기를 넘었을 수 있음.
		//알맞은 인덱스로 갈수있게 갱신해주면서 다음 메소드로 들어감
		dfs(nextIdx%aList.size(), mIdx+1, nextCount);
	}
}
