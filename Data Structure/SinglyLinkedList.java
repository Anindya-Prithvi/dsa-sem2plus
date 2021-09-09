import java.io.*;

public class SinglyLinkedList{
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

	public static void main(String[] args){
		System.out.println("hehe");
		SinglyLinkedList sllob1 = new SinglyLinkedList();
		System.out.println("hehe");
		//chaining 6 elements
		chain(sllob1, 54);
		System.out.println("hehe");
		chain(sllob1, 64);
		System.out.println("hehe");
		chain(sllob1, 74);
		chain(sllob1, 84);
		chain(sllob1, 94);
		chain(sllob1, 104);
		System.out.println("hehe");
		//traversal
		Node temp_node = sllob1.head;
		while(temp_node!=null){
			System.out.print(temp_node.storage+" ");
			temp_node = temp_node.next;
		}
		sllob1 = dechain(sllob1, 3); //dechaining 84
		System.out.println();
		temp_node = sllob1.head;
		while(temp_node!=null){
			System.out.print(temp_node.storage+" ");
			temp_node = temp_node.next;
		}
	}

	public static SinglyLinkedList chain(SinglyLinkedList list, int to_store){
		//this function yeets another SLL
		//check if list is empty
		Node input = new Node(to_store);
		if (list.head == null){
			//send package
			list.head = input;
		}
		else{
			//traverse LL --> O(n)
			Node temp_node = list.head;
			while(true){
				if (temp_node.next == null){
					temp_node.next = input;
					break;
				}
				else{
					temp_node = temp_node.next;
				}
			}
		}
		return list;
	}

	public static SinglyLinkedList dechain(SinglyLinkedList list, int position){
		//assuming 0 index at head
		if (position==0){
			//can't dechain if list is empty, kinda obvious lol
			// it can for sure make an LL empty
			list.head = list.head.next;
			return list;
		}
		else{
			Node remember = list.head;
			Node temp_node = list.head;
			for(int i = 0;;i++){
				if (temp_node.next==null){
					break;
				}
				if (i == position-1){
					remember = temp_node;
				}
				if (i == position){
					remember.next = temp_node.next;
					break;
				}
				temp_node = temp_node.next;
			}
		}
		return list;
	}
}