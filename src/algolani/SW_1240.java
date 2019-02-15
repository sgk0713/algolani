package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1240 {
	//단순 2진 암호코드
	int[][] map = new int[50][100];
	public SW_1240() {try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T= Integer.parseInt(br.readLine());
		for(int t = 1; t<=T;t++) {
			int answer = 0;
			String tempStr = br.readLine();
			StringTokenizer st = new StringTokenizer(tempStr, " ");
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int checkPoint = -1;
			boolean stopFlag = false;
			for(int i =0;i<N;i++) {//입력값에 암호코드는 한줄만 있어도 판별가능하기 때문에 모든 값을 배열에넣을 필요 없다
				tempStr = br.readLine();
				for(int j= 0; j<M; j++) {
					if(!stopFlag) {
						map[i][j] = tempStr.charAt(j)- '0';
						if(checkPoint == -1 && map[i][j] == 1) {
							checkPoint = i; 
						}
					}
				}
				if(checkPoint != -1) {//1을 찾았으면그줄까지만 배열에입력을 받고 나머지는 대입하지않는다
					stopFlag = true;
				}
			}
			String temp = "";
			for(int i= 0; i< M-56;i++) {//암호 코드가 담길 수 있는 라인에서 길이 56을 가질수 있는 모든 숫자를 체크한다
				temp = "";//초기화
				for(int j =i;j< i+56;j++) {//길이56만큼 String형태로 만든다
					temp += map[checkPoint][j];
				}
				temp = getNumberFromBinary(temp);//무슨 숫자인지 메소드를 통해 알아내어대입한다
				if(isCorrect(temp)) {//올바른 검증코드인지 확인한다
					for(int k = 0; k<8;k++) {//올바른 암호코드이면 합을 구하고 더이상 탐색은 그만둔다
						answer += temp.charAt(k)-'0';
					}
					break;
				}
			}
			System.out.println("#"+t+ " "+ answer);//답 출력
		}
		
	}catch (Exception e) {e.printStackTrace();}
	}
	boolean isCorrect(String number) {
		if(number == null || number.length() != 8) {//입력받은 문자열을 검열한다
			return false;
		}
		int first = 0;
		int second = 0;
		for(int i= 0; i<number.length()-1;i++) {
			if(i%2 == 0) {
				first += Integer.valueOf(number.charAt(i)-'0');
			}else {
				second += Integer.valueOf(number.charAt(i)-'0'); 
			}
		}
		if((Integer.valueOf(number.charAt(7)-'0') + (first*3 + second))%10 == 0){
			return true;
		}
		return false;
	}
	
	String getNumberFromBinary(String num) {
		String number = "";
		if(num.charAt(0) == num.charAt(1)
		&& num.charAt(1) == num.charAt(2)
		&& num.charAt(2) == num.charAt(3)) {//첫 4자리가 같으면 잘못된 숫자이므로 null 반환
			return null;
		}
		int first = 1, second = 1 , third = 1, fourth = 1;//비율담는 변수들
		boolean flag1 = true, flag2 = false, flag3 = false, flag4 = false;//비율체크를 위한 flag
		for(int i = 0; i< 56;i+=7) {
			first = second = third = fourth = 1;
			flag1 = true;
			flag2 = flag3 = flag4 = false;// 초기화
			for(int j =0 ;j< 6;j++) {
				if(num.charAt(i+j) == num.charAt(i+j+1)) {//다음값과 같은 경우
					if(flag1) {
						first++;
					}else if(flag2) {
						second++;
					}else if(flag3) {
						third++;
					}else if(flag4) {
						fourth++;
					}
				}else {//다음값과 다른경우, 다음 변수에 접근하도록 flag를 끄고 켠다
					if(flag1) {
						flag1 = false;
						flag2 = true;
					}else if(flag2) {
						flag2 = false;
						flag3 = true;
					}else if(flag3) {
						flag3 = false;
						flag4 = true;
					}
				}
			}
			number+=getNumberFromRatio(first, second, third, fourth);//비율로 숫자를 구한다
		}
			return number;
	}
	String getNumberFromRatio(int a, int b, int c, int d) {//비율로 숫자 구하는 메소드
		if(a == 3 && b == 2 && c == 1 && d == 1) {
			return "0";
		}
		if(a == 2 && b == 2 && c == 2 && d == 1) {
			return "1";
		}
		if(a == 2 && b == 1 && c == 2 && d == 2) {
			return "2";
		}
		if(a == 1 && b == 4 && c == 1 && d == 1) {
			return "3";
		}
		if(a == 1 && b == 1 && c == 3 && d == 2) {
			return "4";
		}
		if(a == 1 && b == 2 && c == 3 && d == 1) {
			return "5";
		}
		if(a == 1 && b == 1 && c == 1 && d == 4) {
			return "6";
		}
		if(a == 1 && b == 3 && c == 1 && d == 2) {
			return "7";
		}
		if(a == 1 && b == 2 && c == 1 && d == 3) {
			return "8";
		}
		if(a == 3 && b == 1 && c == 1 && d == 2) {
			return "9";
		}
		return null;
	}
	
}
