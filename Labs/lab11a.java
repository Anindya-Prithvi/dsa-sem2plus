//lab11a
//AC
import java.util.Scanner;
import java.util.ArrayList;
//buggy bfs lmao
class Main{
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        int t = sc.nextInt();
        while(t-->0){
            int n = sc.nextInt(); //number of stations
            int m = sc.nextInt(); //number of adj
            ArrayList<Node>[] amr = new ArrayList[n];
            Node[] nodejs = new Node[n];
            for (int i = 0; i<n; i++) {
                amr[i] = new ArrayList<Node>();
                nodejs[i] = new Node(i);
            }
            for(int i = 0; i<m; i++){
                int sno1 = sc.nextInt()-1;
                int sno2 = sc.nextInt()-1;
                amr[sno1].add(nodejs[sno2]);
                amr[sno2].add(nodejs[sno1]);
            }
            int s = sc.nextInt(); //starting location
            bfs(amr,s-1,nodejs); //no destination search
            //people like me LOL
            for(int i=0; i<n; i++){
                System.out.print((i==s-1)?"":(nodejs[i].color==0)?"-1 ":nodejs[i].color*10+" ");
            }
            System.out.print("\n");
        }
    }

    public static void bfs(ArrayList<Node>[] amr, int s,Node[] nodejs){
        Queues qq = new Queues();
        qq.push(s);
        while(!qq.isEmpty()){
            int k = qq.poll();
            for (Node i: amr[k]){
                if(i.color==0){qq.push(i.name);i.color+=nodejs[k].color+1;};
            }
        }


    }
}

class Node{
    int color = 0;
    int name;

    Node(int data){
        this.name = data;
    }

    public void seeing(){
        //only when seeing this
        //also useful for tracing path
        this.color = 1;
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
