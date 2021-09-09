//follows standard and non repetitive methodology
//JEP standards later.
//Exception search not used (adds overhead)...instead null point and shell feedback

import java.util.Scanner;
class Main{
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		String string = sc.next();
		int n = string.length();
		GQueues queue = new GQueues(n);
		for(int i = 0; i<n; i++){
			queue.push(new Node(string.charAt(i)));
		}
		
		Node nice = queue.peek();
		while(nice!=null){
			if(queue.membership(nice.data)){nice = nice.next; continue;}
			else{
				System.out.println("YES");
				System.out.println(nice.data);
				break;
			}

		}
		if(nice==null){System.out.println("NO");}


	}
}

class Node{
		char data;
		Node next;
		Node (char to_store){
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

	public boolean membership(char data){
		if(current_size == 0){return false;}
		int count = 0;
		Node temp = head;
		while(temp!=null){
			if(count>1){break;}
			if(temp.data == data){count++;}
			temp = temp.next;
		}
		
		if(count>1){return true;}
		return false;
	}

	public void push(Node element){
		if (current_size == size){
			System.out.println("QueueOverflowError");
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
		tail = new_head;
		current_size += 1;
		return;
	}

	public Node poll(){
		//add print method as per req
		if (current_size==0){
			System.out.println("QueueUnderflowError");
			return null;
		}
		Node temp_head = head;
		head = head.next;
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
			System.out.println("Empty Stack");
			return null;
		}
		return head;
	}
}

