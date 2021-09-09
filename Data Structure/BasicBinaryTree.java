//node is generic

class Main{
	public static void main(String[] args) {
		BinaryTree experiment = new BinaryTree();
		experiment.head = new BinaryTree.Node(1);
		BinaryTree.Node primary_head = experiment.head;

		experiment.push(new BinaryTree.Node(0));
		experiment.push(new BinaryTree.Node(1));
		experiment.push(new BinaryTree.Node(2));
		experiment.push(new BinaryTree.Node(3));
		experiment.push(new BinaryTree.Node(4));
		experiment.push(new BinaryTree.Node(5));
		experiment.push(new BinaryTree.Node(6));
		experiment.push(new BinaryTree.Node(7));
		experiment.push(new BinaryTree.Node(8));
		experiment.push(new BinaryTree.Node(9));
		experiment.push(new BinaryTree.Node(10));
		experiment.push(new BinaryTree.Node(11));
		experiment.push(new BinaryTree.Node(12));
		//
		experiment.PostOrderTraversal(primary_head);
		System.out.print("\n");
		
		experiment.PreOrderTraversal(primary_head);
		
		System.out.print("\n");
		experiment.InOrderTraversal(primary_head);
		
		System.out.print("\n");
		experiment.LevelOrderTraversal();
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

	public Node getHead(){
		return head;
	}

	public Node min(Node top){
		while(top.left!=null){
			top = top.left;
		}
		return top;
	}

	public Node max(Node top){
		while(top.right!=null){
			top = top.right;
		}
		return top;
	}

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

	public Node parentof(Node top, int e){
		if(e>top.data){
			if (top.right!=null){
				if (top.right.data==e){return top;}
				return parentof(top.right, e);
			}
			return null;
		}
		else if(e<top.data){
			if (top.left!=null){
				if (top.left.data==e){return top;}
				return parentof(top.left, e);
			}
			return null;
		}
		else{
			return null;
		}

	}

	public Node delete(int e){
		Node to_del = parentof(head, e);
		if(to_del == null){return null;}
		//case 1,{2,3},4
		//find data
		return to_del;
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

	public void InOrderTraversal(Node top){
		//pseudo
		if (top.left!=null){
			//head to tree as head left
			InOrderTraversal(top.left);
		}
		System.out.print(top.data+" ");
		if (top.right!=null){
			//head to tree as head right (eventually may reach the left most)
			InOrderTraversal(top.right);
		}
		return;
	}

	public void PreOrderTraversal(Node top){
		System.out.print(top.data+" ");
		if (top.left!=null){
			PreOrderTraversal(top.left);
		}
		if (top.right!=null){
			PreOrderTraversal(top.right);
		}
		return;
	}
	public void PostOrderTraversal(Node top){
		if (top.left!=null){
			PostOrderTraversal(top.left);
		}
		if (top.right!=null){
			PostOrderTraversal(top.right);
		}
		System.out.print(top.data+" ");
		return;
	}
	public void LevelOrderTraversal(){
		//essentially BFS, using Queues
		Node root = head;
		GQueues current = new GQueues(Integer.MAX_VALUE);
		GQueues next = new GQueues(Integer.MAX_VALUE);
		current.push(new GQueues.NodeQ(root));
		boolean flag = true;
		while(flag){
			if(current.isEmpty()){break;}
			current.print_queue();
			while(!current.isEmpty()){
				GQueues.NodeQ pulled = current.poll();
				if(pulled.data.left!=null){next.push(new GQueues.NodeQ(pulled.data.left));};
				if(pulled.data.right!=null){next.push(new GQueues.NodeQ(pulled.data.right));};
				//System.out.println(next.current_size);
			}
			current.head = next.head;
			current.current_size = next.current_size;
			current.tail = next.tail;
			next = new GQueues(Integer.MAX_VALUE);
		}
		
	}
}

class GQueues{
	static class NodeQ{
		BinaryTree.Node data;
		NodeQ next;
		NodeQ (BinaryTree.Node data){
			this.data = data;
			next = null;
		}
	}
	//push
	//pop
	//head
	//traverse?
	//this is an infinite queue

	int size = 0;
	int current_size = 0;

	public GQueues(int size_of_stack){
		size = size_of_stack;
	}

	NodeQ head = null; //stores the intermeddiate head

	NodeQ tail = null; //GQueues' tail

	public void push(NodeQ element){
		if (current_size == size){
			System.out.println("QueueOverflowError");
			return ;
		}
		NodeQ new_head = element;
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

	public NodeQ poll(){
		//add print method as per req
		if (current_size==0){
			System.out.println("QueueUnderflowError");
			return null;
		}
		NodeQ temp_head = head;
		head = head.next;
		current_size -= 1;
		if(current_size == 0){tail = null;}
		return temp_head;
	}

	public void print_queue(){
		if (head == null){
			System.out.println();
			return;
		}
		NodeQ temp_head = head;
		do {
			System.out.print(temp_head.data.data + " ");
			temp_head = temp_head.next;
		}
		while (temp_head != null);
		return;
	}

	public NodeQ peek(){
		if (head==null){
			System.out.println("Empty Stack");
			return null;
		}
		return head;
	}

	public boolean isEmpty(){
		if(current_size == 0){
			return true;
		}
		return false;
	}
}

