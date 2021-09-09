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
	
class Quick_sort{
	static Random rand = new Random(10);
	Node[] arr;
	public Quick_sort(Node[] arr, int start, int end){
		this.arr = arr;
		quick_sort(start, end);
	}
	public void quick_sort(int start, int end){
		//reimplementing
		//works on global array
		//start,end inclusive
		////System.out.println("Quick sort from "+start+" to "+end);

		//base case
		if (end<=start){return;}
		if ((end-start)==1){
			if (arr[end].data<arr[start].data){
			Node temp = arr[end];
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
		Node temp = arr[lo];
		arr[lo] = arr[p];
		arr[p] = temp;
		Node e = arr[lo]; //this is the pivot element

		//System.out.println("before "+hi);
		lo++;
		// now our array is like PeEeeEEEeeEEeEE
		while(hi>=lo){
			//if (hi<=lo){break;}
			if (hi==lo){
				if (arr[hi].data>e.data){
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
			if ((arr[lo].data>e.data) && (arr[hi].data<e.data)){
				Node swap = arr[hi];
				arr[hi] = arr[lo];
				arr[lo] = swap;
				lo++;
				//hi--;
			}
			else if (arr[lo].data<=e.data){
				lo++;
			}
			else if (arr[hi].data>=e.data){
				hi--;
			}
		}
		//System.out.println("after "+hi);
		quick_sort(start,hi-1);
		quick_sort(hi+1,end);

	}
}
