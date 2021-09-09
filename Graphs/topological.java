// Anindya Prithvi

import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

class Node{
    int color = 0;
    int name;
    char c;
    boolean visited = false;

    Node(int data){
        this.name = data;
        this.c = (char) data;
        this.c+= 'm';
    }

    public void seeing(){
        //only when seeing this
        //also useful for tracing path
        this.color = 1;
    }
}

class Main{
	static Node[] nodejs;
	static ArrayList<Node>[] arr;
	static Stacks<Integer> top = new Stacks<>();
	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		int n = Reader.nextInt();
        int e = Reader.nextInt();
		//tree rooted at 1
		arr = new ArrayList[n];
		nodejs = new Node[n];
        for (int i = 0; i<n; i++) {
            arr[i] = new ArrayList<Node>();
            nodejs[i] = new Node(i);
        }
        for(int i = 0; i<e; i++){
            int sno1 = Reader.nextInt()-1;
            int sno2 = Reader.nextInt()-1;
            arr[sno1].add(nodejs[sno2]);
    	}

        for(int i=n-1; i>=0; i--){

        	topological(i);
        }
        top.print_stack();
	}
	
	public static void topological(int i){System.out.println("Current vertex: "+(i+1));
		if(nodejs[i].visited==true){return;}
		else{
			nodejs[i].visited=true;
			for(Node j: arr[i]){
				if(j.visited==false){System.out.println("visiting: "+(j.name+1));topological(j.name);}
			}
			top.push(nodejs[i].name+1);
		}
	}
}

class Stacks<T>{
	//push, pop, head, raverse
	//this is an infinite stack (theoretically)
	int size = 0;
	int current_size = 0;
	public Stacks(){
		this.size = Integer.MAX_VALUE;
	}
	public Stacks(int size_of_stack){
		this.size = size_of_stack;
	}
	Node head = null; //stores the intermeddiate head
	class Node{
		T data;
		Node next;
		Node (T to_store){
			data = to_store;
			next = null;
		}
	}

	public void push(T element){
		if (current_size == size){
			System.out.println("StackOverflowError");
			return;
		}
		Node new_head = new Node(element);
		if (head == null){
			head = new_head;
			current_size = 1;
			return;
		}
		//new element pushes element backwards.

		new_head.next = head;
		head = new_head;
		current_size += 1;
		return;
	}

	public T pop(){
		//add print method as per req
		if (current_size==0){
			System.out.println("StackUnderflowError");
			return null;
		}
		Node temp_head = head;
		head = head.next;
		current_size -= 1;
		return temp_head.data;
	}

	public void print_stack(){
		if (head == null){
			System.out.println();
			return;
		}
		Node temp_head = head;
		do {
			char c;
			c = (char) ((int)temp_head.data);
			c+='l';
			System.out.print(c + " ");
			temp_head = temp_head.next;
		}
		while (temp_head != null);
		return;
	}

	public T peek(){
		if (head==null){
			System.out.println("Empty Stack");
			return null;
		}
		return head.data;
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