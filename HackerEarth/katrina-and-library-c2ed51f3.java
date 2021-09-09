import java.util.Scanner;
//katrina-and-library-c2ed51f3
//https://www.hackerearth.com/practice/data-structures/stacks/basics-of-stacks/practice-problems/algorithm/katrina-and-library-c2ed51f3/

//AC

class TestClass{
	public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String inp = sc.next();
        int len = inp.length();
        GStacks stack = new GStacks(len);
        GStacks auxstack = new GStacks(len-2);
        //System.out.println(auxstack.size);
		for(int i = 0; i<len; i++){
			
			if(inp.charAt(i)=='\\'){
				for(int j=i ; j>0 ; j--){
					
					Node el = GStacks.pop(stack);
					if(el.data=='/'){break;}
					GStacks.push(auxstack,el.data);
					
				}
				//
				//System.out.println(auxstack.current_size);
				GStacks.reverse(auxstack);

				while(GStacks.peek(auxstack)!=null){
					
					Node el = GStacks.pop(auxstack);
					GStacks.push(stack,el.data);

				}
				//GStacks.reverse(stack);
				continue;
			}
			GStacks.push(stack,inp.charAt(i));

			//System.out.println(stack.current_size);
			
		}
		GStacks.reverse(stack);
		GStacks.print_stack(stack);
		
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

	public static void push(GStacks old, char element){
		if (old.current_size > old.size){
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

	public static Node pop(GStacks old){
		//add print method as per req
		if (old.current_size==0){
			System.out.println("StackUnderflowError");
			return null;
		}
		Node temp_head = old.head;
		old.head = old.head.next;
		old.current_size -= 1;
		return temp_head;
	}

	public static void print_stack(GStacks old){
		if (old.head == null){
			System.out.println();
			return;
		}
		Node temp_head = old.head;
		do {
			System.out.print(temp_head.data + "");
			temp_head = temp_head.next;
		}
		while (temp_head != null);
		return;
	}

	public static Node peek(GStacks old){
		if (old.head==null){
			//System.out.println("Empty Stack");
			return null;
		}
		return old.head;
	}

	public static void reverse(GStacks old){
		GStacks rev = new GStacks(old.size);
		int n = old.current_size;
		while(n-->0){
			GStacks.push(rev, GStacks.pop(old).data);
		}
		old.head = rev.head;
		old.current_size = rev.current_size;
		return;
	}
}

