package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_16917 {
	public B_16917() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int seasoned = Integer.parseInt(st.nextToken());
		int fried = Integer.parseInt(st.nextToken());
		int half = Integer.parseInt(st.nextToken());
		int full = half*2;//반반 두개시킬때 가격
		
		int requiredSeasoned = Integer.parseInt(st.nextToken());
		int requiredFired = Integer.parseInt(st.nextToken());
		int max = 0, min = 0;;
		if(requiredSeasoned> requiredFired) {//요구 치킨이 뭐가 더 많은지 비교
			max = requiredSeasoned;
			min = requiredFired;
		}else {
			max = requiredFired;
			min = requiredSeasoned;
		}
		int minPrice = 0;// 초기화 
		if(seasoned+fried <= full) {//개별로 각 한마리시킨게 반반 두개 시킨거 보다 저렴할때
			//개별로 주문 함
			System.out.println(requiredSeasoned*seasoned+requiredFired*fried);
		}else {
			minPrice = max * full;//초기화, 반반으로 다 시키는 경우
			int temp  = 0;
			if(max-requiredSeasoned == 0) {//양념치킨 요구가 더 많을 때
				temp = (max - min)*seasoned + min*full;//반반을 최소로 시키고, 나머지는개별로 시키는 경우
				if(temp < minPrice) {
					minPrice = temp;
				}
				temp = max * seasoned + min* fried;//각 개별로 시키는 경우
				if(temp < minPrice) {
					minPrice = temp;
				}
			}else {//프라이드 치킨 요구가 더 많을 때
				temp = (max - min)*fried + min*full;// 위와 동일 
				if(temp < minPrice) {
					minPrice = temp;
				}
				temp = max * fried + min* seasoned;
				if(temp < minPrice) {
					minPrice = temp;
				}
			}
			System.out.println(minPrice);//답출력 
		}
}catch (Exception e) {}
	}
}
