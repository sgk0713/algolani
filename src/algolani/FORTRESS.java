package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FORTRESS {//요새, 종만북
	int N, highest, longest;//루트에서 최대깊이, 잎과 잎사이 최장거리 
	int[] x = new int[100];
	int[] y = new int[100];
	int[] r = new int[100];
	PriorityQueue<Circle> pq = new PriorityQueue<>();//우선순위큐. 반지름이 내림차순으로 꺼낸다
	LinkedList<Integer>[] tree = new LinkedList[100];//linkedlist를 담는 배열
	public FORTRESS() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());//테스트 케이스 입력
		while(T-- > 0) {//테스트케이스만큼 실행
			highest = longest = 0;
			Arrays.fill(x, -1);
			Arrays.fill(y, -1);
			Arrays.fill(r, -1);
			for(int i= 0;i<100;i++) {
				tree[i] = new LinkedList<>();  
			}
			//초기화 완료
			
			N = Integer.parseInt(br.readLine());
			for(int i = 0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				x[i] = Integer.parseInt(st.nextToken());
				y[i] = Integer.parseInt(st.nextToken());
				r[i] = Integer.parseInt(st.nextToken());
				pq.offer(new Circle(x[i], y[i], r[i], i));//x, y좌표와 반지름 r, idx를 입력받아 pq에 삽입
			}
			//입력 완료
			
			pq.poll();//첫번 째 가장 큰 원은 루트이므로 제외
			while(!pq.isEmpty()) {//큐가 빌때까지 진행
				Circle temp = pq.poll();
				int idx = temp.idx;
				int parent = getParent(0, idx, 1);
				tree[parent].add(idx);
			}
			getLeafDepth(0);//잎과 잎사이 최장거리 계산 
			
			System.out.println(Math.max(highest, longest));//답안 출력
		}
}catch (Exception e) {}
	}
	int getLeafDepth(int parent) {//잎과 잎사이 거리 계산 dfs
		int size = tree[parent].size();
		if(size == 0) {
			return 1;
		}
		int[] arr = new int[size];
		for(int i= 0;i<size;i++) {
			arr[i] = getLeafDepth(tree[parent].get(i));
		}
		Arrays.sort(arr);//오름차순으로 정렬
		if(size < 2) {//잎과 잎사이의 최장 거리 갱신
			longest = Math.max(longest, arr[size-1]);
		}else {
			longest = Math.max(longest, arr[size-1] + arr[size-2]);
		}
		return arr[size-1]+1;//현재 위치에서 가장 긴 경로 반환
	}
	
	
	int getParent(int parent, int child, int height) {//child가 속할 부모노드 구하는 메소드
		highest = Math.max(highest, height);//루트에서부터의 최장거리 갱신
		for(int i=0;i<tree[parent].size();i++) {
			int tempParent = tree[parent].get(i);
			if(isInBoundery(tempParent, child)) {
				return getParent(tempParent, child, height+1);
			}
		}
		return parent;
	}
	boolean isInBoundery(int a, int b) {//원안에 포함되는 좌표인지 확인 메소드
		if(Math.pow(x[a]-x[b], 2) + Math.pow(y[a]-y[b], 2) < Math.pow(r[a], 2)) {
			return true;
		}return false;
	}
	class Circle implements Comparable<Circle>{
		int x, y, r, idx;
		public Circle(int x, int y, int r, int idx) {
			this.x = x;
			this.y = y;
			this.r = r;
			this.idx = idx;
		}
		@Override
		public int compareTo(Circle o) {
			return o.r - this.r;
		}
	}
}
