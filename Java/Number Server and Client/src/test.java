import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class test {
	static List<Integer> ar;

	public static void main (String [] args){
		ar = Collections.synchronizedList(new ArrayList<Integer>());
//		ar = new ArrayList<Integer>();
//		ar.synchronizedList(ar);

		NumberServer ns = new NumberServer();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			NumberClient nc = new NumberClient(ns);
			executor.execute(nc);
//			ar.add(nc.num);
		}
		executor.shutdown();
		while(!executor.isTerminated()){
//			Collections.sort(ar);
			System.out.println(ar);	
		}

	}

}
