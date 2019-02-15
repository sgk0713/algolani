package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SW_5658 {
	//보물상자 비밀번호
	public SW_5658() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		TreeSet<Integer> set = new TreeSet<>();//중복 ㄴ ㄴ 정렬 ㅇ 
		StringBuilder sb = new StringBuilder();
		sb.append("0x");//16진수임을 표시하기 위해 초기값 생성
		for(int t = 1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String str = br.readLine();
			set.clear();//초기화
			int count = N/4;// 몇번 돌릴지계산
			for(int i = 0;i<count;i++) {
				int idx = 0;
				int k = i;
				for(int j = 0;j<N;j++) {
					if(idx < count) {
						sb.append(str.charAt(k));//자리수채움
						idx++;
						if (idx == count){//자리수만큼 채워지면
							idx = 0;
							set.add(Integer.decode(sb.toString()));//10진수로 변환해서 treeset에 넣어주고
							sb.delete(2, sb.length());//0x제외하고 다 지움
						}
					}
					k=(k+1)%N;//인덱스 순환
				}
			}
			count = 0;
			for(int answer : set) {//오름차순 정렬돼있으므로
				if(count++ == set.size()-K) {//사이즈에서K를 빼야 내림차순에서 몇번째인지 알 수 있음
					System.out.println("#"+t+" "+ answer);
					break;
				}
			}
		}
}catch (Exception e) {e.printStackTrace();}
	}
}
