package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_10025 {
	//게으른 백곰
	//입력을 받아서 K의 범위만큼의 합산을 처음에 구하고
	//K의 범위안에서 첫값과 끝값을 더학고 빼어 비교하여 답추출
	public B_10025() {
try {
		int MAX = 10000000;
		long answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tempStr = br.readLine();
		StringTokenizer st = new StringTokenizer(tempStr, " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[] cage = new long[MAX+1];
		int inputMax = 0;
		for(int i= 0;i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int ice = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			cage[x] = ice;
			if(x > inputMax) {
				inputMax = x;//입력받은 좌표중 가장 큰값+K까지만 돌리기 위함 
			}
		}
		//입력완료
		long temp = 0;
		for(int i = 0;i<K*2+1;i++) {//0부터 K*2+1전까지의 합을 구한다
			answer = temp += cage[i];
		}
		if(inputMax-K<=0){
            inputMax = 0;
        }else if(inputMax+K>= MAX){
            inputMax = MAX-K;
        }else{
            inputMax -= K;
        }
		for(int i= K*2+1;i<=inputMax-K;i++) {
			temp += (cage[i]-cage[i-K*2-1]);//첫값과 다음값을 더해 temp에 저장
			if(temp>answer) {//큰값을 answer에 대입
				answer = temp;
			}
		}
		System.out.println(answer);
}catch (Exception e) {e.printStackTrace();}	
	}
}