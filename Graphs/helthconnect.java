import java.util.Scanner;
import java.util.ArrayList;

//diameter
//correct bfs
//tree diameter practice

class Node{
    //int color = 0;
    int name;
    boolean visited = false;

    Node(int data){
        this.name = data;
    }

    // public void seeing(){
    //     //only when seeing this
    //     //also useful for tracing path
    //     this.color = 1;
    // }
}

class Main{
    static int conn;
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
        int t = sc.nextInt();
        while(t-->0){
            int conn = 0;
    		int n = sc.nextInt(); //nodes
            int m = sc.nextInt(); //edges
    		//tree rooted at 1
            long c = sc.nextLong();
            int r = sc.nextInt();
            if(c<=r){
                System.out.println(c*n);
                //ignore stdin
                while(m-->0){sc.nextInt();sc.nextInt();}
            }
            else{
                long cost=0;
        		ArrayList<Node>[] arr = new ArrayList[n];
        		Node[] nodejs = new Node[n];
                    for (int i = 0; i<n; i++) {
                        arr[i] = new ArrayList<Node>();
                        nodejs[i] = new Node(i);
                    }
                    for(int i = 0; i<m; i++){
                        int sno1 = sc.nextInt()-1;
                        int sno2 = sc.nextInt()-1;
                        arr[sno1].add(nodejs[sno2]);
                        arr[sno2].add(nodejs[sno1]);
                	}

                for(int i=0; i<n; i++){
                    if(nodejs[i].visited==true){continue;}
                    else{cost+=(bfs(arr, i, nodejs)-1)*r+c;}
                }

                // for(Node i: nodejs){
                // 	System.out.println("for "+i+" the color is:"+i.visited);
                // }
                System.out.println(cost);
            }
        }
	}
	public static int bfs(ArrayList<Node>[] amr, int s,Node[] nodejs){
        conn++;
        int visits=0;
        Queues qq = new Queues();
        qq.push(s);
        while(!qq.isEmpty()){
            int k = qq.poll();
            visits++;
            nodejs[k].visited=true;
            for (Node i: amr[k]){
                if(i.visited==false){qq.push(i.name);i.visited=true;}
            }
        }
        return visits;
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
