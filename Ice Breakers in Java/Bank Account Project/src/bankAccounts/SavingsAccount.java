package bankAccounts;


public abstract class SavingsAccount extends BaseAccount{
	public SavingsAccount(double balance) {
	super(balance);
	//System.out.println("s");
	}

	public abstract double getBalanceAfterNumYears(int numYears);	
	public abstract String getAccountType();
}
