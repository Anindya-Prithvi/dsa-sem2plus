//https://www.hackerearth.com/practice/data-structures/trees/binary-search-tree/practice-problems/algorithm/monk-and-his-friends/
//PLAGIARIZED QUESTION LOL, here's my old answer :) 
//Anindya Prithvi
//AC

//incomplete stupid ques

import java.util.Scanner;


class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long t = sc.nextLong();
		BinaryTree.Node x;
		while(t-->0){
			BinaryTree experiment = new BinaryTree();
			long n = sc.nextLong();
			long m = sc.nextLong();
			while(n-->0){experiment.push(new BinaryTree.Node(sc.nextLong()));}
			while(m-->0){
				long tr = sc.nextLong();
				
				x = experiment.find(experiment.getHead(),tr);

				if (x!=null){
					System.out.println("YES");
				}
				else{
					System.out.println("NO");
				}
				experiment.push(new BinaryTree.Node(tr));
			}
			
		}

		
	}
}


class BinaryTree{
	//current functions, inorder, preorder, postorder, levelorder
	//push method
	static class Node{
		long data; //add possibility of generic data or modify acc needs.
		Node left,right;
		Node (long data_self){
			data = data_self;
			left = null;
			right = null;
		}
	}

	//method nomenclature
	//starting a Tree, just create an instance
	//no insertion algorithm yet
	Node head; //stores the longermeddiate tree
	long size = 0;

	public Node find(Node top, long e){
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
}