import java.util.*;

class Main{

	static int[] arr;
	static Random rand = new Random(10);
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int k = sc.nextInt();
		int d = sc.nextInt();
		arr = new int[t];
		for(int i=0; i<t; i++){
			arr[i]=sc.nextInt();
		}
		merge_sort(0,t-1);
		if (d==1){
			System.out.println(arr[t-k]);
		}
		else if(d==0){
			System.out.println(arr[k-1]);
		}
	}

	static int[] merge_sort(int start, int end){
		// operates on GLOBAL array
		// can only sort an integer array
		// System.out.println("Merge sort smaller array from "+start + " to " + end);
		int[] ep = {start, end};
		if ((end - start == 1)||(end == start)){
			if (arr[end]<arr[start]){
				int temp = arr[start];
				arr[start] = arr[end];
				arr[end] = temp;
			}
			return ep;
		}
		return merge(merge_sort(start,start+(end-start)/2), merge_sort(start+(end-start)/2+1,end));
	}


	static int[] merge(int[] a1, int[] a2){
		//a1 and a2 both have start and end...start of a1  is always < start a2 
		//end a2 > end a1
		// System.out.println("merging from "+a1[0]+" to "+a2[1]);
		int[] ep = {a1[0],a2[1]};
		int merge_size = a2[1]-a1[0]+1;
		int[] aux = new int[merge_size];
		int iter_pos_1 = a1[0];
		int iter_pos_2 = a2[0];
		for(int i = 0; i<merge_size; i++){
			if (iter_pos_1 <= a1[1]){
				if (iter_pos_2 <= a2[1]){
					if (arr[iter_pos_1] < arr[iter_pos_2]){
						aux[i] = arr[iter_pos_1];
						iter_pos_1++;
					}
					else{
						aux[i] = arr[iter_pos_2];
						iter_pos_2++;
					}
				}
				else{
					aux[i] = arr[iter_pos_1];
					iter_pos_1++;
				}
			}
			else if (iter_pos_2<=a2[1]){
				aux[i] = arr[iter_pos_2];
				iter_pos_2++;
			}
		}
		//now we assume aux array is sorted
		//overwrite
		for(int i = a1[0]; i <= a2[1]; i++){
			arr[i]=aux[i-a1[0]];
		}
	return ep;
	}
}