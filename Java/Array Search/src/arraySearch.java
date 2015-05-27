import java.util.*;


public class arraySearch {
	
	public class arrayClass{ // creates and controls array
		public arrayClass(int search, int arrSize){ // create array with passed in parameter
			System.out.println("in constructor class; the integer to search for is " + search );
			
			Random randomNum = new Random();
			//int size = (randomNum.nextInt(10)); // generate random size
			
			System.out.println("size of array is " +arrSize);
			int[] arr = new int[arrSize]; // randomly determine size of array
			
			int index=0;
			System.out.println("Array Generated: ");
			for(int number: generateIncreasingRandoms(arrSize,100)){
				//System.out.print(number + " ");
				arr[index] = number;
				index++;	
			}
			
			for(int i = 0; i<arrSize; i++){
				System.out.print(arr[i]+" ");
			}
			// perform both search operations
			int find1= bruteForce(search,arrSize,arr );
			if(find1 == -1){
				System.out.println("number not found using brute force");
			}
			int find2 = binarySearch(search, arrSize,arr);
			//System.out.print("haha"+find1+find2);
			if(find2 == -1){
				System.out.println("number not found using Binary Search");
			}
			
		}
	}
	
	public static int[] generateIncreasingRandoms(int amount, int max) { // generate random number function
	    int[] randomNumbers = new int[amount];
	    Random random = new Random();
	    for (int i = 0; i < randomNumbers.length; i++) {
	        randomNumbers[i] = random.nextInt(max);
	    }
	    Arrays.sort(randomNumbers);
	    return randomNumbers;
	}
	
	public static int bruteForce(int num, int size1,int [] arr){
		System.out.println("Search through brute force: ");
		
		int count=0;
		for(int i=size1-1; i>-1;i--){
			count++;
			if(arr[i] == num) return i;
		}		
		System.out.println("Number of operations for Brute Force: " + count);
		return -1;
	}
	
	public static int binarySearch(int num, int size2, int[] arr ){
		System.out.println("Search through Binary Search: ");
		
		int count=0;
		int first = 0;
		int upto = size2-1;
		
		while (first < upto) {
			count++;
	        int mid = (first + upto) / 2;  // Compute mid point.
	        if (num < arr[mid]) {
	            upto = mid;     // repeat search in bottom half.
	        } else if (num > arr[mid]) {
	            first = mid + 1;  // Repeat search in top half.
	        } else {
	            return mid;     // Found it. return position
	        }
	    }
		
	
		System.out.println("Number of operations for Binary Search: " + count);
		return -1;
	}
	
	
	public static void main (String [] args){
		
		int find =0 ;
		int userSize =0;
		
		// user must input 1. number to search for 2. size of array in the command line argument
		if(args.length ==2){
			try{
				find = Integer.parseInt(args[0]); // check the the validity of command line argument (should be an integer that will be size of array)
				userSize = Integer.parseInt(args[1]); 
			}
			catch(NumberFormatException e){
				System.err.println("Argument" + args[0]+"must be an integer.");
				System.exit(1);
			}
		}
		
		
		arraySearch create = new arraySearch();
		arrayClass newArray = create.new arrayClass(find, userSize); 
		
		
	}
	


}
