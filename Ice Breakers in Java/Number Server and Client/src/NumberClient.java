import java.util.ArrayList;


public class NumberClient implements Runnable{
	NumberServer ns;
	int num;

	NumberClient(NumberServer ns){
		this.ns = ns;
	}
	public void run() {
		this.num = ns.getNumber();
		test.ar.add(num);
//			System.out.println(num);

	}

	

}
