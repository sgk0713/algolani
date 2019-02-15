package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class B_1244 {
	//스위치 켜고 끄기
	public B_1244() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//for(int t = 1; ;t++) {
//System.out.println("#"+t);
		int N = Integer.parseInt(br.readLine());//스위치 개수입력 : 8
		boolean[] lights = new boolean[N+1];// !(NOT) 연산자를 쓰기 위해boolean 배열 선언
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1;i<=N;i++) {//스위치입력 : 0 1 0 1 0 0 0 1
			if(Integer.parseInt(st.nextToken()) == 0) {
				lights[i] = false;
			}else {
				lights[i] = true;
			}
		}
		int T = Integer.parseInt(br.readLine());//학생수 입력 3번째줄
		for(int i = 0;i<T ;i++) {//학생수만큼 진행
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());// 1이나 2
			int idx = Integer.parseInt(st.nextToken());// 인덱스
			switch (gender) {
			case 1://남자면
				for(int j = idx;j<=N;j+=idx) {//주어진 수만큼 증가하면서 반대로 바꿈
					lights[j] = !lights[j];
				}
				break;
			case 2://여자면
				lights[idx] = !lights[idx];//현재위치 일단 반대로 바꾸고
				for(int j = 1;j<=N; j++) {//1씩증가하면서 비교하고 바꿈
					if(idx+j <= N && idx-j >0 && lights[idx+j] == lights[idx-j] ) {
						lights[idx+j] = lights[idx-j] = !lights[idx+j];
					}else {
						break;
					}
				}
				break;
			}
		}
//System.out.println("답: ");
		showLights(lights);//답출력
//System.out.println("\n");	
//		}
}catch (Exception e) {e.printStackTrace();}
	}
	void showLights(boolean[] lists) {
		for(int i= 1;i<lists.length;i++) {
			if(lists[i]) {
				System.out.print("1 ");
			}else {
				System.out.print("0 ");
			}
			if(i%20 == 0) {
				System.out.println();
			}
		}
	}
	void makeTestCase() {
		int switchNum = 8;
		int studentNum = 1;
		System.out.println(switchNum);
		for(int i = 0;i<switchNum;i++) {
			System.out.print(new Random().nextInt(2)+" ");
		}
		System.out.println();
		System.out.println(studentNum);
		for(int i = 0;i<studentNum;i++) {
			System.out.println(new Random().nextInt(2)+ " " + (new Random().nextInt(switchNum)+1));
		}
	}
}
