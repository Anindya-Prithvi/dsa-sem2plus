import java.util.Scanner;
import java.util.ArrayList;

//AC
//edge is bridge?

class Node{
    int color = 0;
    int name;
    boolean visited = false;

    Node(int data){
        this.name = data;
    }

    public void seeing(){
        //only when seeing this
        //also useful for tracing path
        this.color = 1;
    }
}

class Main{
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		int n = sc.nextInt();
        int e = sc.nextInt();
		//tree rooted at 1
		ArrayList<Node>[] arr = new ArrayList[n];
		Node[] nodejs = new Node[n];
        for (int i = 0; i<n; i++) {
            arr[i] = new ArrayList<Node>();
            nodejs[i] = new Node(i);
        }
        for(int i = 0; i<e; i++){
            int sno1 = sc.nextInt()-1;
            int sno2 = sc.nextInt()-1;
            arr[sno1].add(nodejs[sno2]);
            arr[sno2].add(nodejs[sno1]);
    	}

        bfs(arr, sc.nextInt()-1, sc.nextInt()-1, nodejs);
        System.out.println((vista>1)?"0":"1");
        // for(Node i: nodejs){
        // 	System.out.println("for "+k+" the color is:"+i.color);
        // }
	}

    static int vista = 0;
	public static void bfs(ArrayList<Node>[] amr, int s, int e, Node[] nodejs){
        Queues qq = new Queues();
        qq.push(s);
        while(!qq.isEmpty()){
            if(vista>1){break;}
            int k = qq.poll();
            nodejs[k].visited=true;
            for (Node i: amr[k]){
                if(i.name==e){vista++; continue;}
                if(i.visited==false){qq.push(i.name);i.color+=nodejs[k].color+1;i.visited=true;}
            }
        }
        return;
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
