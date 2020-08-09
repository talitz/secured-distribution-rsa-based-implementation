
public class Source {

	public Source() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		distributer dis = new distributer();
		String message1 = "hello world";
		String sig = dis.getSignature(message1);
		
		//send sig to edge
	}

}
