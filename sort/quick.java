import java.io.BufferedReader;
import java.util.*;

//patched error 97  26   7  31  22  21  50  66  74  67
class Main{

	static int[] arr;
	static Random rand = new Random(10);

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] array_test = new int[10];
		while(n-->0){
			for(int i=0; i<10;i++){
				array_test[i] = sc.nextInt();
			}
			arr = array_test;
			int start = 0; //start is the index from where sort should start
			int end = arr.length-1; //end is number of elements from start to send to quick_sort
			quick_sort(start, end);
			for(int i = 0 ; i<= end; i++){
				System.out.print(arr[i]+" ");
			}
			System.out.printf("%n");
		}
	}

	static void quick_sort(int start, int end){
		//reimplementing
		//works on global array
		//start,end inclusive
		////System.out.println("Quick sort from "+start+" to "+end);

		//base case
		if (end<=start){return;}
		if ((end-start)==1){
			if (arr[end]<arr[start]){
			int temp = arr[end];
			arr[end] = arr[start];
			arr[start] = temp;
			////System.out.println(arr[start]+" BIN "+arr[end]);
			}
			return;
		}
		
		//else
		int p = rand.nextInt(end-start+1);
		p+=start;
		//System.out.println("The pivot for "+start+" to "+end+" is "+arr[p]+" lol "+p);
		// find the perfect position of pivot here
		
		//step 1

		int lo = start;
		int hi = end;
		int temp = arr[lo];
		arr[lo] = arr[p];
		arr[p] = temp;
		int e = arr[lo]; //this is the pivot element

		//System.out.println("before "+hi);
		lo++;
		// now our array is like PeEeeEEEeeEEeEE
		while(hi>=lo){
			//if (hi<=lo){break;}
			if (hi==lo){
				if (arr[hi]>e){
					temp = arr[hi-1];
					arr[hi-1] = e;
					hi--;
				}
				else{
					temp = arr[hi];
					arr[hi] = e;
				}
				arr[start] = temp;
				break;
			}
			if ((arr[lo]>e) && (arr[hi]<e)){
				int swap = arr[hi];
				arr[hi] = arr[lo];
				arr[lo] = swap;
				lo++;
				//hi--;
			}
			else if (arr[lo]<=e){
				lo++;
			}
			else if (arr[hi]>=e){
				hi--;
			}
		}
		//System.out.println("after "+hi);
		quick_sort(start,hi-1);
		quick_sort(hi+1,end);

	}
}