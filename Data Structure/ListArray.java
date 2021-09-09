import java.util.Scanner;

class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ListArray a = new ListArray();
		int n = sc.nextInt();
		while(n-->0){
			a.add(sc.nextInt());
		}
		a.print();
	}

}

class ListArray{
	int current_size = 0;
	int filled = 0;
	private int max_size_allowable = 1024;
	//if it exceeds this, manually override
	int[] main_arr;

	public ListArray(){
		this.current_size = 0;
	}

	public ListArray(int[] arr){
		current_size = arr.length;
		main_arr = arr;
	}

	public ListArray(int override){
		max_size_allowable = override;
	}

	public void add(int element){
		if(current_size == 0){
			main_arr = new int[16];
			main_arr[0] = element;
			this.current_size = 16;
			this.filled = 1;
			return;
		}
		if(filled<current_size){
			main_arr[filled] = element;
			filled++;
			return;
		}
		if(filled==current_size){
			int t_size = Math.min(current_size<<1,max_size_allowable);
			int[] temp = new int[t_size];
			System.arraycopy(main_arr, 0, temp, 0, current_size);
			main_arr = temp;
			current_size = t_size;
			add(element);
			return;
		}
	}

	public void print(){
		for(int i=0; i<filled; i++){
			System.out.print(main_arr[i]+" ");
		}
		System.out.print("\n");
	}
}