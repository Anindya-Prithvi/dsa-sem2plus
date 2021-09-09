// monk-and-iq
// https://www.hackerearth.com/practice/data-structures/trees/heapspriority-queues/practice-problems/algorithm/monk-and-iq/
// Generalizing
// PriorityQGen minHeap
//STUPID IDIOTIC QUESTION: DID NOT SPECIFY THAT IQ ARRAY COULD BE INCOMPLETE, F&*@^
// Anindya Prithvi

import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

class Main{
	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		int c = Reader.nextInt(); 
		int p = Reader.nextInt();
		int n = Reader.nextInt();
		PriorityQ pq = new PriorityQ(c);

		PriorityQ.Node[] arrayN = new PriorityQ.Node[c];
		for (int i=0; i<n; i++){
			arrayN[i] = new PriorityQ.Node(i+1, Reader.nextInt());
		}
		for (int i=n; i<c; i++){
			arrayN[i]=new PriorityQ.Node(i+1);
		}
		pq.autoHeap(arrayN);
		
		while(p-->0){
			//System.out.println(pq.verify_heap_invariant());
			//pq.print();
			PriorityQ.Node best = pq.poll();
			//pq.print();
			System.out.print(best.getCNO()+" ");
			pq.push(new PriorityQ.Node(best.getCNO(), Reader.nextInt(), best.getiq1(), 1+best.getnum()));
		}
	}
}


class PriorityQ {
	static class Node{
	private int cno; //generic node
	private int iq1;
	private int iq2;
	private int num;
	private int cmp;
	Node(int cno){
		this.cno = cno;
		this.iq1 = 0;
		this.iq2 = 0;
		this.num = 0;
		this.cmp = 0;
	}
	Node(int cno, int iq1){
		this.cno = cno;
		this.iq1 = iq1;
		this.iq2 = 0;
		this.num = 1;
		this.cmp = getZ();
	}
	Node(int cno, int iq1, int iq2, int num){
		this.cno = cno;
		this.iq1 = iq1;
		this.iq2 = iq2;
		this.num = num;
		this.cmp = getZ();
	}
	//make insertion based on req
	//override cmp IFF req
	public int getiq1(){
		return this.iq1;
	}
	public int getZ(){
		return num*(iq1+iq2);
	}

	public int getCNO(){
		return this.cno;
	}

	public int getnum(){
		return this.num;
	}

	public int getT(){
		return cmp;
	}

    public int compareTo(Node o) {
		if(o==null){return 0;}
        int diff = (getT() - o.getT());
        if (diff == 0){
        	return getCNO() - o.getCNO();
        }
        return diff;

    }
}

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

	public void print(){
		int n = tofill;
		for(Node i : arr){
			if (i==null){break;}
			if(n>0){System.out.print(i.getZ()+"cls:"+i.getCNO()+" ");n--;continue;};
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

class Reader {
	
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
    
    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }
    
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
    
}

