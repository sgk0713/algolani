package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1107 {
	//리모컨
	int N, M, answer, len, min, max;
	final int curChannel = 100;
	final int maxChannel = 1000000;
	StringBuilder sb;
	boolean[] num = new boolean[10];
	public B_1107() {
try {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		Arrays.fill(num, true);
		if(M>0) {//고장난 버튼이 있을 경우만3번째줄 입력 받음
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i= 0; i< M;i++) {
				int temp = Integer.parseInt(st.nextToken());
				num[temp] = false;
			}
		}
		min = 11;
		max = -1;
		for(int i= 0;i<10;i++) {
			if(num[i] == true) {
				min = Math.min(min, i);
				max = Math.max(max, i);
			}
		}		
		if(N == curChannel) {//현재채널이면 0 출력
			System.out.println(0);
		}else if(M == 10) {//전부다 고장나면 + - 로만 갈수있으니 차이값 출력
			System.out.println(Math.abs(N-curChannel));
		}else if(M == 0) {//하나도 고장안나면 숫자의 개수와 +-로 갈수있는 개수중 최소값 출력
			System.out.println(Math.min(getLen(N), Math.abs(N-curChannel)));
		}else {
			answer = Math.abs(N-curChannel);//+ - 로 갈 수있는것으로 초기화
			//원하는값이상에서 최소값 찾기
			sb = new StringBuilder(N+"");
			sb.insert(0, "0");
			for(int i = 1;i<sb.length();i++) {//입력받을시 앞에 0이 붙어있기때문에 index1부터 출발
				for(int j = sb.charAt(i)-'0';j<10;j++) {//자리값의 숫자보다 큰값들을 봄
					if(num[j] == true) {//누를 수있는 버튼이면 다음 자리값을 봄
						if(j>sb.charAt(i)-'0') {//누를 수 있는 버튼인데 현재 자리값보다 크면 그뒷자리값은 누를수있는 최소값만 가져야한다
							sb.replace(i, i+1, j+"");//현재자리값을 큰값으로 바꾸고
							for(int k = i+1;k<sb.length();k++) {//그뒤로는 최소값으로 바꾼다
								sb.replace(k, k+1, min+"");
							}
							i = sb.length();//첫번째 for문을 탈출하기 위해 초기화
						}
						break;//끝
					}else if(j == 9) {//현재값보다 큰값들을 봤는데 다 고장났을 경우
						if(i == 1) {//1번인덱스면 숫자의 자리수를 하나 늘린다
							for(int k = 1;k<10;k++) {
								if(num[k] == true) {
									sb.replace(0, 1, k+"");//0번 인덱스에 1이상의 최소값을 넣고
									break;
								}
							}
							for(int k = 1;k<sb.length();k++) {//그 뒤로는 최소값으로 채운다
								sb.replace(k, k+1, min+"");
							}
							i=sb.length();
						}else {//그외 다른 자리값일 경우
							int tmp = Integer.parseInt(sb.substring(0, i));//앞 문자열을 숫자로 변환하고
							tmp++;//1을 증가하여
							if(getLen(tmp)==i) {//다시 붙여준다. 그 과정에서 자리수가 늘었을수도있으니 길이비교를 하고 알맞게넣는다
								sb.replace(0, i, tmp+"");
							}else {
								sb.replace(0, i, "0"+tmp);
							}
							for(int k = i;k<sb.length();k++) {//그 뒤로는 최소값을 넣어준다
								sb.replace(k, k+1, min+"");
							}
							i=0;//i를 0으로 초기화후 for문에서 1증가하여 1번인덱스부터 검사한다
						}
					}
				}
			}
			int tempNum = Integer.parseInt(sb.toString());//문자열로 숫자로 변환
			if(tempNum >= N) {//원하는 값보다 큰값이면
//				System.out.println("최대 : " + tempNum);
				answer = Math.min(answer, getLen(tempNum) + Math.abs(N-tempNum));//버튼 누를 횟수 최소비교
			}
			
			//원하는값이하에서 최대값 찾기
			sb = new StringBuilder(N+"");
			for(int i = sb.length()-1;i>=0;i--) {//1의자리수부터 10의자리 100의자리 순으로 검사한다
				for(int j = sb.charAt(i)-'0';j>=0;j--) {//자리수의 값보다 작은값을 확인한다
					if(num[j] == true) {//작은값이 있을때
						if(j<sb.charAt(i)-'0') {//기존보다 더 작은값이면
							sb.replace(i, i+1, j+"");
							for(int k = i+1;k<sb.length();k++) {//그 뒤자리 값들은 최대값을 넣어준다
								sb.replace(k, k+1, max+"");
							}
						}
						break;
					}else if(j == 0) {//현재값 이하의 버튼들이 전부 고장난경우
						if(i == 0) {//0번 인데스일 경우
							sb.replace(0, 1, 0+"");//자리수를 하나 감소하기 위해 0으로 채우고
							for(int k = 1;k<sb.length();k++) {//나머지는 최대값으로 채운다
								sb.replace(k, k+1, max+"");
							}
							i = -1;//for문탈출 초기화 
							break;
						}else {//그외 다른 인덱스일 경우
							int tmp = Integer.parseInt(sb.substring(0, i));//0번인덱스부터 전 인덱스까지 숫자로 변환후
							tmp--;//1감소한다
							if(getLen(tmp) == i) {//자리수에 맞게 다시 넣어주고
								sb.replace(0, i, tmp+"");
							}else {
								sb.replace(0, i, "0"+tmp);
							}
							for(int k = i;k<sb.length();k++) {//그 뒤값은 최대값으로 채운다
								sb.replace(k, k+1, max+"");
							}
							//이전 인덱스로 이동하여 값들을 확인한다
						}
						
					}
				}
			}
			tempNum = Integer.parseInt(sb.toString());
			if(tempNum < N && !(tempNum == 0 && num[0] == false)) {
//				System.out.println("최소 : " + tempNum);
				answer = Math.min(answer, getLen(tempNum) + Math.abs(N-tempNum));
			}
			
			
			
			
 			System.out.println(answer);
		}
}catch (Exception e) {e.printStackTrace();}	
	}
	int getLen(int number) {
		
		if(number == 0) {
			return 1;
		}else {
			return (int)(Math.log10(number)+1);
		}
	}
}