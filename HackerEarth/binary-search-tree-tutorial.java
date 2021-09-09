//https://www.hackerearth.com/practice/data-structures/trees/binary-search-tree/tutorial/
//Anindya Prithvi
//AC

import java.util.Scanner;


class Main{
	public static void main(String[] args) {
		BinaryTree experiment = new BinaryTree();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while(n-->0){
			experiment.push(new BinaryTree.Node(sc.nextInt()));
		}

		experiment.PreOrderTraversal(experiment.find(experiment.getHead(),sc.nextInt()));
	}
}


class BinaryTree{
	//current functions, inorder, preorder, postorder, levelorder
	//push method
	static class Node{
		int data; //add possibility of generic data or modify acc needs.
		Node left,right;
		Node (int data_self){
			data = data_self;
			left = null;
			right = null;
		}
	}

	//method nomenclature
	//starting a Tree, just create an instance
	//no insertion algorithm yet
	Node head; //stores the intermeddiate tree
	int size = 0;

	public Node find(Node top, int e){
		if(e>top.data){
			if (top.right!=null){
				return find(top.right, e);
			}
			return null;
		}
		else if(e<top.data){
			if (top.left!=null){
				return find(top.left, e);
			}
			return null;
		}
		else{
			return top;
		}
	}

	public Node getHead(){
		return head;
	}

	public void push(Node element){

		size++;
		if (head == null){head = element; return;}
		Node temp = head;
		while(temp!=null){
			if(temp.data<element.data){
				if (temp.right==null){
					temp.right = element;
					return;
				}
				else{
					temp = temp.right;
				}
			}
			else{
				if (temp.left==null){
					temp.left = element;
					return;
				}
				else{
					temp = temp.left;
				}
			}
		}
		return;
		
	}

	public void PreOrderTraversal(Node top){
		System.out.print(top.data+"\n");
		if (top.left!=null){
			PreOrderTraversal(top.left);
		}
		if (top.right!=null){
			PreOrderTraversal(top.right);
		}
		return;
	}
}