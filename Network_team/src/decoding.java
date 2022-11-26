



public class decoding {
	private static int p=17, q=11, d=23, N=p*q;
	
	//인터넷에서 우리의 개인정보는 보안이 되었다. 따라서 다른 유저가 쉽게 접근하는 것을 막기 위해
	//비밀번호를 RSA 알고리즘을 사용해 보안화했다..
	
	private static int pow(int i, int j, int k) {
		int l, temp, p=1;
		for(temp=0; temp<j; temp++) {
			p=(p*i);
			l=p/k;
			p=p-(l*k);
		}
		return (int)p; 
	}
	//encodes는 비밀번호를 보안화한다.
	//decode는 보안화된 비밀번호를 풀어 원래의 비밀번호를 반환한다. 
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

