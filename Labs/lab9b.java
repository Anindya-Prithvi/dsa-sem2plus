import java.util.Scanner;

class Main{
  public static void main(String[] args) {
    BinaryTree experiment = new BinaryTree();
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    while(n-->0){
      String op = sc.next();
      if (op.equals("i")){experiment.insert(sc.nextInt());}
      else if(op.equals("d")){experiment.kill(sc.nextInt());};
    }
    
    experiment.InOrderTraversal(experiment.getHead());
  }
}


class BinaryTree{
  int lr=0;
  int rr=0;

  public void InOrderTraversal(Node top){
    //pseudo
    if (top==null){return;}
    if (top.left!=null){
      //head to tree as head left
      InOrderTraversal(top.left);
    }
    System.out.print((top.life==true)?top.data+" ":"");
    if (top.right!=null){
      //head to tree as head right (eventually may reach the left most)
      InOrderTraversal(top.right);
    }
    return;
  }

  static class Node{
    int data; 
    boolean life;
    Node left,right;
    int height,bf;
    Node (int data_self){
      data = data_self;
      left = null;
      life = true;
      right = null;
      height = 0; //leaf (currently)
      bf = 0; //obv leaf (currently)
    }
  }

  Node head; 
  int size = 0;

  public Node getHead(){
    return head;
  }

  public int height(Node r){
    if(r==null){return 0;}
    return Math.max(1+height(r.left), 1+height(r.right));
  }

  public int heightNode(Node r){
    return r.height;
  }

  // manual failsafe
  public int getBalanceFactor(Node b){
    return height(b.right)-height(b.left);  
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
    if (top==null){return null;}
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

  public void kill(int e){
      Node dest = find(head, e);
      if (dest==null){return;}
      else{dest.life = false;}
      //death.
      return;
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

    
    public void insert(int value) {
      size++;
      Node thereq = find(head, value);
      // balancing can decapicitate a tree
        if (thereq==null){
          head = insert(head, value);
        }
        else{thereq.life=true;} //rebirth

        return;
    }

    
    private Node insert(Node element, int value) {
      //go indepth, set height 1, return, update adds 1
      if (element == null){return new Node(value);}
      if (value-element.data < 0) {
        element.left = insert(element.left, value);
      } 
      else if(value-element.data>0){
        element.right = insert(element.right, value);
      }

      update(element); //height
      return balance(element);
    }

  
    private void update(Node element) {

      int lnh = (element.left == null) ? -1 : element.left.height;
      int rnh = (element.right == null) ? -1 : element.right.height;

      element.height = 1 + ((lnh > rnh)?lnh:rnh);
      element.bf = rnh - lnh;
    }

  
    private Node balance(Node element) {
      if (element.bf == -2) {
          if (element.left.bf <= 0) {
            return leftLeftCase(element);
          } 
          else {
            return leftRightCase(element);
          }
        }
    else if (element.bf == +2) {
        if (element.right.bf >= 0) {
            return rightRightCase(element);
          }
          else {
            return rightLeftCase(element);
          }
      }
      else{
        return element;
      }
    }
    //LL,LR,RR,RL on element of bst
    private Node leftLeftCase(Node element) {
      return rightRotation(element);
    }

    private Node leftRightCase(Node element) {
      element.left = leftRotation(element.left);
      return leftLeftCase(element);
    }

    private Node rightRightCase(Node element) {
      return leftRotation(element);
    }

  private Node rightLeftCase(Node element) {
      element.right = rightRotation(element.right);
      return rightRightCase(element);
    }

    private Node rightRotation(Node element) {
      rr++;
      Node parN = element.left;
      element.left = parN.right;
      parN.right = element;
      update(element);
      update(parN);
      return parN;
    }

  private Node leftRotation(Node element) {
    lr++;
      Node parN = element.right;
      element.right = parN.left;
      parN.left = element;
      update(element);
      update(parN);
      return parN;
    }
}
