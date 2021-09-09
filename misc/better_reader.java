import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class codeforces3 {
    public static int bsear(long[] arr ,int lo,int hi,long f,int l){
        int ans = arr.length;
        while (lo<=hi) {
//            System.out.println("s");
            int mid = (lo + hi) / 2;

            if(arr[mid]+f>=l){
                ans = mid;
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }
        return ans;
    }
    public static int hsear(long[] arr ,int lo,int hi,long f,int l){
        int ans = arr.length;
        while (lo<=hi) {
            int mid = (lo + hi) / 2;
//            System.out.println(mid);
            if(arr[mid]+f<=l){
                lo = mid+1;
            }else{
                ans = mid;
                hi = mid-1;
            }
        }
        return ans;
    }
    public static void main(String[] Args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(input.readLine());

        for(int i=0;i<test;i++){
            String [] arr = input.readLine().split(" ");
            int n = Integer.parseInt(arr[0]);
            int l = Integer.parseInt(arr[1]);
            int k = Integer.parseInt(arr[2]);
            String [] array = input.readLine().split(" ");
            long [] ar = new long[array.length];
            for(int j = 0;j<array.length;j++){
                ar[j] = Long.parseLong(array[j]);
            }
            Arrays.sort(ar);
            long count = 0;
            for(int j=1;j<ar.length;j++){
                int a = bsear(ar,j,ar.length-1,ar[j-1],l);
                int b = hsear(ar,j,ar.length-1,ar[j-1],k);
//                System.out.println(b+" "+a);
                count+= Math.abs(b-a);
            }
            System.out.println(count);
        }

    }
}
