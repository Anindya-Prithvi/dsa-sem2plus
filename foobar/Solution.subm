import java.util.Stack;
class Salute {
    public static int solution(String s) {
        Stack<Character> left = new Stack<Character>();
        int k = s.length();
        int num = 0;
        for(int i=0; i<k; i++){
        	if(s.charAt(i)=='>'){
        		left.push('>');
        	}
        	else if(s.charAt(i)=='<'){
        		num+=left.size();
        	}

        }
        return num*2; //more efficient since multiplying once :P
    }
}