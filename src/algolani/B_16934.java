package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class B_16934 {
	public B_16934() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringBuilder tempSb = new StringBuilder();
		HashMap<String, Integer> hashMap = new HashMap<>();
		String answer = null;
		for(int k= 0;k<N;k++) {
			answer = null;//초기화
			sb.delete(0, sb.length());//초기화
			tempSb.delete(0, tempSb.length());//초기화
			
			tempSb.append(br.readLine());//문자열 입력 받음
			
			int len = tempSb.length();
			for(int i =0;i < len;i++) {
				sb.append(tempSb.charAt(i));
				
				if(!hashMap.containsKey(sb.toString())) {//하나하나 접두사로 인지하여 있는지 체크하고 넣어준다
					hashMap.put(sb.toString(), 0);
					if(answer == null) {//답갱신이 된적없으면
						answer = sb.toString();//쓰인적없던 첫번째 접두사가 정답으로갱신된다
					}
				}
			}
			int count = hashMap.get(tempSb.toString());//쓰인횟수를 위해 value값을 가져온다
			if(answer == null) {//모든 접두사들이 쓰였다면
				if(count == 0) {//닉네임의 별칭을 찾으려던 횟수가 처음이라면
					answer = tempSb.toString();//그대로 출력
				}else {
					answer = tempSb.toString()+(count+1);//이전에도 쓰였던 닉네임이라면 2부터 하나씩 증가하며 닉네임에 횟수를 붙여 답으로 갱신
				}
			}
			hashMap.put(tempSb.toString(), count+1);//닉네임의 사용횟수를 1증가시켜준다
			System.out.println(answer);//답출력
		}
}catch (Exception e) {e.printStackTrace();}
	}
}
