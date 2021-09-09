import java.io.*;
//dont use anymore
//poor use of static vars

class Main{
	public static void main(String[] args){
		Stacks stack = new Stacks(3);
		stack.push(100);
		stack.push(100);
		stack.push(100);
		stack.push(100);
		Object k = stack.pop();
		System.out.println(k);
		stack.print_stack();
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
			System.out.print(temp_head.data + " ");
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