package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_16506 {
	boolean isZero = false;//4번 인덱스가 0인지 1인지 확인하는 플래그
	public B_16506() {try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String answer = null;
		while(T-->0) {//테스트케이스 수만큼 반복
			answer = "";//초기화
			String tempStr = br.readLine();
			StringTokenizer st = new StringTokenizer(tempStr, " ");
			answer += getOPCODE(st.nextToken());//0~4번 인덱스 입력
			answer += "0";//5번 인덱스 고정 입력
			answer += getBinary(st.nextToken(), 3);//6~8번 인덱스 입력
			answer += getBinary(st.nextToken(), 3);//9~11번 인덱스 입력
			if(isZero) {//4번 인덱스가 0이면 rB+0 입력
				answer += getBinary(st.nextToken(), 3);
				answer += "0";
			}else{//1이라면 #C 4자리 비트 입력
				answer += getBinary(st.nextToken(), 4);
			}
			System.out.println(answer);//답 출력
			
		}
	}catch (Exception e) {}
	}
	String getBinary(String number, int length) {//length 길이 만큼의 비트수를 2진수로 반환
		int num = Integer.parseInt(number);
		String temp = Integer.toBinaryString(num);
		int len = length - temp.length();
		for(int i= 0; i< len;i++) {
			temp = "0".concat(temp);
		}
		return temp;
	}
		
	String getOPCODE(String opcode) {//opcode에 맞는 2진수 반환
		isZero = true;//5번인덱스가 0인 경우
		if(opcode.equals("ADD"))
			return "00000";
		if(opcode.equals("SUB"))
			return "00010";
		if(opcode.equals("MOV"))
			return "00100";
		if(opcode.equals("AND"))
			return "00110";
		if(opcode.equals("OR"))
			return "01000";
		if(opcode.equals("NOT"))
			return "01010";
		if(opcode.equals("MULT"))
			return "01100";
		if(opcode.equals("LSFTL"))
			return "01110";
		if(opcode.equals("LSFTR"))
			return "10000";
		if(opcode.equals("ASFTR"))
			return "10010";
		if(opcode.equals("RL"))
			return "10100";
		if(opcode.equals("RR"))
			return "10110";
		isZero = false;//5번인덱스가 1인경우
		if(opcode.equals("ADDC"))
			return "00001";
		if(opcode.equals("SUBC"))
			return "00011";
		if(opcode.equals("MOVC"))
			return "00101";
		if(opcode.equals("ANDC"))
			return "00111";
		if(opcode.equals("ORC"))
			return "01001";
		if(opcode.equals("MULTC"))
			return "01101";
		if(opcode.equals("LSFTLC"))
			return "01111";
		if(opcode.equals("LSFTRC"))
			return "10001";
		if(opcode.equals("ASFTRC"))
			return "10011";
		if(opcode.equals("RLC"))
			return "10101";
		if(opcode.equals("RRC"))
			return "10111";
		return null;
	}
}