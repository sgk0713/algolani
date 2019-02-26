package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_5052 {//전화번호 목록
	public B_5052() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		boolean flag = true;
		for(int t = 0;t<T;t++) {
			flag = true;
			int N = Integer.parseInt(br.readLine());
			String[] strList = new String[N];
			
			for(int i = 0;i<N;i++) {
				strList[i] = br.readLine();
			}
			Arrays.sort(strList);
			for(int i =0;i<N-1;i++) {
				if(strList[i+1].startsWith(strList[i])) {
					flag = false;
					break;
				}
			}
			if(flag) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
}catch (Exception e) {e.printStackTrace();}
	}
}
