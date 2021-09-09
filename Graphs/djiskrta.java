// Anindya Prithvi
// working dj skirt
// currently undirected, update line 53,54 as per need
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

class NodeWE{
    int weight;
    int destination;
    public NodeWE(int d, int w){
        weight = w;
        destination = d;
    }
}

class Node{
    int name;
    int key;
    int path;
    public int compareTo(Node k){
        if(k==null){return 0;}
        return this.key - k.key;
    }
}

class Graph{
    int v;
    ArrayList<NodeWE>[] adj;
    Graph(int v)
        {
            this.v = v;
            adj = new ArrayList[v];
            for (int i=0; i<v; i++)
                adj[i] = new ArrayList<>();
        }
}

class Main{
    public static void main(String[] args) throws IOException{
        Reader.init(System.in);
        int n = Reader.nextInt();
        Graph graph = new Graph(n); //init graph of size n vertices
        int e = Reader.nextInt();
        while(e-->0){
            //undirected
            int n1 = Reader.nextInt()-1;
            int n2 = Reader.nextInt()-1;
            int w = Reader.nextInt();
            NodeWE nodes = new NodeWE(n2, w);
            NodeWE noded = new NodeWE(n1, w);
            graph.adj[n1].add(nodes);
            graph.adj[n2].add(noded);
        }
        int s = Reader.nextInt()-1;
        Node[] parentarr = djskirta(graph, s);
        for(Node f: parentarr){
            System.out.println("Distance from "+(s+1)+" to vertex "+(f.name+1)+" is "+f.key+" having parent "+(f.path+1));
        }
    }

    static Node[] djskirta(Graph graph, int s){
        Boolean[] visitar = new Boolean[graph.v];
        Node[] e = new Node[graph.v];
        Node[] f = new Node[graph.v];
        int[] dist = new int[graph.v];
 
        for (int o = 0; o < graph.v; o++) {
            e[o] = new Node();
            visitar[o] = false;
            e[o].key = Integer.MAX_VALUE;
            e[o].path = -1; //can store anything; usually overwritten
            e[o].name = o;
            f[o] = new Node();
            f[o].key = Integer.MAX_VALUE;
            f[o].path = -1; //can store anything; usually overwritten
            f[o].name = o;
        }

        visitar[s] = true; //root init
        e[s].key = 0;
        f[s].key = 0;
 
        PriorityQ<Node> queue = new PriorityQ<Node>();
        queue.autoHeap(e);

        while (!queue.isEmpty()) {
 
            Node p = queue.poll();
            visitar[p.name] = true; //wont visit again
            //System.out.println(p.name+" was polled");
 
            // For all adj \in V
            for (NodeWE u :graph.adj[p.name]){ 
                //System.out.println(u.destination);
                // If V \in queue
                if (visitar[u.destination] == false){
                    // for(Node i: exact){
                    //     System.out.println(i.key);
                    // }
                    //w(u,v)<key[v];
                    if (f[u.destination].key > p.key + u.weight){
                        int k = linearsearch(e, f[u.destination]);
                        f[u.destination].key = u.weight + p.key;
                        f[u.destination].path = p.name;
                        e[k].key = u.weight + p.key;
                        e[k].path = p.name;
                        queue.heap_balance(k);
                        //System.out.println(k);
                    }
                }
            }
        }
        return f;
    }

    public static int linearsearch(Node[] arr, Node e){
        for(int i=0; i<arr.length; i++){
            if(arr[i].name==e.name){return i;}
        }
        return -1;
    }
}

class PriorityQ<T> {
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

    public void push(Node element){
        //no overflow checker added yet
        if (tofill==this.size){
            resize(this.size*2);
        }
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
        //System.out.println(verify_heap_invariant()?"Success":"Failure");
    }

    private void childbalance(int k) {
        while (true) {
            int left = 2*k + 1; 
            int right = 2*k + 2; 
            int smallest = left; // Assume left tobe <
            if ((right < this.size) && (arr[right]!=null) && arr[right].compareTo(arr[left])<0){
                smallest = right;
            }
            if (left >= this.size || arr[k].compareTo(arr[smallest])<0) break;
            Node temp = arr[k];
            arr[k] = arr[smallest];
            arr[smallest] = temp;
            k = smallest;
        }
    }

    public void autoHeap(Node[] array){//heapsautte
        this.size = array.length;
        this.arr = array;
        this.tofill = this.size;
        // Heapify process, O(n)
        for (int i = Math.max(0, (this.size / 2) - 1); i >= 0; i--) childbalance(i);
    }

    public boolean verify_heap_invariant(){
        boolean flag = true;
        for(int i = 0; i < tofill/2; i++){
            if (arr[i]==null){break;}
            if( (arr[i].compareTo(arr[2*i+1])>0) || (arr[i].compareTo(arr[2*i+2])>0) ){flag = false; return flag;}
        }
        return flag;
    }

}

class Reader {
    
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
    
    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }
    
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
    
}