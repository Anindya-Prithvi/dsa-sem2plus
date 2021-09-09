//PriorityQ maxHeap
//intent, node can have other information, 
class Node{
	int data; //change data type with reqs
	Node(int data){
		this.data = data;
	}
}

class Main{
	public static void main(String[] args){
		PriorityQ pq = new PriorityQ(10);

		pq.push(new Node(10));
//DO NOT USE
		pq.push(new Node(29));
//DO NOT USE
		pq.push(new Node(82));
//DO NOT USE
		pq.push(new Node(72));
//DO NOT USE
		pq.push(new Node(26));
//DO NOT USE
		pq.push(new Node(25));
//DO NOT USE
		pq.push(new Node(0));
//DO NOT USE
		pq.push(new Node(63));
//DO NOT USE THIS IS FAULTY
		pq.push(new Node(2134));

		pq.push(new Node(1));

		while(!pq.isEmpty()){
			System.out.println(pq.poll().data);
			pq.print();
		}
		
	}
}

class PriorityQ{
	Node[] arr;
	int size;
	PriorityQ(int size){
		this.size = size;
		arr = new Node[size];
	}
	int lastFilled = 0; //One based, also points at next element to fill

	public boolean isEmpty(){
		if (lastFilled == 0){return true;}
		return false;
	}

	public Node poll(){
		Node temp = arr[0];
		arr[0] = arr[lastFilled-1];
		arr[lastFilled-1] = null;
		lastFilled--;
		heap_balance(0);
		return temp;
	}

	public void print(){
		int n = lastFilled;
		for(Node i : arr){
			if(n>0){System.out.print(i.data+" ");n--;continue;};
			break;
		}
		System.out.print("\n");
	}

	public void push(Node element){
		//no overflow checker added yet
		arr[lastFilled] = element;
		heap_balance(lastFilled);
		lastFilled++;
		return;
	}

	public void heap_balance(int k){
		if(lastFilled == 0){return;}
		while(true){
			if(arr[k/2].data < arr[k].data){
				//bubble
				Node temp = arr[k];
				arr[k] = arr[k/2];
				arr[k/2] = temp;
				k = k/2;
				continue;
			}
			if(arr[k]!=null && k<(lastFilled/2)){
				if((arr[2*k].data > arr[k].data) && (arr[2*k].data > arr[2*k+1].data)){
					Node temp = arr[2*k];
					arr[2*k] = arr[k];
					arr[k] = temp;
					k = 2*k;
					continue;
				}
				else if(arr[2*k+1].data > arr[k].data){
					Node temp = arr[2*k+1];
					arr[2*k+1] = arr[k];
					arr[k] = temp;
					k = 2*k+1;
					continue;
				}
			}
			break;

		}
		//System.out.println(verify_heap_invariant()?"Success":"Failure");
	}

	public boolean verify_heap_invariant(){
		boolean flag = true;
		for(int i = 0; i < lastFilled/2; i++){
			if(arr[i].data<arr[2*i].data || arr[i].data<arr[2*i+1].data){flag = false; return flag;}
		}
		return flag;
	}

}