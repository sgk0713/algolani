package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class B_11967 {
	//불 켜기
	//1차원 배열을 2차원처럼 사용한다. map배열, 0번인덱스는 사용하지않는다. 방향 : [동, 남, 서, 북] = [+1, +N, -1, -N]
	//N=3일경우,		 N = 4일 경우
	//1 2 3			 1  2  3  4 
	//4 5 6			 5  6  7  8	
	//7 8 9			 9 10 11 12
	//				13 14 15 16
	//HashSet인 waitingRooms(이하 대기방)에 '방불이 켜질때' 갈수있는 인접한 방들의 번호를 넣어둔다
	//예를들어 N=3일 경우, 1번방은 처음에 켜져있으니 대기방에는 1번방+1 인 '2번방'과 1+N인 '4번방'이 대기방에 들억간다.
	//
	//@ q => 불을 켤 수 있는 모든 방이 담긴 Deque
	//1. q 에 담긴 방들의 불을 모두 켠 후,
	//2. waitingRooms(대기방)에  있는 방들이 켜져있는지 확인한다.
	//3. 켜져있으면 q의 맨앞에 담고, 대기방에서 지우고, 그 방 주변을 대기방에 넣어둔다.
	//4. 대기방들중에 하나라도 켜져있다면 2번~3번 과정을 반복한다.
	//5. 1번~4번 과정을 반복한다.
	//6. q가 비면 (갈수있는방이 없으면) 종료하고 답을 출력한다
	int N,M, maxIndex, answer = 1;
	int[] dir = new int[4];
	boolean[] map;
	boolean[] isChecked;
	Set<Integer> waitingRooms = new HashSet<>();
	ArrayList<Queue<Integer>> list = new ArrayList<>();
	Deque<Integer> q = new LinkedList<>();
	public B_11967() {
try {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maxIndex = N*N;//N=4일때, maxindex는 16
		map = new boolean[maxIndex+1];//불이 켜져있는지 확인하는 배열, 1~16까지 사용하므로 +1하여생성
		isChecked = new boolean[maxIndex+1];//스위치를 켠적있는방인지 확인하기 위한 배열
		map[1] = true;//1번방은 항상 켜져있다.
		isChecked[1] = true;//1번방부터 스위치를 키므로 초기화
		dir[0] = 1; dir[1] = N; dir[2] = -1; dir[3] = -N;//동 남 서 북 순으로 값입력
		for(int i= 0;i<= maxIndex;i++) {//arraylist에 큐 생성
			list.add(new LinkedList<Integer>());
		}
		for(int i =1;i<=M;i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int rr= Integer.parseInt(st.nextToken());
			int cc= Integer.parseInt(st.nextToken());
			list.get((r-1)*N + c).offer((rr-1)*N + cc);//1 3이면 3번방이다. 각방에서 켤수있는 방번호를 큐에 담는다
		}
		q.offer(1);//켤수있는 방을 담는 deque
		waitingRooms.add(1+N);//대기방에 1번방의 근접한 방을 넣는다. 북
		waitingRooms.add(2);//동
		
		while(!q.isEmpty()){//켤수있는방이 없을때까지 반복
			int size = q.size();//켤수있는방은 모두 켠다
			boolean anyNew = false;//방을 하나라도 켜거나, 새로운 방을 찾을때를 위한 변수
			for(int k = 0;k< size;k++) {
				int cur = q.poll();
				Queue<Integer> tempQ = list.get(cur);//해당되는 방번호에 담긴 큐를 가져온다.
				while(!tempQ.isEmpty()) {//모든 스위치를 켠다.
					anyNew = true;
					int tempRoomNum = tempQ.poll();//켤 방번호를 가져와서
					if(map[tempRoomNum] == false) {//꺼져있는 방이면
						map[tempRoomNum] = true;//불을 켜고
						answer++;				//답을 1증가		
					}
				}
			}
			while(anyNew) {//대기방 갱신이 없을때까지 반복
				anyNew = false;//초기화
				List<Object> templist = Arrays.asList(waitingRooms.toArray());//대기방들을 가져온다
				
				for(int i = 0; i < templist.size(); i++) {//대기방수만큼 확인한다
					
					int tempRoomNum = (int) templist.get(i);//방번호를 가져온다.
					
					if(map[tempRoomNum] == true//불이켜져있고
					&& isChecked[tempRoomNum] == false) {//스위치를 켠방이 아니라면
						anyNew = true;//대기방 갱신이 이루어진다.
						isChecked[tempRoomNum] = true;//스위치를 켤 방으로 저장
						waitingRooms.remove(tempRoomNum);//대기방에서 지운다
						q.offerFirst(tempRoomNum);//불을 켤수있는 방목록에 담는다
						
						for(int j = 0;j<4;j++) {//주변방을 대기방으로 넣는다
							int tempIndex = tempRoomNum + dir[j];
							if((j == 0 || j == 2)//동이나 서 방향일때
							&& (tempIndex >= (tempRoomNum - ((tempRoomNum-1)%N)))//1차원배열이므로 N만큼의 범위안에 있어야한다
							&& (tempIndex < (tempRoomNum - ((tempRoomNum-1)%N)+N))
							&& (tempIndex <= maxIndex)//최대 방번호를 넘지않고
							&& (isChecked[tempIndex] == false)){//켜보지않은 방이라면
								
								waitingRooms.add(tempIndex);//대기방에 저장한다
								
							}else if((j == 1 || j == 3)//남 북일 경우 +N, -N이므로
							&& (tempIndex > 0)//방번호 범위안에만 있으면 된다
							&& (tempIndex <= maxIndex)
							&& (isChecked[tempIndex] == false)){//켜보지않은 방이라면
								
								waitingRooms.add(tempIndex);//대기방에 저장한다
								
							}
						}
					}//if문 끝
				}//대기방 수 만큼 확인하는 for문 끝
			}//대기방 갱신 while문 끝 
		}//켤수있는방 while문 끝
		System.out.println(answer);//답 출력
}catch (Exception e) {e.printStackTrace();}
	}
}
