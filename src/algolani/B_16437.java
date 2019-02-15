package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16437 {
	//양 구출 작전
	int N;
	long[] numbersIn;//index번째 섬에 있는 마리수
	ArrayList<Queue<Integer>> list;//리스트안에 큐
	public B_16437() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numbersIn = new long[N+1];
		list = new ArrayList<>();
		for(int i = 0;i<=N;i++) {
			list.add(new LinkedList<Integer>());//각 리스트에 큐생성하여 추가(초기화)
		}
		//리스트에 0번은 안쓸 예정. 루트는 1번
		String tempStr = null;
		StringTokenizer st = null;
		for(int i = 2;i<=N;i++) {
			tempStr = br.readLine();
			st = new StringTokenizer(tempStr, " ");
			st.nextToken();
			char tempChar = tempStr.charAt(0);//양인지 늑대인지
			long num = Integer.parseInt(st.nextToken());//마리수
			int parent = Integer.parseInt(st.nextToken());//부모 노드
			if(tempChar == 'S') {//양이면
				numbersIn[i] = num;//양수로 저장
			}else {
				numbersIn[i] = -num;//음수로 저장
			}
			list.get(parent).offer(i);//각 부모노드의 자식들을 큐에 저장
		}
		//입력완료
		
//		System.out.println(q.toString());
		System.out.println(dfs(1));//루트부터 
}catch (Exception e) {e.printStackTrace();}	
	}
	long dfs(int nodeNum){
		Queue<Integer> tempQ = list.get(nodeNum);//입력받은 노드번호의 자식들이 담긴 큐를 가져옴
		long returnValue = numbersIn[nodeNum];//현재노드의 값 저장 루트는 0
		while(!tempQ.isEmpty()) {//자식들을 다 볼때까지
			int node = tempQ.poll();//자식노드 번호 저장
			long numbers = dfs(node);//자식노드에 담긴 마리수를 numbers 변수에 저장
			if(numbers < 0) {//음수면 다음 노드
				continue;
			}
			returnValue += numbers;//양수면 반환값에 추가
		}
		return returnValue>0?returnValue:0;//음수일때만 0 리턴
	}
}