



public class encoding {
	private static int p=17, q=11, e=7, N=p*q;
	public static int size;
	
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
	public static String encode(String pass, int _size) {
		String s=new String();
		size=_size;
		int[] arr=new int[20];
		char[] ch=new char[20];
		ch=pass.toCharArray();
		for(int i=0; i<size; i++) {
			arr[i]=(int)ch[i];
		}
		arr=encryption(arr, e, N);
		for(int i=0; i<size; i++) {
			s+=arr[i]+"/";
		}
		return s;
	}

	
	
	private static int[] encryption(int[] arr, int e, int N) {
		for(int i=0; i<size; i++) {
			int temp=pow(arr[i], e, N);
			arr[i]=temp;
		}
		return arr;
	}
}

