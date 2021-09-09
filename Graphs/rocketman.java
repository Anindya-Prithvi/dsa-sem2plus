// Anindya Prithvi
//very slow
import java.util.Scanner;
import java.util.ArrayList;

class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		ArrayList<Integer> arr = new ArrayList<>(t);
		while(t-->0){
			String opc = sc.next();
			int v = sc.nextInt();
			if(opc.equals("r")){
				boolean bb = arr.remove((Integer) v);
				if (bb==false){System.out.println("Wrong!");}
				else{media(arr);}
			}
			if(opc.equals("a")){
				arr.add(v);
				media(arr);
			}
		}
	}
	public static void media(ArrayList<Integer> arr){
		if(arr.size()==0){
			System.out.println("Wrong!");return;
		}
		if(arr.size()%2==1){
			System.out.println(arr.get(arr.size()/2));
		}
		else{
			System.out.println((double)(arr.get(arr.size()/2)+arr.get(arr.size()/2-1))/2);
		}
	}
}
