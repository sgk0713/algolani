package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_10655 {	
	int x1, y1, x2, y2, x3, y3;
	int totalDistance;
	int distanceOne, distanceTwo;
	int threePointDist;//3개 좌표들간의 거리
	int twoPointDist;//중간값을 뺀 좌표사이의 거리
	int maxProfit;//취할수있는 거리 이득량
	public B_10655() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		x1 = Integer.parseInt(st.nextToken());
		y1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		x2 = Integer.parseInt(st.nextToken());
		y2 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		x3 = Integer.parseInt(st.nextToken());
		y3 = Integer.parseInt(st.nextToken());
		//3번째 좌표까지 입력 받기
		distanceOne = getDistance(x1, y1, x2, y2);//1와 2 사이 거리
		distanceTwo = getDistance(x2, y2, x3, y3);//2와 3 사이 거리
		
		totalDistance = threePointDist = distanceOne + distanceTwo;//둘의 합을 총거리에 더하기, 
		twoPointDist = getDistance(x1, y1, x3, y3);//일단 2번 좌표가 사라져야된다고 초기화 해둠
		maxProfit = threePointDist - twoPointDist;//취할 수 있는 거리 이득을 초기값으로 잡음
		for(int i= 3;i<N;i++) {
			x1 = x2;
			y1 = y2;
			x2 = x3;
			y2 = y3;//한칸씩 땡겨줌
			st = new StringTokenizer(br.readLine());
			x3 = Integer.parseInt(st.nextToken());//새로운 좌표 받기
			y3 = Integer.parseInt(st.nextToken());
			distanceOne = getDistance(x1, y1, x2, y2);
			distanceTwo = getDistance(x2, y2, x3, y3);
			totalDistance += distanceTwo;//총 거리에 더해줌
			
			threePointDist = distanceOne + distanceTwo;//1,2,3 각 사이들의 거리
			twoPointDist = getDistance(x1, y1, x3, y3);//1과 3사이의 가리, 2가 없을 경우
			
			if(threePointDist-twoPointDist > maxProfit) {//취할수 있는 거리 이득이 이전에 계산했던 거리 이득보다 크면
				maxProfit = threePointDist-twoPointDist;//갱신
			}
		}
		System.out.println(totalDistance-maxProfit);//이득량만큼 빼주고 답 제출 
}catch (Exception e) {}
	}
	int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
}
