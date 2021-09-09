import java.util.*;

//patched error 97  26   7  31  22  21  50  66  74  67

class Node{
		int data; //add possibility of generic data or modify acc needs.
		Node (String data_self){
			data = Integer.parseInt(data_self); //assuming data is comparable ><=
		}
}

class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of inputs: ");
		int n = sc.nextInt();
		Node[] arr = new Node[n];
		for(int i = 0; i<n; i++){
			Node e = new Node(sc.next());
			arr[i] = e;
		}
		int start = 0; //start is the index from where sort should start
		int end = n-1; //end is number of elements from start to send to quick_sort
		Quick_sort sorted = new Quick_sort(arr, start, end);
		//sorted.arr contains the sorted elements
		arr = sorted.arr;
		for(int i = 0; i<n; i++){
			System.out.print(arr[i].data+" ");
		}

	}
}

class Merge_sort{
	Node[] arr;
	public Merge_sort(Node[] arr, int start, int end){
		this.arr = arr;
		merge_sort(start, end);
	}

	public int[] merge_sort(int start, int end){
		// operates on GLOBAL array
		// can only sort an integer array
		// System.out.println("Merge sort smaller array from "+start + " to " + end);
		int[] ep = {start, end};
		if ((end - start == 1)||(end == start)){
			if (arr[end].data<arr[start].data){
				Node temp = arr[start];
				arr[start] = arr[end];
				arr[end] = temp;
			}
			return ep;
		}
		return merge(merge_sort(start,start+(end-start)/2), merge_sort(start+(end-start)/2+1,end));
	}

	public int[] merge(int[] a1, int[] a2){
		//a1 and a2 both have start and end...start of a1  is always < start a2 
		//end a2 > end a1
		// System.out.println("merging from "+a1[0]+" to "+a2[1]);
		int[] ep = {a1[0],a2[1]};
		int merge_size = a2[1]-a1[0]+1;
		Node[] aux = new Node[merge_size];
		int iter_pos_1 = a1[0];
		int iter_pos_2 = a2[0];
		for(int i = 0; i<merge_size; i++){
			if (iter_pos_1 <= a1[1]){
				if (iter_pos_2 <= a2[1]){
					if (arr[iter_pos_1].data < arr[iter_pos_2].data){
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