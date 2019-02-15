package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_1248 {
	//맞춰봐
	final int POSITIVE = 10;
	final int NEGATIVE = -10;
	final int ZERO = 0;
	int N;
	char[][] arr;
	int[] num = new int[21];
	int[] hint;//값의 부호를 저장해둠
	int[] answer;//답이 들어있는 배열
	public B_1248() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		answer = new int[N];
		hint = new int[N];		
		arr = new char[N][N];
		int count = 0;
		for(int i= -10;i<=10;i++) {//-20에서 20까지 총 21개숫자로 초기화
			num[count++] = i;
		}
		int idx = 0;
		count = 0;
		for(int i= 0;i<N;i++) {
			for(int j = i;j<N;j++) {
				arr[i][j] = str.charAt(count++);
				if(i==j) {//같은자리일 경우 숫자하나에 대해 계산한 값이므로 부호유추 가능
					if(arr[i][j] == '+') {
						hint[idx] = POSITIVE;
					}else if(arr[i][j] == '-') {
						hint[idx] = NEGATIVE;
					}else {
						hint[idx] = ZERO;
					}
					idx++;
				}
			}
		}
		dfs(0);
		for(int i = 0;i<N;i++) {//답출력
			System.out.print(answer[i] + " ");
		}
		
}catch (Exception e) {e.printStackTrace();}	
	}
	boolean dfs(int idx) {
		if(idx == N) {//N으로 갔을때 확인하고 참인지 거짓인지 리턴
			if(check(idx-1)) {
				return true;			
			}
			return false;
		}
		switch(hint[idx]) {//힌트의 부호를 보고 부호에 맞는 숫자를 입력해서 비교
			case NEGATIVE://음수일경우
				for(int i = 9;i>=0;i--) {//num배열의 0~9번인덱스에 음수저장돼있음
					answer[idx] = num[i];//넣어주고
					if(check(idx)) {//현재 넣은값에 대해 부호를 체크해서 성립하는지 체크
						if(dfs(idx+1)) {//성립하면 다음값을 넣을 인덱스를 하나 늘려서 dfs돌림
							return true;//만약끝까지 갔다면 참일테니 true를 반환하면서 dfs나가게됨
						}
					}
				}
				break;
			case POSITIVE:
				for(int i = 11;i<21;i++) {
					answer[idx] = num[i];
					if(check(idx)) {
						if(dfs(idx+1)) {
							return true;
						}
					}
				}
				break;
			case ZERO:
				answer[idx] = num[10];
				if(check(idx)) {
					if(dfs(idx+1)) {
						return true;
					}
				}
				break;
		}
		return false;
	}
	boolean check(int idx) {
		int temp = 0;//값의 부호를 위해누적 합산을 위한 변수
		for(int i= 0;i<=idx;i++) {//현재 입력된 인덱스 만큼돌려서 부호성립하는지 체크
			temp = 0;
			for(int j = i;j<=idx;j++) {
				temp += answer[j];
				if(arr[i][j] == '+' && temp <= 0) {
					return false;
				}else if(arr[i][j] == '-' && temp >= 0) {
					return false;
				}else if(arr[i][j] == '0' && temp != 0) {
					return false;
				}
			}
		}
		return true;
	}
}
