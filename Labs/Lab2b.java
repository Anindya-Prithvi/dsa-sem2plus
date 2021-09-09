import java.util.*;

class Main{
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.print(treasure(n,"",-1)?"":-1);
	}
	static boolean treasure(int n, String s, int m){
		//initial
		boolean a = false;
		for (int i=m+1;i<10;i++){
			int temp = (int) Math.pow(7,i);
			if (temp>n){
				return false;
			}
			else if (temp==n){
				System.out.println(s+i);
				return true;
			}
			a = a||treasure(n-temp,s+i,i);
			if (a){return true;}

		}
		return a;
	}
}