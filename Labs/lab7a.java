//follows standard and non repetitive methodology
//JEP standards later.
//Exception search not used (adds overhead)...instead null point and shell feedback

import java.util.Scanner;
class Main{
	public static void main(String[] args){
		GQueues queue = new GQueues(Integer.MAX_VALUE);
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while(n-->0){
			String op = sc.next();
			switch (op){
				case "DELETE":	queue.poll();break;
				case "INSERT":	queue.push(new Node(sc.nextInt()));break;
				case "INCREMENT": queue.incr(sc.nextInt(), sc.nextInt()); break;
			}
			//queue.print_queue();
			System.out.println((queue.peek()==null)?"EMPTY":queue.peek().data);
		}
	}
}

class Node{
		long data;
		Node next;
		Node prev;
		Node (long to_store){
			data = to_store;
			next = null;
		}
	}

class GQueues{
	//push
	//pop
	//head
	//traverse?
	//this is an infinite queue

	int size = 0;
	int current_size = 0;

	public GQueues(int size_of_stack){
		size = size_of_stack;
	}

	Node head = null; //stores the intermeddiate head

	Node tail = null; //GQueues' tail

	public void incr(int k, int d){
		Node temp_node = tail;
		for (int i = 0; i<k; i++){
			temp_node.data += d;
			temp_node = temp_node.prev;
		}
		return;
	}

	public void push(Node element){
		if (current_size == size){
			//System.out.println("QueueOverflowError");
			return ;
		}
		Node new_head = element;
		if (head == null){
			head = new_head;
			current_size = 1;
			tail = new_head;
			return;
		}
		//new element pushes element backwards.
		//use tail ptr
		tail.next = new_head;
		new_head.prev = tail;
		tail = new_head;
		current_size += 1;
		return;
	}

	public Node poll(){
		//add print method as per req
		if (current_size==0){
			//System.out.println("QueueUnderflowError");
			return null;
		}
		Node temp_head = head;
		head = head.next;
		if(head!=null){head.prev = null;}
		current_size -= 1;
		if(current_size == 0){tail = null;}
		return temp_head;
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

	public Node peek(){
		if (head==null){
			//System.out.println("EMPTY");
			return null;
		}
		return head;
	}
}

