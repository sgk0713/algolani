package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_16462 {
	public B_16462() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double sum = 0;
		int tempNum = 0, len = 0;
		StringBuilder sb = new StringBuilder();
		for(int i= 0;i<N;i++) {
			sb.delete(0, 3);//초기화
			sb.append(br.readLine());//입력받음
			tempNum = 0;//초기화
			len = sb.length();//길이 입력
			for(int j = 0;j < len;j++) {
				if(sb.charAt(j) == '0' || sb.charAt(j) == '6') {
					tempNum = tempNum*10+9;//0이나 6이면 9더해줌
				}else {//그외엔 그냥 더해줌
					tempNum = tempNum*10+(sb.charAt(j)-'0');
				}
			}
			sum += (tempNum>100 ? 100:tempNum);//100보다크면 100더하고 그외엔 그냥 숫자 더함
		}
		System.out.println((int)Math.round(sum/N));//반올림한 숫자 정수형으로 출력
}catch (Exception e) {}
	}
}
