package algolani;

import java.util.Arrays;

public class programmers_2 {
	public programmers_2() {
		
		
	}
	boolean num_1(int[] arrA, int[] arrB) {
        boolean answer = false;
		int c = -1;
		int sizeA = arrA.length;
		int sizeB = arrB.length;
		if(sizeA!= sizeB) {
			return false;
		}
		while(++c < sizeB) {
			if(arrA[c] == arrB[0]) {
				for(int i = 0;i<sizeB;i++) {
					if(arrB[i] != arrA[(c+i)%(sizeB)]) {
						answer = false;
						break;
					}else {
						answer = true;
					}
				}
				if(answer) {
					return true;
				}
			}
		}
		return answer;
    }
	
	int num_2(int l, int[] v) {
		Arrays.sort(v);
		int size = v.length;
		int answer = 0;
		if(v[0]-0 > answer) {
			answer = v[0]-0;
		}
		if(l-v[size-1] > answer) {
			answer = l-v[size-1];
		}
		for(int i= 0;i<size-1;i++) {
			int temp = ((v[i+1]+1) - v[i]) / 2;
			if(temp>answer) {
				answer = temp;
			}
		}
		return answer;
		
	}
	
	int num_3(int[][] board, int[] nums) {
		int answer = 0;
        int N = board.length;
        int numSize = nums.length;
        boolean[] isChecked = new boolean[N*N+1];
        for(int i= 0;i<numSize;i++) {
        	isChecked[nums[i]] = true;
        }
        
        boolean cross1 = true;
        boolean cross2 = true;
        for(int i= 0;i<N;i++){
        	boolean flagR = true;
            boolean flagC = true;
            
    		if(isChecked[board[i][i]] == false) {
    			cross1 = false;
    			flagR = false;
    		}
    		if(isChecked[board[N-1-i][i]] == false) {
    			cross2 = false;
    			flagC = false;
    		}

        	if(flagR || flagC) {
        		for(int j = 0;j < N; j++) {
        			if(flagR && isChecked[board[i][j]] == false) {
        				flagR = false;
        			}else if(flagR && j == N-1) {
        				answer++;
        			}
        			if(flagC && isChecked[board[j][i]] == false) {
        				flagC = false;
        			}else if(flagC && j == N-1) {
        				answer++;
        			}
        		}
        	}
        }
        if(cross1)
        	answer++;
        if(cross2)
        	answer++;
        return answer;
	}
	
}
