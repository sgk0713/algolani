package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

import algolani.B_2210.Pair_2210;

public class B_2210 {
		//숫자판 점프, 시작 19:03,끝 19:33
		int[][] map = new int[5][5];
		Set<String> set = new HashSet<>();
		Queue<Pair_2210> q = new LinkedList<>();
		final int MAXMOVE = 6;//이동해야할 횟수
		int[] dirR = {0, 1, 0, -1};//동향부터 시작, 시계방향
		int[] dirC = {1, 0, -1, 0};
		public B_2210() {try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			for(int i =0;i<5;i++) {
				String tempStr = br.readLine();
				StringTokenizer st = new StringTokenizer(tempStr , " ");
				for(int j =0;j< 5;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}//입력 완료
			
			for(int i =0;i<5;i++) {
				for(int j =0;j< 5;j++) {//각 위치마다 탐색
					q.clear();
					q.offer(new Pair_2210(i, j, map[i][j]+""));//좌표와 값을 넣은 객체를 큐에 삽입 
					int moveCount = 0;//이동횟수 초기화
					while(!q.isEmpty()) {
						int qSize = q.size();
						moveCount++;//이동횟수 증가
						if(moveCount == MAXMOVE)//이동횟수까지 도달하면 while문 탈출
							break;
						for(int k = 0; k < qSize; k++) {//동시다발적으로 위치계산
							Pair_2210 tp = q.poll();
							for(int l=0;l<4;l++) {//좌표 계산 후 큐에 삽입
								int rr = tp.r + dirR[l];
								int cc = tp.c + dirC[l];
								String str = tp.number;
								if(rr>=0 && rr<5 && cc >= 0 && cc <5) {
									q.offer(new Pair_2210(rr, cc, str+map[rr][cc]));
								}
							}
						}
					}
					while(!q.isEmpty()) {//set함수에 넣어 중복 제거
						set.add(q.poll().number);
					}
				}
			}
			System.out.println(set.size());//답안 출력
		}catch (Exception e) {
		}
		}
		class Pair_2210{
			int r, c;
			String number;
			public Pair_2210(int r, int c, String number) {
				this.r = r;
				this.c = c;
				this.number = number;
			}
		}
	}
