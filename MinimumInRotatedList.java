
public class MinimumInRotatedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ar[]={3,4,5,1,2};
		System.out.println(getMinimumIndex(ar,0,4));
	}
	static int getMinimumIndex(int[] ar,int a,int b){
		System.out.println(a+" "+b);
		if(a+1==(b) || a==b){
			if(ar[a]>ar[b]){
				return b;
			}
			else{
				return a;
			}
		}
		if(ar[b]<ar[(a+b)/2]){
			return getMinimumIndex(ar,((a+b)/2),b);
		}
		else{
			return getMinimumIndex(ar, a,( (a+b)/2));
		}
	}
}
