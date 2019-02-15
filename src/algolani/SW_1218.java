package algolani;

import java.util.Scanner;

public class SW_1218 {
	int t;
	int c1, c2, c3, c4;
	String str = null;
	
	public SW_1218() {
		Scanner sc = new Scanner(System.in);		
		String temp = null;
		for(int i = 1; i<= 10; i++) {
			c1=c2=c3=c4=0;
			str = null;
			t = sc.nextInt();
			sc.nextLine();
			str = sc.nextLine();
			for(int j = 0 ; j< str.length();j++) {
				temp = str.substring(j, j+1);
				if(temp.equals("(")) {
					c1++;
				}else if(temp.equals(")")) {
					c1--;
				}else if(temp.equals("[")) {
					c2++;
				}else if(temp.equals("]")) {
					c2--;
				}else if(temp.equals("{")) {
					c3++;
				}else if(temp.equals("}")) {
					c3--;
				}else if(temp.equals("<")) {
					c4++;
				}else if(temp.equals(">")) {
					c4--;
				}
			}
			if(c1 == 0 && c2 == 0 && c3 == 0 && c4 == 0) {
				System.out.println("#"+i + " 1");
			}else {
				System.out.println("#"+i + " 0");
			}
			
			
		}
	}

}
