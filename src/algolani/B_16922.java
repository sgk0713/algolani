package algolani;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class B_16922 {
	public B_16922() {
try {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		Integer[] list = {1,5,10,50};
		set.add(1);set.add(5);set.add(10);set.add(50);
		for(int k = 2;k<=N;k++ ) {
			set.clear();
			for(int i = 0;i<list.length;i++) {
				int num = list[i];
				if(!set.contains(num+1))
					set.add(num+1);
				if(!set.contains(num+5))
					set.add(num+5);
				if(!set.contains(num+10))
					set.add(num+10);
				if(!set.contains(num+50))
					set.add(num+50);
				
			}
			if(k != N) {
				list = new Integer[set.size()];
				set.toArray(list);
			}
		}
		System.out.println(set.size());
}catch (Exception e) {}
	}
}
