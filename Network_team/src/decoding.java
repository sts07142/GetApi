



public class decoding {
	private static int p=17, q=11, d=23, N=p*q;
	//use RSA algorithm to protect information
	
	private static int pow(int i, int j, int k) {
		int l, temp, p=1;
		for(temp=0; temp<j; temp++) {
			p=(p*i);
			l=p/k;
			p=p-(l*k);
		}
		return (int)p; 
	}
	public static String decode(String arr) {
		String s;
		s=decryption(arr, d, N);
		return s;
	}
	
	private static String decryption(String arr, int d, int N) {
		String s=new String();
		String ar[]=arr.split("/");
		for(int i=0; i<ar.length; i++) {
			int temp=Integer.parseInt(ar[i]);
			int caltemp=pow(temp, d, N);
			s +=(char)caltemp;
		}
		return s;
	}
}

