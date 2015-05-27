//	Designer: Xiaohan Chen
//	9/6/2014
//	University of Southern California

package miniSearch;
import java.util.Scanner;
import java.util.Arrays;


public class MiniSearch {
	
	public static void outputResult(int rows, int columns, char[] printResult, char[][] grid, boolean adminFlag ){ // write correct result to new array
		int r=0,c=0,ind =0;
		boolean up = false; // dictates vertical direction of zigzag
		while(c<columns){	
//			System.out.print(grid[r][c]+" ");
			if(adminFlag){
				System.out.println("R: "+r+" C: "+c+" L: "+grid[r][c]);
			}
			printResult[ind++] = grid[r][c];
			c++;
			if(up==false && r!=rows-1){ r++;} // error check for changing rows
			if(up==true && r!=0){r--;}
			if(r==rows-1){up = true;}
			if(r==0){up = false;}	
		}
		
		System.out.print("Result: ");
		System.out.println(printResult);
	}
	
	public static void printGrid(boolean adminFlag, int rows, int columns, char [][] grid){ // prints grid for admin
		if(adminFlag== true){
			System.out.println("Here is the grid, just for you admin!");
			for(int i=0; i<rows; i++){ 
				for(int j=0;j<columns;j++){
					System.out.print(grid[i][j]+" ");			
				}
				System.out.println("");
			}
		}
	}
	
	public static boolean adminCheck(char check){ // checks whether user is admin or not
		if(check == 'A') return true; 
		else return false;
		
	}


	public static void main (String [] args){
		
		System.out.println("Welcome to the Maze Game!");
		@SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
		
		System.out.print("Are you an admin?");
		char checkAdmin = userInput.next(".").charAt(0);
		boolean adminFlag = adminCheck(checkAdmin); // check if user is admin
		
		System.out.println("How many rows are there in the grid?");	
		while (!userInput.hasNextInt()){
			System.out.println("Error: that is not a number. Please try again.");
			userInput.next();
		}	
		String row = userInput.next();
		//System.out.println("number of rows are : " + row);
		
		System.out.println("How many columns are there in the grid?");	
		while (!userInput.hasNextInt()){
			System.out.println("Error: that is not a number. Please try again.");
			userInput.next();
		}	
		String cols = userInput.next();
		//System.out.println("number of columns are : " + col);
		int columns = Integer.parseInt(cols);
		int rows = Integer.parseInt(row);
		
		char[][] grid = new char[rows][columns]; // setting up main game grid
		int size = rows*columns; // initialize size
		
		System.out.println("Enter " + size + " letters separated by space");
		
		boolean correctNumChars = false;
		
		char []charArray = new char[size]; // temporarily stores valid letters
		String noSpace = "";
				
		while (!correctNumChars){ // check if inputed right number of letters	
			String letters= userInput.next()+userInput.nextLine(); // read all user input in a line
			noSpace= letters.replaceAll("\\s","");//delete all spaces in input
			if(noSpace.length()==size){correctNumChars=true;}
			//if(validCount == size){flag =1;}
			else {
				System.out.println("Error: That is not "+size + " characters. Try again!");
			}
		}

		charArray = noSpace.toCharArray(); // convert string to char array
		
		int k=0;
		for(int i=0; i<rows; i++){  // convert from 1d to 2d array
			for(int j=0;j<columns;j++){
				grid[i][j] = charArray[k];
				k++;
			}
		}
		
		printGrid(adminFlag,rows,columns,grid); // prints grid for admin, if not admin, then don't print grid
		
		char [] printResult = new char[size];		
		if(adminFlag){
			System.out.println("Here is the step by step, just for you admin!");
		}	
		outputResult(rows,  columns,  printResult,  grid, adminFlag ); // goes through array and prints out result
		
		System.out.println("Thanks for using this program!");
		System.exit(1);

		
	}

}
