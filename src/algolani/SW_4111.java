package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SW_4111{
	//무선 단속 카메라
	int answer = 0;
	int N, K;
	TreeSet<Integer> treeSet = new TreeSet<>(); 
	Integer[] arr; 
//	PriorityQueue<Node_4111> pq = new PriorityQueue<>();
	PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	
	public SW_4111() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			//초기화
			answer = 0;
			treeSet.clear();
			N = Integer.parseInt(br.readLine());
			K = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i= 0;i<N;i++) {
				treeSet.add(Integer.parseInt(st.nextToken()));//정렬과 중복제거를 동시에 하며 입력받음
			}
			arr = new Integer[treeSet.size()];
			if(K>=treeSet.size()) {//수신기가 무선카메라보다 많으면 각 자리마다 설치 할수있으므로 0 출력
				System.out.println("#" + t + " " + 0);
			}else {
				treeSet.toArray(arr);//arr 배열에 담아주고
				
				if(K == 1) {//사용할 수 있는 수신기 개수가 하나면 전체거리이므로 끝에서 처음뺀수 
					answer = arr[arr.length-1] - arr[0];
				}else {//수신기가 2개이상일 경우
					int count = 1;
					//디버깅용
//					for(int i= 0;i< arr.length;i++) {
//						System.out.printf(" %3d ", i);
//					}
//					System.out.println();
//					for(int i= 0;i< arr.length;i++) {
//						System.out.printf(" %3d ",arr[i]);
//					}
//					System.out.println();				
					//디버깅 끝
					
					for(int i= 0;i<arr.length-1;i++) {//다음 위치와의 거리를 우선순위큐에 삽입하여 역순으로 정렬한다
						pq.offer(arr[i+1]-arr[i]);
					}
					while(!pq.isEmpty()) {//큐가 빌때까지진행
						if(count == K) {//수신기를 다 쓰면 정지
							break;
						}
						pq.poll();//거리가 가장 먼 것부터 삭제
						count++;//수신기개수 증가
					}
					/*
					 * 1개는 설치돼있다고 가정
					 * 1
					 * 6
					 * 2
					 * 1 6 9 3 6 7 경우
					 * 1 3 6 7 9 순으로 treeset에 담긴다
					 * 각 위칙간의 거리는 
					 *  2 3 1 2이고
					 *  가장 큰 수인 3을 빼게되면
					 *  = 3과 6사이의 거리를 삭제하겠다는 뜻
					 *  = 양 쪽에수신기를 설치해서 둘 사이의 거리를 없애겠다는 뜻
					 *  이런식으로 내림차순으로 정렬된 우선순위큐에서 수신기의 개수 만큼빼면
					 *  넓은 거리를 수신기설치로 인해 둘 사이의 거리를 없애므로 남는 수를 다 더하면 총 거리가 나온다 
					 */
					while(!pq.isEmpty()) {//나머지 개수 전부 합하여 답으로 대입
						answer += pq.poll();
					}
				}
				
				System.out.println("#" + t + " " + answer);//출력
			}
		}
}catch (Exception e) {e.printStackTrace();}
	}
}