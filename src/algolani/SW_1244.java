package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class SW_1244 {
	//최대 상금
	int answer, switchCount, maxNum;
	Integer[] arr;
	StringBuilder sb;
	public SW_1244() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br .readLine());
		for(int t = 1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			sb = new StringBuilder(st.nextToken());//숫자 입력받음
			switchCount = Integer.parseInt(st.nextToken());//교환횟수 입력
			arr = new Integer[sb.length()];
			maxNum = answer = 0;//초기화
			for(int i = 0;i<sb.length();i++) {
				arr[i] = sb.charAt(i)-'0';//입력받은 숫자를 각자리수를 입력받음
			}
			Arrays.sort(arr, Collections.reverseOrder());//내림차순으로 정렬(최대값만들기위해)
			for(int n:arr) {
				maxNum = 10*maxNum+n;//최대값이 뭔지 찾음
			}			
			dfs(0,0);//계산
			System.out.println("#"+t + " " + answer);//답 출력
		}
}catch (Exception e) {e.printStackTrace();}
	}

	void dfs(int idx, int count) {
		if(count == switchCount || (Integer.parseInt(sb.toString()) == maxNum && count > 0)) {//최대값을 찾았거나 교환횟수를 다 사용했을때
			if((switchCount-count)%2 == 0) {//남은 교환횟수가 짝수면 같은카드 두번 교환 가능하니 최대값 비교
				answer = Math.max(answer, Integer.parseInt(sb.toString()));
			}else {//홀수면 마지막 1의 자리와 10의 자리만 교환해주고 최대값 비교
				char temp = sb.charAt(sb.length()-2);
				sb.setCharAt(sb.length()-2, sb.charAt(sb.length()-1));
				sb.setCharAt(sb.length()-1, temp);
				answer = Math.max(answer, Integer.parseInt(sb.toString()));
				sb.setCharAt(sb.length()-1, sb.charAt(sb.length()-2));
				sb.setCharAt(sb.length()-2, temp);
			}
			return;
		}else {
			for(int i = idx; i<sb.length()-1;i++) {//각 자리수를 바꿔가며 재귀함수로 들어간다
				for(int j = i;j<sb.length();j++) {
					char temp = sb.charAt(i);
					sb.setCharAt(i, sb.charAt(j));
					sb.setCharAt(j, temp);
					dfs(i+1, count+1);
					if(answer == maxNum) {//빠르게 끝내기
						return;
					}
					sb.setCharAt(j, sb.charAt(i));
					sb.setCharAt(i, temp);
				}
			}
		}
	}
}