package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_5550 {
	//나는 개구리로소이다
	public SW_5550() {try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {//T번 반복한다
			int answer = -1;
			String tempStr = br.readLine();//울음소리 저장
			int len = tempStr.length();//울음소리 길이 저장
			char tempChar;
			char[] frogs = new char[500];//개구리 수, 문자열이 5~2500 이므로 최대 500마리
			int maxJ = 0;//몇마리인지 세기위한 위치값
			
			//croakcroak 은 1이다.즉, 울음을 마친 개구리는 다시 울수있다
			
			for(int i =0; i< len;i++) {//각 배열의 문자를 갱신하면서 저장한다
				tempChar = tempStr.charAt(i);//인덱스의 문자를 받아온다
				for(int j = 0; j< 500;j++) {//크기만큼 돌려본다
					//c r o a k
					maxJ = Math.max(maxJ, j);//maxJ 변수 갱신
					if(tempChar == 'c' && (frogs[j] == 0 || frogs[j] == 'k')) {//c인경우 첫시작이므로 울음을 끝난개구리(k값)거나 한번도 안운 개구리(0값)일때 저장한다
						frogs[j] = tempChar;
						break;//더이상의 반복문은 필요없다
					}else if(tempChar == 'r' && frogs[j] == 'c') {//r인 경우 c다음에 올수 있다. 이하 생략
						frogs[j] = tempChar;
						break;
					}else if(tempChar == 'o' && frogs[j] == 'r') {//c r o a k
						frogs[j] = tempChar;
						break;
					}else if(tempChar == 'a' && frogs[j] == 'o') {//c r o a k
						frogs[j] = tempChar;
						break;
					}else if(tempChar == 'k' && frogs[j] == 'a') {//c r o a k
						frogs[j] = tempChar;
						break;
					}
				}
			}
			answer = maxJ+1;//0부터 세었기때문에 +1 해준다
			for(int i=0;i<=maxJ;i++) {
				if(frogs[i] != 'k') {//k로 끝나지 않은 값이 있다는 것은 개구리울음소리가 아니다!
					answer = -1;
					break;//더이상 볼것도 없이 반복문 끝
				}
			}
			System.out.println("#"+ t + " " + answer);//답안 출력
		}}catch (Exception e) {}
	}
}




//public class SW_5550 {
//	//나는 개구리로소이다
//	public SW_5550() {try {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(br.readLine());
//		for(int t = 1;t<=T;t++) {
//			int answer = -1, tempAnswer = 0;
//			String tempStr = br.readLine();
//			int len = tempStr.length();
//			char tempChar;
//			boolean endFlag = false;
//			int c = 0, r = 0, o = 0, a = 0, k = 0;
//			for(int i= 0;i<len;i++) {
//				tempChar = tempStr.charAt(i);
//				
//				if(tempChar == 'c') {
//					if(!endFlag) {
//						++tempAnswer;
//					}else {
//						if((c+r+o+a+k) == (c*5)) {
//							answer = tempAnswer;
//							tempAnswer = 1;
//							endFlag = false;
//						}
//						if(c==k) {
//							endFlag = false;
//						}
//					}
//					c++;r++;o++;a++;k++;
//				}else if(tempChar == 'r') {
//					if(r!=0) {
//						r--;
//					}else {
//						tempAnswer = answer = -1;
//						break;
//					}
//				}else if(tempChar == 'o') {
//					if(o!=0 && o > r) {
//						o--;
//					}else {
//						tempAnswer = answer = -1;
//						break;
//					}
//				}else if(tempChar == 'a') {
//					if(a!=0 && a > o) {
//						a--;
//					}else {
//						tempAnswer = answer = -1;
//						break;
//					}
//				}else if(tempChar == 'k') {
//					if(!endFlag) {
//						endFlag = true;
//					}
//					if(k!=0 && k > a) {
//						k--;
//						c--;
//					}else {
//						tempAnswer = answer = -1;
//						break;
//					}
//				}
////				System.out.println("c : " + c + " r : " + r + " o : " + o + " a : " + a + " k : " + k + " answer : " + answer + " tempAsnwer : " + tempAnswer);
//			}
//			if((c+r+o+a+k) != 0) {
//				answer = -1;
//			}else {
//				answer = Math.max(answer, tempAnswer);
//				
//			}
//			System.out.println("#"+ t + " " + answer);
//		}}catch (Exception e) {}
//	}
//}
