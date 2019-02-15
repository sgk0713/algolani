package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class B_1700 {
	//멀티탭 스케줄링 
	int N, K;
	HashSet<Integer> valueSet = new HashSet<>();//멀티탭에 있는 플러그를 빠르게 찾기 위해 사용
	int[] order, multiTab, number;
	int answer = 0;
	public B_1700() {
try {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		order = new int[K];//입력받는 순서
		number = new int[K+1];//각 숫자들의 남은 개수
		multiTab = new int[N];//멀티탭
		for(int i= 0;i<K;i++) {
			order[i] = Integer.parseInt(st.nextToken());//순서를 채우고
			number[order[i]]++;//순서의 개수를 파악한다
		}
		int count = 0, idx = 0;
		while(valueSet.size()!=N) {//멀티탭이 다 사용될때까지
			if(!valueSet.contains(order[count])) {//중복된 값이 아니면
				valueSet.add(order[count]);//멀티탭의 집합에 추가
				multiTab[idx] = order[count];//멀티탭 배열에 추가
				number[order[count]]--;//추가한 task숫자 감소
				idx++;
			}else{
                number[order[count]]--;
            }
			count++;
			if(count == K) {//모든 멀티탭을 사용했다면 종료
				break;
			}
		}
		for(int orderIdx = count; orderIdx<K; orderIdx++) {//다 넣은시점 다음부터 진행
			int tempValue = order[orderIdx];//들어갈 순서 변수에 넣음
			if(!valueSet.contains(tempValue)) {//멀티탭에 없는 숫자라면
				int max = -1;
				int switchIdx = -1;
				for(int multiIdx = 0; multiIdx<N; multiIdx++) {//멀티탭에 꽂혀있는 것들중에 더 이상 사용하지않을것이 있는지부터 확인
					if(number[multiTab[multiIdx]] == 0) {//있다면 뽑을 인덱스 갱신
						switchIdx = multiIdx;
						break;
					}
				}
				if(switchIdx == -1) {//뽑을 인덱스가 없다면
					for(int multiIdx = 0; multiIdx<N; multiIdx++) {//꼽혀있는 플러그 중 가장 나중에 쓰일것을 찾는다
						for(int k = orderIdx+1;k<K;k++) {//꼽을 순서의 다음 숫자부터 확인
							if(multiTab[multiIdx] == order[k]) {//현재 꼽혀있는 플러그와 같은 플러그를 순서쌍에서 찾았을때
								if(k>max) {//몇번째 순서가 가장 멀리있는 순서인지 갱신
									max = k;
									switchIdx = multiIdx;
								}
								break;
							}
						}
					}
				}
				valueSet.remove(multiTab[switchIdx]);//멀티탭에 꼽힌 플러그들의 집합에서 지우고
				valueSet.add(tempValue);//새로운 플러그를 추가
				multiTab[switchIdx] = tempValue;//멀티탭에 새플러그를 꼽고
				number[tempValue]--;//미래에 있을 플러그의 개수 감소
				answer++;//뽑은 횟수 증가
			}else {//멀티탭에 있는 숫자면 감소만 해줌
				number[tempValue]--;
			}
		}
		System.out.println(answer);
		
}catch (Exception e) {e.printStackTrace();}	
	}
}