//and-choice-0e5db566
//https://www.hackerearth.com/practice/data-structures/trees/heapspriority-queues/practice-problems/algorithm/and-choice-0e5db566/
//Anindya Prithvi
// Generalizing
// PriorityQGen maxHeap
//slow

import java.util.Scanner;
class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		PriorityQ pq = new PriorityQ();
		int s = sc.nextInt();
		int[] arr = new int[s];
		for(int i=0; i<s; i++){
			arr[i] = sc.nextInt();
		}

		Node[] arrayN = new Node[(int)Math.pow(s,2)];
		for(int i=0; i<s; i++){
			for(int j=0; j<s; j++){
				if (i==j) {arrnew Node(-1); continue;}
				arrayN[i*s+j] = new Node(arr[i]&arr[j]);
			}
		}
		for(Node i:arrayN){
			System.out.println(i);
		}
		System.out.println("WTF");
		
		pq.autoHeap(arrayN);
		System.out.println(pq.poll().getT());
	}
}

class Node{
	private int data; //generic node
	private int cmp;
	Node(int data){
		this(0, data);
	}
	Node(int data, int cmp){
		this.data = data;
		this.cmp = cmp;
	}
	//make insertion based on req
	//override cmp IFF req
	public int getT(){
		return cmp;
	}

	public int getD(){
		return data;
	}

    public int compareTo(Node o) {
    	if(o==null){return 0;}
        return getT() - o.getT();
    }
}

class PriorityQ {
	Node[] arr;

	int size;
	PriorityQ(){//empty queue
		this(1);
	}
	PriorityQ(int size){
		this.size = size;
		arr = new Node[size];
	}
	int tofill = 0; //the place to fill

	private void resize(int new_size){
		Node[] new_arr = new Node[new_size];
		for(int i = 0; i<this.size; i++){
			new_arr[i] = arr[i];
		}
		this.arr = new_arr;
		this.size = new_size;
	}

	public boolean isEmpty(){
		if (tofill == 0){return true;}
		return false;
	}

	public Node poll(){
		Node temp = arr[0];
		arr[0] = arr[tofill-1];
		arr[tofill-1] = null;
		tofill--;
		//heap_balance(0);
		return temp;
	}

	public void push(Node element){
		//no overflow checker added yet
		if (tofill==this.size){
			resize(this.size*2);
		}
		arr[tofill] = element;
		heap_balance(tofill);
		tofill++;
		return;
	}

	public void heap_balance(int k){
		if(tofill == 0){return;}
		while(true){
			if(arr[(k-1)/2].compareTo(arr[k])<0){
				//bubble
				Node temp = arr[k];
				arr[k] = arr[(k-1)/2];
				arr[(k-1)/2] = temp;
				k = (k-1)/2;
				continue;
			}
			if(arr[k]!=null && k<((tofill)/2)){
				if((arr[2*k+1].compareTo(arr[k])>0) && (arr[2*k+1].compareTo(arr[2*k+2])>=0)){
					Node temp = arr[2*k+1];
					arr[2*k+1] = arr[k];
					arr[k] = temp;
					k = 2*k+1;
					continue;
				}
				else if((arr[2*k+2]!=null) && (arr[2*k+2].compareTo(arr[k])>0)){
					Node temp = arr[2*k+2];
					arr[2*k+2] = arr[k];
					arr[k] = temp;
					k = 2*k+2;
					continue;
				}
			}
			break;

		}
		//System.out.println(verify_heap_invariant()?"Success":"Failure");
	}

	private void childbalance(int k) {
		while (true) {
	    	int left = 2*k + 1; 
	      	int right = 2*k + 2; 
	      	int largest = left; // Assume left tobe <
	      	if ((right < this.size) && (arr[right]!=null) && arr[right].compareTo(arr[left])>0){
	      		largest = right;
	      	}
	      	if (left >= this.size || arr[k]==null || arr[k].compareTo(arr[largest])>0) break;
	      	Node temp = arr[k];
	      	arr[k] = arr[largest];
	      	arr[largest] = temp;
	      	k = largest;
	    }
	}

	public void autoHeap(Node[] array){//heapsautte
		this.size = array.length;
		this.arr = array;
		this.tofill = this.size;
	    // Heapify process, O(n)
	    
    	for (int i = Math.max(0, (this.size / 2) - 1); i >= 0; i--) childbalance(i);
	}

	public boolean verify_heap_invariant(){
		boolean flag = true;
		for(int i = 0; i < (tofill+1)/2; i++){
			if (arr[i]==null){break;}
			if( (arr[i].compareTo(arr[2*i+1])>0) || (arr[i].compareTo(arr[2*i+1+2])>0) ){flag = false; return flag;}
		}
		return flag;
	}

}