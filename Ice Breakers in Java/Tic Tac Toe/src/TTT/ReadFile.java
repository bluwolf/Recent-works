package TTT;
import java.lang.ArithmeticException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class ReadFile {
	
	static int checkWinner(int i, int j, char player, char[][]grid){
		if(player =='X'){
			if(i ==0 && j==0){
				if(grid[1][1] =='X'&& grid[2][2]=='X') return 1;
				if(grid[0][1] =='X'&& grid[0][2]=='X') return 1;
				if(grid[1][0] =='X'&& grid[2][0]=='X') return 1;
			}
			if(i ==0 && j==1){
				if(grid[0][0] =='X'&& grid[0][2]=='X') return 1;
				if(grid[1][1] =='X'&& grid[2][1]=='X') return 1;
			}
			if(i ==0 && j==2){
				if(grid[0][0] =='X'&& grid[0][1]=='X') return 1;
				if(grid[1][1] =='X'&& grid[2][0]=='X') return 1;
				if(grid[1][2] =='X'&& grid[2][2]=='X') return 1;
			}
			if(i ==1 && j==0){
				if(grid[0][0] =='X'&& grid[2][0]=='X') return 1;
				if(grid[1][1] =='X'&& grid[2][2]=='X') return 1;
			}
			if(i ==1 && j==1){
				if(grid[0][0] =='X'&& grid[2][2]=='X') return 1;
				if(grid[2][0] =='X'&& grid[0][2]=='X') return 1;
				if(grid[1][0] =='X'&& grid[1][2]=='X') return 1;
				if(grid[0][1] =='X'&& grid[2][1]=='X') return 1;
			}
			if(i ==1 && j==2){
				if(grid[1][1] =='X'&& grid[1][2]=='X') return 1;
				if(grid[0][1] =='X'&& grid[1][1]=='X') return 1;

			}
			if(i ==2 && j==0){
				if(grid[1][1] =='X'&& grid[2][2]=='X') return 1;
				if(grid[2][1] =='X'&& grid[2][2]=='X') return 1;
				if(grid[1][0] =='X'&& grid[0][0]=='X') return 1;
			}
			if(i ==2 && j==1){
				if(grid[2][2] =='X'&& grid[2][0]=='X') return 1;
				if(grid[0][1] =='X'&& grid[1][1]=='X') return 1;
			}
			if(i ==2 && j==2){
				if(grid[1][1] =='X'&& grid[0][0]=='X') return 1;
				if(grid[2][1] =='X'&& grid[2][0]=='X') return 1;
				if(grid[1][2] =='X'&& grid[0][2]=='X') return 1;
			}

		}
		
		if(player =='O'){
			if(i ==0 && j==0){
				if(grid[1][1] =='O'&& grid[2][2]=='O') return 2;
				if(grid[0][1] =='O'&& grid[0][2]=='O') return 2;
				if(grid[1][0] =='O'&& grid[2][0]=='O') return 2;
			}
			if(i ==0 && j==1){
				if(grid[0][0] =='O'&& grid[0][2]=='O') return 2;
				if(grid[1][1] =='O'&& grid[2][1]=='O') return 2;
			}
			if(i ==0 && j==2){
				if(grid[0][0] =='O'&& grid[0][1]=='O') return 2;
				if(grid[1][1] =='O'&& grid[2][0]=='O') return 2;
				if(grid[1][2] =='O'&& grid[2][2]=='O') return 2;
			}
			if(i ==1 && j==0){
				if(grid[0][0] =='O'&& grid[2][0]=='O') return 2;
				if(grid[1][1] =='O'&& grid[2][2]=='O') return 2;
			}
			if(i ==1 && j==1){
				if(grid[0][0] =='O'&& grid[2][2]=='O') return 2;
				if(grid[2][0] =='O'&& grid[0][2]=='O') return 2;
				if(grid[1][0] =='O'&& grid[1][2]=='O') return 2;
				if(grid[0][1] =='O'&& grid[2][1]=='O') return 2;
			}
			if(i ==1 && j==2){
				if(grid[1][1] =='O'&& grid[1][2]=='O') return 2;
				if(grid[0][1] =='O'&& grid[1][1]=='O') return 2;

			}
			if(i ==2 && j==0){
				if(grid[1][1] =='O'&& grid[2][2]=='O') return 2;
				if(grid[2][1] =='O'&& grid[2][2]=='O') return 2;
				if(grid[1][0] =='O'&& grid[0][0]=='O') return 2;
			}
			if(i ==2 && j==1){
				if(grid[2][2] =='O'&& grid[2][0]=='O') return 2;
				if(grid[0][1] =='O'&& grid[1][1]=='O') return 2;
			}
			if(i ==2 && j==2){
				if(grid[1][1] =='O'&& grid[0][0]=='O') return 2;
				if(grid[2][1] =='O'&& grid[2][0]=='O') return 2;
				if(grid[1][2] =='O'&& grid[0][2]=='O') return 2;
			}

		}
		
		
		return -1;
	}
	static int readLines() throws IOException{
		FileReader fr = new FileReader("C:/Users/Xiaohan/Desktop/test.txt");
		BufferedReader textReader = new BufferedReader(fr);
		
		FileWriter fw = new FileWriter("C:/Users/Xiaohan/Desktop/output.txt");
		PrintWriter writer = new PrintWriter(fw);
		//writer.println("killo");
		//System.out.println("kkkk");
		
		String aLine= "";
		int numLine =0;
		
		char[][] grid = new char[2][2]; // game grid
		
		//initialize grid
		try{
			for(int i=0; i<3; i++){ 
				for(int j=0;j<3;j++){
					grid[i][j]=	'_'	;	
				}
			}
		}
		catch(Exception e) {
			System.out.println("error");
		}
			
		int checkWin=-1;
		int neitherFlag =1;
		
		while( (aLine = textReader.readLine()) !=null){
			numLine++;
			aLine = aLine.replaceAll("\\D+","");
	
			char[] move = new char[2];
			move = aLine.toCharArray(); // convert to char to read moves
			int i = Character.getNumericValue(move[0]);
			int j = Character.getNumericValue(move[1]);
			
			if(i<0 ||i>2||j<0||j>2){ // error check
				
				try{
					for(int k=0; k<3; k++){ 
						for(int s=0;s<3;s++){
							System.out.print(grid[k][s]+" ");			
						}
						System.out.println("");
					}
				}
				
				catch(Exception ex){
					System.out.println("problem");
				}
				writer.println("Out of Bounds");
				System.out.println("Out of Bounds");
				neitherFlag=0;
				System.exit(1);
			}
			//System.out.println(i+" "+j);
			if(numLine%2==0){
				if(grid[i][j]=='_'){
					grid[i][j] = 'O';
					checkWin=checkWinner(i,j,'O',grid); // check winner after each move
				}
				else{
					writer.println("Repeated Move");
					System.out.println("Repeated Move");
					neitherFlag=0;
				}

			}
			else{
				if(grid[i][j]=='_'){
					grid[i][j] ='X';
					checkWin=checkWinner(i,j,'X',grid);
				}
				else{
					writer.println("Repeated Move");
					System.out.println("Repeated Move");
					neitherFlag=0;
				}
			}
		}
		
		
		//System.out.println("checking value of winner: "+checkWin+" "+checkWin);
		
		// print out the grid for now
		for(int i=0; i<3; i++){ 
			for(int j=0;j<3;j++){
				writer.print(grid[i][j]+" ");	
				System.out.print(grid[i][j]+" ");
			}
			writer.println("");
			System.out.println("");
		}
		
		
		if(checkWin==2){
			writer.println("Player2");
			System.out.println("Player2");
			neitherFlag=0;
			
		}
		if(checkWin==1){
			writer.println("Player1");
			System.out.println("Player1");
			neitherFlag=0;
		}
		
		

		// error check
		for(int i=0; i<3; i++){ 
			for(int j=0;j<3;j++){
				if (grid[i][j]==	'_'	&& (neitherFlag == 1))	{
					writer.println("Neither");
					System.out.println("Neither");
					writer.flush();
					writer.close();
					System.exit(1);
					//throw new ArithmeticException("Neither");
				}
			}
		}
		textReader.close();
//		if(numLine>=9){ // exception check
//			writer.println("Out of bounds");
//			System.out.println("Out of bounds");
//			throw new ArithmeticException("Number of moves exceeds number of available space.");
//		}
		writer.flush();
		writer.close();
		return numLine;
	}
	
	
	static public String[] OpenFile() throws IOException{
	
		int numLine =readLines();
		
		FileReader fr = new FileReader("C:/Users/Xiaohan/Desktop/test.txt");
		BufferedReader textReader = new BufferedReader(fr);
//		
//		String[] data = new String[numLine];
//		
//		for(int i=0;i<numLine;i++){
//			data[i] = textReader.readLine();
//		}
		

//		for(int i=0;i<numLine;i++){
//			System.out.println(data[i]);
//		}
		
		return null;
	}
	
	public static void main(String[] args) throws IOException{
		String file_name = "C:/Users/Xiaohan/Desktop/test.txt";
		String[] data = OpenFile();
	}


}

