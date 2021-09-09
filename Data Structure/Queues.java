//follows standard and non repetitive methodology
//JEP standards later.
//Exception search not used (adds overhead)...instead null point and shell feedback

class Main{
	public static void main(String[] args){
		Queues queue = new Queues(4);
		queue.push(2039);
		queue.push(100);
		queue.push(200);
		queue.push(300);
		System.out.println(queue.peek());
		queue.poll();
		queue.push(400);
		queue.print_queue();
		queue.push(1293);
		queue.poll();
		queue.poll();
		queue.poll();
		queue.poll();
		queue.poll();
		}
}

class Queues<T>{
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
		T data;
		Node next;
		Node (T to_store){
			this.data = to_store;
			this.next = null;
		}
	}

	Node head = null; //stores the intermeddiate head
	Node tail = null; //Queues tail

	public void push(T element){
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

	public T poll(){
		//add print method as per req
		if (current_size==0){
			System.out.println("QueueUnderflowError");
			return null;
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

	public T peek(){
		if (head==null){
			System.out.println("Empty Stack");
			return null;
		}
		return head.data;
	}
}

