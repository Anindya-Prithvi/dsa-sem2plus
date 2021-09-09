import java.util.Scanner;

class Main{
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Stacks deez_stack = new Stacks(n);
        for (int i = 0; i < n; i++) {
            String str = scanner.next();
            if (str.charAt(0) == '+' || str.charAt(0) == '-' || str.charAt(0) == '*' || str.charAt(0) == '/') {
                int x = Stacks.pop(deez_stack);
                int y = Stacks.pop(deez_stack);
                if (str.charAt(0) == '+') {
                    Stacks.push(deez_stack, y + x);
                } else if (str.charAt(0) == '-') {
                    Stacks.push(deez_stack, y - x);
                } else if (str.charAt(0) == '*') {
                    Stacks.push(deez_stack, y * x);
                } else {
                    Stacks.push(deez_stack, y / x);
                }

            } else {
                Stacks.push(deez_stack, Integer.valueOf(str));
            }

        }
        System.out.println(Stacks.pop(deez_stack));
		

	}
}

class Stacks{
	//push
	//pop
	//head
	//traverse?
	//this is an infinite stack

	static int size = 0;
	static int current_size = 0;

	public Stacks(int size_of_stack){
		size = size_of_stack;
	}

	Node head = null; //stores the intermeddiate head
	static class Node{
		int data;
		Node next;
		Node (int to_store){
			data = to_store;
			next = null;
		}
	}

	public static void push(Stacks old, int element){
		if (old.current_size == old.size){
			System.out.println("StackOverflowError");
			return ;
		}
		Node new_head = new Node(element);
		if (old.head == null){
			old.head = new_head;
			old.current_size = 1;
			return;
		}
		//new element pushes element backwards.

		new_head.next = old.head;
		old.head = new_head;
		old.current_size += 1;
		return;
	}

	public static int pop(Stacks old){
		//add print method as per req
		if (old.current_size==0){
			System.out.println("StackUnderflowError");
			return Integer.MIN_VALUE;
		}
		Node temp_head = old.head;
		old.head = old.head.next;
		old.current_size -= 1;
		return temp_head.data;
	}

	public static void print_stack(Stacks old){
		if (old.head == null){
			System.out.println();
			return;
		}
		Node temp_head = old.head;
		do {
			System.out.print(temp_head.data + " ");
			temp_head = temp_head.next;
		}
		while (temp_head != null);
		return;
	}

	public static int peek(Stacks old){
		if (old.head==null){
			System.out.println("Empty Stack");
			return Integer.MIN_VALUE;
		}
		return old.head.data;
	}
}

