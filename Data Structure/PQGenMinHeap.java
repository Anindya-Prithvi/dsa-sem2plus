// Generalizing
// PriorityQGen minHeap

class Main{
	public static void main(String[] args){
		PriorityQ pq = new PriorityQ();
		Node[] arrayN = new Node[10];
		for (int i=0; i<10; i++){
			arrayN[i] = new Node(-i*92);
		}
		pq.autoHeap(arrayN);
		pq.print();
		// pq.push(new Node(10,10));
		// pq.push(new Node(103434,9));
		// pq.push(new Node(1220,8));
		// pq.push(new Node(29,7));
		// pq.push(new Node(82,6));
		// pq.push(new Node(72,5));
		// pq.push(new Node(26,4));
		// pq.push(new Node(25,3));
		// pq.push(new Node(0,2));
		// pq.push(new Node(63,1));
		// pq.push(new Node(2134,0));
		// pq.push(new Node(1,-1));
		while(!pq.isEmpty()){
			System.out.println(pq.poll().getT());
			//pq.print();
		}
	}
}

class Node<T>{
	private T data; //generic node
	int cmp;
	Node(int data){
		this(null, data);
	}
	Node(T data, int cmp){
		this.data = data;
		this.cmp = cmp;
	}
	//make insertion based on req
	//override cmp IFF req
	public int getT(){
		return cmp;
	}

	public T getD(){
		return data;
	}

    public int compareTo(Node<T> o) {
    	if(o==null){return 0;}
        return getT() - o.getT();
    }
}

class PriorityQ<T> {
	Node<T>[] arr;

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
		Node<T>[] new_arr = new Node[new_size];
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
		heap_balance(0);
		return temp;
	}

	public void print(){
		int n = tofill;
		for(Node i : arr){
			if (i==null){break;}
			if(n>0){System.out.print(i.getD()+" ");n--;continue;};
			break;
		}
		System.out.print("\n");
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
			if(arr[(k-1)/2].compareTo(arr[k])>0){
				//bubble
				Node temp = arr[k];
				arr[k] = arr[(k-1)/2];
				arr[(k-1)/2] = temp;
				k = (k-1)/2;
				continue;
			}
			if(arr[k]!=null && k<((tofill)/2)){
				if((arr[2*k+1].compareTo(arr[k])<0) && (arr[2*k+1].compareTo(arr[2*k+2])<=0)){
					Node temp = arr[2*k+1];
					arr[2*k+1] = arr[k];
					arr[k] = temp;
					k = 2*k+1;
					continue;
				}
				else if((arr[2*k+2]!=null) && (arr[2*k+2].compareTo(arr[k])<0)){
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
	      	int smallest = left; // Assume left tobe <
	      	if ((right < this.size) && (arr[right]!=null) && arr[right].compareTo(arr[left])<0){
	      		smallest = right;
	      	}
	      	if (left >= this.size || arr[k].compareTo(arr[smallest])<0) break;
	      	Node<T> temp = arr[k];
	      	arr[k] = arr[smallest];
	      	arr[smallest] = temp;
	      	k = smallest;
	    }
	}

	public void autoHeap(Node<T>[] array){//heapsautte
		this.size = array.length;
		this.arr = array;
		this.tofill = this.size;
	    // Heapify process, O(n)
    	for (int i = Math.max(0, (this.size / 2) - 1); i >= 0; i--) childbalance(i);
	}

	public boolean verify_heap_invariant(){
		boolean flag = true;
		for(int i = 0; i < tofill/2; i++){
			if (arr[i]==null){break;}
			if( (arr[i].compareTo(arr[2*i+1])>0) || (arr[i].compareTo(arr[2*i+2])>0) ){flag = false; return flag;}
		}
		return flag;
	}

}