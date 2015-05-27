package bankAccounts;

public class CheckingAccount extends BaseAccount{
	public CheckingAccount(double balance) {
	super(balance);
	//System.out.println("c");
	}

	public double getBalanceAfterNumYears(int numYears){
		return getBalance();
	}
	
	public String getAccountType(){
		return "Checking";
	}
}
