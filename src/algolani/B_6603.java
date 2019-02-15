package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_6603 {
	//로또
	int N;
	final int MAX = 6;
	int[] arr;
	StringBuilder sb = new StringBuilder();
	public B_6603() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		arr = new int[49];
		String tempStr = null;
		while(!(tempStr = br.readLine()).equals("0")) {//0이면 끝
			st = new StringTokenizer(tempStr, " ");
			N = Integer.parseInt(st.nextToken());
			
			sb.delete(0, sb.length());//초기화
			
			for(int i= 0;i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			//숫자 입력완료
			
			for(int i = 0;i <= N-MAX;i++) {//계산 ㄱㄱ
				sb.append(arr[i]+ " ");//
				dfs(1, i+1);//count 1과 i+1번 인덱스를 넣도록 인자값을 넘겨줌
				sb.delete(0, sb.length());//초기화
			}
			System.out.println();//다 출력되면 한줄을 띄운다
		}
}catch (Exception e) {e.printStackTrace();}	
	}
	void dfs(int count, int index) {
		if(count == MAX) {//숫자가 MAX숫자만큼썼다면 출력하고 함수 리턴
			System.out.println(sb.toString());
			return;
		}
		for(int i = index;i < N;i++) {//받아온 index부터 반복
			if(i<N) {//N을 초과하지않으면
				sb.append(arr[i]+ " ");//인덱스 숫자 + space해주어 Stringbuilder에 추가해준다
				dfs(count+1, i+1);//그 다음 인덱스와 몇개의 숫자를 썼는지 dfs함수로 들어간다
				sb.delete(sb.lastIndexOf(arr[i]+""), sb.length());//리턴되면  새로운값을 붙일 수 있도록 전에 넣었던값을 지운다
			}
		}
	}
}