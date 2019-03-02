package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_BaaaaaaaaaaarkingDogA {
	int N, answer;
	boolean isBroken[];
	int[] eggsWeight;
	int[] eggsDurability;
	public B_BaaaaaaaaaaarkingDogA() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isBroken = new boolean[N+1];
		StringTokenizer st = null;
		eggsWeight = new int[N+1];
		eggsDurability = new int[N+1];
		for(int i= 1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			eggsDurability[i] = Integer.parseInt(st.nextToken());
			eggsWeight[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(dfs(1,0));
}catch (Exception e) {}
	}
	
	int dfs(int currIdx, int brokeNumber) {
		int answer = brokeNumber;
		if(brokeNumber == N) {
			return brokeNumber;
		}
		
		for(int i= 1;i<=N;i++) {
			if(i != currIdx && isBroken[i] == false) {
				int tempDurability = eggsDurability[currIdx];
				int otherDurability = eggsDurability[i];
				int tempBrokeNum = brokeNumber;
				boolean brokeOne = false, brokeTwo = false;
				eggsDurability[i] -= eggsWeight[currIdx];
				eggsDurability[currIdx] -= eggsWeight[i];
				
				if(isBroken[i] == false && eggsDurability[i] <= 0) {
					isBroken[i] = true;
					tempBrokeNum++;
					brokeOne = true;
				}
				if(isBroken[currIdx] == false && eggsDurability[currIdx] <= 0) {
					isBroken[currIdx] = true;
					tempBrokeNum++;
					brokeTwo = true;
				}
				
				int tempNextIdx = currIdx+1;
				while(tempNextIdx<=N && isBroken[tempNextIdx] == true) {
					tempNextIdx++;
				}
				if(tempNextIdx <= N) {
					answer = Math.max(answer, dfs(tempNextIdx, tempBrokeNum));
				}else {
					answer = Math.max(answer, tempBrokeNum);
				}
				
				if(brokeOne == true) {
					isBroken[i] = false;
				}
				if(brokeTwo == true) {
					isBroken[currIdx] = false;
				}
				eggsDurability[currIdx] = tempDurability;
				eggsDurability[i] = otherDurability;
			}
		}
		return answer;
	}
}
