import java.util.Scanner;


class Main{
	public static void main(String[] args){
		Stacks deez_braket = new Stacks(Integer.MAX_VALUE);
		Scanner in = new Scanner(System.in);
		int mod = 1000000007;
		int n = in.nextInt();
		String str = in.next();
		char arr[] = str.toCharArray();
		int sum =0;
		for(int i=0; i<n;i++){
			if(arr[i]=='('){
				Stacks.push(deez_braket,-1);
			}
			else if(!Stacks.isEmpty(deez_braket) && Stacks.peek(deez_braket)==-1){
				Stacks.pop(deez_braket);
				Stacks.push(deez_braket,1);
			}
			else{
				sum =0;
				while( Stacks.peek(deez_braket)!=-1){
					sum+=Stacks.peek(deez_braket);
					Stacks.pop(deez_braket);
					sum%=mod;
				}
				Stacks.pop(deez_braket);
				Stacks.push(deez_braket,((2*sum)%mod));
			}
		}
		sum =0;
		while(Stacks.isEmpty(deez_braket)==false){
			sum+=Stacks.peek(deez_braket);
			Stacks.pop(deez_braket);
			sum%=mod;
		}
		System.out.println(sum);
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

	public static boolean isEmpty(Stacks old){
		if(old.head==null){
			return true;
		}
		return false;
	}
}

