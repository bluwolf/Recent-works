package bankAccounts;

public abstract class BaseAccount {
	private double balance;
	
	public BaseAccount(double balance) {
	setBalance(balance);
	//System.out.println("b");
	}
	
	public double getBalance() {
	return this.balance;
	}
	
	public void setBalance(double balance) {
	this.balance = balance;
	}

	protected abstract double getBalanceAfterNumYears(int numYears);

	public abstract String getAccountType();
	public boolean withdraw(double amount) {
		if((balance-amount)<0){
			return false;
		}
		else{
			balance = balance-amount;
			return true;
		}
	}
	public boolean deposit(double amount) {
		balance = balance+amount;
		return true;
	}


}
