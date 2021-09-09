import java.util.Scanner;

class Main{
	public static void main(String[] args){
		PriorityQ minhip = new PriorityQ();
		Scanner sc = new Scanner(System.in);
		int day = 0;
		int win = 0;
		int ndays = sc.nextInt();
		int[] fruits = new int[ndays];
		int[] exp = new int[ndays];
		for(int i=0; i<ndays; i++){
			fruits[i] = sc.nextInt();
		}
		for(int i=0; i<ndays; i++){
			exp[i] = sc.nextInt();
		}
		while(ndays-->0){
			if(fruits[day]!=0) {minhip.push(new Node(fruits[day], exp[day] + day));}
			win+=minhip.get(day);
			day++;
		}
		while(minhip.isEmpty()==false){
			win+=minhip.get(day);
			day++;
		}
		System.out.println(win);
	}
}

class Node{
	int data; //generic node
	int cmp;
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
		heap_balance(0);
		return temp;
	}

	public Node peek(){
		return arr[0];
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

	public int get(int day){
			if(isEmpty()){
				return 0;
			}
			if(peek().getT()>day){
				if (peek().getD()==1){
					poll();
				}
				else {
					arr[0].data-=1;
				}
				return 1;
			}
			else{
				poll();
				return get(day);
			}
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
	      	Node temp = arr[k];
	      	arr[k] = arr[smallest];
	      	arr[smallest] = temp;
	      	k = smallest;
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
		for(int i = 0; i < tofill/2; i++){
			if (arr[i]==null){break;}
			if( (arr[i].compareTo(arr[2*i+1])>0) || (arr[i].compareTo(arr[2*i+2])>0) ){flag = false; return flag;}
		}
		return flag;
	}

}