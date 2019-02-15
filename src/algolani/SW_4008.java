package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4008 {
	int N, max, min;
	final int PLUS = 0;
	final int MINUS = 1;
	final int MULTILPY = 2;
	final int DIVISION = 3;
	int[] operand = new int[12];
	int[] operator = new int[4];
	public SW_4008() {try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			max = -100000000;//초기화
			min =  100000000;
			N = Integer.parseInt(br.readLine());
			String tempStr = br.readLine();
			StringTokenizer st = new StringTokenizer(tempStr, " ");
			for(int i= 0;i<4;i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			tempStr = br.readLine();
			st = new StringTokenizer(tempStr, " ");
			for(int i =0; i< N; i++) {
				operand[i] = Integer.parseInt(st.nextToken());
			}
			//입력 완료
			
			dfs(operand[0], 0);//첫번째 숫자와 사용한 연산자 갯수 입력
			System.out.println("#"+t + " " + (max-min));
		}
		
		
		}catch (Exception e) {e.printStackTrace();}
	}
	
	void dfs(int num, int count) {
		if(count == N-1) {//넘겨받은 연산자의 갯수가 마지막일 때 결과값 비교하여 return
			if(num < min) 
				min = num;
			if(num > max) 
				max = num;
			return;
		}
		for(int i= 0;i<4;i++) {
			if(operator[i] != 0) {
				operator[i]--;
				if(i == PLUS) dfs(num+operand[count+1], count+1);
				else if(i == MINUS) dfs(num-operand[count+1], count+1);
				else if(i == MULTILPY) dfs(num*operand[count+1], count+1);
				else if(i == DIVISION) dfs(num/operand[count+1], count+1);
				operator[i]++;
			}
		}
	}
}

