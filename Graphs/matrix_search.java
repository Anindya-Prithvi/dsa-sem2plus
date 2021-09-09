import java.util.Scanner;
// to get only answer 1 time
class Node{
	boolean visitar; //generic node
	int color = 0;

	Node(int data){
		this.visitar = (data==0)||(data==2);
	}

	public boolean isblocked(){
		return !visitar;
	}

	public void seeing(){
		//only when seeing this
		//also useful for tracing path
		this.color = 1;
	}

	public void done(){
		//only when popping out from stack/queue
		this.color = 2;
	}
}
/*una testo caseo
1 0 0 0 1 1 1 1 1 1
1 0 0 0 1 1 0 1 0 0
1 0 1 1 1 1 1 1 0 1
1 1 0 1 1 0 1 1 1 0
1 1 1 0 1 1 1 1 0 0
1 0 1 1 0 1 1 0 0 1
1 1 1 0 1 1 1 1 1 1
0 0 1 1 1 1 0 0 0 1
0 1 1 1 1 1 1 0 0 1
1 1 0 1 0 1 1 0 1 1*/

class Main{
	static Scanner sc = new Scanner(System.in);
	static int sx;
	static int sy;

	public static Node[][] adj(){
		//here the matrix represents an explorable space
		//if m==0, space is unexplorable, else if 1, explorable
		int m = sc.nextInt(); //scans rows
		int n = sc.nextInt(); //scans columns
		Node[][] adj = new Node[m][n];
		for(int i = 0; i<m; i++){
			for(int j=0; j<n; j++){
				int temp = sc.nextInt();
				if(temp==2){sx = i; sy = j;}
				adj[i][j] = new Node(temp);
			}
		}
		return adj;
	}
	public static void main(String[] args){
		//adjasensei matrix
		Node[][] matr = adj();
		System.out.println(bfs(matr,sx,sy));
		for(Node[] i: matr){
			for(Node j: i){
				System.out.print(j.color+" ");
			}
			System.out.print("\n");
		}
	}

	public static boolean bfs(Node[][] adj, int m, int n){
		Queues bfsq = new Queues();
		int szr = adj.length;
		int szc = adj[0].length;
		bfsq.push(new int[]{m,n});
		adj[m][n].seeing();
		while(!bfsq.isEmpty()){
			int[] mn = bfsq.poll();
			m = mn[0]; n = mn[1];
			if((m<szr-1)&&!adj[m+1][n].isblocked()&&(adj[m+1][n].color==0)){bfsq.push(new int[]{m+1,n});adj[m+1][n].color=adj[m][n].color+1;}
			if((m>=1)&&!adj[m-1][n].isblocked()&&(adj[m-1][n].color==0)){bfsq.push(new int[]{m-1,n});adj[m-1][n].color=adj[m][n].color+1;}
			if((n<szc-1)&&!adj[m][n+1].isblocked()&&(adj[m][n+1].color==0)){bfsq.push(new int[]{m,n+1});adj[m][n+1].color=adj[m][n].color+1;}
			if((n>=1)&&!adj[m][n-1].isblocked()&&(adj[m][n-1].color==0)){bfsq.push(new int[]{m,n-1});adj[m][n-1].color=adj[m][n].color+1;}
		}
		return false;
	}

	public static boolean dfs(Node[][] adj, int m, int n, int a, int b){ //implementation of dfs on 2D plane
		//m,n row and column (origin)
		//a,b row and column (destination)
		int szr = adj.length;
		int szc = adj[0].length;
		if((m==szr)||(m==-1)){return false;}
		if((n==szr)||(n==-1)){return false;}
		if (adj[m][n].color!=0){return false;}
		if (adj[m][n].isblocked()){return false;}
		adj[m][n].seeing();
		boolean path_exist = (m==a)&&(n==b);
		if(path_exist){return path_exist;}
		path_exist = dfs(adj,m+1,n,a,b)||dfs(adj,m-1,n,a,b)||dfs(adj,m,n+1,a,b)||dfs(adj,m,n-1,a,b);
		if(path_exist){return path_exist;}
		adj[m][n].done();
		return path_exist;
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
		int[] data;
		Node next;
		Node (int[] to_store){
			this.data = to_store;
			this.next = null;
		}
	}

	Node head = null; //stores the intermeddiate head
	Node tail = null; //Queues tail

	public void push(int[] element){
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

	public int[] poll(){
		//add print method as per req
		if (current_size==0){
			System.out.println("QueueUnderflowError");
			return null;
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

	public int[] peek(){
		if (head==null){
			System.out.println("Empty Stack");
			return null;
		}
		return head.data;
	}

	public boolean isEmpty(){
		return (current_size==0);
	}
}


