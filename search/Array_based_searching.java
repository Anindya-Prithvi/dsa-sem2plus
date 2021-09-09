import java.io.BufferedReader;
import java.util.*;

class Main{

	static int[] arr;

	static int linear_search(int e){
		// USES GLOBAL ARRAY
		// can only search an integer array and returns index (-1 if not found.)
		for(int i=0;i<arr.length;i++){
			if(arr[i]==e){
				return i;
			}
		}
		return -1;
	}

	static int binary_search(int e, int start, int end){
		// USES GLOBAL ARRAY
		// can only search an integer array and returns index (-1 if not found.)
		// uncomment below to see arrays while being searched.
		// for(int j=start; j<=end; j++){
		// 	System.out.printf("%d ",arr[j]);
		// }
		// System.out.println("");
		
		int length = end - start + 1;
		if (arr[start+(length/2)]<e){
			//search right side of mid arr
			return binary_search(e, start+length/2, end);
		}
		else if (arr[start+(length/2)]>e){
			//search left side of mid arr
			return binary_search(e, start, start+length/2);
		}
		else if (arr[start+(length/2)]==e){
			return start+length/2;
		}
		return -1;
	}

	public static void main(String[] args){
		int[] array_test = {1,2,3,4,6,7,9,10,111,113,1134,3256,45436,453444};
		arr = array_test;
		System.out.println(linear_search(10));
		System.out.println(binary_search(10, 0, array_test.length-1));
	}

}