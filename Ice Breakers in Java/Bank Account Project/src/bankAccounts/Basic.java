package bankAccounts;



public class Basic extends SavingsAccount{
	double rate = 0.001;
	public Basic(double balance) {
		super(balance);
	}
	public String getAccountType() {
		return "Basic";
	}
	
	public double getBalanceAfterNumYears(int numYears){
		double amt = getBalance();
		double interest = (amt*rate);
		System.out.printf("%-10s %-10s %-10s\n","Year", "Amount", "Interest" );
		System.out.printf("%-10s %-10s %-10s\n","----", "------", "--------" );
		System.out.printf("%-10d %-10.2f %-10.2f\n",0, amt,interest);
		for (int i =1;i<=numYears;i++){
			amt = interest + amt;
			interest = (amt*rate);	
			//System.out.printf("%-.2f",i+" "+amt+" "+interest);
			System.out.printf("%-10d %-10.2f %-10.2f\n",i, amt,interest);
			
		}
		return 0;	
	}


}
