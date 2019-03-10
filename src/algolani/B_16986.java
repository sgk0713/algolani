package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_16986 {//인싸들의 가위바위보
	//순번과 순서는 다르다.
	//게임순서상 뒤라는 뜻은 맨처음 진행하기로한 지우, 경희, 민호 의 순서를 뜻하며  (순번이라 칭하겠음)
	//이전 경기의 승자와 이전경끼에 참여하지않은 사람과 경기를 하더라도 "순번"은 변하지않는다.
	//즉, 순서상의 뒤라는 것은 순번상 큰 숫자로 비교해야함
	//지우는 0, 경희는 1, 민호는 2로 입력해서 대소비교로 순번 비교 가능하게 구현함
	final int Jiwoo = 0;
	final int Kyung = 1;
	final int Min   = 2;
	int N, K, answer;
	int turns[] = new int[3];
	int rule[][];
	int[] JisOrder;
	int[][] order = new int[3][20];
	Person_16986[] players = new Person_16986[3];
	public B_16986() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		rule = new int[N+1][N+1];
		JisOrder = new int[N];
		for(int i = 0;i<N;i++) {
			JisOrder[i] = i+1;//지우의 손동작 순서를 1~N까지로 초기화 해준다
		}
		for(int i= 1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1;j<=N;j++) {
				//가위바위보 상성 입력
				rule[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 1;i<3;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<20;j++) {
				//1번인덱스와 2번 인덱스에 경희와 민호의 손동작을 입려해준다
				order[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		permutation(0);//게임 진행
		System.out.println(answer);
}catch (Exception e) {}
	}
	void permutation(int pivot) {//순열 구하기
		if(answer == 1) {//이길 방법이 있으면 더이상 진행안함
			return;
		}
		if(pivot == N) {
			copyOrder();//구한 순열을 가위바위보 순서에 카피
			init();//게임진행을 위한 초기화
			playGame();//게임 진행
		}
		for(int i= pivot;i<N;i++) {
			swap(i, pivot);
			permutation(pivot+1);
			swap(i, pivot);
		}
	}
	void playGame() {
		while(true) {
			int play1Hand = order[players[0].name][turns[players[0].name]];//1번 선수가 낼 손동작
			int play2Hand = order[players[1].name][turns[players[1].name]];//2번 선수가 낼 손동작
			int result = rule[play1Hand][play2Hand];//결과값
			turns[players[0].name]++;//두 선수 턴 증가
			turns[players[1].name]++;
			//횟수초과했을 경우 더이상 진행안함
			if(turns[0] > N || turns[1] > 20 || turns[2] > 20) {
				return;
			}
			//이겼거나 비겼는데 상대보다 큰 순번일 경우
			if(result ==2 || (result == 1 && players[0].name > players[1].name)) {
				players[0].win++;//승수 증가
				if(players[0].win == K) {//승수 비교
					if(players[0].name == Jiwoo) {//지우가 이겼는지 확인
						answer = 1;
					}
					return;
				}
				Person_16986 tempP = players[1];//순서 재 배치
				players[1] = players[2];
				players[2] = tempP;
			}else {//뒷사람이 이길 경우, 비겼는데 뒷사람순번이 클경우
				players[1].win++;//승수 증가
				if(players[1].win == K) {//승수 비교
					if(players[1].name == Jiwoo) {//지우가 이겼는지 확인
						answer = 1;
					}
					return;
				}
				Person_16986 tempP = players[0];//순서 재 배치
				players[0] = players[1];
				players[1] = players[2];
				players[2] = tempP;
			}
		}
	}
	void copyOrder() {
		for(int i= 0;i<N;i++) {
			order[0][i] = JisOrder[i];
		}
	}
    void init() {
		turns[0] = 0;
		turns[1] = 0;
		turns[2] = 0;
		players[0] = new Person_16986(Jiwoo, 0);
		players[1] = new Person_16986(Kyung, 0);
		players[2] = new Person_16986(Min, 0);
	}
    void swap(int a, int b) {
		int temp = JisOrder[a]; 
		JisOrder[a] = JisOrder[b]; 
		JisOrder[b] = temp;
	}
	class Person_16986{
		int name, win;
		public Person_16986(int name, int win) {
			this.name = name;
			this.win = win;
		}
	}
//	void showOrder() {
//		for(int i= 0;i<N;i++) {
//			System.out.print(order[0][i]+ " ");
//		}
//		System.out.println();
//		System.out.println(turns[0] + " " + turns[1] + " " + turns[2]);
//		System.out.println(players[0].name + " " + players[1].name + " " + players[2].name);
//		System.out.println();
//	}
}
