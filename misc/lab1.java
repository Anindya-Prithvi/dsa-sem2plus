import java.util.*;

class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt(); //may not be found
		boolean flag = true;
		for(int i = 0; i<n; i++){
			int temp = sc.nextInt();
			if (temp==x){
				System.out.println(i+1);
				flag=false;
				break;
			}
		}
		if (flag){
			System.out.println(-1);
		}
	}
}