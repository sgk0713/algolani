package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class B_3425 {
	//고스택
	Stack<Integer> s = new Stack<>();
	ArrayList<String> list = new ArrayList<>();
	int N, idx;
	int[] numbers;
	public B_3425() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
while(true) {
		list.clear();
		for(;;) {
			String str = br.readLine();
			if(str.equals("QUIT")) {
				System.exit(0);
			}else if(str.equals("END")) {
				N = Integer.parseInt(br.readLine());
				numbers = new int[N];
				for(int i= 0;i<N;i++) {
					numbers[i] = Integer.parseInt(br.readLine());
				}
				break;
			}else {
				list.add(str);
			}
		}
			br.readLine();
			for(int i= 0;i<N;i++) {
				s.clear();
				s.push(numbers[i]);
try {
				for(idx = 0;idx<list.size();idx++) {
					String value;
					switch (value = list.get(idx)) {
					case "POP":
						POP();
						break;
					case "INV":
						INV();
						break;
					case "DUP":
						DUP();
						break;
					case "SWP":
						SWP();
						break;
					case "ADD":
						ADD();
						break;
					case "SUB":
						SUB();
						break;
					case "MUL":
						MUL();
						break;
					case "DIV":
						DIV();
						break;
					case "MOD":
						MOD();
						break;
					default:
						NUM(Integer.parseInt(value.substring(4, value.length())));
						break;
					}
				}
}catch (Exception e) {idx = list.size();s.clear();}
				if(s.size() == 1) {
					System.out.println(s.pop());
				}else {
					System.out.println("ERROR");
					s.clear();
				}
			}
			System.out.println();
		
}
}catch (Exception e) {}
	}
	void NUM(int x) {
		s.push(x);
	}
	void POP() {
		s.pop();
	}
	void INV() {
		s.push(-s.pop());
	}
	void DUP() {
		s.push(s.peek());
	}
	void SWP() {
		int temp = s.pop();
		int temp2 = s.pop();
		s.push(temp);
		s.push(temp2);
	}
	void ADD() {
		int temp = s.pop() + s.pop();
		if(Math.abs(temp) > Math.pow(10, 9)) {
			idx = list.size();
			s.clear();
			return;
		}
		s.push(temp);
	}
	void SUB() {
		int temp = s.pop();
		temp = s.pop() - temp;
		if(Math.abs(temp) > Math.pow(10, 9)) {
			idx = list.size();
			s.clear();
			return;
		}
		s.push(temp);
	}
	void MUL() {
		int temp = s.pop();
		int temp2 = s.pop();
		if(Math.abs(temp) > Math.abs(Math.pow(10, 9)/temp2)) {
			idx = list.size();
			s.clear();
			return;
		}
		s.push(temp2*temp);
	}
	void DIV() {
		int temp = s.pop();
		if(temp == 0) {
			idx = list.size();
			s.clear();
			return;
		}
		temp = s.pop()/temp;
		if(Math.abs(temp) > Math.pow(10, 9)) {
			idx = list.size();
			s.clear();
			return;
		}
		s.push(temp);
	}
	void MOD() {
		int temp = s.pop();
		if(temp == 0) {
			idx = list.size();
			s.clear();
			return;
		}
		temp = s.pop()%temp;
		if(Math.abs(temp) > Math.pow(10, 9)) {
			idx = list.size();
			s.clear();
			return;
		}
		s.push(temp);
	}
}
