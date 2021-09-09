import java.io.*;

public class DoublyLinkedList{
	//method nomenclature
	//insert, delete (to/from start)
	//display

	Node head; //stores the intermeddiate head
	static class Node{
		int storage;
		Node next, prev;
		Node (int to_store){
			storage = to_store;
			next = null;
			prev = null;
		}
	}

	public static void main(String[] args){
		System.out.println("hehe");
		DoublyLinkedList sllob1 = new DoublyLinkedList();
		System.out.println("hehe");
		//inserting 6 elements
		sllob1 = insert(sllob1, 54);
		System.out.println("hehe");
		sllob1 = insert(sllob1, 64);
		System.out.println("hehe");
		sllob1 = insert(sllob1, 74);
		sllob1 = insert(sllob1, 84);
		sllob1 = insert(sllob1, 94);
		sllob1 = insert(sllob1, 104);
		System.out.println("hehe");
		//traversal
		Node temp_node = sllob1.head;
		while(temp_node!=null){
			System.out.print(temp_node.storage+" ");
			temp_node = temp_node.next;
		}
		sllob1 = remove(sllob1, 3); //deinserting 74
		System.out.println();
		temp_node = sllob1.head;
		while(temp_node!=null){
			System.out.print(temp_node.storage+" ");
			temp_node = temp_node.next;
		}
	}

	public static DoublyLinkedList insert(DoublyLinkedList list, int to_store){
		//check if list is empty
		Node input = new Node(to_store);
		if (list.head == null){
			//send package
			list.head = input;
		}
		else{
			//add to beginning
			Node temp = list.head;
			list.head = input;
			input.next = temp;
			temp.prev = input;
		}
		return list;
	}

	public static DoublyLinkedList remove(DoublyLinkedList list, int position){
		//assuming 0 index at head
		if (position==0){
			//can't deinsert if list is empty, kinda obvious lol
			// it can for sure make an LL empty
			list.head = list.head.next;
			list.head.prev = null;
			return list;
		}
		else{
			Node temp_node = list.head;
			for(int i = 0;;i++){
				if (temp_node.next==null){
					break;
				}
				if (i == position){
					temp_node.prev.next = temp_node.next;
					temp_node.next.prev = temp_node.prev.next;
					break;
				}
				temp_node = temp_node.next;
			}
		}
		return list;
	}
}