import java.io.*;
import java.util.Scanner;

class SinglyLinkedList{
	//method nomenclature
	//starting an LL, just create an instance
	//adding to end of ll (chain)
	//just has chain dechain atm, depends on lab what i need

	Node head; //stores the intermeddiate head
	static class Node{
		int storage;
		Node next;
		Node (int to_store){
			storage = to_store;
			next = null;
		}
	}
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args){
		int n = in.nextInt(); //number of songs, reimplement chain
		int m = in.nextInt(); //number of operations
		//incoming song queue
		//make Linkedlistinstance
		SinglyLinkedList song_queue = new SinglyLinkedList();
		song_queue = chain_init(song_queue, n);
		//N,M  =  size, operation
		//queries
		while(m-->0){
			String opcode = in.next();
			//System.out.println(opcode);
			if (opcode.equals("I")){
				//System.out.println("here");
				song_queue = insert(song_queue, in.nextInt()-1, in.nextInt());
			}
			else{
				song_queue = dechain(song_queue, in.nextInt(), in.nextInt());
			}
		}
		Node temp_node = song_queue.head;
		while(temp_node!=null){
			System.out.print(temp_node.storage+" ");
			temp_node = temp_node.next;
		}
		if (song_queue.head == null){
			System.out.println(-1);
		}
	}

	public static SinglyLinkedList insert(SinglyLinkedList list , int position, int songid){
		Node to_add = new Node(songid);
		if (position==0){
			//can't dechain if list is empty, kinda obvious lol
			// it can for sure make an LL empty
			to_add.next = list.head.next;
			list.head.next = to_add;
			return list;
		}
		else{
			Node remember = list.head;
			Node temp_node = list.head;
			for(int i = 0;;i++){
				if (temp_node==null){
					break;
				}
				if (i == position){
					to_add.next = temp_node.next;
					temp_node.next = to_add;
					break;
				}
				temp_node = temp_node.next;
			}
		}
		return list;
	}


	public static SinglyLinkedList chain_init(SinglyLinkedList list,int size){

		Node input = new Node(in.nextInt());
		list.head = input;
		size--;
		Node temp_node = list.head;
		while(size-->0){
			input = new Node(in.nextInt());
			temp_node.next = input;
			temp_node = temp_node.next;
		}
		return list;
	}

	public static SinglyLinkedList dechain(SinglyLinkedList list, int id, int instance){
		//assuming 0 index at head
		//instance=0; from first
		//instance=1; from last
		Node remember = null;
		Node temp_node = list.head;
		boolean redflag = false;
		if ((list.head.storage == id)&&(instance==0)){
			list.head=list.head.next;
			return list;
		}
		else if ((list.head.storage == id)){
			redflag = true;
		}
		for(int i = 0;;i++){
			if (temp_node.next==null){
				break;
			}
			if (temp_node.next.storage == id){
				if (instance==0){
					temp_node.next = temp_node.next.next;
					return list;
				}
				else{
					redflag=false;
					remember = temp_node;
				}
			}
			temp_node = temp_node.next;
		}
		if (redflag){
			list.head = list.head.next;
			return list;
		}
		if (remember == null){
			return list;
		}
		remember.next= remember.next.next;
		return list;
	}
}