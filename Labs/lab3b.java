import java.util.*;

class Main{

	static int sum;
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n_disks = sc.nextInt();
		sum=0;
		toh(n_disks);
		System.out.println(sum);

	}

	static void toh(int n){
		for(int i=0;i<n;i++){sum++;}
		if (n==0){return;}
		if (n==1){return;}
		else{
			toh(n-1);
			toh(n-1);
		}
	}
}