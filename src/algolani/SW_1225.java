package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1225 {
	//암호 생성기
	int T;
	int[] answer = new int[8];//암호 8자리 넣을공간 생성
	SW_1225(){try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tempStr;
		
		while((tempStr = br.readLine()) != null) {//입력받은만큼 처리한다
			T = Integer.parseInt(tempStr);
			tempStr = br.readLine();
			StringTokenizer st = new StringTokenizer(tempStr, " ");
			for(int i =0 ; i< 8;i++) {//배열에 입력 값 저장
				answer[i] = Integer.parseInt(st.nextToken());
			}
			int idx = 0;//배열의 인덱스위치 값
			int minusValue = 0;//감소할값
			while(true) {//break문을만날때까지 반복한다 
				if((answer[idx] -= (minusValue)%5+1) <= 0) {//해당 배열에 1~5사이 범위의 값을 감소하고 0이하가 되면 중단한다
					answer[idx] = 0;
					break;
				}
				idx = (idx+1)%8;//0~7까지의 범위를 반복한다
				minusValue = (minusValue+1)%5;//1~5까지의 범위를 반복한다
			}
			
			//출력문
			System.out.print("#"+T+" ");
			for(int i =0 ; i< 8;i++) {
				System.out.print(answer[idx = (idx+1)%8]+" ");
			}
			System.out.println();
			//출력문 END
		}
	}catch (Exception e) {}
	}
}
