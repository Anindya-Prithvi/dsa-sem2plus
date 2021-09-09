import java.util.*;

class bruh{
static int maxSubArray(int[] nums) {
    int result = nums[0];
    int sum = nums[0];
 
    for(int i=1; i<nums.length; i++){
        sum = Math.max(nums[i], sum + nums[i]);
        result = Math.max(result, sum);
    }
 
    return result;
}
public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
	int t_case = sc.nextInt();
	while(t_case-->0){
	int numel = sc.nextInt();
	int[] arr = new int[numel];
	while(numel-->0){
	arr[numel]= sc.nextInt();
}
System.out.println(maxSubArray(arr));
}
}
}