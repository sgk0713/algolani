package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_16434 {//드래곤 앤 던전
	final int DRAGON_ROOM = 1;
	final int POTION_ROOM = 2;
	
	public B_16434() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Long curAttck = new Long(st.nextToken());//초기화
		Long maxHP = Long.valueOf(1);//1로 초기화
		Long curHP = maxHP;//1로 초기화
		int type = 0, dmg = 0, hp = 0;//변수 선언
		for(int i = 0;i < N;i++) {
			st = new StringTokenizer(br.readLine());
			type = Integer.parseInt(st.nextToken());
			dmg = Integer.parseInt(st.nextToken());
			hp = Integer.parseInt(st.nextToken());
			if(type == DRAGON_ROOM) {//드래곤 방일 경우
				curHP -= (long) (( Math.ceil((double)hp/curAttck) - 1 ) * dmg);//드래곤을 죽일때까지 맞으면서 나의 체력을 깎는다..
				if(curHP<=0) {//0보다 작거나 같은 경우..더 많은 체력이 필요하다는 뜻이므로
					maxHP+= Math.abs(curHP)+1;//필요한만큼+1을 더 해서 다 맞아도 최소1을 될수있게 최대체력을 설정해준다
					curHP = Long.valueOf(1);//다시 현재체력은 1로 설정한다
				}
			}else if(type == POTION_ROOM){//포션 방일 경우
				if(maxHP-curHP <= hp) {//최대체력보다 많은체력을 먹을 경우
					curHP = maxHP;//최대체력이 현재 체력이 된다
				}else {
					curHP += hp;// 그외엔 그냥 마신다
				}
				curAttck+=dmg;//공격력 증가!
			}
		}
		System.out.println(maxHP);//맞으면서 갱신해왔던 최대체력을 출력
}catch (Exception e) {e.printStackTrace();}
	}
}
