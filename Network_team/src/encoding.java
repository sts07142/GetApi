



public class encoding {
	private static int p=17, q=11, e=7, N=p*q;
	public static int size;
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

