class Main{
    public static void main(String[] args) {
        HashMap nn = new HashMap();
        nn.add("iuefhniwuefneif");
        nn.add("iuefhniwuefneif");
        nn.add("iuefhniwuefneif");
        nn.add("iuefhniwuefneif");
        nn.add("iuefhniwuefneif");
        nn.add("iuefhniwuefneif");
        nn.add("oefwjiueoftgt4wegwfo");
        nn.add("oefwj34te5hofjwfo");
        nn.add("oefewwwiueofjwfo");
        nn.add("oefwjoefwefjwfo");
        nn.add("oefwefwegjiueofjwfo");
        nn.add("oefwjiueofeewgwfjwfo");
        nn.add("oefwjiuwefeofjwfo");
        System.out.println(nn.find("oefwjiuwefeofjwfo"));
    }
}

class HashMap{
    //ordered min heap
    PriorityQ hm;
    public HashMap(){
        hm = new PriorityQ();
    }
    public int hashit(String tohash){
        int hash = 10;
        for(int i = 0; i<tohash.length(); i++){
            hash = (hash+i)^tohash.charAt(i)+hash-hash/3;
        }
        return hash;
    }
    public void add(String shash){
        //use parseInt or whatever as required.
        int hash = hashit(shash);
        if(find(shash)==null){hm.push(new Node(hash, shash));return;}
        System.out.println("Be creative!");
    }
    public String find(String shash){
        Node f = hm.find(hashit(shash),0);
        if(f==null){return null;}
        f = f.find(shash);
        if(f==null){return null;}
        return f.data;
    }
}

class Node{
    String data; //generic node
    int cmp;
    Node next=null;
    Node(int cmp, String data){
        this.data = data;
        this.cmp = cmp;
    }
    //make insertion based on req
    //override cmp IFF req
    public int getT(){
        return cmp;
    }

    public String getD(){
        return data;
    }

    public int compareTo(Node o) {
        if(o==null){return 0;}
        return getT() - o.getT();
    }

    public void push(Node collision){
        System.out.println("COLLISION FOUND "+ cmp +" "+ data);
        if(next==null){
            next = collision;
        }
        else{
            next.push(collision);
        }
    }

    public Node find(String sh){
        if (this.data.equals(sh)){
            return this;
        }
        else{
            if(this.next == null){return null;}
            Node f = next.find(sh);
            return f;
        }
    }
}

class PriorityQ{
    //add collision chaining
    Node[] arr;

    int size;
    PriorityQ(){//empty queue
        this(1);
    }
    PriorityQ(int size){
        this.size = size;
        arr = new Node[size];
    }
    int tofill = 0; //the place to fill

    private void resize(int new_size){
        Node[] new_arr = new Node[new_size];
        for(int i = 0; i<this.size; i++){
            new_arr[i] = arr[i];
        }
        this.arr = new_arr;
        this.size = new_size;
    }

    public boolean isEmpty(){
        if (tofill == 0){return true;}
        return false;
    }

    public Node poll(){
        Node temp = arr[0];
        arr[0] = arr[tofill-1];
        arr[tofill-1] = null;
        tofill--;
        heap_balance(0);
        return temp;
    }

    public void print(){
        int n = tofill;
        for(Node i : arr){
            if (i==null){break;}
            if(n>0){System.out.print(i.getD()+" ");n--;continue;};
            break;
        }
        System.out.print("\n");
    }

    public Node find(int hashkey, int start_node){
        if(isEmpty()){return null;}
        if(arr[start_node].cmp > hashkey){return null;}
        if(arr[start_node].cmp == hashkey){return arr[start_node];}
        if((start_node*2+1)<tofill){return find(hashkey, start_node*2+1);}
        if((start_node*2+2)<tofill){return find(hashkey, start_node*2+2);}
        return null;
    }

    public void push(Node element){
        //no overflow checker added yet
        if (tofill==this.size){
            resize(this.size*2);
        }
        Node was_found = find(element.cmp, 0);
        if(was_found!=null){was_found.push(element);return;}
        arr[tofill] = element;
        heap_balance(tofill);
        tofill++;
        return;
    }

    public void heap_balance(int k){
        if(tofill == 0){return;}
        while(true){
            if(arr[(k-1)/2].compareTo(arr[k])>0){
                //bubble
                Node temp = arr[k];
                arr[k] = arr[(k-1)/2];
                arr[(k-1)/2] = temp;
                k = (k-1)/2;
                continue;
            }
            if(arr[k]!=null && k<((tofill)/2)){
                if((arr[2*k+1].compareTo(arr[k])<0) && (arr[2*k+1].compareTo(arr[2*k+2])<=0)){
                    Node temp = arr[2*k+1];
                    arr[2*k+1] = arr[k];
                    arr[k] = temp;
                    k = 2*k+1;
                    continue;
                }
                else if((arr[2*k+2]!=null) && (arr[2*k+2].compareTo(arr[k])<0)){
                    Node temp = arr[2*k+2];
                    arr[2*k+2] = arr[k];
                    arr[k] = temp;
                    k = 2*k+2;
                    continue;
                }
            }
            break;

        }
    }
}