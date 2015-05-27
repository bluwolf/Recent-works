package bankAccounts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;

// may update file only when the user log out
public class UserInterface {
	
	public static boolean checkExist(String name, Vector<User> database){
		for(int i=0;i<database.size();i++){
			//System.out.println( "checking: " +database.get(i).returnUserName() );
			if((database.get(i)).returnUserName().equals(name)){
				System.out.println("I'm sorry, but the username "+ name+" is already associated with an account. Please try again (or enter ��q�� to return to the main menu). " );
				return true;
			}
		}
		
		return false;	
	}
	
	public static boolean checkPass(String name, String password,  Vector<User> database){
		for(int i=0;i<database.size();i++){
			if((database.get(i)).returnUserName().equals(name)){
				if((database.get(i)).returnPassWord().equals(password)) return true; // check username and password combination
			}
		}		
		System.out.println("I��m sorry, but that username and password does not match any at our bank. Please try again (or enter ��q�� to return to the main menu).");
		return false;
	}
	
	public static int checkInt(Scanner userInput){
		while (!userInput.hasNextInt()){ // error check
			System.out.println("That is not a number. Please try again.");
			userInput.next();
		}	
		return Integer.parseInt(userInput.next());
	}
	
	public static double checkDouble(Scanner userInput){
		while (!userInput.hasNextDouble()){ // error check
			System.out.println("That is not a decimal number. Please try again.");
			userInput.next();
		}	
		return Double.parseDouble(userInput.next());
	}
	
