import java.util.Scanner;
import java.util.ArrayList;

class Solution{
	static class Fraction{
		long x;
		long y;
		public Fraction(int x, int y){
			this.x = x;
			this.y = y;
		}
		public long gcd(long a, long b){
   			if (b==0) return a;
   			return gcd(b,a%b);
		}
		public void simplify(){
			long g = gcd(x,y);
			if (g==1) return;
			x = x/g;
			y = y/g;
		}
		public void add(Fraction another){
			long num = this.x*another.y + another.x*this.y;
			long den = this.y*another.y;
			x = num;
			y = den;
			simplify();
		}
		public void negate(){
			this.x = -x;
		}
		public Fraction plusone(){
			this.x = this.x+this.y;
			simplify();
			return this;
		}
	}
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter rows:\t");
        int r = sc.nextInt();
        System.out.println("Enter columns:\t");
        int c = sc.nextInt();
        int[][] put = new int[r][c];
        for(int j=0; j<r; j++){
        	for(int i=0; i<c; i++){
        		put[j][i] = sc.nextInt();
        	}
        }
        int[] arr = solution(put);
        for(int i:arr){
        	System.out.print(i+" ");
        }
		sc.close();
    }

    public static int[] solution(int[][] m) {
    	int r = m.length;
    	int sum = 0;
    	ArrayList<Integer> qindx = new ArrayList<Integer>();
    	ArrayList<Integer> nindx = new ArrayList<Integer>();
    	Fraction[][] opmat = new Fraction[r][r];
    	for(int i = 0; i<r; i++){
    		for(int j=0; j<r; j++){
    			sum+=m[i][j];
    		}
    		if(sum!=0){
    			qindx.add(i); //add transition row name
    		}
    		else{
    			nindx.add(i); //add terminal row name
    		}
    		if(sum==0){sum=1;}
    		for(int j=0; j<r; j++){
    			opmat[i][j] = new Fraction(m[i][j], sum);
    		}
    		sum=0;
    	}
		System.out.println();
    	Fraction[][] topmat = transform(opmat, qindx, nindx);
		for(int i=0; i<qindx.size(); i++){
			for(int j=0; j<r; j++){
				System.out.print(topmat[i][j].x+"\t");
			}
			System.out.println();
		}
		//handle state 0 as terminal
		if(qindx.size()==0){
			int[] initer = new int[r+1];
			initer[0] = 1;
			initer[initer.length-1] = 1;
			return initer;
		}
		System.out.println();
    	return m[0];//no dont lol
    }

    public static Fraction[][] transform(Fraction[][] given, ArrayList<Integer> qidx, ArrayList<Integer> nidx){
    	ArrayList<Integer> corres = new ArrayList<Integer>();
    	for(int i:qidx) corres.add(i);
    	for(int i:nidx) corres.add(i); //now corres has new ordering
		int mtr_dim = corres.size();
    	Fraction[][] topmat = new Fraction[qidx.size()][mtr_dim];
		for(int i=0; i<qidx.size(); i++){
			for(int j=0; j<mtr_dim; j++){
				topmat[i][j]=given[corres.get(i)][corres.get(j)];
				if((i<qidx.size())&&(j<qidx.size())) topmat[i][j].negate();
				if(i==j) topmat[i][j].plusone();
			}
		}
    	return topmat;
    }
}