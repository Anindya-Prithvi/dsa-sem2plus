// Anindya Prithvi

import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

class Node{
    int color1 = 0;
    int color2 = 0;
    int name;
    boolean visited1 = false;
    boolean visited2 = false;

    Node(int data){
        this.name = data;
    }
}


class Main{
	public static void main(String[] args) throws IOException{
		Reader.init(System.in);
		int n = Reader.nextInt();
		int m = Reader.nextInt();
		int a = Reader.nextInt()-1;
		int b = Reader.nextInt()-1;
		ArrayList<Node>[] arr = new ArrayList[n];
		Node[] nodejs = new Node[n];
        for (int i = 0; i<n; i++) {
            arr[i] = new ArrayList<Node>();
            nodejs[i] = new Node(i);
        }
		for(int i = 0; i<m; i++){
			int sno1 = Reader.nextInt()-1;
            int sno2 = Reader.nextInt()-1;
            arr[sno1].add(nodejs[sno2]);
            arr[sno2].add(nodejs[sno1]);
		}
		System.out.println(bfs(arr, a, b, nodejs));
	}

	public static int bfs(ArrayList<Node>[] amr, int s, int e, Node[] nodejs){
        Queues qq = new Queues();
        Queues pp = new Queues();
        pp.push(e);
        qq.push(s);
        while(!qq.isEmpty()){
            int k = qq.poll();
            nodejs[k].visited1=true;
            for (Node i: amr[k]){
                if(i.visited1==false){qq.push(i.name);i.color1+=nodejs[k].color1+1;i.visited1=true;}
            }
        }
        while(!pp.isEmpty()){
            int k = pp.poll();
            nodejs[k].visited2=true;
            for (Node i: amr[k]){
                if(i.visited2==false){pp.push(i.name);i.color2+=nodejs[k].color2+1;i.visited2=true;}
            }
        }
        int sum = 0;
        for(Node i:nodejs){
        	if(i.color1==0){continue;}
        	if(i.color1==i.color2){sum+=1;}
        }
        if(s==e){sum+=1;}
    return sum;
	}
}


class Queues{
    //push
    //pop
    //head
    //traverse?
    //this is an infinite queue

    int size = 0;
    int current_size = 0;

    public Queues(){
        this.size = Integer.MAX_VALUE;
    }

    public Queues(int size_of_stack){
        this.size = size_of_stack;
    }

    class Node{
        int data;
        Node next;
        Node (int to_store){
            this.data = to_store;
            this.next = null;
        }
    }

    Node head = null; //stores the intermeddiate head
    Node tail = null; //Queues tail

    public void push(int element){
        if (current_size == size){
            System.out.println("QueueOverflowError");
            return ;
        }
        Node new_head = new Node(element);
        if (head == null){
            head = new_head;
            current_size = 1;
            tail = new_head;
            return;
        }
        //new element pushes element backwards.
        //use tail ptr
        tail.next = new_head;
        tail = new_head;
        current_size += 1;
        return;
    }

    public int poll(){
        //add print method as per req
        if (current_size==0){
            System.out.println("QueueUnderflowError");
            return 0;
        }
        Node temp_head = head;
        head = head.next;
        current_size -= 1;
        if(current_size == 0){tail = null;}
        return temp_head.data;
    }

    public void print_queue(){
        if (head == null){
            System.out.println();
            return;
        }
        Node temp_head = head;
        do {
            System.out.print(temp_head.data + " ");
            temp_head = temp_head.next;
        }
        while (temp_head != null);
        return;
    }

    public int peek(){
        if (head==null){
            System.out.println("Empty Stack");
            return 0;
        }
        return head.data;
    }

    public boolean isEmpty(){
        return (current_size==0);
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