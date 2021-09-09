//node is generic
import java.util.Scanner;
class Main{
	public static void main(String[] args) {
		BinaryTree experiment = new BinaryTree();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] arr = new long[n];
		for(int i = 0; i<n; i++){
			arr[i] = sc.nextLong();
		}
		while(n-->0){
			experiment.push(new BinaryTree.Node(arr[n]));
		}

		experiment.runner();
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

	//add all left
	//add all leaf
	//add all right

	static long cache = 0;

	public void someOrderTraversal(Node top){
		//pseudo
		if(top==null){return;}
		if (top.left!=null){
			//head to tree as head left
			someOrderTraversal(top.left);
		}
		if (top.right!=null){
			//head to tree as head right (eventually may reach the left most)
			someOrderTraversal(top.right);
		}
		if ((top.right==null)&&(top.left == null)){
			//System.out.println(top.data);
			cache+=top.data;
		}
		return;
	}

	public void lsum(Node top){
		if(top!=null){
			if (top.left!=null){
				cache+=top.data;
				//System.out.println(top.data);
				lsum(top.left);
			}
			else if(top.right!=null){
				cache+=top.data;
				//System.out.println(top.data);
				lsum(top.right);
			}
		}
	}

	public void rsum(Node top){
		if(top!=null){
			if (top.right!=null){
				cache+=top.data;
				rsum(top.right);
				//System.out.println(top.data);
			}
			else if(top.left!=null){
				cache+=top.data;
				//System.out.println(top.data);
				rsum(top.left);
			}
		}
	}

	public void runner(){
		if(head.left!=null){lsum(head.left);someOrderTraversal(head.left);}
		if(head.right!=null){rsum(head.right);someOrderTraversal(head.right);}

		cache+=head.data;
		System.out.println(cache);
	}
}