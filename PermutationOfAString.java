import java.util.List;
import java.util.ArrayList;
public class PermutationOfAString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(permuteTheString("b",0,0));
	}
	static List<String> permuteTheString(String s,int a,int b){
		List<String> permutation = new ArrayList<String>();
		if(a==b){
			permutation.add(s.charAt(a)+"");
		}
		else{
			List<String> permutation2=new ArrayList<String>();
			permutation2=permuteTheString(s, a, b-1);
			for(String aa:permutation2){
				List<String> permutation3=merge(s,a,b-1,s.charAt(b));
				for(String f:permutation3){
					permutation.add(f);
				}
			}
		}
		return permutation;
	}
	static List<String> merge(String s,int a,int b,char c){
		List<String> permutation = new ArrayList<String>();
		String q=s.substring(a,b+1);
		for(int i=0;i<=q.length();i++){
			String e=q.substring(0,i);
			String r=q.substring(i,q.length());
			String t=e+c+r;
			permutation.add(t);
		}
		return permutation;
	}
}
