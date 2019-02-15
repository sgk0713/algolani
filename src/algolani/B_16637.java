package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B_16637 {
	//괄호 추가하기
	int N;
    int answer;
	ArrayList<Character> operator = new ArrayList<>();
	ArrayList<Integer> operand = new ArrayList<>();
	public B_16637() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
        answer = Integer.MIN_VALUE;//초기화 ㄹㅇ중요..... 이거 안해주면 틀림...ㅠ
		String str = br.readLine();
		for(int i = 0;i<N;i++) {
			if(i%2 == 0) {
				operand.add((int) (str.charAt(i)-'0'));
			}else {
				operator.add(str.charAt(i));
			}
		}
		//연산자와 피연산자 arraylist에 각각 순서대로 담아줌
		
		dfs(0);//계산
		System.out.println(answer);//답출력
}catch (Exception e) {e.printStackTrace();}
	}
	
	void dfs(int idx) {//괄호를 넣은경우와 안넣은 경우로 dfs로 접근한다
		
		if(idx+1 >= operand.size()) {//마지막 숫자까지 갔으면 순서대로 계산하고 답안 갱신
			cal();
			
		}else {
			//괄호를 넣고 다음 값으로 이동할때
			int firstNode = operand.get(idx);
			int secondNode = operand.get(idx+1);
			char tempOperator = operator.get(idx);
			
			operand.remove(idx);//지우고
			operand.remove(idx);
			operator.remove(idx);
			
			int tempValue = firstNode;//계산하고
			if(tempOperator == '+') {
				tempValue += secondNode;
			}else if(tempOperator == '-') {
				tempValue -= secondNode;
			}else {
				tempValue *= secondNode;
			}
			operand.add(idx, tempValue);//추가해줌
//			System.out.println("before :: "+operand.toString());
//			System.out.println("\t   " + operator.toString());
			dfs(idx+1);//다음값으로 이동(괄호를 넣었을 경우)
			
			//괄호를 안넣고 다음 값으로 이동할때
			operand.set(idx, firstNode);//원래 값으로 만들어줌
			operand.add(idx+1, secondNode);
			operator.add(idx, tempOperator);
//			System.out.println("after :: "+operand.toString());
//			System.out.println("\t   " + operator.toString());
			dfs(idx+1);//다음값으로 이동
		}
	}
	void cal() {
		int temp = operand.get(0);
		char tempOperator;
		for(int i= 1;i< operand.size();i++) {
			tempOperator = operator.get(i-1);
			if(tempOperator == '+') {
				temp += operand.get(i);
			}else if(tempOperator == '-') {
				temp -= operand.get(i);
			}else {
				temp *= operand.get(i);
			}
		}
		answer = Math.max(answer, temp);
	}
}