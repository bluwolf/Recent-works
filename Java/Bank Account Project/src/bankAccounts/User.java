package bankAccounts;

public class User { // every user is a possible basic, premium, and deluxe. 
	String userName;
	String passWord;
	double checking;
	double saving;
	
	CheckingAccount cUser; // checking 
	
	Basic bUser =null; // savings types
	Premium pUser = null;
	Deluxe dUser= null;
	
	
	User(String n, String p, double c, double s){
		userName=n;
		passWord =p;
		checking = c;
		saving = s;
		cUser = new CheckingAccount(checking); // only one type of checking account
		determineAccountType(saving); // initializes account type when user is created
		
	}
	
	protected void determineAccountType(double money){ 
		if((money+cUser.getBalance())<=1000.00){ // calculate combine amount to determine savings account type
			bUser=new Basic(money);
			pUser = null;
			dUser = null;
		}if((money+cUser.getBalance())>1000.00 && (money+cUser.getBalance()) <=10000.00){
			pUser = new Premium(money);
			bUser = null;
			dUser = null; 
		}if((money+cUser.getBalance())>10000.00){
			dUser=new Deluxe(money);
			bUser = null;
			pUser = null;
		}
		
	}
	
	void updateAccountType(double money){
		determineAccountType(money);
	}
	
	 SavingsAccount returnSavingsAccount(){
		if(bUser!=null)return bUser;
		if(pUser!=null) return pUser;
		if(dUser!=null)return dUser;
		return null;	
	}
	
	String returnSavingsAccountType(){
		if(bUser!=null)return "Basic";
		if(pUser!=null) return "Premium";
		if(dUser!=null)return "Deluxe";
		return null;
	}
	

	String returnUserName(){
		return userName;
	}
	String returnPassWord(){
		return passWord;
	}
	

	
	
}
