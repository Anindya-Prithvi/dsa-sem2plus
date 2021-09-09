//very sleek implementation standard
//use JEP later.

class Main{
	public static void main(String[] args){
		GStacks stack = new GStacks(3);
		stack.push(new Node('a'));
		stack.push(new Node('b'));
		stack.push(new Node('c'));
		stack.push(new Node('d'));
		Node k = stack.pop();
		System.out.println(k);
		stack.print_stack();
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

	public void push(Node element){
		if (current_size > size){
			System.out.println("StackOverflowError");
			return ;
		}
		Node new_head = element;
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
			System.out.println("StackUnderflowError");
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
			rev.push(new Node(pop().data));
		}
		head = rev.head;
		current_size = rev.current_size;
		return;
	}
}

