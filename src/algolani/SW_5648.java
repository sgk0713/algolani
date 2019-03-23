package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_5648 {
	int answer, N;
	int[][] map = new int[2001][2001];
	int[][] dirInfo = new int[2001][2001];
	Queue<Atom_5648> q = new LinkedList<>();
	int[] dirR = {-1, 1, 0, 0};
	int[] dirC = {0, 0, -1, 1};
	public SW_5648() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			answer = 0;
			N = Integer.parseInt(br.readLine());
			for(int i= 0;i<2001;i++) {
				Arrays.fill(dirInfo[i], 0);
				Arrays.fill(map[i], -1);
			}
			for(int i= 0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken())+1000;
				int r = Math.abs(Integer.parseInt(st.nextToken())-1000);
				int dir = Integer.parseInt(st.nextToken());
				int energy = Integer.parseInt(st.nextToken());
				dirInfo[r][c] |= 1<<dir;
				map[r][c] = 0;
				q.offer(new Atom_5648(r, c, dir, energy));
			}
			int count = 0;
			while(!q.isEmpty()) {
				int qSize = q.size();
				count++;
				if(count > 2000) {
					q.clear();
					break;
				}
				for(int i = 0;i<qSize;i++) {
					Atom_5648 temp = q.poll();
					int r = temp.r;
					int c = temp.c;
					int dir = temp.dir;
					int oppositeDir = dir;
					if(oppositeDir == 0){
						oppositeDir = 1;
					}else if(oppositeDir == 1) {
						oppositeDir = 0;
					}else if(oppositeDir == 2) {
						oppositeDir = 3;
					}else if(oppositeDir == 3) {
						oppositeDir = 2;
					}
					if((dirInfo[r][c] & 1<<4) != 0) {
						answer += temp.energy;
						map[r][c] = -1;
						dirInfo[r][c] ^= 1<<4;
						continue;
					}else if((dirInfo[r][c] & 1<<5) != 0) {
						answer += temp.energy;
						if(map[r][c]!=count) {
							map[r][c] = -1;
							dirInfo[r][c] ^= 1<<dir;
							continue;
						}
						dirInfo[r][c] ^= 1<<5;
					}
					int rr = r + dirR[dir];
					int cc = c + dirC[dir];
					
					if(isInBoundry(rr, cc)) {
						if(map[rr][cc]>=count-1) {
							//동시에 만날 경우
							if(map[rr][cc]==count) {
								dirInfo[rr][cc] |= 1<<4;
								if(map[r][c]<count) {
									map[r][c] = -1;
									dirInfo[r][c] ^= 1<<dir;
								}
								answer += temp.energy;
							//0.5초에 만날 경우
							}else if(map[rr][cc] == count-1 && (dirInfo[rr][cc]&1<<oppositeDir) != 0) {
								if(map[r][c]<count) {
									map[r][c] = -1;
									dirInfo[r][c] ^= 1<<dir;
								}
								dirInfo[rr][cc] |= 1<<5;
								answer += temp.energy;
							}
						}else {
							map[rr][cc] = count;
							dirInfo[rr][cc] |= 1<<dir;
							if(map[r][c]!=count) {
								dirInfo[r][c] ^= 1<<dir;
							}
							q.offer(new Atom_5648(rr, cc, dir, temp.energy));
						}
					}else {
						map[r][c] = count;
						q.offer(temp);
					}
				}
			}
			System.out.println("#" + t + " " + answer);
		}
}catch (Exception e) {e.printStackTrace();}
	}
	class Atom_5648{
		int r, c, dir, energy;
		public Atom_5648(int r, int c, int dir, int energy) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.energy = energy;
		}
	}
	boolean isInBoundry(int r, int c) {
		if(r >= 0 && r< 2001 && c>=0 && c<2001) {
			return true;
		}return false;
	}
}