	public static User returnKnownUser(String name, Vector<User> database){ // return a user already known to exist
		for(int i=0;i<database.size();i++){
			if((database.get(i)).returnUserName().equals(name)){
				return database.get(i); 
			}
		}
		
		return null;
	}
	
	
	
	
	public static  void main( String[] args) throws IOException{
	
		Vector<User> database = new Vector<User>(); // initial setup
		
		//File file = new File("C:/Users/Xiaohan/Desktop/", ".txt");
		File file = new File("database.txt");
		if (!file.isFile() && !file.createNewFile()){ // create file if file does not exist
		        throw new IOException("Error creating new file: " + file.getAbsolutePath());
		}
		
		FileReader fr = new FileReader("database.txt");
		BufferedReader textReader = new BufferedReader(fr);
		
		String username="";
		String password="";
		double checking;
		double saving;
		
		String aLine= ""; //read in info from file. If empty, no read
		while( (aLine = textReader.readLine()) !=null){
			if(!aLine.isEmpty()){
				String[] splited = aLine.split("\\s+");
				username= splited[0];
				password = splited[1];
				checking = Double.parseDouble(splited[2]);
				saving = Double.parseDouble(splited[3]);
			//	System.out.println(username+" "+password+" "+checking+" "+saving);
				User u = new User(username, password, checking, saving);
				database.add(u);
				
			}
		}
		
//		for(int i=0;i<database.size();i++){
//			System.out.println(database.get(i).returnUserName());
//		}
		//System.out.println(((database.get(1)).cUser).getBalanceAfterNumYears(2));
		Scanner userInput = new Scanner(System.in);
		//char check='k'; // initialize to wahtever
		while(true){
			System.out.println("");
			System.out.println("Welcome to the bank.");
			System.out.println("\t1) Existing Account Holder");
			System.out.println("\t2) Open a New Account");
			System.out.println("What would you like to do?");
			System.out.println("");
			
			
			int choice = checkInt(userInput);			
			while(choice<1 || choice >2){
				System.out.println("That number is not within the range of choices. Please try again");
				choice = checkInt(userInput);
			}
			
			//System.out.println(choice);
			String name ="";
			String pass ="";
			switch(choice){
			case 1:
				while(name!="q"){
					System.out.print("Username: ");
					name = userInput.next();
					if(name.equals("q")) break; // break if user enters q
					System.out.print("Password: ");
					pass = userInput.next();
					
					while(true){
						if(checkPass(name, pass, database)){ // check if username and password combination is valid
							System.out.println("");
							System.out.println("Welcome to your accounts, "+ name+".");
							System.out.println("\t1) View Account Information");
							System.out.println("\t2) Make a Deposit");
							System.out.println("\t3) Make a Withdrawal");
							System.out.println("\t4) Determine Balance in x Years");
							System.out.println("\t5) Logout");
							System.out.println("What would you like to do?");
							System.out.println("");
							
							User currentUser = returnKnownUser(name, database);// returns current user
							
							int innerChoice=checkInt(userInput);
							while(choice<1 || choice >5){ // check userinput validity
								System.out.println("That number is not within the range of choices. Please try again");
								choice = checkInt(userInput);
							}
							switch(innerChoice){
							case 1:
								
								System.out.println("You have a Checking account with a balance of $"+currentUser.cUser.getBalance() );
								System.out.println("You have a "+currentUser.returnSavingsAccountType()+" savings account with a balance of $"+currentUser.returnSavingsAccount().getBalance() );
								break;
								
							case 2:
								System.out.println("Here are the accounts you have:");
								System.out.println("\t1) Checking");
								System.out.println("\t2) " +currentUser.returnSavingsAccountType()+ " Savings");
								System.out.print("Into which account would you like to make a deposit?");
								int dChoice = checkInt(userInput);
								while(choice<1 || choice >2){ // check userinput validity
									System.out.println("That number is not within the range of choices. Please try again");
									dChoice = checkInt(userInput);
								}
								switch(dChoice){
								case 1: 
									
									double deposit=-1;	
									while(deposit<0){
										System.out.print("How much to deposit into your checking?");
										deposit = checkDouble(userInput);
										if(deposit<0) System.out.println("You are not allowed to deposite a negative amount");
									}
									currentUser.cUser.deposit(deposit);
									System.out.println("$"+ deposit+" deposited into your checking account");
									currentUser.updateAccountType(currentUser.returnSavingsAccount().getBalance());
									break;
								case 2: 
									
									double deposit1=-1;	
									while(deposit1<0){
										System.out.println("How much to deposit into your "+currentUser.returnSavingsAccountType()+ " Savings account?");
										deposit1 = checkDouble(userInput);
										if(deposit1<0) System.out.println("You are not allowed to deposite a negative amount");
									}
									currentUser.returnSavingsAccount().deposit(deposit1);
									System.out.println("$"+ deposit1+" deposited into your "+ currentUser.returnSavingsAccountType()+" Savings account");
									currentUser.updateAccountType(currentUser.returnSavingsAccount().getBalance());
									break;
								}
							break;
							
							case 3:
								System.out.println("Here are the accounts you have:");
								System.out.println("\t1) Checking");
								System.out.println("\t2) " +currentUser.returnSavingsAccountType()+ " Savings");
								System.out.print("From which account would you like to withdraw?");
								int wChoice = checkInt(userInput);
								while(choice<1 || choice >2){ // check userinput validity
									System.out.println("That number is not within the range of choices. Please try again");
									dChoice = checkInt(userInput);
								}
								switch(wChoice){
								case 1: 
									double withdraw=-1;	
									while (withdraw<0 || !currentUser.cUser.withdraw(withdraw)){
										while(withdraw<0){
											System.out.print("How much to withdraw from your Checking account?");
											withdraw = checkDouble(userInput);
											if(withdraw<0) System.out.println("You are not allowed to withdraw a negative amount");
										}
										if(!currentUser.cUser.withdraw(withdraw)){
											System.out.println("You do not have $"+withdraw+" in your checking account.");
											System.out.print("How much to withdraw from your checking?");
											withdraw = checkDouble(userInput);
										}
									}
									System.out.println("$"+ withdraw+" withdrawn from your checking account");
									currentUser.updateAccountType(currentUser.returnSavingsAccount().getBalance());
									break;
								case 2: 
									double withdraw1=-1;	
									while ((withdraw1<0) || (!(currentUser.returnSavingsAccount()).withdraw(withdraw1))){
										while(withdraw1<0){
											System.out.print("How much to withdraw from your "+currentUser.returnSavingsAccount().getAccountType()+" Savings account?");
											withdraw1 = checkDouble(userInput);
											if(withdraw1<0) System.out.println("You are not allowed to withdraw a negative amount");
										}
										if(!currentUser.returnSavingsAccount().withdraw(withdraw1)){
											System.out.println("You do not have $"+withdraw1+" in your "+currentUser.returnSavingsAccount().getAccountType()+" savings account");
											System.out.print("How much to withdraw from your "+currentUser.returnSavingsAccount().getAccountType()+" savings account?");
											withdraw1 = checkDouble(userInput);
										}
									}
									System.out.println("$"+ withdraw1+" withdrawn from your "+currentUser.returnSavingsAccountType()+ " savings account");
									currentUser.updateAccountType(currentUser.returnSavingsAccount().getBalance());
									break;
								}
								
							break;
							
							case 4:
								System.out.print("In how many years? ");
								int years = checkInt(userInput);
								System.out.println("Your "+currentUser.returnSavingsAccountType()+" savings account will have the following:");
								double useless = currentUser.returnSavingsAccount().getBalanceAfterNumYears(years);
												
								break;
								
								
							case 5:
								System.out.println("Thank you for coming into the bank!");
								FileWriter fw = new FileWriter("database.txt");
								PrintWriter writer = new PrintWriter(fw);
								for(int i=0;i<database.size();i++){
									writer.println(database.get(i).returnUserName()+" "+database.get(i).returnPassWord()+" "+database.get(i).cUser.getBalance()+" "+database.get(i).returnSavingsAccount().getBalance());
								}
								writer.flush();
								writer.close();
								System.exit(0);
							
							}
						}
						else break;
					}
					
				}
				
				break;
			case 2:

				while(name!="q"){
					System.out.print("Username: ");
					name = userInput.next();	
					if(name.equals("q")) break;
					
					if(!checkExist(name, database)) {
						System.out.println("Great, that username is not in use!");
						System.out.print("Password: ");
						pass = userInput.next();
						System.out.print("How much would you like to deposite in checkings? ");
						double checkamt = checkDouble(userInput);
						System.out.print("How much would you like to deposite in savings? ");
						double saveamt = checkDouble(userInput);
						User u = new User(name, pass, checkamt, saveamt);
						database.add(u);
						break;
						
					}
		
				}
				break;
				
				
			}
							
				
		}
	}
		
		
		
}

