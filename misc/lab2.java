import java.util.*;

// input format,
// 3 2 1 ;  gm req
// 6 4 1 ;  gm at home
// 1 2 3 ;  price per gm
// 4     ;  money with her

class Main{
	public static void main(String[] args){
		//inits
		Scanner sc = new Scanner(System.in);
		int create = 4;
		long fin_ans=0;
		int[] gm_req = new int[3];
		int[] gm_home = new int[3];
		int[] gm_price = new int[3];
		long money;

		//scans
		for(int i=0; i<3; i++){
			gm_req[i]=sc.nextInt();
		}
		for(int i=0; i<3; i++){
			gm_home[i]=sc.nextInt();
		}
		for(int i=0; i<3; i++){
			gm_price[i]=sc.nextInt();
		}
		money = sc.nextLong();

		//process 1
		int temp_min = 2147483647;
		for(int i=0; i<3;i++){
			if (gm_req[i]>0){
			temp_min = Math.min(temp_min,gm_home[i]/gm_req[i]);
		}
	}
		fin_ans = temp_min;


		//update left at home
		for(int i=0; i<3;i++){
			gm_home[i]= gm_home[i]-gm_req[i]*temp_min;
		}

		// with home supply + buying
		long temp_min_req_cost=0;
		for(int i=0; i<3;i++){
			if (gm_req[i]>=gm_home[i]){
			temp_min_req_cost=temp_min_req_cost+((gm_req[i]-gm_home[i])*gm_price[i]);
			gm_home[i]=0;

		}
		}

		//check if we exceed budget
		if (temp_min_req_cost<money && temp_min_req_cost>0){
			money-=temp_min_req_cost;
			fin_ans++;
		}


		temp_min_req_cost=0;
		for(int i=0; i<3;i++){

			if (gm_req[i]>=gm_home[i]){
			temp_min_req_cost=temp_min_req_cost+(gm_req[i]-gm_home[i])*gm_price[i];
			gm_home[i]=0;

		}
		}

		//check if we exceed budget
		if (temp_min_req_cost<money && temp_min_req_cost>0){
			money-=temp_min_req_cost;
			fin_ans++;
		}

		temp_min_req_cost=0;
		for(int i=0; i<3;i++){
			if (gm_req[i]>=gm_home[i]){
			temp_min_req_cost=temp_min_req_cost+(gm_req[i]-gm_home[i])*gm_price[i];
			gm_home[i]=0;
		}
		}

		//check if we exceed budget
		if (temp_min_req_cost<money && temp_min_req_cost>0){
			money-=temp_min_req_cost;
			fin_ans++;
		}

		long req_per_pot = 0;

		for(int i=0; i<3;i++){
			req_per_pot=req_per_pot+gm_req[i]*gm_price[i];
		}

		//final iff we still have budget
		
		fin_ans = fin_ans+ money/req_per_pot;
		System.out.println(fin_ans);
	}
}