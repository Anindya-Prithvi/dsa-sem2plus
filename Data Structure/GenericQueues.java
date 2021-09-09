//follows standard and non repetitive methodology
//JEP standards later.
//Exception search not used (adds overhead)...instead null point and shell feedback

class Main{
	public static void main(String[] args){
		GQueues queue = new GQueues(4);
		queue.push(new Node(2039));
		queue.push(new Node(100));
		queue.push(new Node(200));
		queue.push(new Node(300));
		System.out.println(queue.peek());
		queue.poll();
		queue.push(new Node(400));
		queue.print_queue();
		queue.push(new Node(1293));
		queue.poll();
		queue.poll();
		queue.poll();
		queue.poll();
		queue.poll();
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

