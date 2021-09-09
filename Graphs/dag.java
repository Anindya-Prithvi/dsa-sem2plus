import java.util.Scanner;
//AC DAG Detector
class Main{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int[][] arr;
        int n = sc.nextInt();
        int e = sc.nextInt();
        arr = new int[n][n];
        while(e-->0){
            arr[sc.nextInt()-1][sc.nextInt()-1]=1;
        }

        // for(int[] k: arr){
        //     for(int o: k){
        //         System.out.print(o+" ");
        //     }
        //     System.out.print("\n");
        // }

        while(true){
            int removable = fzero(arr);
            // System.out.println(removable);
            if(removable==-1){System.out.println(0);break;}
            if(removable==-2){System.out.println(1);break;}
            arr = red(arr, removable);

        }

        // for(int[] brr : red(arr,1)){
        //     for(int i: brr){
        //         System.out.print(i+" ");
        //     }
        //     System.out.print("\n");
        // }
    }

    public static int fzero(int[][] arr){
        if(arr.length==0){return -2;}
        for(int i=0; i<arr.length; i++){
            boolean flag = true;
            for(int j=0; j<arr.length; j++){
                if(arr[i][j]==1){flag=false;break;}
            }
            if(flag){return i;}
        }
        return -1;
    }

    public static int[][] red(int[][] arr, int rem){
        int[][] kk = new int[arr.length-1][arr.length-1];
        int ilf=0;
        int jlf=0;
        for(int i=0; i<arr.length; i++){
        if(i==rem){continue;}
            jlf=0;
            for(int j=0; j<arr.length; j++){
                //System.out.println(i+" "+j+" ilfjlf "+ ilf+" "+jlf);
            if(j==rem){continue;}
                kk[ilf][jlf] = arr[i][j];
                jlf++;
            }
            ilf++;
        }
        return kk;
    }
}