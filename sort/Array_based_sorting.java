import java.io.BufferedReader;
import java.util.*;

class Main{

	static int[] arr;
	static Random rand = new Random(10);

	public static void main(String[] args){
		int[] array_test = {453444, 45436, 3256, 1134, 113, 111, 10, 9, 7, 6, 4, 3, 2, 1, 
							453444, 45436, 3256, 1134, 113, 111, 10, 9, 7, 6, 4, 3, 2, 1, 
							453444, 45436, 3256, 1134, 113, 111, 10, 9, 7, 6, 4, 3, 2, 1, 
							453444, 45436, 3256, 1134, 113, 111, 10, 9, 7, 6, 4, 3, 2, 1,
							453444, 45436, 3256, 1134, 113, 111, 10, 9, 7, 6, 4, 3, 2, 1, 
							453444, 45436, 3256, 1134, 113, 111, 10, 9, 7, 6, 4, 3, 2, 1, 
							453444, 45436, 3256, 1134, 113, 111, 10, 9, 7, 6, 4, 3, 2, 1, 
							453444, 45436, 3256, 1134, 113, 111, 10, 9, 7, 6, 4, 3, 2, 1,
							453444, 45436, 3256, 1134, 113, 111, 10, 9, 7, 6, 4, 3, 2, 1, 
							453444, 45436, 3256, 1134, 113, 111, 10, 9, 7, 6, 4, 3, 2, 1, 
							453444, 45436, 3256, 1134, 113, 111, 10, 9, 7, 6, 4, 3, 2, 1, 
							453444, 45436, 3256, 1134, 113, 111, 10, 9, 7, 6, 4, 3, 2, 1};
		arr = array_test;
		int start = 0; //start is the index from where sort should start
		int end = arr.length-1; //end is number of elements from start to send to merge_sort

		quick_sort(start, end);
		merge_sort(start, end);
		
		for(int i = 0 ; i<= end; i++){
			System.out.print(arr[i]+" ");
		}
	}

	static void quick_sort(int start, int end){
		//reimplementing
		//works on global array
		//start,end inclusive
		//System.out.println("Quick sort from "+start+" to "+end);

		//base case
		if (end<=start){return;}
		if ((end-start)==1){
			if (arr[end]<arr[start]){
			int temp = arr[end];
			arr[end] = arr[start];
			arr[start] = temp;
			//System.out.println(arr[start]+" BIN "+arr[end]);
			}
			return;
		}
		
		//else
		int p = rand.nextInt(end-start+1);
		p+=start;
		//System.out.println("The pivot for "+start+" to "+end+" is "+p);
		// find the perfect position of pivot here
		
		//step 1

		int lo = start;
		int hi = end;
		int temp = arr[lo];
		arr[lo] = arr[p];
		arr[p] = temp;
		int e = arr[lo]; //this is the pivot element

		lo++;
		// now our array is like PeEeeEEEeeEEeEE
		while(hi>=lo){
			//if (hi<=lo){break;}
			if (hi==lo){
				if (arr[hi]>e){
					temp = arr[hi-1];
					arr[hi-1] = e;
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
				hi--;
			}
			else if (arr[lo]<=e){
				lo++;
			}
			else if (arr[hi]>=e){
				hi--;
			}
		}
		quick_sort(start,hi-1);
		quick_sort(hi,end);

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