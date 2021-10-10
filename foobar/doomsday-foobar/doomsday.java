import java.util.Scanner;
import java.util.ArrayList;

class Solution{
    static class Fraction{
        long x;
        long y;
        public Fraction(long x, long y){
            this.x = x;
            this.y = y;
            simplify();
        }
        public static long gcd(long a, long b){
               if (b==0) return a;
               return gcd(b,a%b);
        }
        public static long lcm(long a, long b){
            return a*b/gcd(a,b);
        }
        public long abs(long x){
            return (x<0)?-x:x;
        }
        public void simplify(){
            long g = gcd(abs(x),abs(y));
            if (g==1) return;
            x = x/g;
            y = y/g;
        }
        public void add(Fraction another){
            long num = this.x*another.y + another.x*this.y;
            long den = this.y*another.y;
            this.x = num;
            this.y = den;
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
        public void div(Fraction f){//this/f
            this.x = f.y*this.x;
            this.y = f.x*this.y;
            simplify();
        }
        public void sub(Fraction another){
            another.x = -another.x;
            add(another);
        }
        public Fraction mul(Fraction another){
            Fraction toret = new Fraction(this.x*another.x,this.y*another.y);
            return toret;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter rows:\t");
        int r = sc.nextInt();
        int[][] put = new int[r][r];
        for(int j=0; j<r; j++){
            for(int i=0; i<r; i++){
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
        boolean flag=false;
        for(int i = 0; i<r; i++){
            for(int j=0; j<r; j++){
                sum+=m[i][j];
            }
            if(sum!=0){
                qindx.add(i); //add transition row name
            }
            else{
                if(i==0){flag = true;}
                nindx.add(i); //add terminal row name
            }
            if(sum==0){sum=1;}
            for(int j=0; j<r; j++){
                opmat[i][j] = new Fraction(m[i][j], sum);
            }
            sum=0;
        }
        if (flag){
            int sz = nindx.size();
            int[] ans = new int[sz+1];
            for(int i=0; i<sz; i++){
                ans[i] = 0;
            }
            ans[0] = 1; ans[sz] = 1;
            return ans;
        }
        System.out.println();
        Fraction[][] topmat = transform(opmat, qindx, nindx);
        for(int i=0; i<qindx.size(); i++){
            for(int j=0; j<r; j++){
                System.out.print(topmat[i][j].x+"\t");
            }
            System.out.println();
        }
        //TODO: handle state 0 as terminal
        if(topmat.length==0){
            int[] ans = new int[opmat.length+1];
            for(int i=0; i<opmat.length; i++){
                ans[i] = 0;
            }
            ans[0] = 1; ans[opmat.length] = 1;
            return ans;
        }
        Fraction[] sol = find_sol(topmat);
        System.out.println(sol);
        long[] numar = new long[sol.length];
        long[] denar = new long[sol.length];
        long lcm = 1;
        for(int i=0; i<sol.length; i++){
            numar[i] = sol[i].x;
            denar[i] = sol[i].y;
            lcm = Fraction.lcm(denar[i], lcm);
        }
        int[] ansar = new int[sol.length+1];
        for(int i=0; i<sol.length; i++){
            ansar[i] = (int) ((int) numar[i])*(((int) lcm)/((int)denar[i]));
        }
        ansar[sol.length] = (int) lcm;
        return ansar;//no dont lol
    }

    public static Fraction[] find_sol(Fraction[][] qr){
        //qr[i][i] can never be zero
        //CODE HERE
        for(int i=qr.length-1; i>0; i--){
            for(int j=i-1; j>-1; j--){
                if(qr[j][i].x==0){continue;}
                zerofy(qr[j], qr[i], i);
            }
        }
        
        Fraction normalize = new Fraction(qr[0][0].x, qr[0][0].y);
        for(int i=0; i<qr[0].length; i++){
            qr[0][i].div(normalize);
        }
        //qr[0][0] is now 1/1

        Fraction[] tr = new Fraction[qr[0].length-qr.length];
        for(int i=0;i<qr[0].length-qr.length; i++){
            tr[i] = qr[0][qr.length+i];
        }

        return tr;
    }

    public static void zerofy(Fraction[] tozero, Fraction[] ref, int c){
        //r = r-r[0]*ref
        Fraction sobject = new Fraction(tozero[c].x, tozero[c].y);
        sobject.div(ref[c]);
        for(int i=0; i<tozero.length; i++){
            tozero[i].sub(sobject.mul(ref[i]));
        }
    }

    // public static void rowswap(Fraction[][] qr, int i, int j){
    // 	for(int k=0; k<qr[0].length;k++){
    // 		Fraction temp = qr[i][k];
    // 		qr[i][k] = qr[j][k];
    // 		qr[j][k] = temp;
    // 	}
    // }

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