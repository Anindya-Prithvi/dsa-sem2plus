//fun-game-91510e9f
//https://www.hackerearth.com/practice/data-structures/stacks/basics-of-stacks/practice-problems/algorithm/fun-game-91510e9f/

//Anindya Prithvi
//implementation using cpp inbuilt stacks AC, JAVA fail (java slow)
import java.util.Scanner;
class Main{
	public static void main(String[] args){
		GStacks stack = new GStacks(3);
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		GStacks a = new GStacks(n); //straight stack
		GStacks b = new GStacks(n); //reverse stack
		in.nextLine();
		String tokens[]= in.nextLine().split(" ");
		for(int i = 0; i<n; i++){
			b.push(Integer.parseInt(tokens[i]));
		}
		Node temp_head = b.head;
		while(temp_head!=null){
			a.push(temp_head.data);
			temp_head = temp_head.next;
		}

		while(true){
			Node t1 = a.peek();
			Node t2 = b.peek();
			if(t1==null){break;}
			if(t2==null){break;}
			if(t1.data>t2.data){System.out.print(1+" "); b.pop();}
			if(t1.data<t2.data){System.out.print(2+" "); a.pop();}
			if(t1.data==t2.data){System.out.print(0+" "); a.pop(); b.pop();}
		}
	}
}

class Node{
		int data;
		Node next;
		Node (int to_store){
			data = to_store;
			next = null;
		}
	}

class GStacks{
	//push
	//pop
	//head
	//traverse?
	//this is an infinite stack

	 int size = 0;
	 int current_size = 0;

	public GStacks(int size_of_stack){
		size = size_of_stack;
	}

	Node head = null; //stores the intermeddiate head

	public void push(int element){
		if (current_size > size){
			System.out.println("StackOverflowError");
			return ;
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

	public Node pop(){
		//add print method as per req
		if (current_size==0){
			//System.out.println("StackUnderflowError");
			return null;
		}
		Node temp_head = head;
		head = head.next;
		current_size -= 1;
		return temp_head;
	}

	public void print_stack(){
		if (head == null){
			System.out.println();
			return;
		}
		Node temp_head = head;
		do {
			System.out.print(temp_head.data + "");
			temp_head = temp_head.next;
		}
		while (temp_head != null);
		return;
	}

	public Node peek(){
		if (head==null){
			//System.out.println("Empty Stack");
			return null;
		}
		return head;
	}

	public void reverse(){
		GStacks rev = new GStacks(size);
		int n = current_size;
		while(n-->0){
			rev.push(pop().data);
		}
		head = rev.head;
		current_size = rev.current_size;
		return;
	}
}

