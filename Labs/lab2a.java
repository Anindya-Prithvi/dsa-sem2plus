import java.util.*;

class Main{
	static int[]varr;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int init = sc.nextInt();
		int n = sc.nextInt();
		sc.nextLine();
		int[] arr = new int[n];
		varr = new int[n];
		for(int i=0;i<n;i++){
			arr[i]=sc.nextInt();
		}
		//try split if it doesnt work
		System.out.println(monkey_food(arr,init,n));
	}

	static boolean monkey_food(int[] arr, int init, int n){
		//make base case
		if (arr[init]==0){
			return true;
		}
		else{
			//set visited to 1
			varr[init]=1;
			if ((init+arr[init]<n)&&(init-arr[init]>=0)){
				if ((varr[init+arr[init]]!=1)&&(varr[init-arr[init]]!=1)){
					return monkey_food(arr,init-arr[init],n) || monkey_food(arr,init+arr[init],n);
				}
				else if(varr[init+arr[init]]!=1){
					return monkey_food(arr,init+arr[init],n);
				}
				else if(varr[init-arr[init]]!=1){
					return monkey_food(arr,init-arr[init],n);
				}
			}
			else if (init+arr[init]>n-1){
				if (init-arr[init]<0){
					return false;
				}
				else{
					if (varr[init-arr[init]]!=1){
						return monkey_food(arr,init-arr[init],n);
					}
					else{
						//check
						return false;
					}
				}
			}
			else if (init-arr[init]<0){
				if (init+arr[init]>n-1){
					return false;
				}
				else{
					if (varr[init+arr[init]]!=1){
						return monkey_food(arr,init+arr[init],n);
					}
					else{
						//check
						return false;
					}
				}
			}
		}
		return false;
	}
}