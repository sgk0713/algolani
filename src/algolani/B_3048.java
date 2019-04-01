package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B_3048 {
	public B_3048() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N1 = Integer.parseInt(st.nextToken());
		int N2 = Integer.parseInt(st.nextToken());
		HashSet<Integer> hashSet = new HashSet<>();
		int[] line = new int[26];//알파벳은 26개
		
		String str = br.readLine();
		for(int i = 0;i<N1;i++) {
			hashSet.add((int) str.charAt(N1-1-i));
			line[i] = str.charAt(N1-1-i);
		}
		str = br.readLine();
		for(int i = N1;i<N2+N1;i++) {
			line[i] = str.charAt(i-N1);
		}
		int T  = Integer.parseInt(br.readLine());
		while(T-->0) {//
			boolean flag = false;
			for(int i= 0;i<N2+N1;i++) {//두 그룹을 마주한 라인을 본다
				if(!flag && hashSet.contains(line[i])) {//현재값이 그룹1의 개미일때
					flag = true;//flag를 켠다
				}else if(flag && !hashSet.contains(line[i])) {//이전값이 그룹1의 개미였는데 현재값이 그룹1의 값이 아니라면(마주보는 상태)
					flag = false;//flag를 끄고
					if(i!=0) {//i가 0이 아니면
						int temp = line[i];//이전값과 현재값을 바꾼다
						line[i] = line[i-1];
						line[i-1] = temp;
					}
				}
			}
		}
		for(int i= 0;i<N2+N1;i++) {//line배열에는 숫자를 담았기에 char형으로 변환하여 출력
			System.out.print((char)line[i]);
		}
}catch (Exception e) {e.printStackTrace();}
	}
}
