import java.io.*;
import java.util.Scanner;

class SinglyLinkedList{
	//method nomenclature
	//starting an LL, just create an instance
	//adding to end of ll (chain)
	//just has chain dechain atm, depends on lab what i need
	static SinglyLinkedList secone;
	static Node vtemp_node;
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

	public static void yeet(SinglyLinkedList list){
		if (vtemp_node!=null){
			//System.out.println("THIS");
			System.out.print(vtemp_node.storage+" ");
			vtemp_node = vtemp_node.next;
		}
		if (list.head.next==null){
			//System.out.println("HERE");
			System.out.print(list.head.storage+" ");
		}
		else{
			int k  = list.head.storage;
			list.head = list.head.next;
			yeet(list);
			System.out.print(k+" ");
		}
	}
	public static void main(String[] args){
		SinglyLinkedList sec1 = new SinglyLinkedList();
		SinglyLinkedList sec2 = new SinglyLinkedList();
		sec1 = chain_init(sec1);
		secone = sec1;
		sec2 = chain_init(sec1);
		vtemp_node = secone.head;
		yeet(sec2);
	}

	public static SinglyLinkedList chain_init(SinglyLinkedList list){
		Node input = new Node(in.nextInt());
		list.head = input;
		Node temp_node = list.head;
		int nexi = in.nextInt();
		while(nexi!=-1){
			input = new Node(nexi);
			nexi = in.nextInt();
			temp_node.next = input;
			temp_node = temp_node.next;
		}
		return list;
	}
}