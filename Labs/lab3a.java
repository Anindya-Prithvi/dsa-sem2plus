import java.util.*;

class Main{

	static int[] last_3 = {1,1,1};
	//t1,t2,t3
	static int l;
	static int r;
	static int term;
	static int sum;
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-->0){
			int a = sc.nextInt();
			l=a;
			int b = sc.nextInt();
			r=b;
			term = 1;
			sum=0;
			funtion();
			System.out.println(sum);
			last_3[0]=1;
			last_3[1]=1;
			last_3[2]=1;
		}
	}

	static void funtion(){
		if ((term==1)||(term==2)||(term==3)){
			if ((term>=l)&&(term<=r)){
			sum+=last_3[2];
		}
			term++;
			funtion();
		}
		int temp;
		temp=last_3[0];
		last_3[0]=last_3[1];
		last_3[1]=last_3[2];
		last_3[2]=temp+last_3[0]+last_3[1];
//System.out.println("EXEC "+term+" "+l+ " "+r);
		if ((term>=l)&&(term<=r)){
			sum+=last_3[2];
		}
		if(term>r){return;}
//System.out.println(term);
		term++;
		funtion();
//System.out.println(term+" lolol "+last_3[2]);

	}
	
}