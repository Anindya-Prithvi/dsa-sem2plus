import java.util.*;

class Main{

	// typedef pseudocode
	// take string = STR
	// define nice
	// nice(STR, "" <-- provided initially)

	// #nice
	// checks length of STR
	// if =1
	// return STR
	// else
	// {
	// 	if last character is same as STR.charAt(0) ; return nice(STR.substring(1,end))
	// 	else; return STR.charAt(0)+nice(STR.substring(1,end))

	public static String nice(String substr, String l_char){
		int end=substr.length();
		
		if (end==1){
			if (l_char.equals(substr)){return "";}
			else{return substr;}
		}
		else{
			if (l_char.equals(""+substr.charAt(0))){return nice(substr.substring(1,end),l_char);}
			else{return substr.charAt(0)+nice(substr.substring(1,end),""+substr.charAt(0));}
		}

		}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println(nice(sc.next(),""));
	}
}